/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.UserEntity;
import com.example.demo.dao.UserDao;
import com.example.demo.response.UserResponse;

/**
 * [OVERVIEW] User Data access object Implementation.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/09      LinhDT             Create new
 * 002       1.1       2021/05/07      LinhDT             Add getUserByEmail
*/
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    /**
     * addUser
     * @author: LinhDT
     * @param entity
     */
    public UserEntity addUser(UserEntity entity) {
        LOGGER.info("----------addUser START----------");
        this.entityManager.persist(entity);
        LOGGER.info("----------addUser END----------");
        return entity;
    }

    /**
     * updateUser
     * @author: LinhDT
     * @param entity
     * @return
     */
    public UserEntity updateUser(UserEntity entity) {
        LOGGER.info("----------updateUser START----------");
        this.entityManager.merge(entity);
        LOGGER.info("----------updateUser END----------");
        return entity;
    }

    /**
     * getUserByUsername
     * @author: LinhDT
     * @param username
     * @return
     */
    public UserResponse getUserByUsername(String username) {
        LOGGER.info("----------getUserByUsername START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.UserResponse(");
        sql.append("    ue.userId, ");
        sql.append("    ue.username, ");
        sql.append("    ue.email, ");
        sql.append("    ue.phone, ");
        sql.append("    ue.dob, ");
        sql.append("    ue.address, ");
        sql.append("    ue.role ) ");
        sql.append(" FROM ");
        sql.append("    UserEntity ue ");
        sql.append(" WHERE ");
        sql.append("    ue.username = :username ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("username", username);
        UserResponse entity = null;
        try {
            entity = (UserResponse) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("------getUserByUsername END--------------");
        return entity;
    }

    /**
     * getUserByPhone
     * @author: LinhDT
     * @param phone
     * @return
     */
    public UserEntity getUserByPhone(String phone) {
        LOGGER.info("----------getUserByPhone START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    UserEntity ue ");
        sql.append(" WHERE ");
        sql.append("    ue.phone = :phone ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("phone", phone);
        UserEntity entity = null;
        try {
            entity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("----------getUserByPhone END----------");
        return entity;
    }

    /**
     * getUserEntityByUsername
     * @author: LinhDT
     * @param username
     * @return
     */
    public UserEntity getUserEntityByUsername(String username) {
        LOGGER.info("----------getUserEntityByUsername START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    UserEntity ue ");
        sql.append(" WHERE ");
        sql.append("    ue.username = :username ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("username", username);
        UserEntity entity = null;
        try {
            entity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }

        LOGGER.info("----------getUserEntityByUsername END----------");
        return entity;
    }

    /**
     * getUserEntityById
     * @author: LinhDT
     * @param id
     * @return
     */
    public UserEntity getUserEntityById(Integer userId) {
        LOGGER.info("----------getUserEntityById START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    UserEntity ue ");
        sql.append(" WHERE ");
        sql.append("    ue.userId = :userId ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("userId", userId);
        UserEntity entity = null;
        try {
            entity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }

        LOGGER.info("----------getUserEntityById END----------");
        return entity;
    }

    /**
     * getUserByEmail
     * @author: LinhDT
     * @param email
     * @return
     */
    @Override
    public UserEntity getUserByEmail(String email) {
        LOGGER.info("----------getUserByEmail START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    UserEntity ue ");
        sql.append(" WHERE ");
        sql.append("    ue.email = :email ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("email", email);
        UserEntity entity = null;
        try {
            entity = (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {
        }
        LOGGER.info("----------getUserByEmail END----------");
        return entity;
    }

}
