/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import java.util.Map;

import com.example.demo.bean.ResultBean;
import com.example.demo.data.UserRole;
import com.example.demo.exception.ApiValidateException;
import com.example.demo.response.UserResponse;

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
     * addUser
     * @author: LinhDT
     * @param data
     * @param role
     * @return
     * @throws ApiValidateException
     */
    public ResultBean addUser(String data, UserRole role) throws ApiValidateException;

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
    public UserResponse login(String entity) throws ApiValidateException;

    /**
     * changePassword
     * @author: LinhDT
     * @param json
     * @throws ApiValidateException
     */
    public void changePassword(String json) throws ApiValidateException;

}
