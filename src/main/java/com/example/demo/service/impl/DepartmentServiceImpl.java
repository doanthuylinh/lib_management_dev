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

import com.example.demo.bean.DepartmentEntity;
import com.example.demo.bean.ResultBean;
import com.example.demo.dao.DepartmentDao;
import com.example.demo.service.DepartmentService;
import com.example.demo.utils.ApiValidateException;
import com.example.demo.utils.ConstantColumn;
import com.example.demo.utils.DataUtils;
import com.example.demo.utils.MessageUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * [OVERVIEW] Department Service Implementation.
 *
 * @author: LinhDT
 * @version: 1.1
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2021/04/17      LinhDT       Create new
*/
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    private static final Logger LOGGER = LogManager.getLogger(DepartmentServiceImpl.class);

    /**
     * getListDepartments
     * @author: LinhDT
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean getListDepartments() throws ApiValidateException {
        LOGGER.info("----------getListDepartments START----------");
        List<DepartmentEntity> listEntity = departmentDao.getListDepartments();
        if (Objects.isNull(listEntity)) {
            return new ResultBean("ERR14", MessageUtils.getMessage("ERR14"));
        }
        LOGGER.info("----------getListDepartments END----------");
        return new ResultBean(listEntity, "200", MessageUtils.getMessage("MSG01", new Object[] { "list of departments" }));
    }

    /**
     * addDepartment
     * @author: LinhDT
     * @param data
     * @return
     * @throws ApiValidateException
     */
    @Override
    public ResultBean addDepartment(String data) throws ApiValidateException {
        LOGGER.info("----------addDepartment START----------");
        if (DataUtils.isNullOrEmpty(data)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", "Data is not null"));
        }
        JsonObject json = new Gson().fromJson(data, JsonObject.class);

        String departmentName = DataUtils.getAsStringByJson(json, ConstantColumn.DEPARTMENT_NAME);

        if (DataUtils.isNullOrEmpty(departmentName)) {
            throw new ApiValidateException("ERR04", MessageUtils.getMessage("ERR04", new Object[] { "Department name" }));
        }

        // Check if department exists in DB, if yes throw a message.
        DepartmentEntity departmentEntity = departmentDao.getDepartmentByDepartmentName(departmentName);
        if (!Objects.isNull(departmentEntity)) {
            throw new ApiValidateException("ERR03", MessageUtils.getMessage("ERR03", new Object[] { "Department name" }));
        }

        DepartmentEntity department = new DepartmentEntity();
        department.setDepartmentName(departmentName);

        departmentDao.addDepartment(department);
        LOGGER.info("----------addDepartment END----------");
        return new ResultBean(department, "200", MessageUtils.getMessage("MSG02", ConstantColumn.DEPARTMENT));
    }

}
