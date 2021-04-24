/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import java.util.List;

import com.example.demo.bean.DepartmentEntity;

/**
 * [OVERVIEW] Department Data access object.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/17      LinhDT             Create new
 * 002       1.1       2021/04/24      LinhDT             Create Add Department
*/
public interface DepartmentDao {

    /**
     * getListDepartments
     * @author: LinhDT
     * @return
     */
    public List<DepartmentEntity> getListDepartments();
    
    /**
     * addDepartment
     * @author: LinhDT
     * @param departmentEntity
     * @return
     */
    public DepartmentEntity addDepartment(DepartmentEntity departmentEntity);
    
    /**
     * getDepartmentByDepartmentName
     * @author: LinhDT
     * @param departmentName
     * @return
     */
    public DepartmentEntity getDepartmentByDepartmentName(String departmentName);
}
