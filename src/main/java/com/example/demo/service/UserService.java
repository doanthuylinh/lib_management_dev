/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import java.util.Map;

import com.example.demo.bean.ResultBean;
import com.example.demo.exception.ApiValidateException;

/**
 * [OVERVIEW] UserService.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/09      LinhDT             Create new
*/
public interface UserService {

    /**
     * addUser (Registration)
     * @author: LinhDT
     * @param entity
     * @return
     * @throws ApiValidateException
     */
    public ResultBean addUser(String entity) throws ApiValidateException;

    /**
     * updateUser
     * @author: LinhDT
     * @param entity
     * @throws ApiValidateException
     */
    public void updateUser(String entity) throws ApiValidateException;

    /**
     * viewProfile
     * @author: LinhDT
     * @return
     */
    public ResultBean viewProfile() throws ApiValidateException;

    /**
     * login
     * @author: LinhDT
     * @param entity
     * @return
     * @throws ApiValidateException
     */
    public Map<String, String> login(String entity) throws ApiValidateException;

    /**
     * changePassword
     * @author: LinhDT
     * @param json
     * @throws ApiValidateException
     */
    public void changePassword(String json) throws ApiValidateException;
}
