package com.example.demo.utils;

import org.springframework.http.HttpStatus;

import com.example.demo.bean.ResultBean;

public class ResponseUtils {
	
	public static HttpStatus getResponseStatus(ResultBean result) {
		String code = result.getMeta().getCode();
		
		if (code.equals("200")) {
			return HttpStatus.OK;
		}
		
		return HttpStatus.BAD_REQUEST;
	}
}
