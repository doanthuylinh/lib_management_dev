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

import com.example.demo.bean.BookEntity;
import com.example.demo.dao.BookDao;
import com.example.demo.response.BookResponse;
import com.example.demo.utils.DataUtils;

/**
 * [OVERVIEW] Book Data Object Access Implementation.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT             Create new
 * 002       1.1       2021/05/06      LinhDT             Add getBooksByDepartment
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
     * getBookEntityById
     * @author: LinhDT
     * @param bookId
     * @return
     */
    @Override
    public BookEntity getBookEntityById(Integer bookId) {
        LOGGER.info("----------getBookById START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    BookEntity be ");
        sql.append(" WHERE ");
        sql.append("    be.bookId = :bookId ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("bookId", bookId);
        BookEntity entity = null;
        try {
            entity = (BookEntity) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("----------getBookById END----------");
        return entity;
    }

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
        sql.append("    be.publicationDate, ");
        sql.append("    be.thumbnail, ");
        sql.append("    be.rentCost, ");
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
        sql.append("    be.publicationDate, ");
        sql.append("    be.thumbnail, ");
        sql.append("    be.price, ");
        sql.append("    be.rentCost) ");
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
     * getBooksByAuthor
     * @author: LinhDT
     * @param author
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<BookEntity> getBooksByAuthor(String author) {
        LOGGER.info("----------getBooksByAuthor START----------");
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
        sql.append("    be.price, ");
        sql.append("    be.rentCost) ");
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
        sql.append("    be.author = :author ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("author", author);
        List<BookEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("----------getBooksByAuthor END----------");
        return entity;

    }

    /**
     * getBooksByDepartment
     * @author: LinhDT
     * @param departmentId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BookEntity> getBooksByDepartment(Integer departmentId) {
        LOGGER.info("----------getBooksByDepartment START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    BookEntity be ");
        sql.append(" WHERE ");
        sql.append("    be.departmentId = :departmentId ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("departmentId", departmentId);
        List<BookEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("----------getBooksByDepartment END----------");
        return entity;
    }

    /**
     * getBooksByCategory
     * @author: LinhDT
     * @param categoryId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<BookEntity> getBooksByCategory(Integer categoryId) {
        LOGGER.info("----------getBooksByCategory START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    BookEntity be ");
        sql.append(" WHERE ");
        sql.append("    be.categoryId = :categoryId ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("categoryId", categoryId);
        List<BookEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("----------getBooksByCategory END----------");
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
        sql.append("    be.price, ");
        sql.append("    be.rentCost) ");
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
        sql.append("    be.publicationDate = :publicationDate ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("publicationDate", publicationDate);
        List<BookEntity> entity = null;
        entity = query.getResultList();
        LOGGER.info("----------getBookByPublicationDate END----------");
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BookEntity> searchBook(String q, Integer from, Integer limit) {
        boolean isQueryEmpty = DataUtils.isNullOrEmpty(q);
        from = from == null ? 0 : from;
        limit = limit == null ? 1000 : limit;

        StringBuilder sql = new StringBuilder();
        sql.append(" FROM ");
        sql.append("    BookEntity be ");

        if (!isQueryEmpty) {
            sql.append(" WHERE ");
            sql.append(" be.bookName LIKE :searchKey ");
            sql.append(" OR ");
            sql.append(" be.author LIKE :searchKey ");
            sql.append(" OR ");
            sql.append(" be.description LIKE :searchKey");
            sql.append(" OR ");
            sql.append(" be.categoryEntity.categoryName LIKE :searchKey");
            sql.append(" OR ");
            sql.append(" be.departmentEntity.departmentName LIKE :searchKey");
        }

        Query query = this.entityManager.createQuery(sql.toString());
        if (!isQueryEmpty) {
            query.setParameter("searchKey", "%" + q + "%");
        }
        query.setFirstResult(from);
        query.setMaxResults(limit);
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

    @Transactional
    @Override
    public void removeBook(Integer bookId) {

        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM ");
        sql.append("    BookEntity be ");

        sql.append(" WHERE be.bookId = :bookId");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("bookId", bookId);
        query.executeUpdate();
    }

}
