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

import com.example.demo.bean.CategoryEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.dao.CategoryDao;
import com.example.demo.service.CategoryService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.MessageUtils;

/**
 * [OVERVIEW] Category Service.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/17      LinhDT       	  Create new
*/
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    private static final Logger LOGGER = LogManager.getLogger(CategoryServiceImpl.class);

    /**
     * getListCategories
     * @author: LinhDT
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean getListCategories() throws ApiValidateException {
        LOGGER.info("----------getListCategories START----------");
        List<CategoryEntity> listEntity = categoryDao.getListCategories();
        if (Objects.isNull(listEntity)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }
        LOGGER.info("----------getListCategories END----------");
        return new ResultBean(listEntity, "200", MessageUtils.getMessage("MSG01", new Object[] { "list of categories" }));
    }
}
