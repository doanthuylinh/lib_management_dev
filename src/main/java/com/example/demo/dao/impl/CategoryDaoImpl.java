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
 * 002       1.1       2021/04/24      LinhDT             Create Add Category
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

    /**
     * addCategory
     * @author: LinhDT
     * @param categoryEntity
     * @return
     */
    @Override
    public CategoryEntity addCategory(CategoryEntity categoryEntity) {
        LOGGER.info("----------addCategory START----------");
        this.entityManager.persist(categoryEntity);
        LOGGER.info("----------addCategory END----------");
        return categoryEntity;
    }

    /**
     * getCategoryByCategoryName
     * @author: LinhDT
     * @param categoryName
     * @return
     */
    @Override
    public CategoryEntity getCategoryByCategoryName(String categoryName) {
        LOGGER.info("----------getCategoryByCategoryName START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    CategoryEntity ce ");
        sql.append(" WHERE ");
        sql.append("    ce.categoryName = :categoryName ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("categoryName", categoryName);
        CategoryEntity entity = null;
        try {
            entity = (CategoryEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("----------getCategoryByCategoryName END----------");
        return entity;
    }
}
