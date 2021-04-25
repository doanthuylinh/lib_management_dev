package com.example.demo.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.bean.TransactionEntity;
import com.example.demo.dao.TransactionDao;


@Repository
@Transactional
public class TransactionDaoImpl implements TransactionDao{

	@Override
	public void addTransaction(TransactionEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTransaction(TransactionEntity entity) {
		// TODO Auto-generated method stub
		
	}
	
}
