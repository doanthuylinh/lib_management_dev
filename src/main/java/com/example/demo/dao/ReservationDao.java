/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.data.ReservationStatus;

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
public interface ReservationDao {

    /**
     * addReservation
     * @author: LinhDT
     * @param entity
     * @return
     */
    public ReservationEntity addReservation(ReservationEntity entity);

    /**
     * updateReservation
     * @author: LinhDT
     * @param entity
     * @return
     */
    public ReservationEntity updateReservation(ReservationEntity entity);

    /**
     * getReservationById
     * @author: LinhDT
     * @param id
     * @return
     */
    public ReservationEntity getReservationById(Integer id);

    /**
     * getCurrentTempReservation
     * @author: LinhDT
     * @param userId
     * @return
     */
    ReservationEntity getCurrentTempReservation(Integer userId);

    /**
     * getReservationWithStatus
     * @author: LinhDT
     * @param status
     * @return
     * @throws AccessDeniedException
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    List<ReservationEntity> getReservationWithStatus(ReservationStatus status) throws AccessDeniedException;

    /**
     * getReservationWithStatusByUserId
     * @author: LinhDT
     * @param userId
     * @param status
     * @return
     */
    List<ReservationEntity> getReservationWithStatusByUserId(Integer userId, ReservationStatus status);

}
