/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.data.ReservationStatus;
import com.example.demo.exception.ApiValidateException;
import com.example.demo.exception.AuthenticateException;
import com.example.demo.exception.LibException;

/**
 * [OVERVIEW] Reservation Data access object.
 *
 * @author: LinhDT
 * @version: 1.1
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/25      LinhDT             Create new
*/
public interface ReservationService {
    
    /**
     * addReservation
     * @author: LinhDT
     * @param entity
     * @return
     * @throws ApiValidateException
     */
    public ResultBean addReservation(ReservationEntity entity) throws ApiValidateException;

    /**
     * updateReservation
     * @author: LinhDT
     * @param entity
     * @return
     * @throws ApiValidateException
     */
    public ResultBean updateReservation(ReservationEntity entity) throws ApiValidateException;

    /**
     * getReservationWithStatus
     * @author: LinhDT
     * @param status
     * @return
     * @throws ApiValidateException
     * @throws AccessDeniedException
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResultBean getReservationWithStatus(Integer status) throws ApiValidateException, AccessDeniedException;

    /**
     * getReservationWithStatus
     * @author: LinhDT
     * @param status
     * @return
     * @throws ApiValidateException
     * @throws AccessDeniedException
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResultBean getReservationWithStatus(ReservationStatus status) throws ApiValidateException, AccessDeniedException;

    /**
     * borrowReservation
     * @author: LinhDT
     * @param reservationId
     * @return
     * @throws LibException
     * @throws AccessDeniedException
     */
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResultBean borrowReservation(ReservationEntity reservation) throws LibException, AccessDeniedException;

    /**
     * issueReservation
     * @author: LinhDT
     * @param reservationId
     * @return
     * @throws LibException
     * @throws AccessDeniedException
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResultBean issueReservation(Integer reservationId) throws LibException, AccessDeniedException;

    /**
     * returnReservation
     * @author: LinhDT
     * @param reservationId
     * @return
     * @throws LibException
     * @throws AccessDeniedException
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResultBean returnReservation(ReservationEntity reservation) throws LibException, AccessDeniedException;

    /**
     * cancelBorrowingReservation
     * @author: LinhDT
     * @param reservationId
     * @return
     * @throws LibException
     * @throws AccessDeniedException
     */
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MEMBER')")
    public ResultBean cancelBorrowingReservation(Integer reservationId) throws LibException, AccessDeniedException;
    
    /**
     * extendReservation
     * @author: LinhDT
     * @param reservation
     * @return
     * @throws LibException
     * @throws AccessDeniedException
     */
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MEMBER')")
    public ResultBean extendReservation(ReservationEntity reservation) throws LibException, AccessDeniedException;

    /**
     * addItemReservation
     * @author: LinhDT
     * @param entity
     * @param bookId
     * @return
     * @throws ApiValidateException
     */
    public ResultBean addItemReservation(ReservationEntity entity, Integer bookId) throws LibException;

    /**
     * addItemReservation
     * @author: LinhDT
     * @param userId
     * @param bookId
     * @return
     * @throws LibException
     */
    public ResultBean addItemReservation(Integer userId, Integer bookId) throws LibException;

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
    public ResultBean removeItemReservation(Integer userId, Integer bookId, Integer amount) throws ApiValidateException, AuthenticateException;

    /**
     * removeItemReservation
     * @author: LinhDT
     * @param entity
     * @param bookId
     * @param amount
     * @return
     * @throws ApiValidateException
     */
    public ResultBean removeItemReservation(ReservationEntity entity, Integer bookId, Integer amount) throws ApiValidateException;

    /**
     * removeItemReservation
     * @author: LinhDT
     * @param entity
     * @param bookId
     * @return
     * @throws ApiValidateException
     */
    public ResultBean removeItemReservation(ReservationEntity entity, Integer bookId) throws ApiValidateException;

    /**
     * getReservationByUserId
     * @author: LinhDT
     * @param userId
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getReservationByUserId(Integer userId) throws ApiValidateException;

    /**
     * getReservationWithStatusByUserId
     * @author: LinhDT
     * @param userId
     * @param status
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getReservationWithStatusByUserId(Integer userId, ReservationStatus status) throws ApiValidateException;

    /**
     * getReservationWithStatusByUserId
     * @author: LinhDT
     * @param userId
     * @param status
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getReservationWithStatusByUserId(Integer userId, Integer status) throws ApiValidateException;
}
