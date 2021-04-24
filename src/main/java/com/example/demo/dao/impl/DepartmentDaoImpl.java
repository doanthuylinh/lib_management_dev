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

import com.example.demo.bean.DepartmentEntity;
import com.example.demo.dao.DepartmentDao;

/**
 * [OVERVIEW] Department Data access object implementation.
 *
 * @author: LinhDT
 * @version: 1.1
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/17      LinhDT             Create new
 * 002       1.1       2021/04/24      LinhDT             Create Add Department
*/
@Repository
@Transactional
public class DepartmentDaoImpl implements DepartmentDao {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    private static final Logger LOGGER = LogManager.getLogger(DepartmentDaoImpl.class);

    /**
     * getListDepartments
     * @author: LinhDT
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DepartmentEntity> getListDepartments() {
        LOGGER.info("----------getListDepartments START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.DepartmentResponse( ");
        sql.append("    de.departmentId, ");
        sql.append("    de.departmentName) ");
        sql.append(" FROM ");
        sql.append("    DepartmentEntity de ");

        Query query = this.entityManager.createQuery(sql.toString());
        List<DepartmentEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("----------getListDepartments END----------");
        return entity;
    }

    /**
     * addDepartment
     * @author: LinhDT
     * @param departmentEntity
     * @return
     */
    @Override
    public DepartmentEntity addDepartment(DepartmentEntity departmentEntity) {
        this.entityManager.persist(departmentEntity);
        return departmentEntity;
    }

    /**
     * getDepartmentByDepartmentName
     * @author: LinhDT
     * @param departmentName
     * @return
     */
    @Override
    public DepartmentEntity getDepartmentByDepartmentName(String departmentName) {
        LOGGER.info("----------getDepartmentByDepartmentName START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    DepartmentEntity de ");
        sql.append(" WHERE ");
        sql.append("    de.departmentName = :departmentName ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("departmentName", departmentName);
        DepartmentEntity entity = null;
        try {
            entity = (DepartmentEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("----------getDepartmentByDepartmentName END----------");
        return entity;
    }
}
