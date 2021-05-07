/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.utils;

/**
 * [OVERVIEW] Regular expression.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/09      LinhDT             Create new
*/
public class Regex {

    /**
     * Regular expressions for User Validation
     */
    public static final String ID_PATTERN = "[0-9]+";
    public static final String NAME_PATTERN = "^[a-zA-Z0-9]*$";
    public static final String PHONE_PATTERN = "^[0-9]{10,11}$";
    public static final String DATE_PATTERN = "^\\d{4}[\\/\\s]?((((0[13578])|(1[02]))[\\/\\s]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\/\\s]?(([0-2][0-9])|(30)))|(02[\\/\\s]?[0-2][0-9]))$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{1,8}$";
    public static final String MONEY_PATTERN = "[0-9]{1,13}(.[0-9]*)?";
    public static final String EMAIL_PATTERN = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
    public static final String BARCODE_PATTERN = "^[0-9]{13}$";
    public static final String ROLE_PATTERN = "^[0-1]$";
}
