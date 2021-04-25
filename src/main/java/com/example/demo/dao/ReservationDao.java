package com.example.demo.dao;

import com.example.demo.bean.ReservationEntity;

public interface ReservationDao {
	public ReservationEntity addReservation(ReservationEntity entity);
	
	public ReservationEntity updateReservation(ReservationEntity entity);
}
