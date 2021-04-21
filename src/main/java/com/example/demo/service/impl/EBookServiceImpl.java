///////////////////////////////////////////////////////////////////////////////
////
//// © 2021 IDTU-CS3332IRFA-21TSP
////
///////////////////////////////////////////////////////////////////////////////
//
//package com.example.demo.service.impl;
//
//import java.util.List;
//import java.util.Objects;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.example.demo.bean.EBookEntity;
//import com.example.demo.bean.ResultBean;
//import com.example.demo.dao.EBookDao;
//import com.example.demo.response.EBookResponse;
//import com.example.demo.service.EBookService;
//import com.example.demo.utils.ApiValidateException;
//import com.example.demo.utils.MessageUtils;
//
///**
// * [OVERVIEW] EBookServiceImpl.
// *
// * @author: LinhDT
// * @version: 1.0
// * @History
// * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
// * --------------------------------------------------------------------------
// * 001       1.0       2021/04/15      LinhDT       	  Create new
//*/
//@Service
//@Transactional
//public class EBookServiceImpl implements EBookService {
//
//    @Autowired
//    private EBookDao eBookDao;
//
//    private static final Logger LOGGER = LogManager.getLogger(EBookServiceImpl.class);
//
//    /**
//     * downloadEBookById
//     * @author: LinhDT
//     * @param bookId
//     * @return
//     * @throws ApiValidateException
//     */
//    @Override
//    public ResultBean downloadEBookById(Integer bookId) throws ApiValidateException {
//        LOGGER.info("----------downloadEBookById START----------");
//        EBookResponse entity = eBookDao.downloadEBookById(bookId);
//        if (Objects.isNull(entity)) {
//            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
//        }
//        LOGGER.info("----------downloadEBookById END----------");
//        return new ResultBean(entity, "200", MessageUtils.getMessage("MSG05", new Object[] { "Download E-Book by Book ID" }));
//    }
//
//    /**
//     * getListEBooks
//     * @author: LinhDT
//     * @return
//     * @throws ApiValidateException
//     */
//    @Override
//    public ResultBean getListEBooks() throws ApiValidateException {
//        LOGGER.info("----------getListEBooks START----------");
//        List<EBookEntity> listEntity = eBookDao.getListEBooks();
//        if (Objects.isNull(listEntity)) {
//            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
//        }
//        LOGGER.info("----------getListEBooks END----------");
//        return new ResultBean(listEntity, "200", MessageUtils.getMessage("MSG01", new Object[] { "list of E-Books" }));
//    }
//}
