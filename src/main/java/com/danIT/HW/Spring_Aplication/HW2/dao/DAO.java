package com.danIT.HW.Spring_Aplication.HW2.dao;

import com.danIT.HW.Spring_Aplication.HW2.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    T save(T obj);
    boolean delete(T obj);
    void deleteAll();
    void saveAll(List<T> entities);
    List<T> findAll();
    boolean deleteById(long id);
    T getOne(long id);
}