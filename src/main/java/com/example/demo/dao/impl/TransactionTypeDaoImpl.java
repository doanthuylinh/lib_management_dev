/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////
package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.TransactionTypeEntity;
import com.example.demo.dao.TransactionTypeDao;

/**
 * [OVERVIEW] Transaction Type Data Object Access Implementation.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/24      LinhDT             Create new
 */
@Repository
@Transactional
public class TransactionTypeDaoImpl implements TransactionTypeDao {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    private static final Logger LOGGER = LogManager.getLogger(TransactionTypeDaoImpl.class);

    /**
     * addTransactionType
     * @author: LinhDT
     * @param entity
     */
    @Override
    public void addTransactionType(TransactionTypeEntity entity) {
        this.entityManager.persist(entity);

    }

    /**
     * updateTransactionType
     * @author: LinhDT
     * @param entity
     */
    @Override
    public void updateTransactionType(TransactionTypeEntity entity) {
        this.entityManager.merge(entity);

    }

    /**
     * getTransactionTypeByName
     * @author: LinhDT
     * @param transactionTypeName
     * @return
     */
    @Override
    public TransactionTypeEntity getTransactionTypeByName(String transactionTypeName) {
        LOGGER.info("----------getTransactionTypeByName START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    TransactionTypeEntity tte ");
        sql.append(" WHERE ");
        sql.append("   tte.transactionTypeName = :transactionTypeName ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("transactionTypeName", transactionTypeName);
        TransactionTypeEntity entity = null;
        try {
            entity = (TransactionTypeEntity) query.getSingleResult();
        } catch (NoResultException e) {
        }
        LOGGER.info("----------getTransactionTypeByName END----------");
        return entity;
    }

    /**
     * getTransactionTypeById
     * @author: LinhDT
     * @param transactionTypeId
     * @return
     */
    @Override
    public TransactionTypeEntity getTransactionTypeById(Integer transactionTypeId) {
        LOGGER.info("----------getTransactionTypeById START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    TransactionTypeEntity tte ");
        sql.append(" WHERE ");
        sql.append("   tte.transactionTypeId = :transactionTypeId ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("transactionTypeId", transactionTypeId);
        TransactionTypeEntity entity = null;
        try {
            entity = (TransactionTypeEntity) query.getSingleResult();
        } catch (NoResultException e) {
        }
        LOGGER.info("----------getTransactionTypeById END----------");
        return entity;
    }

    /**
     * getListTransactionTypes
     * @author: LinhDT
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TransactionTypeEntity> getListTransactionTypes() {
        LOGGER.info("----------getListTransactionTypes START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    TransactionTypeEntity tte ");

        Query query = this.entityManager.createQuery(sql.toString());
        List<TransactionTypeEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("----------getListTransactionTypes END----------");
        return entity;
    }
}
