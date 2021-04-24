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
     * getBookItemByBarcode
     * @author: LinhDT
     * @param barcode
     * @return
     */
    @Override
    public BookItemResponse getBookItemByBarcode(String barcode) {
        LOGGER.info("----------getBookItemByBarcode START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.BookItemResponse( ");
        sql.append("    be.bookId, ");
        sql.append("    be.bookName, ");
        sql.append("    be.description, ");
        sql.append("    be.language, ");
        sql.append("    be.author, ");
        sql.append("    ce.categoryName, ");
        sql.append("    de.departmentName, ");
        sql.append("    bte.bookTypeName, ");
        sql.append("    be.publicationDate, ");
        sql.append("    bie.barcode, ");
        sql.append("    bie.dateOfPurchase, ");
        sql.append("    bie.dateAddedToLibrary, ");
        sql.append("    bie.location, ");
        sql.append("    bie.price, ");
        sql.append("    bse.bookStatusName) ");
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
        sql.append(" INNER JOIN ");
        sql.append("    BookItemEntity bie ");
        sql.append(" ON ");
        sql.append("    be.bookId = bie.bookId ");
        sql.append(" INNER JOIN ");
        sql.append("    BookStatusEntity bse ");
        sql.append(" ON ");
        sql.append("    bie.status = bse.bookStatusId ");
        sql.append(" WHERE ");
        sql.append("    bie.barcode = :barcode ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("barcode", barcode);
        BookItemResponse entity = null;
        try {
            entity = (BookItemResponse) query.getSingleResult();
        } catch (NoResultException e) {

        }
        LOGGER.info("----------getBookItemByBarcode END----------");
        return entity;
    }

    /**
     * getListBookItemByBookId
     * @author: LinhDT
     * @param bookId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BookItemResponse> getListBookItemByBookId(Integer bookId) {
        LOGGER.info("----------getBookItemByBookId START----------");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.example.demo.response.BookItemResponse( ");
        sql.append("    be.bookId, ");
        sql.append("    be.bookName, ");
        sql.append("    be.description, ");
        sql.append("    be.language, ");
        sql.append("    be.author, ");
        sql.append("    ce.categoryName, ");
        sql.append("    de.departmentName, ");
        sql.append("    bte.bookTypeName, ");
        sql.append("    be.publicationDate, ");
        sql.append("    bie.barcode, ");
        sql.append("    bie.dateOfPurchase, ");
        sql.append("    bie.dateAddedToLibrary, ");
        sql.append("    bie.location, ");
        sql.append("    bie.price, ");
        sql.append("    bse.bookStatusName) ");
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
        sql.append(" INNER JOIN ");
        sql.append("    BookItemEntity bie ");
        sql.append(" ON ");
        sql.append("    be.bookId = bie.bookId ");
        sql.append(" INNER JOIN ");
        sql.append("    BookStatusEntity bse ");
        sql.append(" ON ");
        sql.append("    bie.status = bse.bookStatusId ");
        sql.append(" WHERE ");
        sql.append("    bie.bookId = :bookId ");

        Query query = this.entityManager.createQuery(sql.toString());
        query.setParameter("bookId", bookId);
        List<BookItemResponse> entity = null;
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
}
