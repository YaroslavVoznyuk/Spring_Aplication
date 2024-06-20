package com.danIT.HW.Spring_Aplication.HW5.services;

import com.danIT.HW.Spring_Aplication.HW5.domain.Account;
import com.danIT.HW.Spring_Aplication.HW5.domain.Currency;
import com.danIT.HW.Spring_Aplication.HW5.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW5.domain.dto.CustomerDTORequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer save(Customer customer);

    void delete(Customer customer);

    void deleteAll();

    void saveAll(List<Customer> entities);

    public Page<Customer> findAll(Integer from, Integer to, Pageable pageable);

    void deleteById(long id);

    Customer getOne(long id);

    public Customer createACustomer(CustomerDTORequest customer);

    public Customer changeCustomerData(CustomerDTORequest customer, Long customerId);

    public Account createAnAccountForASpecificCustomer(Long id, Currency currency);

    public boolean deleteTheAccountFromTheCustomer(Long customerId, UUID accountNumber);

}
