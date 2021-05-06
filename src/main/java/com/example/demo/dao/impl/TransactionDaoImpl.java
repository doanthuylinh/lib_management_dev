/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.bean.TransactionEntity;
import com.example.demo.dao.TransactionDao;

/**
 * [OVERVIEW] Transaction Data access object Implementation.
 *
 * @author: LinhDT
 * @version: 1.1
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/25      LinhDT             Create new
*/
@Repository
@Transactional
public class TransactionDaoImpl implements TransactionDao {

    /**
     * addTransaction
     * @author: LinhDT
     * @param entity
     */
    @Override
    public void addTransaction(TransactionEntity entity) {
        // TODO Auto-generated method stub
    }

    /**
     * updateTransaction
     * @author: LinhDT
     * @param entity
     */
    @Override
    public void updateTransaction(TransactionEntity entity) {
        // TODO Auto-generated method stub
    }

}
