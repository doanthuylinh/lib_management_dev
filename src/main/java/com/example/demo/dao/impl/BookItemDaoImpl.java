/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
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

import com.example.demo.bean.BookItemEntity;
import com.example.demo.dao.BookItemDao;
import com.example.demo.response.BookItemResponse;

/**
 * [OVERVIEW] Book Item Data Object Access Implementation.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT       	  Create new
*/
@Repository
@Transactional
public class BookItemDaoImpl implements BookItemDao {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    private static final Logger LOGGER = LogManager.getLogger(BookItemDaoImpl.class);

    /**
     * getListBookItemByBookId
     * @author: LinhDT
     * @param bookId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BookItemEntity> getListBookItemByBookId(Integer bookId) {
        LOGGER.info("----------getBookItemByBookId START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    BookItemEntity bie ");
        sql.append(" WHERE ");
        sql.append("    bie.bookId = :bookId ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("bookId", bookId);
        List<BookItemEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("----------getBookItemByBookId END----------");
        return entity;
    }

    @Override
    public long countBookItem(Integer bookId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" select count(*) from BookItemEntity where bookId = :bookId ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("bookId", bookId);

        Long count = (Long) query.getSingleResult();
        return count;
    }

    @Override
    public BookItemEntity updateBookItem(BookItemEntity entity) {
        this.entityManager.merge(entity);

        return entity;
    }
}
