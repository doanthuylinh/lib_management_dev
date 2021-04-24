/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import com.example.demo.bean.ResultBean;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] Category Service.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/17      LinhDT       	  Create new
 * 002       1.1       2021/04/24      LinhDT             Create Add Category
*/
public interface CategoryService {

    /**
     * getListCategories
     * @author: LinhDT
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getListCategories() throws ApiValidateException;

    /**
     * addCategory
     * @author: LinhDT
     * @param data
     * @return
     * @throws ApiValidateException
     */
    public ResultBean addCategory(String data) throws ApiValidateException;
}
