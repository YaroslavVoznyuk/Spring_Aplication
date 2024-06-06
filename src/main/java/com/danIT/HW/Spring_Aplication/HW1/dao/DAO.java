package com.danIT.HW.Spring_Aplication.HW1.dao;

import java.util.List;

public interface DAO<T> {
    T save(T obj);
    boolean delete(T obj);
    void deleteAll();
    void saveAll(T t);
    List<T> findAll();
    boolean deleteById(long id);
    T getOne(long id);
}