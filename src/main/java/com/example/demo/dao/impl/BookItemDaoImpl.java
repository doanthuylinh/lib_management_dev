/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
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

import com.example.demo.bean.BookItemEntity;
import com.example.demo.dao.BookItemDao;
import com.example.demo.data.BookItemStatus;
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
	@SuppressWarnings("unchecked")
    public List<BookItemEntity> getListBookItemWithStatusByBookId(Integer bookId, BookItemStatus status) {
    	return this.getListBookItemWithStatusByBookId(bookId, status.value());
    }

	@Override
	@SuppressWarnings("unchecked")
    public List<BookItemEntity> getListBookItemWithStatusByBookId(Integer bookId, Integer statusValue) {
        LOGGER.info("----------getListBookItemWithStatusByBookId START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    BookItemEntity bie ");
        sql.append(" WHERE ");
        sql.append("    bie.bookId = :bookId ");
        sql.append(" AND ");
        sql.append("	bie.status = :status ");
        
        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("bookId", bookId);
        query.setParameter("status", statusValue);
        List<BookItemEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("----------getListBookItemWithStatusByBookId END----------");
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
    
    public BookItemEntity getBookItem(Integer bookItemId) {
    	StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    BookItemEntity bie ");
        sql.append(" WHERE ");
        sql.append("    bie.bookItemId = :bookItemId ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("bookItemId", bookItemId);
        
        BookItemEntity entity = null;
        try {
        	entity = (BookItemEntity) query.getSingleResult();
        } catch (NoResultException e) {
        	
        }
        
        return entity;
    }

    @Override
    public BookItemEntity updateBookItem(BookItemEntity entity) {
        this.entityManager.merge(entity);

        return entity;
    }

	@Override
	public BookItemEntity addBookItem(BookItemEntity entity) {
		this.entityManager.persist(entity);
		
		return entity;
	}

	@Transactional
	@Override
	public void removeBookItemId(Integer bookItemId) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM ");
        sql.append("    BookItemEntity be ");

        sql.append(" WHERE be.bookItemId = :bookItemId");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("bookItemId", bookItemId);
        query.executeUpdate();
	}

}
