package com.wjd.ssm.mapper;

import com.wjd.ssm.pojo.Employee;

import java.util.List;

/**
 * Date:2022/7/11
 * Author:ybc
 * Description:
 */
public interface EmployeeMapper {

    /**
     * 查询所有的员工信息
     * @return
     */
    List<Employee> getAllEmployee();
}
