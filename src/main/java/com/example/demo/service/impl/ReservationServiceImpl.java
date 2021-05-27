/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.example.demo.bean.BookEntity;
import com.example.demo.bean.BookItemEntity;
import com.example.demo.bean.ReservationEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.dao.BookDao;
import com.example.demo.dao.BookItemDao;
import com.example.demo.dao.ReservationDao;
import com.example.demo.data.BookItemStatus;
import com.example.demo.data.ReservationStatus;
import com.example.demo.exception.ApiValidateException;
import com.example.demo.exception.AuthenticateException;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.LibException;
import com.example.demo.service.ReservationService;
import com.example.demo.service.SecurityService;
import com.example.demo.utils.MessageUtils;

/**
 * [OVERVIEW] Reservation Service Implementation.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/25      LinhDT             Create new
*/
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private BookItemDao bookItemDao;

    @Autowired
    private BookDao bookDao;

    /**
     * addReservation
     * @author: LinhDT
     * @param entity
     * @return
     * @throws ApiValidateException
     */
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

    /**
     * updateReservation
     * @author: LinhDT
     * @param entity
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean updateReservation(ReservationEntity entity) throws ApiValidateException {
        return new ResultBean(reservationDao.updateReservation(entity), "200", MessageUtils.getMessage("MSG04", "reservation"));
    }

    /**
     * addItemReservation
     * @author: LinhDT
     * @param entity
     * @param bookId
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean addItemReservation(ReservationEntity entity, Integer bookId) throws LibException {
        List<BookItemEntity> bookItems = bookItemDao.getListBookItemWithStatusByBookId(bookId, BookItemStatus.AVAILABLE);
        double totalFee = entity.getTotalFee();

        if (bookItems != null && bookItems.size() > 0) {
            BookItemEntity bookItem = bookItems.get(0);
            bookItem.setStatus(BookItemStatus.NOT_AVAILABLE);
            bookItemDao.updateBookItem(bookItem);

            totalFee += bookItem.getBookEntity().getRentCost();

            entity.getBookItemEntities().add(bookItem);
            entity.setTotalFee(totalFee);
        } else {
        	throw new BusinessException("NONE", "This book does't have available bookitems.");
        }

        return this.updateReservation(entity);
    }

    /**
     * removeItemReservation
     * @author: LinhDT
     * @param entity
     * @param bookId
     * @param amount
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean removeItemReservation(ReservationEntity entity, Integer bookId, Integer amount) throws ApiValidateException {
        if (amount == null || amount < 0)
            amount = 1000;

        List<BookItemEntity> bookItems = entity.getBookItemEntities().stream().filter(item -> item.getBookId().equals(bookId)).limit(amount)
                .collect(Collectors.toList());

        bookItems.forEach(item -> {
            item.setStatus(BookItemStatus.AVAILABLE);
            bookItemDao.updateBookItem(item);
        });

        List<BookItemEntity> newBookItems = entity.getBookItemEntities().stream().filter(item -> !bookItems.contains(item)).collect(Collectors.toList());

        double totalFee = newBookItems.stream()
    			.mapToDouble(bookItem -> bookItem.getBookEntity().getRentCost())
    			.sum();
        
        entity.setBookItemEntities(newBookItems);
        entity.setTotalFee(totalFee);

        return this.updateReservation(entity);
    }

    /**
     * removeItemReservation
     * @author: LinhDT
     * @param entity
     * @param bookId
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean removeItemReservation(ReservationEntity entity, Integer bookId) throws ApiValidateException {
        return this.removeItemReservation(entity, bookId, 1000);
    }

    /**
     * getReservationByUserId
     * @author: LinhDT
     * @param userId
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean getReservationByUserId(Integer userId) {
        return getReservationWithStatusByUserId(userId, ReservationStatus.UNDEFINED);
    }

    /**
     * getReservationWithStatusByUserId
     * @author: LinhDT
     * @param userId
     * @param status
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean getReservationWithStatusByUserId(Integer userId, ReservationStatus status) {
        List<ReservationEntity> entities = reservationDao.getReservationWithStatusByUserId(userId, status);

        return new ResultBean(entities, "200", MessageUtils.getMessage("MSG01", "reservation"));
    }

    /**
     * getReservationWithStatusByUserId
     * @author: LinhDT
     * @param userId
     * @param status
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean getReservationWithStatusByUserId(Integer userId, Integer status) {
        return getReservationWithStatusByUserId(userId, ReservationStatus.parse(status));
    }

    /**
     * addNewTempReservation
     * @author: LinhDT
     * @param userId
     * @return
     * @throws LibException
     */
    public ReservationEntity addNewTempReservation(Integer userId) throws LibException {
        ReservationEntity entity = new ReservationEntity();
        entity.setUserId(userId);
        entity.setCreatedTime(new Date());
        entity.setTotalFee(	0d);
        entity.setBookItemEntities(new ArrayList<BookItemEntity>());
        entity.setStatus(ReservationStatus.TEMP);

        return reservationDao.addReservation(entity);
    }

    /**
     * addItemReservation
     * @author: LinhDT
     * @param userId
     * @param bookId
     * @return
     * @throws LibException
     */
    @Override
    public ResultBean addItemReservation(Integer userId, Integer bookId) throws LibException {

        securityService.checkUserWithUserId(userId);

        ReservationEntity tempReservation = reservationDao.getCurrentTempReservation(userId);
        if (tempReservation == null) {
            tempReservation = addNewTempReservation(userId);
        }

        return addItemReservation(tempReservation, bookId);
    }

    /**
     * removeItemReservation
     * @author: LinhDT
     * @param userId
     * @param bookId
     * @param amount
     * @return
     * @throws ApiValidateException
     * @throws AuthenticateException
     */
    @Override
    public ResultBean removeItemReservation(Integer userId, Integer bookId, Integer amount) throws ApiValidateException, AuthenticateException {
        securityService.checkUserWithUserId(userId);

        ReservationEntity tempReservation = reservationDao.getCurrentTempReservation(userId);

        return removeItemReservation(tempReservation, bookId, amount);
    }

    /**
     * getReservationWithStatus
     * @author: LinhDT
     * @param status
     * @return
     * @throws ApiValidateException
     * @throws AccessDeniedException
     */
    @Override
    public ResultBean getReservationWithStatus(Integer status) throws ApiValidateException, AccessDeniedException {
        return getReservationWithStatus(ReservationStatus.parse(status));
    }

    /**
     * getReservationWithStatus
     * @author: LinhDT
     * @param status
     * @return
     * @throws ApiValidateException
     * @throws AccessDeniedException
     */
    @Override
    public ResultBean getReservationWithStatus(ReservationStatus status) throws ApiValidateException, AccessDeniedException {
        return new ResultBean(reservationDao.getReservationWithStatus(status), "200", MessageUtils.getMessage("MSG01", "reservation"));
    }

    /**
     * changeReservationStatus
     * @author: LinhDT
     * @param entity
     * @param status
     * @return
     * @throws ApiValidateException
     */
    protected ResultBean changeReservationStatus(ReservationEntity entity, ReservationStatus status) throws ApiValidateException {
        entity.setStatus(status);

        return new ResultBean(reservationDao.updateReservation(entity), "201", MessageUtils.getMessage("MSG04", "reservation"));
    }

    /**
     * borrowReservation
     * @author: LinhDT
     * @param reservationId
     * @return
     * @throws LibException
     * @throws AccessDeniedException
     */
    @Override
    public ResultBean borrowReservation(ReservationEntity reservation) throws LibException, AccessDeniedException {
        ReservationEntity entity = reservationDao.getReservationById(reservation.getReservationId());
        entity.setReservedTime(reservation.getReservedTime());
        entity.setExpectedReturnDate(reservation.getExpectedReturnDate());

        if (!entity.getStatus().equals(ReservationStatus.TEMP)) {
            throw new BusinessException("402", "the book status is not temp");
        }
        
        if (entity.getBookItemEntities().size() < 1) {
        	throw new BusinessException("402", "This cart is empty.");
        }

        return this.changeReservationStatus(entity, ReservationStatus.RESERVED);
    }

    /**
     * issueReservation
     * @author: LinhDT
     * @param reservationId
     * @return
     * @throws LibException
     * @throws AccessDeniedException
     */
    @Override
    public ResultBean issueReservation(Integer reservationId) throws LibException, AccessDeniedException {
        ReservationEntity entity = reservationDao.getReservationById(reservationId);

        if (!entity.getStatus().equals(ReservationStatus.RESERVED)) {
            throw new BusinessException("402", "the book status is not reserved");
        }

        return this.changeReservationStatus(entity, ReservationStatus.BORROWING);
    }

    /**
     * returnReservation
     * @author: LinhDT
     * @param reservationId
     * @return
     * @throws LibException
     * @throws AccessDeniedException
     */
    @Override
    public ResultBean returnReservation(ReservationEntity reservation) throws LibException, AccessDeniedException {
        ReservationEntity entity = reservationDao.getReservationById(reservation.getReservationId());
        
        if (reservation.getReturnedDate() == null)
        	entity.setReturnedDate(new Date());
        else {
        	entity.setReturnedDate(reservation.getReturnedDate());
        }

        if (!entity.getStatus().equals(ReservationStatus.BORROWING)) {
            throw new BusinessException("402", "the book status is not borrowing");
        }

        List<BookItemEntity> newBookItems = entity.getBookItemEntities();
        newBookItems.forEach(item -> {
            item.setStatus(BookItemStatus.AVAILABLE);
        });

        entity.setBookItemEntities(newBookItems);

        return this.changeReservationStatus(entity, ReservationStatus.CLOSED);
    }

    /**
     * cancelBorrowingReservation
     * @author: LinhDT
     * @param reservationId
     * @return
     * @throws LibException
     * @throws AccessDeniedException
     */
    @Override
    public ResultBean cancelBorrowingReservation(Integer reservationId) throws LibException, AccessDeniedException {
        ReservationEntity entity = reservationDao.getReservationById(reservationId);

        if (!entity.getStatus().equals(ReservationStatus.RESERVED)) {
            throw new BusinessException("402", "the book status is not reserved");
        }

        List<BookItemEntity> newBookItems = entity.getBookItemEntities();
        newBookItems.forEach(item -> {
            item.setStatus(BookItemStatus.AVAILABLE);
        });

        entity.setBookItemEntities(newBookItems);

        return this.changeReservationStatus(entity, ReservationStatus.CANCELED);
    }

	@Override
	public ResultBean extendReservation(ReservationEntity reservation) throws LibException, AccessDeniedException {
		ReservationEntity entity = reservationDao.getReservationById(reservation.getReservationId());
        
		Date newExpectedReturnDate = reservation.getExpectedReturnDate();
		
		if (entity.getIsExtended()) {
			throw new BusinessException("402", "the reservation must be have one time extend.");
		}
		
        if (newExpectedReturnDate == null || 
        		entity.getExpectedReturnDate().compareTo(newExpectedReturnDate) > 0) {
        	throw new BusinessException("402", "the new expected return date is not invalid.");
        }

        if (!entity.getStatus().equals(ReservationStatus.BORROWING)) {
            throw new BusinessException("402", "the book status is not borrowing.");
        }
        
        entity.setExpectedReturnDate(newExpectedReturnDate);
        entity.setIsExtended(true);
        
        return new ResultBean(reservationDao.updateReservation(entity), "201", MessageUtils.getMessage("MSG04", "reservation"));
	}
}
