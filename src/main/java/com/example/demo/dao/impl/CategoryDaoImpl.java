/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.CategoryEntity;
import com.example.demo.dao.CategoryDao;

/**
 * [OVERVIEW] Book Item Data Object Access Implementation.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/17      LinhDT       	  Create new
*/
@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    private static final Logger LOGGER = LogManager.getLogger(BookItemDaoImpl.class);

    /**
     * getListCategories
     * @author: LinhDT
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CategoryEntity> getListCategories() {
        LOGGER.info("----------getListCategories START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.CategoryResponse( ");
        sql.append("    ce.categoryId, ");
        sql.append("    ce.categoryName) ");
        sql.append(" FROM ");
        sql.append("    CategoryEntity ce ");

        Query query = this.entityManager.createQuery(sql.toString());
        List<CategoryEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("----------getListCategories END----------");
        return entity;
    }
}
