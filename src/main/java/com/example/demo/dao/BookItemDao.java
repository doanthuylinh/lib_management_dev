/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import java.util.List;

import com.example.demo.bean.BookItemEntity;
import com.example.demo.response.BookItemResponse;

/**
 * [OVERVIEW] Book Item Data Object Access.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT       	  Create new
*/
public interface BookItemDao {

    /**
    * getListBookItemByBookId
    * @author: LinhDT
    * @param bookId
    * @return
    */
    public List<BookItemEntity> getListBookItemByBookId(Integer bookId);

    /**
     * countBookItem
     * @author: LinhDT
     * @param bookId
     * @return
     */
    public long countBookItem(Integer bookId);

    /**
     * updateBookItem
     * @author: LinhDT
     * @param entity
     * @return
     */
    public BookItemEntity updateBookItem(BookItemEntity entity);
    
    public BookItemEntity addBookItem(BookItemEntity entity);
}
