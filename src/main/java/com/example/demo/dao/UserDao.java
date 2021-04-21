/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import com.example.demo.bean.UserEntity;
import com.example.demo.response.UserResponse;

/**
 * [OVERVIEW] User Data access object.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/09      LinhDT       	  Create new
*/
public interface UserDao {

    /**
     * addUser
     * @author: LinhDT
     * @param entity
     */
    public void addUser(UserEntity entity);

    /**
     * updateUser
     * @author: LinhDT
     * @param entity
     */
    public void updateUser(UserEntity entity);

    /**
     * getUserByUsername
     * @author: LinhDT
     * @param username
     * @return
     */
    public UserResponse getUserByUsername(String username);

    /**
     * getUserEntityByUsername
     * @author: LinhDT
     * @param username
     * @return
     */
    public UserEntity getUserEntityByUsername(String username);

    /**
     * getUserEntityById
     * @author: LinhDT
     * @param id
     * @return
     */
    public UserEntity getUserEntityById(Integer id);

    /**
     * getUserByPhone
     * @author: LinhDT
     * @param phone
     * @return
     */
    public UserEntity getUserByPhone(String phone);

}
