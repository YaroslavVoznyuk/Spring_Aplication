package com.danIT.HW.Spring_Aplication.HW5.services;

import com.danIT.HW.Spring_Aplication.HW5.dao.CollectionAccountJPA;
import com.danIT.HW.Spring_Aplication.HW5.dao.CollectionCustomerJPA;
import com.danIT.HW.Spring_Aplication.HW5.domain.Account;
import com.danIT.HW.Spring_Aplication.HW5.domain.Currency;
import com.danIT.HW.Spring_Aplication.HW5.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW5.domain.dto.CustomerDTORequest;
import com.danIT.HW.Spring_Aplication.HW5.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DefaultCustomerService implements CustomerService {

    private final CollectionCustomerJPA collectionCustomerJPA;

    private final CollectionAccountJPA collectionAccountJPA;

    @Override
    public Customer save(Customer customer) {
        return collectionCustomerJPA.save(customer);
    }

    @Override
    public void delete(Customer customer) {
         collectionCustomerJPA.delete(customer);
    }

    @Override
    public void deleteAll() {
        collectionCustomerJPA.deleteAll();
    }

    @Override
    public void saveAll(List<Customer> entities) {
        collectionCustomerJPA.saveAll(entities);
    }

    @Override
    public Page<Customer> findAll(Integer from, Integer to, Pageable pageable) {
        return collectionCustomerJPA.findAll(pageable);
    }

    @Override
    public void deleteById(long id) {
         collectionCustomerJPA.deleteById(id);
    }

    @Override
    public Customer getOne(long id) {
        return collectionCustomerJPA.getOne(id);
    }


    public Customer createACustomer(CustomerDTORequest customer) {
        Customer newCustomer = new Customer(customer.getName(), customer.getEmail(), customer.getAge(), customer.getPassword(), customer.getPhoneNumber());

        return save(newCustomer);
    }

    public Customer changeCustomerData(CustomerDTORequest customer, Long customerId) {
        Customer currentCustomer = collectionCustomerJPA.getOne(customerId);
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


    public Account createAnAccountForASpecificCustomer(Long id, Currency currency) {

        if (collectionCustomerJPA.getOne(id) != null) {
            Customer customer = collectionCustomerJPA.getOne(id);
            Account account = new Account(currency, customer);
            if(customer.getAccounts() == null) {
                customer.setAccounts(new ArrayList<>());
                List<Account> customerAccounts = customer.getAccounts();
                customerAccounts.add(account);
            }else {
                List<Account> customerAccounts = customer.getAccounts();
                customerAccounts.add(account);
            }
            return collectionAccountJPA.save(account);
        } else {
            throw new IllegalArgumentException();
        }


    }

    public boolean deleteTheAccountFromTheCustomer(Long customerId, UUID accountNumber) {
        Customer customer = collectionCustomerJPA.getOne(customerId);
        if (customer != null) {
            Account accountToDelete = customer.getAccounts().stream()
                    .filter(account -> account.getNumber().equals(accountNumber))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Account with number " + accountNumber + " not found"));

            List<Account> updatedAccounts = customer.getAccounts().stream()
                    .filter(account -> !account.getNumber().equals(accountNumber))
                    .collect(Collectors.toList());

            customer.setAccounts(updatedAccounts);

            collectionAccountJPA.delete(accountToDelete);
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
