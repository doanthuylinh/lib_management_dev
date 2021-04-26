/////////////////////////////////////////////////////////////////////////////
//
// © 2021 IDTU-CS3332IRFA-21TSP
//
/////////////////////////////////////////////////////////////////////////////

package com.example.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ResultBean;
import com.example.demo.exception.ApiValidateException;
import com.example.demo.service.TransactionTypeService;
import com.example.demo.utils.ResponseUtils;

/**
 * [OVERVIEW] Transaction Type Controller.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/24      LinhDT             Create new
*/
@RestController
@RequestMapping(value = "/api")
public class TransactionTypeController {

    @Autowired
    private TransactionTypeService transactionTypeService;

    private static final Logger LOGGER = LogManager.getLogger(TransactionTypeController.class);

    /**
     * addTransactionType
     * @author: LinhDT
     * @param data
     * @return
     */
    @RequestMapping(value = "/transactiontype", method = RequestMethod.POST)
    public ResponseEntity<ResultBean> addTransactionType(@RequestBody String data) {
        LOGGER.info("--- addTransactionType START ---");
        ResultBean resultBean = null;
        try {
            resultBean = transactionTypeService.addTransactionType(data);
        } catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
        LOGGER.info("--- addTransactionType END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }

    /**
     * updateTransactionType
     * @author: LinhDT
     * @param data
     * @return
     */
    @RequestMapping(value = "/transactiontype", method = RequestMethod.PUT)
    public ResponseEntity<ResultBean> updateTransactionType(@RequestBody String data) {
        LOGGER.info("--- updateTransactionType START ---");
        ResultBean resultBean = null;
        try {
            resultBean = transactionTypeService.updateTransactionType(data);
        } catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
        LOGGER.info("--- updateTransactionType END ---");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }
    
    /**
     * getListTransactionTypes
     * @author: LinhDT
     * @return
     */
    @RequestMapping(value = "/transaction-types-list", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getListTransactionTypes() {
        LOGGER.info("----------getListTransactionTypes START----------");
        ResultBean entity = null;
        try {
            entity = transactionTypeService.getListTransactionTypes();
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("----------getListTransactionTypes END----------");
        return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
    }

}
