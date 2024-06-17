package com.danIT.HW.Spring_Aplication.HW3.services;

import com.danIT.HW.Spring_Aplication.HW3.dao.CollectionAccountJPA;
import com.danIT.HW.Spring_Aplication.HW3.dao.CollectionCustomerJPA;
import com.danIT.HW.Spring_Aplication.HW3.domain.Account;
import com.danIT.HW.Spring_Aplication.HW3.domain.Currency;
import com.danIT.HW.Spring_Aplication.HW3.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW3.domain.dto.CustomerDTORequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DefaultCustomerService implements CustomerService {

    private final CollectionCustomerJPA customerDAO;
    private final CollectionAccountJPA accountDAO;
    private final DefaultAccountService defaultAccountService;

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Customer save(Customer customer) {
        return customerDAO.save(customer);
    }

    @Override
    public void delete(Customer customer) {
         customerDAO.delete(customer);
    }

    @Override
    public void deleteAll() {
        customerDAO.deleteAll();
    }

    @Override
    public void saveAll(List<Customer> entities) {
        customerDAO.saveAll(entities);
    }

    @Override
    public Page<Customer> findAll(Integer from, Integer to, Pageable pageable) {
        return customerDAO.findAllInRange(from, to, pageable);
    }

    @Override
    public void deleteById(long id) {
         customerDAO.deleteById(id);
    }

    @Override
    public Customer getOne(long id) {
        return customerDAO.getOne(id);
    }


    public Customer createACustomer(CustomerDTORequest customer) {
        Customer newCustomer = new Customer(customer.getName(), customer.getEmail(), customer.getAge(), customer.getPassword(), customer.getPhoneNumber());

        return update(newCustomer);
    }

    public Customer changeCustomerData(CustomerDTORequest customer, Long customerId) {
        Customer currentCustomer = customerDAO.getOne(customerId);
        if (getOne(customerId) != null) {
            currentCustomer.setName(customer.getName());
            currentCustomer.setEmail(customer.getEmail());
            currentCustomer.setAge(customer.getAge());

            update(currentCustomer);

            return getOne(customerId);
        } else {
            throw new RuntimeException();
        }
    }


    public Account createAnAccountForASpecificCustomer(Long id, Currency currency) {

        if (customerDAO.getOne(id) != null) {
            Customer customer = customerDAO.getOne(id);
            Account account = new Account(currency, customer);
            List<Account> customerAccounts = customer.getAccounts();
            customerAccounts.add(account);

            return defaultAccountService.update(account);
        } else {
            throw new IllegalArgumentException();
        }


    }

    public boolean deleteTheAccountFromTheCustomer(Long customerId, UUID accountNumber) {

        if (customerDAO.getOne(customerId) != null
                && accountDAO.findByNumber(accountNumber) != null) {
            Customer customer = customerDAO.getOne(customerId);
            if (customer.getAccounts().contains(accountDAO.findByNumber(accountNumber))) {
                customer.getAccounts().remove(accountDAO.findByNumber(accountNumber));
                accountDAO.delete(accountDAO.findByNumber(accountNumber));
                return true;
            } else {
                return false;
            }

        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Customer update(Customer customer) {
        if (customer.getId() == null) {
            entityManager.persist(customer);
        } else {
            entityManager.merge(customer);
        }
        return customer;
    }

    public void assignAccountsToCustomers() {

        List<Account> allAccounts = accountDAO.findAll();
        List<Customer> allCustomers = customerDAO.findAll();

        for (Account account : allAccounts) {
            Customer customer = account.getCustomer();
            if (customer != null) {
                for (Customer cust : allCustomers) {
                    if (cust.getId().equals(customer.getId())) {
                        cust.getAccounts().add(account);
                    }
                }
            }
        }
    }



}
