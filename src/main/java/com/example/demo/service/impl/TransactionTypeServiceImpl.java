/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.ResultBean;
import com.example.demo.bean.TransactionTypeEntity;
import com.example.demo.dao.TransactionTypeDao;
import com.example.demo.service.TransactionTypeService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.ConstantColumn;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.MessageUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] Transaction Type Service Implementation.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/24      LinhDT             Create new
*/
@Service
@Transactional
public class TransactionTypeServiceImpl implements TransactionTypeService {

    @Autowired
    private TransactionTypeDao transactionTypeDao;

    private static final Logger LOGGER = LogManager.getLogger(DepartmentServiceImpl.class);

    /**
     * addTransactionType
     * @author: LinhDT
     * @param data
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean addTransactionType(String data) throws ApiValidateException {
        LOGGER.info("----------addTransactionType START----------");
        if (DataUtils.isNullOrEmpty(data)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "Data is not null"));
        }
        JsonObject json = new Gson().fromJson(data, JsonObject.class);

        String transactionTypeName = DataUtils.getAsStringByJson(json, ConstantColumn.TRANSACTION_TYPE_NAME);

        if (DataUtils.isNullOrEmpty(transactionTypeName)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { "Transaction type" }));
        }

        // Check if transaction type exists in DB, if yes throw a message.
        TransactionTypeEntity transactionTypeEntity = transactionTypeDao.getTransactionTypeByName(transactionTypeName);
        if (!Objects.isNull(transactionTypeEntity)) {
            throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { "Transaction type" }));
        }

        TransactionTypeEntity transactionType = new TransactionTypeEntity();
        transactionType.setTransactionTypeName(transactionTypeName);

        transactionTypeDao.addTransactionType(transactionType);

        LOGGER.info("----------addTransactionType END----------");
        return new ResultBean(transactionType, "200", MessageUtils.getMessage("MSG02", new Object[] { "transaction type" }));
    }

    /**
     * updateTransactionType
     * @author: LinhDT
     * @param data
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean updateTransactionType(String data) throws ApiValidateException {
        LOGGER.info("----------updateTransactionType START----------");
        JsonObject json = new Gson().fromJson(data, JsonObject.class);

        Integer transactionTypeId = DataUtils.getAsIntegerByJson(json, ConstantColumn.TRANSACTION_TYPE_ID);
        String transactionTypeName = DataUtils.getAsStringByJson(json, ConstantColumn.TRANSACTION_TYPE_NAME);

        TransactionTypeEntity transactionType = transactionTypeDao.getTransactionTypeById(transactionTypeId);
        transactionType.setTransactionTypeName(transactionTypeName);

        transactionTypeDao.updateTransactionType(transactionType);

        LOGGER.info("----------updateTransactionType END----------");
        return new ResultBean("200", MessageUtils.getMessage("MSG04"));
    }

    /**
     * getListTransactionTypes
     * @author: LinhDT
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean getListTransactionTypes() throws ApiValidateException {
        LOGGER.info("----------getListTransactionTypes START----------");
        List<TransactionTypeEntity> listEntity = transactionTypeDao.getListTransactionTypes();
        if (Objects.isNull(listEntity)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }
        LOGGER.info("----------getListTransactionTypes END----------");
        return new ResultBean(listEntity, "200", MessageUtils.getMessage("MSG01", new Object[] { "list of transaction types" }));
    }

}
