/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import com.example.demo.bean.ResultBean;
import com.example.demo.exception.LibException;

/**
 * [OVERVIEW] Response Utils.
 *
 * @author: LinhDT
 * @version: 1.1
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/20      LinhDT             Create new
*/
public class ResponseUtils {

    public static HttpStatus getResponseStatus(ResultBean result) {
        String code = result.getMeta().getCode();
        
//        if (code.equals("500")) {
//        	return HttpStatus.INTERNAL_SERVER_ERROR;
//        }

        return HttpStatus.OK;
    }
    
    public static ResultBean handleError(Exception e) {
    	ResultBean resultBean = null;
    	if (e instanceof AccessDeniedException)
    		return new ResultBean("401", e.getMessage());
    	if (e instanceof LibException) {
    		return new ResultBean(((LibException)e).getCode(), e.getMessage());
    	}
    	
    	e.printStackTrace();
        return new ResultBean("500", e.getMessage());
    }
}
