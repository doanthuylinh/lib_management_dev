package com.example.demo.service;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.utils.ApiValidateException;

public interface ReservationService {
	public ResultBean addReservation(ReservationEntity entity) throws ApiValidateException;
	
	public ResultBean updateReservation(ReservationEntity entity) throws ApiValidateException;

	ResultBean addItemReservation(ReservationEntity entity, Integer bookId) throws ApiValidateException;
	
	ResultBean removeItemReservation(ReservationEntity entity, Integer bookId) throws ApiValidateException;

	ResultBean removeItemReservation(ReservationEntity entity, Integer bookId, Integer amount)
			throws ApiValidateException;
}
