/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.BookEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.dao.BookDao;
import com.example.demo.dao.BookItemDao;
import com.example.demo.exception.ApiValidateException;
import com.example.demo.response.BookResponse;
import com.example.demo.service.BookService;
import com.example.demo.utils.ConstantColumn;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.MessageUtils;
import com.example.demo.utils.ValidateUtils;

/**
 * [OVERVIEW] Book Service Implementation.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT             Create new
 * 002       1.0       2021/05/06      LinhDT             Add getBooksByDepartment
*/
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    
    @Autowired
    private BookItemDao bookItemDao;

    private static final Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);

    /**
     * getBookById
     * @author: LinhDT
     * @return
     */
    @Override
    public ResultBean getBookById(Integer bookId) {
        LOGGER.info("----------getBookById START----------");
        BookResponse entity = bookDao.getBookById(bookId);
        if (Objects.isNull(entity)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }
        LOGGER.info("----------getBookById END----------");
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "book by ID" }));
    }

    /**
     * getBookByName
     * @author: LinhDT
     * @param query
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getBookByName(String query) throws ApiValidateException {
        LOGGER.info("----------getBookByName START----------");

        // Check whether query is null.
        if (DataUtils.isNullOrEmpty(query)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.QUERY_SEARCH }));
        }

        BookResponse entity = bookDao.getBookByName(query);

        if (Objects.isNull(entity)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }

        LOGGER.info("----------getBookByName END----------");
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "book by name" }));
    }

    /**
     * getBooksByAuthor
     * @author: LinhDT
     * @param query
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getBooksByAuthor(String query) throws ApiValidateException {
        LOGGER.info("----------getBookByAuthor START----------");

        // Check whether query is null.
        if (DataUtils.isNullOrEmpty(query)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.QUERY_SEARCH }));
        }

        List<BookEntity> entity = bookDao.getBooksByAuthor(query);
        if (Objects.isNull(entity)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }

        LOGGER.info("----------getBookByAuthor END----------");
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "book(s) by author" }));
    }

    /**
     * getBooksByCategory
     * @author: LinhDT
     * @param query
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getBooksByCategory(Integer query) throws ApiValidateException {
        LOGGER.info("----------getBookByCategory START----------");

        // Check whether query is null.
        if (DataUtils.isNullOrEmpty(query)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.QUERY }));
        }

        List<BookEntity> entity = bookDao.getBooksByCategory(query);
        if (Objects.isNull(entity)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }

        LOGGER.info("----------getBookByCategory END----------");
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "book(s) by category" }));
    }

    /**
     * getBooksByDepartment
     * @author: LinhDT
     * @param departmentId
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean getBooksByDepartment(Integer query) throws ApiValidateException {
        LOGGER.info("----------getBooksByDepartment START----------");

        // Check whether query is null.
        if (DataUtils.isNullOrEmpty(query)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.QUERY }));
        }

        List<BookEntity> entity = bookDao.getBooksByDepartment(query);
        if (Objects.isNull(entity)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }

        LOGGER.info("----------getBooksByDepartment END----------");
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "book(s) by department" }));
    }

    /**
     * getBookByPublicationDate
     * @author: LinhDT
     * @param query
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getBookByPublicationDate(String query) throws ApiValidateException {
        LOGGER.info("--- getBookByPublicationDate START ---");

        // Check whether query is null.
        if (DataUtils.isNullOrEmpty(query)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.QUERY_SEARCH }));
        }

        List<BookEntity> entity = bookDao.getBookByPublicationDate(query);
        if (Objects.isNull(entity)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }

        LOGGER.info("--- getBookByPublicationDate END ---");
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "book(s) by publication date" }));
    }

    /**
     * searchBook
     * @author: LinhDT
     * @param query
     * @return
     * @throws ApiValidateException
     */
    public ResultBean searchBook(String query, Integer from, Integer limit) throws ApiValidateException {

        List<BookEntity> entitys = bookDao.searchBook(query, from, limit);

        if (Objects.isNull(entitys)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }

        return new ResultBean(entitys, "200", MessageUtils.getMessage("MSG01", new Object[] { "book(s) by search book" }));
    }

    @Override
    public ResultBean updateBook(String data) throws ApiValidateException {
        BookEntity book = DataUtils.getEntityByJsonString(data, BookEntity.class);

        ValidateUtils.validateUpdateBook(book);

        return new ResultBean(bookDao.updateBook(book), "200", MessageUtils.getMessage("MSG04", "book"));
    }

    @Override
    public ResultBean addBook(String data) throws ApiValidateException {
        BookEntity book = DataUtils.getEntityByJsonString(data, BookEntity.class);
        book.setCreateDate(new Date());

        ValidateUtils.validateAddBook(book);

        return new ResultBean(bookDao.addBook(book), "201", MessageUtils.getMessage("MSG02", "book"));
    }

	@Override
	public ResultBean removeBook(String data) throws ApiValidateException {
		Integer bookId = DataUtils.getAsIntegerByJson(data, "book_id");
		long countBookItem = bookItemDao.countBookItem(bookId);
		if (countBookItem > 0) {
			throw new ApiValidateException("ERR04", "Book have bookitems");
		}
		
		bookDao.removeBook(bookId);
		
		return new ResultBean("Remove book success!", "200",  MessageUtils.getMessage("MSG04", "book"));
	}

}
