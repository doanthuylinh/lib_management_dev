/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.util.Objects;

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
import com.example.demo.data.UserRole;
import com.example.demo.exception.ApiValidateException;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.response.UserResponse;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;
import com.example.demo.utils.ConstantColumn;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.MessageUtils;
import com.example.demo.utils.Regex;
import com.example.demo.utils.ValidateUtils;
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
 * 001       1.0       2021/04/09      LinhDT             Create new
 * 001       1.1       2021/05/10      LinhDT             Update addUser, updateUser
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

    @Autowired
    SecurityService securityService;

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    /**
     * addUser (Registration)
     * @author: LinhDT
     * @param data
     * @return
     * @throws ApiValidateException
     */

    @Override
    public ResultBean addUser(String data, UserRole role) throws ApiValidateException {
        UserEntity user = DataUtils.getEntityByJsonString(data, UserEntity.class);
        user.setRole(role == null ? UserRole.MEMBER : role);

        ValidateUtils.validateAddUser(user);

        // Get user name and check whether user name has already existed in database, if yes, throw a message.
        UserEntity userEntity = userDao.getUserEntityByUsername(user.getUsername());
        if (!Objects.isNull(userEntity)) {
            throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { ConstantColumn.USERNAME }));
        }

        // Get email and check whether email has already existed in database, if yes, throw a message.
        userEntity = userDao.getUserByEmail(user.getEmail());
        if (!Objects.isNull(userEntity)) {
            throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { ConstantColumn.EMAIL }));
        }

        // Check whether phone has already existed in database, if phone has existed, throw message.
        userEntity = userDao.getUserByPhone(user.getPhone());
        if (!Objects.isNull(userEntity)) {
            throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { ConstantColumn.PHONE }));
        }
        user.setPassword(webSecurityConfig.passwordEncoder().encode(user.getPassword()));
        return new ResultBean(userDao.addUser(user), "201", MessageUtils.getMessage("MSG02", "user"));
    }

    /**
     * updateUser
     * @author: LinhDT
     * @param json
     * @throws ApiValidateException
     */
    public void updateUser(String json) throws ApiValidateException {
        LOGGER.info("-----------updateUser START----------");

        UserEntity entity = securityService.getCurrentUserEntity();

        UserEntity jObject = DataUtils.getEntityByJsonString(json, UserEntity.class);

        ValidateUtils.validateUpdateUser(jObject);

        // Check whether new email is the same as the current email, if they are different then check whether the new email exists in database.
        if (!entity.getEmail().equals(jObject.getEmail())) {
            UserEntity userEntity = userDao.getUserByEmail(jObject.getEmail());

            // Check whether new email exists in database, if yes, throw message.
            if (!Objects.isNull(userEntity)) {
                throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { ConstantColumn.EMAIL }));
            }
            entity.setEmail(jObject.getEmail());
        }

        // Check whether new phone is the same as the current phone, if they are
        // different then check whether the new phone exists in database.
        if (!entity.getPhone().equals(jObject.getPhone())) {
            UserEntity userEntity = userDao.getUserByPhone(jObject.getPhone());
            // Check whether new phone exists in database, if yes, throw message.
            if (!Objects.isNull(userEntity)) {
                throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { ConstantColumn.PHONE }));
            }
            entity.setPhone(jObject.getPhone());
        }

        entity.setDob(jObject.getDob());
        entity.setAddress(jObject.getAddress());

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
        //UserResponse userResponse = userDao.getUserByUsername(DataUtils.getUsernameByToken());
        UserEntity userEntity = securityService.getCurrentUserEntity();
        LOGGER.info("-----------viewProfile END----------");
        return new ResultBean(userEntity, "200", MessageUtils.getMessage("MSG01", new Object[] { "user profile" }));
    }

    /**
     * login
     * 
     * @author: LinhDT
     * @param json
     * @return
     * @throws ApiValidateException
     */
    public UserResponse login(String json) throws ApiValidateException {
        LOGGER.info("-----------login START----------");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);
        String username = jObject.get("username").getAsString();
        String password = jObject.get("password").getAsString();
        String token = "";
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // If there is no exception, it means the information is valid.
            // Set information of authentication into Security Context.
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = tokenProvider.generateToken((UserDetail) authentication.getPrincipal());
        } catch (Exception e) {
            throw new ApiValidateException("ERR05", MessageUtils.getMessage("ERR05"));
        }
        UserResponse user = new UserResponse(userDao.getUserEntityByUsername(username));
        user.setTokenType("Bearer");
        user.setToken(token);

        LOGGER.info("-----------login END----------");
        return user;
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
