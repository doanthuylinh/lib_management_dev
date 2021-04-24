/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import com.example.demo.bean.ResultBean;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] Transaction Type Service.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/24      LinhDT             Create new
*/
public interface TransactionTypeService {

    /**
     * addTransactionType
     * @author: LinhDT
     * @param data
     * @return
     * @throws ApiValidateException
     */
    public ResultBean addTransactionType(String data) throws ApiValidateException;
    
    /**
     * updateTransactionType
     * @author: LinhDT
     * @param data
     * @return
     * @throws ApiValidateException
     */
    public ResultBean updateTransactionType(String data) throws ApiValidateException;
    
    /**
     * getListTransactionTypes
     * @author: LinhDT
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getListTransactionTypes() throws ApiValidateException;
}
