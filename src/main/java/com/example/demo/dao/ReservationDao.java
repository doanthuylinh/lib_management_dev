package com.example.demo.dao;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.data.ReservationStatus;

public interface ReservationDao {
	public ReservationEntity addReservation(ReservationEntity entity);
	
	public ReservationEntity updateReservation(ReservationEntity entity);
	
	ReservationEntity getCurrentTempReservation(Integer userId);
	
	@PreAuthorize("hasAuthority('ADMIN')")
	List<ReservationEntity> getReservationWithStatus(ReservationStatus status) throws AccessDeniedException;

	List<ReservationEntity> getReservationWithStatusByUserId(Integer userId, ReservationStatus status);
	
	
}
