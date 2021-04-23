package com.example.demo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ResultBean;
import com.google.gson.JsonObject;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping(value = "/api")
public class HealthCheckController {
	
	private static final Logger LOGGER = LogManager.getLogger(HealthCheckController.class);
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<ResultBean> showWelcome() {
		ResultBean result = new ResultBean("200", "Library Management System API");
		
		return new ResponseEntity<ResultBean>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public ResponseEntity<ResultBean> ping() throws InterruptedException {
		LOGGER.info("-----Some one PING me-----");
	
		
		ResultBean result = new ResultBean("200", "pong");
		
		return new ResponseEntity<ResultBean>(result, HttpStatus.OK);
	}
}
