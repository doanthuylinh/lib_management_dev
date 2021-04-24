/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import java.util.List;

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
     * getBookItemByBarcode
     * @author: LinhDT
     * @param barcode
     * @return
     */
    public BookItemResponse getBookItemByBarcode(String barcode);

    /**
    * getListBookItemByBookId
    * @author: LinhDT
    * @param bookId
    * @return
    */
    public List<BookItemResponse> getListBookItemByBookId(Integer bookId);
    
    public long countBookItem(Integer bookId);
}
