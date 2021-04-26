package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.dao.ReservationDao;
import com.example.demo.data.ReservationStatus;

@Repository
@Transactional
public class ReservationDaoImpl implements ReservationDao{

	@PersistenceContext
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public ReservationEntity addReservation(ReservationEntity entity) {
		this.entityManager.persist(entity);
		
		return entity;
	}

	@Override
	public ReservationEntity updateReservation(ReservationEntity entity) {
		this.entityManager.merge(entity);
		
		return entity;
	}

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
		entity = (ReservationEntity) query.getSingleResult();
		
		return entity;
	}
	
}
