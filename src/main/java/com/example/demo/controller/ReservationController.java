package com.example.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ReservationEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.exception.ApiValidateException;
import com.example.demo.exception.LibException;
import com.example.demo.service.ReservationService;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.ResponseUtils;
import com.google.gson.JsonObject;

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
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ResultBean> getReservation(
			@RequestParam("userId") Integer userId,
			@RequestParam(name = "status", required = false, defaultValue = "-1") Integer status) {
		LOGGER.info("--- getReservation START ---");
		ResultBean resultBean = null;
		try {
			resultBean = reservationService.getReservationWithStatusByUserId(userId, status);
		} catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
		
		LOGGER.info("--- getReservation END ---");
		return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
	}
	
	@RequestMapping(value = "/item", method = RequestMethod.POST)
	@PreAuthorize("@appAuthorizer.authorize(authentication,'VIEW',this)")
	public ResponseEntity<ResultBean> addReservationItem(
			@RequestBody String data) {
		LOGGER.info("--- addReservationItem START ---");
		ResultBean resultBean = null;
		try {
			JsonObject obj = DataUtils.getEntityByJsonString(data, JsonObject.class);
			
			// TO DO: validate this
			Integer userId = DataUtils.getAsIntegerByJson(obj, "user_id");
			Integer bookId = DataUtils.getAsIntegerByJson(obj, "book_id");
			
			resultBean = reservationService.addItemReservation(userId, bookId);
		} catch (LibException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        }
		catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
		
		LOGGER.info("--- addReservationItem END ---");
		return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
	} 
	
	@RequestMapping(value = "/item", method = RequestMethod.DELETE)
	@PreAuthorize("@appAuthorizer.authorize(authentication,'VIEW',this)")
	public ResponseEntity<ResultBean> removeReservationItem(
			@RequestBody String data) {
		LOGGER.info("--- removeReservationItem START ---");
		ResultBean resultBean = null;
		try {
			JsonObject obj = DataUtils.getEntityByJsonString(data, JsonObject.class);
			
			// TO DO: validate this
			Integer userId = DataUtils.getAsIntegerByJson(obj, "user_id");
			Integer bookId = DataUtils.getAsIntegerByJson(obj, "book_id");
			Integer amount = DataUtils.getAsIntegerByJson(obj, "amount");
			
			resultBean = reservationService.removeItemReservation(userId, bookId, amount);
		} catch (LibException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        }
		catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
		
		LOGGER.info("--- removeReservationItem END ---");
		return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
	} 
}
