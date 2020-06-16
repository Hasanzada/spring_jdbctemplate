package com.ruslan.service;

import com.ruslan.dao.EmployeeDaoImpl;
import com.ruslan.model.Employee;
import com.ruslan.model.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDaoImpl dao;

    public List<Employee> getAllEmployees(){
        return dao.getAll();
    }

    public Employee getEmployeeById(int id) {
        return dao.getId(id);
    }

    public boolean deleteEmployee(int id) {
        return dao.delete(id);
    }

    public boolean updateEmployee(Employee emp) {
        return dao.update(emp);
    }

    public boolean createEmployee(Employee emp) {
        return dao.create(emp);
    }

}
