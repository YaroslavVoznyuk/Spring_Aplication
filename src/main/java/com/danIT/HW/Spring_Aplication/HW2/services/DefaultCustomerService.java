package com.danIT.HW.Spring_Aplication.HW2.services;

import com.danIT.HW.Spring_Aplication.HW2.dao.CollectionAccountDAO;
import com.danIT.HW.Spring_Aplication.HW2.dao.CollectionCustomerDAO;
import com.danIT.HW.Spring_Aplication.HW2.domain.Account;
import com.danIT.HW.Spring_Aplication.HW2.domain.Currency;
import com.danIT.HW.Spring_Aplication.HW2.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW2.domain.dto.CustomerDTO;
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
    public void deleteAll() {
        customerDAO.deleteAll();
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
    public Customer getOne(long id) {
        return customerDAO.getOne(id);
    }


    public Customer createACustomer(CustomerDTO customer) {
        Customer newCustomer = new Customer(customer.getName(), customer.getEmail(), customer.getAge());

        return customerDAO.save(newCustomer);
    }

    public Customer changeCustomerData(CustomerDTO customer, Long customerId) {
        Customer currentCustomer = customerDAO.getOne(customerId);
        if (getOne(customerId) != null) {
            currentCustomer.setName(customer.getName());
            currentCustomer.setEmail(customer.getEmail());
            currentCustomer.setAge(customer.getAge());

            save(currentCustomer);

            return getOne(customerId);
        } else {
            throw new RuntimeException();
        }
    }

    public boolean deleteCustomer(Long id) {
        return customerDAO.deleteById(id);
    }

    public Account createAnAccountForASpecificCustomer(Long id, Currency currency) {

        if (customerDAO.getOne(id) != null) {
            Customer customer = customerDAO.getOne(id);
            Account account = new Account(currency, customer);
            List<Account> customerAccounts = customer.getAccounts();
            customerAccounts.add(account);

            return accountDAO.save(account);
        } else {
            throw new IllegalArgumentException();
        }


    }

    public boolean deleteTheAccountFromTheCustomer(Long customerId, String accountNumber) {

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
