/////////////////////////////////////////////////////////////////////////////
//
// � 2020 VNEXT TRAINING
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
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.BookEntity;
import com.example.demo.bean.CategoryEntity;
import com.example.demo.dao.BookDao;
import com.example.demo.response.BookResponse;

/**
 * [OVERVIEW] Book Data Object Access Implementation.
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
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;
    
//    @Autowired
//    private Session session;

    private static final Logger LOGGER = LogManager.getLogger(BookDaoImpl.class);

    /**
     * getBookById
     * @author: LinhDT
     * @param bookId
     * @return
     */
    public BookResponse getBookById(Integer bookId) {
        LOGGER.info("----------getBookById START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.BookResponse(");
        sql.append("    be.bookId, ");
        sql.append("    be.bookName, ");
        sql.append("    be.description, ");
        sql.append("    be.language, ");
        sql.append("    be.author, ");
        sql.append("    ce.categoryName, ");
        sql.append("    de.departmentName, ");
        sql.append("    bte.bookTypeName, ");
        sql.append("    be.publicationDate) ");
        sql.append(" FROM ");
        sql.append("    BookEntity be ");
        sql.append(" INNER JOIN ");
        sql.append("    DepartmentEntity de ");
        sql.append(" ON ");
        sql.append("    be.department_id = de.departmentId ");
        sql.append("  INNER JOIN ");
        sql.append("    BookTypeEntity bte ");
        sql.append(" ON ");
        sql.append("    be.bookType = bte.bookTypeId ");
        sql.append("  INNER JOIN ");
        sql.append("    CategoryEntity ce ");
        sql.append(" ON ");
        sql.append("    be.category = ce.categoryId ");
        sql.append(" WHERE ");
        sql.append("    be.bookId = :bookId ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("bookId", bookId);
        BookResponse entity = null;
        try {
            entity = (BookResponse) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("----------getBookById END----------");
        return entity;
    }

    /**
     * getBookByName
     * @author: LinhDT
     * @param bookName
     * @return
     */
    public BookResponse getBookByName(String bookName) {
        LOGGER.info("----------getBookByName START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.BookResponse(");
        sql.append("    be.bookId, ");
        sql.append("    be.bookName, ");
        sql.append("    be.description, ");
        sql.append("    be.language, ");
        sql.append("    be.author, ");
        sql.append("    ce.categoryName, ");
        sql.append("    de.departmentName, ");
        sql.append("    bte.bookTypeName, ");
        sql.append("    be.publicationDate) ");
        sql.append(" FROM ");
        sql.append("    BookEntity be ");
        sql.append(" INNER JOIN ");
        sql.append("    DepartmentEntity de ");
        sql.append(" ON ");
        sql.append("    be.department = de.departmentId ");
        sql.append("  INNER JOIN ");
        sql.append("    BookTypeEntity bte ");
        sql.append(" ON ");
        sql.append("    be.bookType = bte.bookTypeId ");
        sql.append("  INNER JOIN ");
        sql.append("    CategoryEntity ce ");
        sql.append(" ON ");
        sql.append("    be.category = ce.categoryId ");
        sql.append(" WHERE ");
        sql.append("    be.bookName = :bookName ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("bookName", bookName);
        BookResponse entity = null;
        try {
            entity = (BookResponse) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("----------getBookByName END----------");
        return entity;
    }

    /**
     * getBookByAuthor
     * @author: LinhDT
     * @param bookAythor
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<BookEntity> getBookByAuthor(String author) {
        LOGGER.info("----------getBookByAuthor START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.BookResponse(");
        sql.append("    be.bookId, ");
        sql.append("    be.bookName, ");
        sql.append("    be.description, ");
        sql.append("    be.language, ");
        sql.append("    be.author, ");
        sql.append("    ce.categoryName, ");
        sql.append("    de.departmentName, ");
        sql.append("    bte.bookTypeName, ");
        sql.append("    be.publicationDate) ");
        sql.append(" FROM ");
        sql.append("    BookEntity be ");
        sql.append(" INNER JOIN ");
        sql.append("    DepartmentEntity de ");
        sql.append(" ON ");
        sql.append("    be.departmentId = de.departmentId ");
        sql.append("  INNER JOIN ");
        sql.append("    BookTypeEntity bte ");
        sql.append(" ON ");
        sql.append("    be.bookType = bte.bookTypeId ");
        sql.append("  INNER JOIN ");
        sql.append("    CategoryEntity ce ");
        sql.append(" ON ");
        sql.append("    be.category = ce.categoryId ");
        sql.append(" WHERE ");
        sql.append("    be.author = :author ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("author", author);
        List<BookEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("----------getBookByAuthor END----------");
        return entity;

    }

    /**
     * getCategoryByName
     * @author: LinhDT
     * @param categoryName
     * @return
     */
    public CategoryEntity getCategoryByName(String categoryName) {
        LOGGER.info("----------getCategoryByName START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    CategoryEntity ce ");
        sql.append(" WHERE ");
        sql.append("    ce.categoryName = :categoryName ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("categoryName", categoryName);
        CategoryEntity categoryEntity = null;
        try {
            categoryEntity = (CategoryEntity) query.getSingleResult();
        } catch (NoResultException e) {
        }

        LOGGER.info("----------getCategoryByName END----------");
        return categoryEntity;
    }

    /**
     * getBookByCategory
     * @author: LinhDT
     * @param category
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<BookEntity> getBookByCategory(String categoryName) {
        LOGGER.info("----------getBookByCategory START----------");

        CategoryEntity categoryEntity = getCategoryByName(categoryName);
        Integer categoryId = null;
        try {
            categoryId = categoryEntity.getCategoryId();
        } catch (NullPointerException e) {
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.BookResponse(");
        sql.append("    be.bookId, ");
        sql.append("    be.bookName, ");
        sql.append("    be.description, ");
        sql.append("    be.language, ");
        sql.append("    be.author, ");
        sql.append("    ce.categoryName, ");
        sql.append("    de.departmentName, ");
        sql.append("    bte.bookTypeName, ");
        sql.append("    be.publicationDate) ");
        sql.append(" FROM ");
        sql.append("    BookEntity be ");
        sql.append(" INNER JOIN ");
        sql.append("    DepartmentEntity de ");
        sql.append(" ON ");
        sql.append("    be.department = de.departmentId ");
        sql.append(" INNER JOIN ");
        sql.append("    BookTypeEntity bte ");
        sql.append(" ON ");
        sql.append("    be.bookType = bte.bookTypeId ");
        sql.append(" INNER JOIN ");
        sql.append("    CategoryEntity ce ");
        sql.append(" ON ");
        sql.append("    be.category = ce.categoryId ");
        sql.append(" WHERE ");
        sql.append("    be.category = :categoryId ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("categoryId", categoryId);
        List<BookEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("----------getBookByCategory END----------");
        return entity;
    }

    /**
     * getBookByPublicationDate
     * @author: LinhDT
     * @param publicationDate
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<BookEntity> getBookByPublicationDate(String publicationDate) {
        LOGGER.info("----------getBookByPublicationDate START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.BookResponse(");
        sql.append("    be.bookId, ");
        sql.append("    be.bookName, ");
        sql.append("    be.description, ");
        sql.append("    be.language, ");
        sql.append("    be.author, ");
        sql.append("    ce.categoryName, ");
        sql.append("    de.departmentName, ");
        sql.append("    be.publicationDate, ");
        sql.append("    be.thumbnail, ");
        sql.append("    be.price) ");
        sql.append(" FROM ");
        sql.append("    BookEntity be ");
        sql.append(" INNER JOIN ");
        sql.append("    DepartmentEntity de ");
        sql.append(" ON ");
        sql.append("    be.departmentId = de.departmentId ");
        sql.append(" INNER JOIN ");
        sql.append("    CategoryEntity ce ");
        sql.append(" ON ");
        sql.append("    be.categoryId = ce.categoryId ");
        sql.append(" WHERE ");
        sql.append("    be.publicationDate = :publicationDate ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("publicationDate", publicationDate);
        List<BookEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("----------getBookByPublicationDate END----------");
        return entity;
    }
    
    @SuppressWarnings("unchecked")
    public List<BookEntity> searchBook(String q) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.BookResponse(");
        sql.append("    be.bookId, ");
        sql.append("    be.bookName, ");
        sql.append("    be.description, ");
        sql.append("    be.language, ");
        sql.append("    be.author, ");
        sql.append("    ce.categoryName, ");
        sql.append("    de.departmentName, ");
        sql.append("    be.publicationDate, ");
        sql.append("    be.thumbnail, ");
        sql.append("	be.rentCost, ");
        sql.append("    be.price, ");
        sql.append("    be.createDate) ");
        sql.append(" FROM ");
        sql.append("    BookEntity be ");
        sql.append(" LEFT JOIN ");
        sql.append("    DepartmentEntity de ");
        sql.append(" ON ");
        sql.append("    be.departmentId = de.departmentId ");
        sql.append(" LEFT JOIN ");
        sql.append("    CategoryEntity ce ");
        sql.append(" ON ");
        sql.append("    be.categoryId = ce.categoryId ");
        sql.append(" WHERE ");
        sql.append(" be.bookName LIKE :searchKey ");
        sql.append(" OR ");
        sql.append(" be.author LIKE :searchKey ");
        sql.append(" OR ");
        sql.append(" be.description LIKE :searchKey");
        sql.append(" OR ");
        sql.append(" ce.categoryName LIKE :searchKey");
        sql.append(" OR ");
        sql.append(" de.departmentName LIKE :searchKey");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("searchKey", "%" + q + "%");
        List<BookEntity> entity = null;
        entity = query.getResultList();
        
        return entity;
    }

	@Override
	public BookEntity updateBook(BookEntity entity) {
		this.entityManager.merge(entity);
		
		return entity;
	}
	
	@Override
	public BookEntity addBook(BookEntity entity) {
		this.entityManager.persist(entity);
		
		return entity;
	}

}
