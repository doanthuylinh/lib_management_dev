package com.example.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.dao.ReservationDao;
import com.example.demo.data.ReservationStatus;
import com.example.demo.service.ReservationService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.MessageUtils;


@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationDao reservationDao;
	
	@Override
	public ResultBean addReservation(ReservationEntity entity) throws ApiValidateException {
		entity.setCreatedTime(new Date());
		entity.setStatus(ReservationStatus.TEMP);
		return new ResultBean(reservationDao.addReservation(entity), "201", MessageUtils.getMessage("MSG02", "reservation"));
	}

	@Override
	public ResultBean updateReservation(ReservationEntity entity) throws ApiValidateException {
		// TODO Auto-generated method stub
		return null;
	}

}
