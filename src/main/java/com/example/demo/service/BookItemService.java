/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import com.example.demo.bean.ResultBean;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] Book Item Service.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT       	  Create new
*/
public interface BookItemService {

    /**
     * getListBookItemByBookId
     * @author: LinhDT
     * @param bookId
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getListBookItemByBookId(Integer bookId) throws ApiValidateException;
    
    /**
     * countBookItem
     * @author: LinhDT
     * @param bookId
     * @return
     * @throws ApiValidateException
     */
    public ResultBean countBookItem(Integer bookId) throws ApiValidateException;
}
