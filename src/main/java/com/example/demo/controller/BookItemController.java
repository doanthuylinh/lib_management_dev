/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ResultBean;
import com.example.demo.service.BookItemService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.ResponseUtils;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] Book Item Controller.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT       	  Create new
*/
@RestController
@RequestMapping(value = "/api")
public class BookItemController {

    @Autowired
    private BookItemService bookItemService;

    private static final Logger LOGGER = LogManager.getLogger(BookItemController.class);

    /**
     * getBookItemByBarcode
     * @author: LinhDT
     * @param barcode
     * @return
     */
    @RequestMapping(value = "/getbookitembybarcode", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getBookItemByBarcode(@RequestBody String barcode) {
        LOGGER.info("----------getBookItemByBarcode START----------");
        ResultBean resultBean = null;
        try {
            resultBean = bookItemService.getBookItemByBarcode(barcode);
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("----------getBookItemByBarcode END----------");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * getListBookItemByBookId
     * @author: LinhDT
     * @param bookId
     * @return
     */
    @RequestMapping(value = "/getlistbookitembybookid", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getListBookItemByBookId(@RequestBody String bookId) {
        LOGGER.info("----------getListBookItemByBookId START----------");
        ResultBean resultBean = null;
        try {
            resultBean = bookItemService.getListBookItemByBookId(bookId);
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("----------getListBookItemByBookId END----------");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/countBookItem", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> countBookItem(@RequestParam("bookId") Integer bookId) {
    	LOGGER.info("----------countBookItem START----------");
        ResultBean resultBean = null;
        try {
            resultBean = bookItemService.countBookItem(bookId);
        } catch (ApiValidateException e) {
        	resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
        LOGGER.info("----------countBookItem END----------");
        return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));

    }
    
    @RequestMapping(value = "/bookitem", method = RequestMethod.POST)
    public ResponseEntity<ResultBean> addBookItem(@RequestBody String data) {
    	LOGGER.info("----------addBookItem START----------");
    	ResultBean resultBean = null;
    	try {
            resultBean = bookItemService.addBookItem(data);
        } catch (ApiValidateException e) {
        	resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
    	
    	LOGGER.info("----------addBookItem END----------");
    	return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }
    
    @RequestMapping(value = "/bookitem", method = RequestMethod.PUT)
    public ResponseEntity<ResultBean> updateBookItem(@RequestBody String data) {
    	LOGGER.info("----------updateBookItem START----------");
    	ResultBean resultBean = null;
    	try {
            resultBean = bookItemService.updateBookItem(data);
        } catch (ApiValidateException e) {
        	resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
    	
    	LOGGER.info("----------updateBookItem END----------");
    	return new ResponseEntity<ResultBean>(resultBean, ResponseUtils.getResponseStatus(resultBean));
    }
}
