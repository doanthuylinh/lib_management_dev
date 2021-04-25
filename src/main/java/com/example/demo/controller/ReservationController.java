package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.service.ReservationService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.ResponseUtils;

@RestController
@RequestMapping(value = "api/reservation")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	private static final Logger LOGGER = LogManager.getLogger(ReservationController.class);
	
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<ResultBean> addReservation(@RequestBody String data) {
		LOGGER.info("--- addReservation START ---");
		ResultBean resultBean = null;
		try {
			ReservationEntity entity = DataUtils.getEntityByJsonString(data, ReservationEntity.class);
			resultBean = reservationService.addReservation(entity);
		} catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
		
		LOGGER.info("--- addReservation END ---");
		return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
	}
}
