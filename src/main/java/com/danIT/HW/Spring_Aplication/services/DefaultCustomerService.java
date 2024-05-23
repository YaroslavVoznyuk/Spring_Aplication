package com.danIT.HW.Spring_Aplication.services;

import com.danIT.HW.Spring_Aplication.dao.CollectionAccountDAO;
import com.danIT.HW.Spring_Aplication.dao.CollectionCustomerDAO;
import com.danIT.HW.Spring_Aplication.domain.Account;
import com.danIT.HW.Spring_Aplication.domain.Currency;
import com.danIT.HW.Spring_Aplication.domain.Customer;
import com.danIT.HW.Spring_Aplication.dao.CustomerDAO;
import com.danIT.HW.Spring_Aplication.domain.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j

public class DefaultCustomerService implements CustomerService {

    private CollectionCustomerDAO customerDAO;
    private CollectionAccountDAO accountDAO;

    @Autowired
    public DefaultCustomerService(CollectionCustomerDAO customerDAO, CollectionAccountDAO accountDAO) {
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public Customer save(Customer customer) {
        return customerDAO.save(customer);
    }

    @Override
    public boolean delete(Customer customer) {
        return customerDAO.delete(customer);
    }

    @Override
    public void deleteAll(List<Customer> entities) {
        customerDAO.deleteAll(entities);
    }

    @Override
    public void saveAll(List<Customer> entities) {
        customerDAO.saveAll(entities);
    }

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public boolean deleteById(long id) {
        return customerDAO.deleteById(id);
    }

    @Override
    public Optional<Customer> getOne(long id) {
        return customerDAO.getOne(id);
    }


    public Customer createACustomer(CustomerDTO customer) {
        Customer newCustomer = new Customer(customer.getName(), customer.getEmail(), customer.getAge());

        return customerDAO.save(newCustomer);
    }

    public Customer changeCustomerData(CustomerDTO customer, Long customerId) {
        if (getOne(customerId).isPresent()) {
            getOne(customerId).get().setName(customer.getName());
            getOne(customerId).get().setEmail(customer.getEmail());
            getOne(customerId).get().setAge(customer.getAge());

            return getOne(customerId).get();
        } else {
            throw new RuntimeException();
        }
    }

    public boolean deleteCustomer(Long id) {
        return customerDAO.deleteById(id);
    }

    public Account createAnAccountForASpecificCustomer(Long id, Currency currency) {

        if (customerDAO.getOne(id).isPresent()) {
            Customer customer = customerDAO.getOne(id).get();
            Account account = new Account(currency, customer);
            List<Account> customerAccounts = customer.getAccounts();
            customerAccounts.add(account);

            return accountDAO.save(account);
        } else {
            throw new IllegalArgumentException();
        }


    }

    public boolean deleteTheAccountFromTheCustomer(Long customerId, String accountNumber) {

        if (customerDAO.getOne(customerId).isPresent()
                && accountDAO.findByNumber(accountNumber).isPresent()) {
            Customer customer = customerDAO.getOne(customerId).get();
            if (customer.getAccounts().contains(accountDAO.findByNumber(accountNumber).get())) {
                customer.getAccounts().remove(accountDAO.findByNumber(accountNumber).get());
                accountDAO.delete(accountDAO.findByNumber(accountNumber).get());
                return true;
            }else {
                return false;
            }

        } else {
            throw new IllegalArgumentException();
        }
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
