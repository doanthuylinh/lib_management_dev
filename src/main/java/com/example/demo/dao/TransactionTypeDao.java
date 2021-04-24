/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////
package com.example.demo.dao;

import java.util.List;

import com.example.demo.bean.TransactionTypeEntity;

/**
 * [OVERVIEW] Transaction Type Data Object Access.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/24      LinhDT             Create new
*/
public interface TransactionTypeDao {

    /**
     * addTransactionType
     * @author: LinhDT
     * @param entity
     */
    public void addTransactionType(TransactionTypeEntity entity);

    /**
     * updateTransactionType
     * @author: LinhDT
     * @param entity
     */
    public void updateTransactionType(TransactionTypeEntity entity);
    
    /**
     * getTransactionTypeByName
     * @author: LinhDT
     * @param transactionTypeName
     * @return
     */
    public TransactionTypeEntity getTransactionTypeByName(String transactionTypeName);
    
    /**
     * getTransactionTypeById
     * @author: LinhDT
     * @param transactionTypeId
     * @return
     */
    public TransactionTypeEntity getTransactionTypeById(Integer transactionTypeId);
    
    /**
     * getListTransactionTypes
     * @author: LinhDT
     * @return
     */
    public List<TransactionTypeEntity> getListTransactionTypes();
}
