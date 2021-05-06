/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.utils;

import org.springframework.http.HttpStatus;

import com.example.demo.bean.ResultBean;

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

        if (code.equals("200") || code.equals("201") || code.equals("202")) {
            return HttpStatus.OK;
        }

        return HttpStatus.BAD_REQUEST;
    }
}
