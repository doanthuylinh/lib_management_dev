/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service;

import com.example.demo.bean.ResultBean;
import com.example.demo.exception.ApiValidateException;

/**
 * [OVERVIEW] Department Service.
 *
 * @author: LinhDT
 * @version: 1.1
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/17      LinhDT             Create new
 * 002       1.1       2021/04/24      LinhDT             Create Add Department
*/
public interface DepartmentService {

    /**
     * getListDepartments
     * @author: LinhDT
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getListDepartments() throws ApiValidateException;

    /**
     * addDepartment
     * @author: LinhDT
     * @param data
     * @return
     * @throws ApiValidateException
     */
    public ResultBean addDepartment(String data) throws ApiValidateException;
}
