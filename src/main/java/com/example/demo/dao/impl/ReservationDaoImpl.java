/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.dao.ReservationDao;
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
@Repository
@Transactional
public class ReservationDaoImpl implements ReservationDao {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    /**
     * addReservation
     * @author: LinhDT
     * @param entity
     * @return
     */
    @Override
    public ReservationEntity addReservation(ReservationEntity entity) {
        this.entityManager.persist(entity);

        return entity;
    }

    /**
     * updateReservation
     * @author: LinhDT
     * @param entity
     * @return
     */
    @Override
    public ReservationEntity updateReservation(ReservationEntity entity) {
        this.entityManager.merge(entity);

        return entity;
    }

    /**
     * getReservationById
     * @author: LinhDT
     * @param id
     * @return
     */
    @Override
    public ReservationEntity getReservationById(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append(" 	ReservationEntity re ");
        sql.append(" WHERE ");
        sql.append(" 	re.reservationId = :reservationId ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("reservationId", id);
        query.setMaxResults(1);

        ReservationEntity entity = null;
        entity = (ReservationEntity) query.getSingleResult();

        return entity;
    }

    /**
     * getReservationWithStatus
     * @author: LinhDT
     * @param status
     * @return
     * @throws AccessDeniedException
     */
    @Override
    public List<ReservationEntity> getReservationWithStatusByUserId(Integer userId, ReservationStatus status) {
        if (status == null) {
            status = ReservationStatus.UNDEFINED;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append(" 	ReservationEntity re ");
        sql.append(" WHERE ");
        sql.append(" 	re.userId = :userId ");

        if (!status.equals(ReservationStatus.UNDEFINED)) {
            sql.append(" AND ");
            sql.append(" re.status = :statusValue ");
        }

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("userId", userId);
        if (!status.equals(ReservationStatus.UNDEFINED)) {
            query.setParameter("statusValue", status.value());
        }

        List<ReservationEntity> entities = null;
        entities = query.getResultList();

        return entities;
    }

    /**
     * getCurrentTempReservation
     * @author: LinhDT
     * @param userId
     * @return
     */
    @Override
    public ReservationEntity getCurrentTempReservation(Integer userId) {
        ReservationStatus status = ReservationStatus.TEMP;

        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append(" 	ReservationEntity re ");
        sql.append(" WHERE ");
        sql.append(" 	re.userId = :userId ");
        sql.append(" AND ");
        sql.append(" 	re.status = :statusValue ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("statusValue", status.value());
        query.setMaxResults(1);

        ReservationEntity entity = null;
        try {
            entity = (ReservationEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }

        return entity;
    }

    /**
     * getReservationWithStatusByUserId
     * @author: LinhDT
     * @param userId
     * @param status
     * @return
     */
    @Override
    public List<ReservationEntity> getReservationWithStatus(ReservationStatus status) {
        if (status == null) {
            status = ReservationStatus.UNDEFINED;
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append(" 	ReservationEntity re ");

        if (!status.equals(ReservationStatus.UNDEFINED)) {
            sql.append(" WHERE ");
            sql.append(" re.status = :statusValue ");
        }

        Query query = this.entityManager.createQuery(sql.toString());
        if (!status.equals(ReservationStatus.UNDEFINED)) {
            query.setParameter("statusValue", status.value());
        }

        List<ReservationEntity> entities = null;
        entities = query.getResultList();

        return entities;
    }

}
