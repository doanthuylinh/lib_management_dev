/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import java.util.List;

import com.example.demo.bean.BookEntity;
import com.example.demo.response.BookResponse;

/**
 * [OVERVIEW] Book Data Object Access.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT             Create new
 * 002       1.0       2021/05/06      LinhDT             Add getBooksByDepartment
*/
public interface BookDao {

    /**
     * getBookEntityById
     * @author: LinhDT
     * @param bookId
     * @return
     */
    public BookEntity getBookEntityById(Integer bookId);

    /**
     * getBookById
     * @author: LinhDT
     * @param bookId
     * @return
     */
    public BookResponse getBookById(Integer bookId);

    /**
     * getBookByName
     * @author: LinhDT
     * @param bookName
     * @return
     */
    public BookResponse getBookByName(String bookName);

    /**
     * getBookByAuthor
     * @author: LinhDT
     * @param author
     * @return
     */
    public List<BookEntity> getBooksByAuthor(String author);

    /**
     * getBooksByDepartment
     * @author: LinhDT
     * @param departmentId
     * @return
     */
    public List<BookEntity> getBooksByDepartment(Integer departmentId);

    /**
     * getBookByCategory
     * @author: LinhDT
     * @param categoryId
     * @return
     */
    public List<BookEntity> getBooksByCategory(Integer categoryId);

    /**
     * getBookByPublicationDate
     * @author: LinhDT
     * @param publicationDate
     * @return
     */
    public List<BookEntity> getBookByPublicationDate(String publicationDate);

    /**
     * searchBook
     * @author: LinhDT
     * @param query
     * @return
     */
    public List<BookEntity> searchBook(String query);

    /**
     * searchBook
     * @author: LinhDT
     * @param query
     * @param from
     * @param limit
     * @return
     */
    public List<BookEntity> searchBook(String query, Integer from, Integer limit);

    /**
     * updateBook
     * @author: LinhDT
     * @param entity
     * @return
     */
    public BookEntity updateBook(BookEntity entity);

    /**
     * addBook
     * @author: LinhDT
     * @param entity
     * @return
     */
    public BookEntity addBook(BookEntity entity);

}
