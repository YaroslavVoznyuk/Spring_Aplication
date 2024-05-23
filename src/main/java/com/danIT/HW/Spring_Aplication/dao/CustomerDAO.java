package com.danIT.HW.Spring_Aplication.dao;

import com.danIT.HW.Spring_Aplication.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {

    Customer save(Customer customer);

    boolean delete(Customer customer);

    void deleteAll(List<Customer> entities);

    void saveAll(List<Customer> entities);

    List<Customer> findAll();

    boolean deleteById(long id);

    Optional<Customer> getOne(long id);
}
