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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ResultBean;
import com.example.demo.service.CategoryService;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] Category Controller.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/17      LinhDT       	  Create new
*/
@RestController
@RequestMapping(value = "/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    private static final Logger LOGGER = LogManager.getLogger(CategoryController.class);

    /**
     * getListCategories
     * @author: LinhDT
     * @return
     */
    @RequestMapping(value = "/categories-list", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getListCategories() {
        LOGGER.info("----------getListCategories START----------");
        ResultBean entity = null;
        try {
            entity = categoryService.getListCategories();
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("----------getListCategories END----------");
        return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
    }

}
