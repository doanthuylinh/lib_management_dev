/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import com.example.demo.bean.ResultBean;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] Book Service.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT       	  Create new
*/
public interface BookService {

    /**
     * getBookById
     * @author: LinhDT
     * @return
     */
    public ResultBean getBookById(Integer bookId);

    /**
     * getBookByName
     * @author: LinhDT
     * @param bookName
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getBookByName(String bookName) throws ApiValidateException;

    /**
     * getBooksByAuthor
     * @author: LinhDT
     * @param author
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getBooksByAuthor(String author) throws ApiValidateException;

    /**
     * getBooksByCategory
     * @author: LinhDT
     * @param category
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getBooksByCategory(String category) throws ApiValidateException;

    /**
    * getBookByPublicationDate
    * @author: LinhDT
    * @param publicationDate
    * @return
    * @throws ApiValidateException
    */
    public ResultBean getBookByPublicationDate(String publicationDate) throws ApiValidateException;
    
    /**
     * searchBook
     * @author: LinhDT
     * @param data
     * @return
     * @throws ApiValidateException
     */
    public ResultBean searchBook(String data) throws ApiValidateException;
    
    public ResultBean addBook(String data) throws ApiValidateException;
    
    public ResultBean updateBook(String data) throws ApiValidateException;
}
