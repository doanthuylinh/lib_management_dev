package com.example.demo.dao;

import com.example.demo.bean.ReservationEntity;

public interface Reservation {
	public void addReservation(ReservationEntity entity);
	
	public void updateReservation(ReservationEntity entity);
}
