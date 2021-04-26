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
import com.example.demo.exception.ApiValidateException;
import com.example.demo.service.CategoryService;
import com.example.demo.utils.ConstantColumn;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.MessageUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] Category Service.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/17      LinhDT       	  Create new
 * 002       1.1       2021/04/24      LinhDT             Create Add Category
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

    /**
     * addCategory
     * @author: LinhDT
     * @param data
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean addCategory(String data) throws ApiValidateException {
        if (DataUtils.isNullOrEmpty(data)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "Data is not null"));
        }

        JsonObject json = new Gson().fromJson(data, JsonObject.class);

        String categoryName = DataUtils.getAsStringByJson(json, ConstantColumn.CATEGORY_NAME);

        if (DataUtils.isNullOrEmpty(categoryName)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { "Category name" }));
        }

        // Check if category exists in DB, if yes throw a message.
        CategoryEntity categoryEntity = categoryDao.getCategoryByCategoryName(categoryName);
        if (!Objects.isNull(categoryEntity)) {
            throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { "Category name" }));
        }

        CategoryEntity category = new CategoryEntity();
        category.setCategoryName(categoryName);

        categoryDao.addCategory(category);

        return new ResultBean(category, "200", MessageUtils.getMessage("MSG02", ConstantColumn.CATEGORY));
    }
}
