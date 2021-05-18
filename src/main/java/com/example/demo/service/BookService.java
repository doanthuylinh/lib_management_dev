/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import com.example.demo.bean.ResultBean;
import com.example.demo.exception.ApiValidateException;

/**
 * [OVERVIEW] Book Service.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT             Create new
 * 002       1.0       2021/05/06      LinhDT             Add getBooksByDepartment
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
     * @param categoryId
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getBooksByCategory(Integer categoryId) throws ApiValidateException;
    
    /**
     * getBooksByDepartment
     * @author: LinhDT
     * @param departmentId
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getBooksByDepartment(Integer departmentId) throws ApiValidateException;

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
    public ResultBean searchBook(String data, Integer from, Integer limit) throws ApiValidateException;
    
    /**
     * addBook
     * @author: LinhDT
     * @param data
     * @return
     * @throws ApiValidateException
     */
    public ResultBean addBook(String data) throws ApiValidateException;
    
    /**
     * updateBook
     * @author: LinhDT
     * @param data
     * @return
     * @throws ApiValidateException
     */
    public ResultBean updateBook(String data) throws ApiValidateException;
    
    public ResultBean removeBook(String data) throws ApiValidateException;
}
