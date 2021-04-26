package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.BookEntity;
import com.example.demo.bean.BookItemEntity;
import com.example.demo.bean.ReservationEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.bean.UserEntity;
import com.example.demo.dao.BookDao;
import com.example.demo.dao.BookItemDao;
import com.example.demo.dao.ReservationDao;
import com.example.demo.data.BookItemStatus;
import com.example.demo.data.ReservationStatus;
import com.example.demo.exception.ApiValidateException;
import com.example.demo.exception.AuthenticateException;
import com.example.demo.service.BookItemService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.SecurityService;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.MessageUtils;


@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private ReservationDao reservationDao;
	
	@Autowired
	private BookItemDao bookItemDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	public ResultBean addReservation(ReservationEntity entity) throws ApiValidateException {
		entity.setCreatedTime(new Date());
		entity.setStatus(ReservationStatus.TEMP);
		
		List<BookItemEntity> newBookItems = new ArrayList();
		double totalFee = 0;
		
		for (BookEntity item : entity.getBookEntities()) {
			List<BookItemEntity> bookItems = bookItemDao.getListBookItemWithStatusByBookId(item.getBookId(), BookItemStatus.AVAILABLE);
			
			if (bookItems != null && bookItems.size() > 0) {
				BookItemEntity bookItem = bookItems.get(0);
				bookItem.setStatus(BookItemStatus.NOT_AVAILABLE);
				bookItemDao.updateBookItem(bookItem);
				
				totalFee += bookItem.getBookEntity().getRentCost();
				
				newBookItems.add(bookItem);
			}
		}
		
		entity.setBookItemEntities(newBookItems);
		entity.setTotalFee(totalFee);
		
		return new ResultBean(reservationDao.addReservation(entity), "201", MessageUtils.getMessage("MSG02", "reservation"));
	}

	@Override
	public ResultBean updateReservation(ReservationEntity entity) throws ApiValidateException {
		return new ResultBean(reservationDao.updateReservation(entity), "200", MessageUtils.getMessage("MSG04", "reservation"));
	}
	
	@Override
	public ResultBean addItemReservation(ReservationEntity entity, Integer bookId) throws ApiValidateException {
		List<BookItemEntity> bookItems = bookItemDao.getListBookItemWithStatusByBookId(bookId, BookItemStatus.AVAILABLE);
		double totalFee = entity.getTotalFee();
		
		if (bookItems != null && bookItems.size() > 0) {
			BookItemEntity bookItem = bookItems.get(0);
			bookItem.setStatus(BookItemStatus.NOT_AVAILABLE);
			bookItemDao.updateBookItem(bookItem);
			
			totalFee += bookItem.getBookEntity().getRentCost();
			
			entity.getBookItemEntities().add(bookItem);
			entity.setTotalFee(totalFee);
		}
		
		return this.updateReservation(entity);
	}
	
	@Override
	public ResultBean removeItemReservation(ReservationEntity entity, Integer bookId, Integer amount) throws ApiValidateException {
		if (amount == null || amount < 0) amount = 1000;
		
		List<BookItemEntity> bookItems = entity.getBookItemEntities().stream()
				.filter(item -> item.getBookId().equals(bookId)).limit(amount).collect(Collectors.toList());
		
		double bookFee = bookDao.getBookById(bookId).getRentCost();
		double totalFee = entity.getTotalFee() - bookItems.size() * bookFee;
		
		bookItems.forEach(item -> {
			item.setStatus(BookItemStatus.AVAILABLE);
			bookItemDao.updateBookItem(item);
		});
		
		List<BookItemEntity> newBookItems = entity.getBookItemEntities().stream()
				.filter(item -> !bookItems.contains(item)).collect(Collectors.toList());
		
		entity.setBookItemEntities(newBookItems);
		entity.setTotalFee(totalFee);
		
		return this.updateReservation(entity);
	}

	@Override
	public ResultBean removeItemReservation(ReservationEntity entity, Integer bookId) throws ApiValidateException {
		return this.removeItemReservation(entity, bookId, 1000);
	}
	
	@Override
	public ResultBean getReservationByUserId(Integer userId) {
		return getReservationWithStatusByUserId(userId, ReservationStatus.UNDEFINED);
	}

	@Override
	public ResultBean getReservationWithStatusByUserId(Integer userId, ReservationStatus status) {
		List<ReservationEntity> entities = reservationDao.getReservationWithStatusByUserId(userId, status);
		
		return new ResultBean(entities, "200", MessageUtils.getMessage("MSG01", "reservation"));
	}
	
	@Override
	public ResultBean getReservationWithStatusByUserId(Integer userId, Integer status) {
		return getReservationWithStatusByUserId(userId, ReservationStatus.parse(status));
	}

	@Override
	public ResultBean addItemReservation(Integer userId, Integer bookId)
			throws ApiValidateException, AuthenticateException {
		
		securityService.validateUserWithUserId(userId);
		
		ReservationEntity tempReservation = reservationDao.getCurrentTempReservation(userId);
		
		return addItemReservation(tempReservation, bookId);
	}
	
	@Override
	public ResultBean removeItemReservation(Integer userId, Integer bookId, Integer amount)
			throws ApiValidateException, AuthenticateException {
		securityService.validateUserWithUserId(userId);
		
		ReservationEntity tempReservation = reservationDao.getCurrentTempReservation(userId);
		
		return removeItemReservation(tempReservation, bookId, amount);
	}

}
