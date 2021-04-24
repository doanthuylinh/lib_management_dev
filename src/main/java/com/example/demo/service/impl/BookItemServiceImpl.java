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

import com.example.demo.bean.BookItemEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.dao.BookItemDao;
import com.example.demo.response.BookItemResponse;
import com.example.demo.service.BookItemService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.MessageUtils;

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
     * getListBookItemByBookId
     * @author: LinhDT
     * @param json
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean getListBookItemByBookId(Integer bookId) throws ApiValidateException {
        LOGGER.info("----------getListBookItemByBookId START----------");

        List<BookItemEntity> listBookItem = bookItemDao.getListBookItemByBookId(bookId);
        if (Objects.isNull(listBookItem)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }

        LOGGER.info("----------getListBookItemByBookId END----------");
        return new ResultBean(listBookItem, "200", MessageUtils.getMessage("MSG01", new Object[] { "list of book items by book ID" }));
    }

    /**
     * countBookItem
     * @author: LinhDT
     * @param bookId
     * @return
     * @throws ApiValidateException
     */
    public ResultBean countBookItem(Integer bookId) throws ApiValidateException {
        Long count = bookItemDao.countBookItem(bookId);
        return new ResultBean(count, "200", MessageUtils.getMessage("MSG01", new Object[] { "countBookItem by book ID" }));
    }

	@Override
	public ResultBean addBookItem(String data) throws ApiValidateException {
		// TODO Auto-generated method stub
		return null;
	}
}
