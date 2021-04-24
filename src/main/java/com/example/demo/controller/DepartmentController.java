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
import com.example.demo.service.DepartmentService;
import com.example.demo.utils.ApiValidateException;

/**
 * [OVERVIEW] Department Controller.
 *
 * @author: LinhDT
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/17      LinhDT             Create new
*/
@RestController
@RequestMapping(value = "/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private static final Logger LOGGER = LogManager.getLogger(CategoryController.class);

    /**
     * getListDepartments
     * @author: LinhDT
     * @return
     */
    @RequestMapping(value = "/departments-list", method = RequestMethod.GET)
    public ResponseEntity<ResultBean> getListDepartments() {
        LOGGER.info("----------getListDepartments START----------");
        ResultBean entity = null;
        try {
            entity = departmentService.getListDepartments();
        } catch (ApiValidateException e) {
            return new ResponseEntity<ResultBean>(new ResultBean(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(new ResultBean("500", "Internal server error"), HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("----------getListDepartments END----------");
        return new ResponseEntity<ResultBean>(entity, HttpStatus.OK);
    }
    
    /**
     * addDepartment
     * @author: LinhDT
     * @param data
     * @return
     */
    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public ResponseEntity<ResultBean> addDepartment(@RequestBody String data) {
        LOGGER.info("----------addDepartment START----------");
        ResultBean resultBean = null;
        try {
            resultBean = departmentService.addDepartment(data);
        } catch (ApiValidateException e) {
            resultBean = new ResultBean(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultBean = new ResultBean("500", "Internal server error");
        }
        LOGGER.info("----------addDepartment END----------");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

}
