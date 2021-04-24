/////////////////////////////////////////////////////////////////////////////
//
// � 2021 IDTU-CS3332IRFA-21TSP
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
import com.example.demo.dao.BookItemDao;
import com.example.demo.response.BookItemResponse;
import com.example.demo.service.BookItemService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.ConstantColumn;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.MessageUtils;
import com.example.demo.utils.Regex;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] Book Item Service Implementation.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/15      LinhDT       	  Create new
*/
@Service
@Transactional
public class BookItemServiceImpl implements BookItemService {

    @Autowired
    private BookItemDao bookItemDao;

    private static final Logger LOGGER = LogManager.getLogger(BookItemServiceImpl.class);

    /**
     * getBookItemByBarcode
     * @author: LinhDT
     * @param json
     * @return
     * @throws ApiValidateException
     */
    public ResultBean getBookItemByBarcode(String json) throws ApiValidateException {
        LOGGER.info("----------getBookItemByBarcode START----------");
        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);

        //Check whether barcode is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.BARCODE)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.BARCODE }));
        }

        // Get barcode and check pattern.
        String barcode = DataUtils.getAsStringByJson(jObject, ConstantColumn.BARCODE);
        if (!barcode.matches(Regex.BARCODE_PATTERN)) {
            throw new ApiValidateException("ERR15", MessageUtils.getMessage("ERR15"));
        }

        BookItemResponse bookItemResponse = bookItemDao.getBookItemByBarcode(barcode);
        // Check whether Book item got by barcode exists in DB, if not, throw message.
        if (Objects.isNull(bookItemResponse)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }

        LOGGER.info("----------getBookItemByBarcode END----------");
        return new ResultBean(bookItemResponse, "200", MessageUtils.getMessage("MSG01", new Object[] { "book item by barcode" }));
    }

    /**
     * getListBookItemByBookId
     * @author: LinhDT
     * @param json
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean getListBookItemByBookId(String json) throws ApiValidateException {
        LOGGER.info("----------getListBookItemByBookId START----------");

        JsonObject jObject = new Gson().fromJson(json, JsonObject.class);

        //Check whether book ID is null.
        if (DataUtils.isNullWithMemberNameByJson(jObject, ConstantColumn.BOOK_ID)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { ConstantColumn.BOOK_ID }));
        }

        // Get book ID and check pattern.
        Integer bookId = DataUtils.getAsIntegerByJson(jObject, ConstantColumn.BOOK_ID);

        List<BookItemResponse> listBookItemResponse = bookItemDao.getListBookItemByBookId(bookId);
        if (Objects.isNull(listBookItemResponse)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }

        LOGGER.info("----------getListBookItemByBookId END----------");
        return new ResultBean(listBookItemResponse, "200", MessageUtils.getMessage("MSG01", new Object[] { "list of book items by book ID" }));
    }

    public ResultBean countBookItem(Integer bookId) throws ApiValidateException {
    	Long count = bookItemDao.countBookItem(bookId);
    	return new ResultBean(count, "200", MessageUtils.getMessage("MSG01", new Object[] { "countBookItem by book ID" }));
    }
}
