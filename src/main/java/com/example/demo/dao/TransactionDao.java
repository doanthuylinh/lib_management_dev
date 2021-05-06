/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao;

import com.example.demo.bean.TransactionEntity;

/**
 * [OVERVIEW] Transaction Data access object.
 *
 * @author: LinhDT
 * @version: 1.1
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/25      LinhDT             Create new
*/
public interface TransactionDao {

    /**
     * addTransaction
     * @author: LinhDT
     * @param entity
     */
    public void addTransaction(TransactionEntity entity);

    /**
     * updateTransaction
     * @author: LinhDT
     * @param entity
     */
    public void updateTransaction(TransactionEntity entity);
}
