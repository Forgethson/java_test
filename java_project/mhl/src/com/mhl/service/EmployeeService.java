package com.mhl.service;

import com.mhl.dao.EmployeeDAO;
import com.mhl.domain.Employee;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/25
 * 该类完成对 employee 表的各种操作（调用employeeDAO对象来完成）
 */
public class EmployeeService {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    // 完成用户验证功能：根据empId 和 pwd 返回一个employee对象（null为空）
    public Employee getEmployeeByIdAndPwd(String empId, String pwd) {
        return employeeDAO.querySingle("select * from employee where empId=? and pwd=md5(?)", Employee.class, empId, pwd);
    }

}
