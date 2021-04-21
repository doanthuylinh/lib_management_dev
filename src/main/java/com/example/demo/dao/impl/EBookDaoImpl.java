///////////////////////////////////////////////////////////////////////////////
////
//// © 2021 IDTU-CS3332IRFA-21TSP
////
///////////////////////////////////////////////////////////////////////////////
//
//package com.example.demo.dao.impl;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.NoResultException;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.example.demo.bean.EBookEntity;
//import com.example.demo.dao.EBookDao;
//import com.example.demo.response.EBookResponse;
//
///**
// * [OVERVIEW] E-Book Data Object Access Implementation.
// *
// * @author: LinhDT
// * @version: 1.0
// * @History
// * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
// * --------------------------------------------------------------------------
// * 001       1.0       2021/04/17      LinhDT             Create new
//*/
//@Repository
//@Transactional
//public class EBookDaoImpl implements EBookDao {
//
//    @PersistenceContext
//    @Autowired
//    private EntityManager entityManager;
//
//    private static final Logger LOGGER = LogManager.getLogger(EBookDaoImpl.class);
//
//    /**
//     * downloadEBookById
//     * @author: LinhDT
//     * @param bookId
//     * @return
//     */
//    @Override
//    public EBookResponse downloadEBookById(Integer bookId) {
//        LOGGER.info("----------downloadEBookById START----------");
//
//        // Get e-book entity by book ID from database.
//        StringBuilder sql1 = new StringBuilder();
//        sql1.append(" FROM ");
//        sql1.append("    EBookEntity ebe ");
//        sql1.append(" WHERE ");
//        sql1.append("    ebe.bookId = :bookId ");
//
//        Query query1 = this.entityManager.createQuery(sql1.toString());
//        query1.setParameter("bookId", bookId);
//        EBookEntity entity = null;
//        try {
//            entity = (EBookEntity) query1.getSingleResult();
//        } catch (NoResultException e) {
//
//        }
//        // Set the attribute count_download to increase by 1 unit.
//        entity.setCountDownload(entity.getCountDownload() + 1);
//        // Update database.
//        this.entityManager.merge(entity);
//
//        // Get e-book response in order to show to user information of the book getting download.
//        StringBuilder sql = new StringBuilder();
//        sql.append(" SELECT new com.example.demo.response.EBookResponse( ");
//        sql.append("    be.bookId, ");
//        sql.append("    be.bookName, ");
//        sql.append("    be.description, ");
//        sql.append("    be.language, ");
//        sql.append("    be.bookAuthor, ");
//        sql.append("    ce.categoryName, ");
//        sql.append("    de.departmentName, ");
//        sql.append("    be.quantity, ");
//        sql.append("    bte.bookTypeName, ");
//        sql.append("    be.publicationDate, ");
//        sql.append("    ebe.dateAddedToLibrary, ");
//        sql.append("    ebe.countDownload) ");
//        sql.append(" FROM ");
//        sql.append("    BookEntity be ");
//        sql.append(" INNER JOIN ");
//        sql.append("    DepartmentEntity de ");
//        sql.append(" ON ");
//        sql.append("    be.department = de.departmentId ");
//        sql.append(" INNER JOIN ");
//        sql.append("    BookTypeEntity bte ");
//        sql.append(" ON ");
//        sql.append("    be.bookType = bte.bookTypeId ");
//        sql.append(" INNER JOIN ");
//        sql.append("    CategoryEntity ce ");
//        sql.append(" ON ");
//        sql.append("    be.category = ce.categoryId ");
//        sql.append(" INNER JOIN ");
//        sql.append("    EBookEntity ebe ");
//        sql.append(" ON ");
//        sql.append("    be.bookId = ebe.bookId ");
//        sql.append(" WHERE ");
//        sql.append("    ebe.bookId = :bookId ");
//
//        Query query = this.entityManager.createQuery(sql.toString());
//        query.setParameter("bookId", bookId);
//        EBookResponse eBookResponse = null;
//        try {
//            eBookResponse = (EBookResponse) query.getSingleResult();
//        } catch (NoResultException e) {
//        }
//        LOGGER.info("----------downloadEBookById END----------");
//        return eBookResponse;
//    }
//
//    /**
//     * getListEBooks
//     * @author: LinhDT
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public List<EBookEntity> getListEBooks() {
//        LOGGER.info("----------getListEBooks START----------");
//        StringBuilder sql = new StringBuilder();
//        sql.append(" SELECT new com.example.demo.response.EBookResponse( ");
//        sql.append("    be.bookId, ");
//        sql.append("    be.bookName, ");
//        sql.append("    be.description, ");
//        sql.append("    be.language, ");
//        sql.append("    be.bookAuthor, ");
//        sql.append("    ce.categoryName, ");
//        sql.append("    de.departmentName, ");
//        sql.append("    be.quantity, ");
//        sql.append("    bte.bookTypeName, ");
//        sql.append("    be.publicationDate, ");
//        sql.append("    ebe.dateAddedToLibrary, ");
//        sql.append("    ebe.countDownload) ");
//        sql.append(" FROM ");
//        sql.append("    BookEntity be ");
//        sql.append(" INNER JOIN ");
//        sql.append("    DepartmentEntity de ");
//        sql.append(" ON ");
//        sql.append("    be.department = de.departmentId ");
//        sql.append(" INNER JOIN ");
//        sql.append("    BookTypeEntity bte ");
//        sql.append(" ON ");
//        sql.append("    be.bookType = bte.bookTypeId ");
//        sql.append(" INNER JOIN ");
//        sql.append("    CategoryEntity ce ");
//        sql.append(" ON ");
//        sql.append("    be.category = ce.categoryId ");
//        sql.append(" INNER JOIN ");
//        sql.append("    EBookEntity ebe ");
//        sql.append(" ON ");
//        sql.append("    be.bookId = ebe.bookId ");
//        sql.append(" WHERE ");
//        sql.append("    be.bookType = 1 ");
//
//        Query query = this.entityManager.createQuery(sql.toString());
//        List<EBookEntity> entity = null;
//        entity = query.getResultList();
//        LOGGER.info("----------getListEBooks END----------");
//        return entity;
//    }
//}
