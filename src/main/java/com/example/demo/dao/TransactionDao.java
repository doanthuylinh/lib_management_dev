package com.example.demo.dao;

import com.example.demo.bean.TransactionEntity;

public interface TransactionDao {
	public void addTransaction(TransactionEntity entity);
	
	public void updateTransaction(TransactionEntity entity);
}
