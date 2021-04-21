/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.utils;

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
public class ApiValidateException extends Exception {
    private static final long serialVersionUID = 1320995563287455840L;

    private String code;

    private String field;

    private String message;

    public ApiValidateException(String code, String field, String message) {
        this.code = code;
        this.field = field;
        this.message = message;
    }

    public ApiValidateException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
