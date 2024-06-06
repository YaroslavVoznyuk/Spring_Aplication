package com.danIT.HW.Spring_Aplication.HW2.services;

import com.danIT.HW.Spring_Aplication.HW2.domain.Account;
import com.danIT.HW.Spring_Aplication.HW2.domain.Currency;
import com.danIT.HW.Spring_Aplication.HW2.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW2.domain.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    boolean delete(Customer customer);

    void deleteAll();

    void saveAll(List<Customer> entities);

    List<Customer> findAll();

    boolean deleteById(long id);

    Customer getOne(long id);

    public Customer createACustomer(CustomerDTO customer);

    public Customer changeCustomerData(CustomerDTO customer, Long customerId);

    public boolean deleteCustomer(Long customerId);

    public Account createAnAccountForASpecificCustomer(Long id, Currency currency);

    public boolean deleteTheAccountFromTheCustomer(Long customerId, String accountNumber);
}
