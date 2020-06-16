package com.ruslan.controller;

import com.ruslan.model.Employee;
import com.ruslan.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private String fmt(String f, Object... as){
        return String.format(f, as);
    }

    @Autowired
    EmployeeService service;

    /**
     *http://localhost:8080/employee/all
     */
    @GetMapping("all")
    public List<Employee> allEmployeesGet(){
        log.info(fmt("GET -> /all %s", service.getAllEmployees()));
        return service.getAllEmployees();
    }

    /**
     *http://localhost:8080/employee/{id}
     */
    @GetMapping("/{id}")
    public Employee employeeById(@PathVariable int id) {
        log.info(fmt("GET -> /{id} %s", service.getEmployeeById(id)));
        return service.getEmployeeById(id);
    }

    @PostMapping
    public void insertEmployee(@RequestBody Employee employee){
        boolean create = service.createEmployee(employee);
        if(create == true){
            log.info("create new employee");
        }else {
            log.info("doesn't create any employee");
        }
    }

    @PutMapping
    public void updateEmployee(@RequestBody Employee employee){
        boolean update = service.updateEmployee(employee);
        if(update == true){
            log.info("update new employee");
        }else {
            log.info("doesn't update any employee");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id){
        boolean delete = service.deleteEmployee(id);
        if(delete == true){
            log.info("delete employee");
        }else {
            log.info("doesn't delete any employee");
        }
    }

    @ExceptionHandler({Exception.class})
    public void handleException(){
        log.info("exception");
    }

}
