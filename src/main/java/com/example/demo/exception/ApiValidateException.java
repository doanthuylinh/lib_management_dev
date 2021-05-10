/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.exception;

/**
 * [OVERVIEW] ApiValidateException.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/06      LinhDT       	  Create new
*/
public class ApiValidateException extends LibException {
    public ApiValidateException(String code, String message) {
        super(code, message);
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1320995563287455840L;

}
