/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ResultBean;
import com.example.demo.exception.ApiValidateException;
import com.example.demo.service.UserService;
import com.example.demo.utils.MessageUtils;

/**
 * [OVERVIEW] User Controller.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/09      LinhDT             Create new (Registration, login)
 * 002       1.0       2021/04/11      LinhDT             View profile, change password, update profile
*/
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    /**
     * addUser (registration)
     * @author: LinhDT
     * @param entity
     * @return
     */
    @RequestMapping(value = "/user/registration", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addUser(@RequestBody String entity) {
        LOGGER.info("----------addUser START----------");
        ResultBean resultBean = null;
        try {
            resultBean = userService.addUser(entity);
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.OK);
        }
        LOGGER.info("----------addUser END----------");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * updateUser
     * @author: LinhDT
     * @param entity
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> updateUser(@RequestBody String entity) {
        LOGGER.info("----------updateUser START----------");
        try {
            userService.updateUser(entity);
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.OK);
        }

        LOGGER.info("----------updateUser END----------");
        return new ResponseEntity<ResultBean>(new ResultBean("200", MessageUtils.getMessage("MSG04")), HttpStatus.OK);
    }

    /**
     * login 
     * @author: LinhDT
     * @param entity
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> login(@RequestBody String entity) {
        LOGGER.info("----------login START----------");
        Map<String, String> result = null;
        try {
            result = userService.login(entity);
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.OK);
        }
        LOGGER.info("----------login END----------");
        return new ResponseEntity<ResultBean>(new ResultBean(result, "200", MessageUtils.getMessage("MSG03")), HttpStatus.OK);
    }

    /**
     * changePassword
     * @author: LinhDT
     * @param entity
     * @return
     */
    @RequestMapping(value = "/changepassword", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> changePassword(@RequestBody String entity) {
        LOGGER.info("----------changePassword START----------");

        try {
            userService.changePassword(entity);
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.OK);
        }

        LOGGER.info("----------changePassword END----------");
        return new ResponseEntity<ResultBean>(new ResultBean("200", MessageUtils.getMessage("MSG04")), HttpStatus.OK);
    }

    /**
     * viewProfile
     * @author: LinhDT
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @PreAuthorize("@appAuthorizer.authorize(authentication,'VIEW',this)")
    public ResponseEntity<ResultBean> viewProfile() {
        LOGGER.info("----------viewProfile START----------");
        ResultBean entity = null;
        try {
            entity = userService.viewProfile();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
        }
        LOGGER.info("----------viewProfile END----------");
        return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
    }
}
