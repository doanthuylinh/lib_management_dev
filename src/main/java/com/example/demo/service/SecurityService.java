/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import com.example.demo.bean.UserEntity;
import com.example.demo.exception.AuthenticateException;

/**
 * [OVERVIEW] Security Service.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/20      LinhDT             Create new
 */
public interface SecurityService {

    /**
     * getCurrentUserEntity
     * @author: LinhDT
     * @return
     */
    UserEntity getCurrentUserEntity();

    /**
     * getCurrentUsername
     * @author: LinhDT
     * @return
     */
    String getCurrentUsername();

    /**
     * checkUserWithUserId
     * @author: LinhDT
     * @param userId
     * @throws AuthenticateException
     */
    void checkUserWithUserId(Integer userId) throws AuthenticateException;

}
