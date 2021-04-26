/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.ResultBean;
import com.example.demo.bean.UserDetail;
import com.example.demo.bean.UserEntity;
import com.example.demo.config.WebSecurityConfig;
import com.example.demo.dao.UserDao;
import com.example.demo.exception.ApiValidateException;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.response.UserResponse;
import com.example.demo.service.UserService;
import com.example.demo.utils.ConstantColumn;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.MessageUtils;
import com.example.demo.utils.Regex;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] User Service Implementation.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/09      LinhDT       	  Create new
*/
@Service
@Transactional(rollbackFor = { Exception.class, ApiValidateException.class })
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    WebSecurityConfig webSecurityConfig;

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    /**
     * addUser (Registration)
     * 
     * @author: LinhDT
     * @param json
     * @return
     * @throws ApiValidateException
     */
    public ResultBean addUser(String json) throws ApiValidateException {
        LOGGER.info("----------addUser START----------");

        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);

        // Check whether user name is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.USERNAME)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.USERNAME }));
        }

        // Get user name and check validation.
        String username = DataUtils.getAsStringByJson(jObject, ConstantColumn.USERNAME);
        if (!username.matches(Regex.NAME_PATTERN)) {
            throw new ApiValidateException("ERR06", MessageUtils.getMessage("ERR06"));
        }

        // Get user name and check whether user name has already existed in database, if
        // yes, throw a message.
        UserEntity userEntity = userDao.getUserEntityByUsername(username);
        if (!Objects.isNull(userEntity)) {
            throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { ConstantColumn.USERNAME }));
        }

        // Check whether password is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.PASSWORD)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.PASSWORD }));
        }

        // Get password and check validation.
        String password = DataUtils.getAsStringByJson(jObject, ConstantColumn.PASSWORD);
        if (!password.matches(Regex.PASSWORD_PATTERN)) {
            throw new ApiValidateException("ERR07", MessageUtils.getMessage("ERR07"));
        }

        // Check whether email is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.EMAIL)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.EMAIL }));
        }

        // Get email and check validation.
        String email = DataUtils.getAsStringByJson(jObject, ConstantColumn.EMAIL);
        if (!email.matches(Regex.EMAIL_PATTERN)) {
            throw new ApiValidateException("ERR08", MessageUtils.getMessage("ERR08"));
        }

        // Check whether phone is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.PHONE)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.PHONE }));
        }

        // Get phone and check validation.
        String phone = DataUtils.getAsStringByJson(jObject, ConstantColumn.PHONE);
        if (!phone.matches(Regex.PHONE_PATTERN)) {
            throw new ApiValidateException("ERR09", MessageUtils.getMessage("ERR09"));
        }

        // Check whether phone has already existed in database, if phone has existed,
        // throw message.
        // Get user by phone.
        UserEntity user = userDao.getUserByPhone(phone);
        if (!Objects.isNull(user)) {
            throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { ConstantColumn.PHONE }));
        }

        // Check whether date of birth is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.DOB)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.DOB }));
        }

        // Get date of birth and check validation.
        String dob = DataUtils.getAsStringByJson(jObject, ConstantColumn.DOB);
        if (!dob.matches(Regex.DATE_PATTERN)) {
            throw new ApiValidateException("ERR10", MessageUtils.getMessage("ERR10", new Object[] { "date" }));
        }

        // Check whether address is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.ADDRESS)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.ADDRESS }));
        }

        // Get address.
        String address = DataUtils.getAsStringByJson(jObject, ConstantColumn.ADDRESS);

        // Check whether role is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.ROLE)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.ROLE }));
        }

        // Get role and check validation.
        String role = DataUtils.getAsStringByJson(jObject, ConstantColumn.ROLE);
        if (!role.matches(Regex.ROLE_PATTERN)) {
            throw new ApiValidateException("ERR16", MessageUtils.getMessage("ERR16"));
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setPassword(webSecurityConfig.passwordEncoder().encode(password));
        entity.setEmail(email);
        entity.setPhone(phone);
        entity.setDob(dob);
        entity.setAddress(address);
        entity.setRole(role.charAt(0));
        userDao.addUser(entity);

        UserEntity resultClone = SerializationUtils.clone(entity);
        resultClone.setPassword(null);

        LOGGER.info("----------addUser END----------");
        return new ResultBean(resultClone, "201", MessageUtils.getMessage("MSG02", new Object[] { "user" }));
    }

    /**
     * updateUser
     * 
     * @author: LinhDT
     * @param json
     * @throws ApiValidateException
     */
    public void updateUser(String json) throws ApiValidateException {
        LOGGER.info("-----------updateUser START----------");

        UserEntity entity = userDao.getUserEntityByUsername(DataUtils.getUsernameByToken());

        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);

        // Check whether email is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.EMAIL)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.EMAIL }));
        }

        // Get email and check validation.
        String email = DataUtils.getAsStringByJson(jObject, ConstantColumn.EMAIL);
        if (!email.matches(Regex.EMAIL_PATTERN)) {
            throw new ApiValidateException("ERR08", MessageUtils.getMessage("ERR08", new Object[] { ConstantColumn.EMAIL }));
        }
        entity.setEmail(email);

        // Check whether phone is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.PHONE)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.PHONE }));
        }

        // Get phone and check validation.
        String phone = DataUtils.getAsStringByJson(jObject, ConstantColumn.PHONE);
        if (!phone.matches(Regex.PHONE_PATTERN)) {
            throw new ApiValidateException("ERR09", MessageUtils.getMessage("ERR09"));
        }

        // Check whether new phone is the same as the current phone, if they are
        // different then check whether the new phone exists in database.
        if (!entity.getPhone().equals(phone)) {
            UserEntity userEntity = userDao.getUserByPhone(phone);
            // Check whether new phone exists in database, if yes, throw message.
            if (!Objects.isNull(userEntity)) {
                throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { ConstantColumn.PHONE }));
            }
            entity.setPhone(phone);
        }

        // Check whether date of birth is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.DOB)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.DOB }));
        }

        // Get date of birth and check validation.
        String dob = DataUtils.getAsStringByJson(jObject, ConstantColumn.DOB);
        if (!dob.matches(Regex.DATE_PATTERN)) {
            throw new ApiValidateException("ERR10", MessageUtils.getMessage("ERR10", new Object[] { "date" }));
        }
        entity.setDob(dob);

        // Check whether address is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.ADDRESS)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.ADDRESS }));
        }

        // Get address.
        String address = DataUtils.getAsStringByJson(jObject, ConstantColumn.ADDRESS);
        entity.setAddress(address);

        userDao.updateUser(entity);

        LOGGER.info("-----------updateUser END----------");
    }

    /**
     * viewProfile
     * 
     * @author: LinhDT
     * @return
     */
    public ResultBean viewProfile() throws ApiValidateException {
        LOGGER.info("-----------viewProfile START----------");
        UserResponse userResponse = userDao.getUserByUsername(DataUtils.getUsernameByToken());
        LOGGER.info("-----------viewProfile END----------");
        return new ResultBean(userResponse, "200", MessageUtils.getMessage("MSG01", new Object[] { "user profile" }));
    }

    /**
     * login
     * 
     * @author: LinhDT
     * @param json
     * @return
     * @throws ApiValidateException
     */
    public Map<String, String> login(String json) throws ApiValidateException {
        LOGGER.info("-----------login START----------");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);
        String username = jObject.get("username").getAsString();
        String password = jObject.get("password").getAsString();
        String temp = "";
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // If there is no exception, it means the information is valid.
            // Set information of authentication into Security Context.
            SecurityContextHolder.getContext().setAuthentication(authentication);
            temp = tokenProvider.generateToken((UserDetail) authentication.getPrincipal());
        } catch (Exception e) {
            throw new ApiValidateException("ERR05", MessageUtils.getMessage("ERR05"));
        }
        Map<String, String> result = new HashMap<String, String>();
        result.put("type", "Bearer");
        result.put("token", temp);
        LOGGER.info("-----------login END----------");
        return result;
    }

    /**
     * changePassword
     * 
     * @author: LinhDT
     * @param json
     * @throws ApiValidateException
     */
    public void changePassword(String json) throws ApiValidateException {
        LOGGER.info("-----------changePassword START----------");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);

        UserEntity userEntity = userDao.getUserEntityByUsername(DataUtils.getUsernameByToken());

        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.CURRENT_PASSWORD)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.CURRENT_PASSWORD }));
        }

        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.PASSWORD)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.PASSWORD }));
        }

        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.CONFIRMED_PASSWORD)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.CONFIRMED_PASSWORD }));
        }

        // Check whether current password, which has just been input, is the same as old
        // password.
        String currentPassword = DataUtils.getAsStringByJson(jObject, ConstantColumn.CURRENT_PASSWORD);
        if (!webSecurityConfig.passwordEncoder().matches(currentPassword, userEntity.getPassword())) {
            throw new ApiValidateException("ERR11", MessageUtils.getMessage("ERR11", new Object[] { ConstantColumn.PASSWORD }));
        }

        // Get new password and check validation.
        String password = DataUtils.getAsStringByJson(jObject, ConstantColumn.PASSWORD);
        if (!password.matches(Regex.PASSWORD_PATTERN)) {
            throw new ApiValidateException("ERR07", MessageUtils.getMessage("ERR07"));
        }

        // Get new confirmed password.
        String confirmerPassword = DataUtils.getAsStringByJson(jObject, ConstantColumn.CONFIRMED_PASSWORD);

        // Check password matches confirmed password. If they do not match, throw
        // message.
        if (!password.equals(confirmerPassword)) {
            throw new ApiValidateException("ERR12", MessageUtils.getMessage("ERR12"));
        }

        // Check whether the new password is the same as the current one, if yes, throw
        // message.
        if (webSecurityConfig.passwordEncoder().matches(password, userEntity.getPassword())) {
            throw new ApiValidateException("ERR13", MessageUtils.getMessage("ERR13"));
        }

        userEntity.setPassword(webSecurityConfig.passwordEncoder().encode(password));
        userDao.updateUser(userEntity);

        LOGGER.info("-----------changePassword END----------");
    }
}
