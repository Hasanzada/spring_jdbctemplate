package com.ruslan.dao;

import com.ruslan.model.Employee;
import com.ruslan.model.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl implements DAO<Employee> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_EMPLOYEE = "SELECT * FROM EMPLOYEES WHERE ID = ?";
    private final String SQL_DELETE_EMPLOYEE = "DELETE FROM EMPLOYEES WHERE ID = ?";
    private final String SQL_UPDATE_EMPLOYEE = "UPDATE EMPLOYEES SET NAME = ?, SURNAME = ?," +
            "AGE = ?, SALARY = ? WHERE ID = ?";
    private final String SQL_GET_ALL = "SELECT * FROM EMPLOYEES";
    private final String SQL_INSERT_EMPLOYEE = "INSERT INTO EMPLOYEES(NAME, SURNAME, AGE, SALARY) " +
            "VALUES(?,?,?,?)";


    @Override
    public Optional<Employee> getId(int id) {
        return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_EMPLOYEE, new Object[]{id},
                new BeanPropertyRowMapper<Employee>(Employee.class)));
    }

    @Override
    public List<Employee> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new EmployeeMapper());
    }

    @Override
    public boolean delete(int id) {
        Employee emp = this.getId(id).get();
        return jdbcTemplate.update(SQL_DELETE_EMPLOYEE, emp.getId()) > 0;
    }

    @Override
    public boolean update(Employee emp) {
        return jdbcTemplate.update(SQL_UPDATE_EMPLOYEE, emp.getName(), emp.getSurname(),
                emp.getAge(), emp.getSalary(), emp.getId()) > 0;
    }

    @Override
    public boolean create(Employee emp) {
        return jdbcTemplate.update(SQL_INSERT_EMPLOYEE, emp.getName(), emp.getSurname(),
                emp.getAge(), emp.getSalary()) > 0;
    }
}
