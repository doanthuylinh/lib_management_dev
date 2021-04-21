/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import java.util.List;

import com.example.demo.bean.BookEntity;
import com.example.demo.bean.CategoryEntity;
import com.example.demo.response.BookResponse;

/**
 * [OVERVIEW] Book Data Object Access.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT       	  Create new
*/
public interface BookDao {

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
     * @param bookAythor
     * @return
     */
    public List<BookEntity> getBookByAuthor(String bookAuthor);

    /**
     * getBookByCategory
     * @author: LinhDT
     * @param category
     * @return
     */
    public List<BookEntity> getBookByCategory(String category);

    /**
     * getCategoryByName
     * @author: LinhDT
     * @param categoryName
     * @return
     */
    public CategoryEntity getCategoryByName(String categoryName);

    /**
     * getBookByPublicationDate
     * @author: LinhDT
     * @param publicationDate
     * @return
     */
    public List<BookEntity> getBookByPublicationDate(String publicationDate);

}
