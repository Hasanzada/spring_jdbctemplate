package com.ruslan.dao;

import com.ruslan.model.Employee;

import java.util.List;
import java.util.Optional;

public interface DAO<A> {

    Optional<Employee> getId(int id);

    List<A> getAll();

    boolean delete(int id);

    boolean update(A a);

    boolean create(A a);

}
