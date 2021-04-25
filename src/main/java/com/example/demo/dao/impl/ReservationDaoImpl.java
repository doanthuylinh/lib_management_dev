package com.example.demo.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.dao.ReservationDao;

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

}
