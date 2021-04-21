/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.UserDetail;
import com.example.demo.bean.UserEntity;
import com.example.demo.dao.UserDao;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] User Details Service Implementation.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT       	  Create new
*/
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    private static final Logger LOGGER = LogManager.getLogger(UserDetailsServiceImpl.class);

    /**
     * loadUserByUsername
     * @author LinhDT
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("----------loadUserByUsername START----------");
        UserEntity userEntity = userDao.getUserEntityByUsername(username);
        if (Objects.isNull(userEntity)) {
            throw new UsernameNotFoundException(username);
        }
        LOGGER.info("----------loadUserByUsername END----------");
        return new UserDetail(userEntity);
    }

    /**
     * loadUserById
     * @author: LinhDT
     * @param id
     * @return
     * @throws ApiValidateException
     */
    public UserDetails loadUserById(Integer id) throws ApiValidateException {
        LOGGER.info("----------loadUserById START----------");
        UserEntity userEntity = userDao.getUserEntityById(id);
        LOGGER.info("----------loadUserById END----------");
        return new UserDetail(userEntity);
    }

}
