package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.LibException;
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

	public ReservationEntity addNewTempReservation(Integer userId) throws LibException {
		ReservationEntity entity = new ReservationEntity();
		entity.setUserId(userId);
		entity.setCreatedTime(new Date());
		entity.setTotalFee(0d);
		entity.setBookItemEntities(new ArrayList<BookItemEntity>());
		entity.setStatus(ReservationStatus.TEMP);
		
		return entity;
	}
	
	@Override
	public ResultBean addItemReservation(Integer userId, Integer bookId)
			throws LibException {
		
		securityService.checkUserWithUserId(userId);
		
		ReservationEntity tempReservation = reservationDao.getCurrentTempReservation(userId);
		if (tempReservation == null) {
			tempReservation = addNewTempReservation(userId);
		}
		
		return addItemReservation(tempReservation, bookId);
	}
	
	@Override
	public ResultBean removeItemReservation(Integer userId, Integer bookId, Integer amount)
			throws ApiValidateException, AuthenticateException {
		securityService.checkUserWithUserId(userId);
		
		ReservationEntity tempReservation = reservationDao.getCurrentTempReservation(userId);
		
		return removeItemReservation(tempReservation, bookId, amount);
	}

	@Override
	public ResultBean getReservationWithStatus(Integer status) throws ApiValidateException, AccessDeniedException {
		return getReservationWithStatus(ReservationStatus.parse(status));
	}

	@Override
	public ResultBean getReservationWithStatus(ReservationStatus status) throws ApiValidateException, AccessDeniedException {
		return new ResultBean(reservationDao.getReservationWithStatus(status), "200", MessageUtils.getMessage("MSG01", "reservation"));
	}
	
	protected ResultBean changeReservationStatus(ReservationEntity entity, ReservationStatus status) throws ApiValidateException {
		entity.setStatus(status);
		
		return new ResultBean(reservationDao.updateReservation(entity), "201", MessageUtils.getMessage("MSG04", "reservation"));
	}

	@Override
	public ResultBean borrowReservation(Integer reservationId) throws LibException, AccessDeniedException {
		ReservationEntity entity = reservationDao.getReservationById(reservationId);
		
		if (!entity.getStatus().equals(ReservationStatus.TEMP)) {
			throw new BusinessException("402", "the book status is not temp");
		}
		
		return this.changeReservationStatus(entity, ReservationStatus.BORROWING);
	}

	@Override
	public ResultBean issueReservation(Integer reservationId) throws LibException, AccessDeniedException {
		ReservationEntity entity = reservationDao.getReservationById(reservationId);
		entity.setReservedTime(new Date());
		
		if (!entity.getStatus().equals(ReservationStatus.BORROWING)) {
			throw new BusinessException("402", "the book status is not borrowing");
		}
		
		return this.changeReservationStatus(entity, ReservationStatus.RESERVED);
	}

	@Override
	public ResultBean returnReservation(Integer reservationId) throws LibException, AccessDeniedException {
		ReservationEntity entity = reservationDao.getReservationById(reservationId);
		entity.setReturnedDate(new Date());
		
		if (!entity.getStatus().equals(ReservationStatus.RESERVED)) {
			throw new BusinessException("402", "the book status is not reserved");
		}
		
		List<BookItemEntity> newBookItems = entity.getBookItemEntities();
		newBookItems.forEach(item -> {
			item.setStatus(BookItemStatus.AVAILABLE);
		});
		
		entity.setBookItemEntities(newBookItems);
		
		return this.changeReservationStatus(entity, ReservationStatus.CLOSED);
	}

	@Override
	public ResultBean cancelBorrowingReservation(Integer reservationId) throws LibException, AccessDeniedException {
		ReservationEntity entity = reservationDao.getReservationById(reservationId);
		entity.setReturnedDate(new Date());
		
		if (!entity.getStatus().equals(ReservationStatus.BORROWING)) {
			throw new BusinessException("402", "the book status is not temp");
		}
		
		List<BookItemEntity> newBookItems = entity.getBookItemEntities();
		newBookItems.forEach(item -> {
			item.setStatus(BookItemStatus.AVAILABLE);
		});
		
		entity.setBookItemEntities(newBookItems);
		
		return this.changeReservationStatus(entity, ReservationStatus.CANCELED);
	}

}
