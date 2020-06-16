package com.ruslan.dao;

import java.util.List;

 public interface DAO<A> {

    A getId(int id);

    List<A> getAll();

    boolean delete(int id);

    boolean update(A a);

    boolean create(A a);

}
