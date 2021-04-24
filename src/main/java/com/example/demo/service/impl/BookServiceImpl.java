/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
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
import com.example.demo.response.BookResponse;
import com.example.demo.service.BookService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.ConstantColumn;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.MessageUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] Book Service Implementation.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT             Create new
*/
@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

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
    public ResultBean getBooksByCategory(String query) throws ApiValidateException {
        LOGGER.info("----------getBookByCategory START----------");

        // Check whether query is null.
        if (DataUtils.isNullOrEmpty(query)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.QUERY_SEARCH }));
        }

        List<BookEntity> entity = bookDao.getBooksByCategory(query);
        if (Objects.isNull(entity)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }

        LOGGER.info("----------getBookByCategory END----------");
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "book(s) by category" }));
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
    public ResultBean searchBook(String query) throws ApiValidateException {
        LOGGER.info("--- Search Book START ---");

        // Check whether query is null.
        if (DataUtils.isNullOrEmpty(query)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.QUERY_SEARCH }));
        }

        List<BookEntity> entity = bookDao.searchBook(query);
        if (Objects.isNull(entity)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }

        LOGGER.info("--- Search Book END ---");
        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG01", new Object[] { "book(s) by search book" }));
    }

    public ResultBean updateBook(String data) throws ApiValidateException {
        JsonObject json = new Gson().fromJson(data, JsonObject.class);

        Integer bookId = DataUtils.getAsIntegerByJson(json, "book_id");
        String bookName = DataUtils.getAsStringByJson(json, "book_name");
        String description = DataUtils.getAsStringByJson(json, "description");
        String language = DataUtils.getAsStringByJson(json, "language");
        String author = DataUtils.getAsStringByJson(json, "author");
        Integer categoryId = DataUtils.getAsIntegerByJson(json, "category_id");
        Integer departmentId = DataUtils.getAsIntegerByJson(json, "department_id");
        String publicationDate = DataUtils.getAsStringByJson(json, "publication_date");
        String thumbnail = DataUtils.getAsStringByJson(json, "thumbnail");
        Double price = DataUtils.getAsDoubleByJson(json, "price");
        Double rentCost = DataUtils.getAsDoubleByJson(json, "rent_cost");

        BookEntity book = bookDao.getBookEntityById(bookId);
        book.setBookName(bookName);
        book.setDescription(description);
        book.setLanguage(language);
        book.setAuthor(author);
        book.setCategoryId(categoryId);
        book.setDepartmentId(departmentId);
        book.setPublicationDate(publicationDate);
        book.setThumbnail(thumbnail);
        book.setPrice(price);
        book.setRentCost(rentCost);

        return new ResultBean(bookDao.updateBook(book), "200", MessageUtils.getMessage("MSG04", "book"));
    }

    @Override
    public ResultBean addBook(String data) throws ApiValidateException {
        if (DataUtils.isNullOrEmpty(data)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "Data is not null"));
        }

        JsonObject json = new Gson().fromJson(data, JsonObject.class);

        String bookName = DataUtils.getAsStringByJson(json, "book_name");
        String description = DataUtils.getAsStringByJson(json, "description");
        String language = DataUtils.getAsStringByJson(json, "language");
        String author = DataUtils.getAsStringByJson(json, "author");
        Integer categoryId = DataUtils.getAsIntegerByJson(json, "category_id");
        Integer departmentId = DataUtils.getAsIntegerByJson(json, "department_id");
        String publicationDate = DataUtils.getAsStringByJson(json, "publication_date");
        String thumbnail = DataUtils.getAsStringByJson(json, "thumbnail");
        Double price = DataUtils.getAsDoubleByJson(json, "price");
        Double rentCost = DataUtils.getAsDoubleByJson(json, "rent_cost");

        if (DataUtils.isNullOrEmpty(bookName)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "book_name"));
        }

        if (DataUtils.isNullOrEmpty(description)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "description"));
        }

        if (DataUtils.isNullOrEmpty(author)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "author"));
        }

        BookEntity book = new BookEntity();
        book.setBookName(bookName);
        book.setDescription(description);
        book.setLanguage(language);
        book.setAuthor(author);
        book.setCategoryId(categoryId);
        book.setDepartmentId(departmentId);
        book.setPublicationDate(publicationDate);
        book.setThumbnail(thumbnail);
        book.setPrice(price);
        book.setRentCost(rentCost);
        book.setCreateDate(new Date());

        bookDao.addBook(book);

        return new ResultBean(book, "201", MessageUtils.getMessage("MSG02", "book"));
    }
}
