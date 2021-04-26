package com.example.demo.dao;

import java.util.List;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.data.ReservationStatus;

public interface ReservationDao {
	public ReservationEntity addReservation(ReservationEntity entity);
	
	public ReservationEntity updateReservation(ReservationEntity entity);

	List<ReservationEntity> getReservationWithStatusByUserId(Integer userId, ReservationStatus status);
	
	ReservationEntity getCurrentTempReservation(Integer userId);
}
