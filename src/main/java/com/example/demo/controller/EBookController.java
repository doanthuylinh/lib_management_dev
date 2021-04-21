///////////////////////////////////////////////////////////////////////////////
////
//// © 2021 IDTU-CS3332IRFA-21TSP
////
///////////////////////////////////////////////////////////////////////////////
//
//package com.example.demo.controller;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.bean.ResultBean;
//import com.example.demo.service.EBookService;
//import com.example.demo.utils.ApiValidateException;
//
///**
// * [OVERVIEW] E-Book Controller.
// *
// * @author: LinhDT
// * @version: 1.0
// * @History
// * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
// * --------------------------------------------------------------------------
// * 001       1.0       2021/04/15      LinhDT       	  Create new
//*/
//@RestController
//@RequestMapping(value = "/api")
//public class EBookController {
//
//    @Autowired
//    private EBookService eBookService;
//
//    private static final Logger LOGGER = LogManager.getLogger(EBookController.class);
//
//    /**
//     * downloadEBookById
//     * @author: LinhDT
//     * @param bookId
//     * @return
//     */
//    @RequestMapping(value = "/ebook/{bookId}", method = RequestMethod.GET)
//    public ResponseEntity<ResultBean> downloadEBookById(@PathVariable Integer bookId) {
//        LOGGER.info("----------downloadEBookById START----------");
//        ResultBean entity = null;
//        try {
//            entity = eBookService.downloadEBookById(bookId);
//        } catch (ApiValidateException e) {
//            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
//        }
//        LOGGER.info("----------downloadEBookById END----------");
//        return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
//    }
//
//    /**
//     * getListEBooks
//     * @author: LinhDT
//     * @return
//     */
//    @RequestMapping(value = "/ebook-list", method = RequestMethod.GET)
//    public ResponseEntity<ResultBean> getListEBooks() {
//        LOGGER.info("----------getListEBooks START----------");
//        ResultBean entity = null;
//        try {
//            entity = eBookService.getListEBooks();
//        } catch (ApiValidateException e) {
//            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
//        }
//        LOGGER.info("----------getListEBooks END----------");
//        return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
//    }
//
//}
