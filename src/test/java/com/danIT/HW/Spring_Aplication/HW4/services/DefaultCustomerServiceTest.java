package com.danIT.HW.Spring_Aplication.HW4.services;

import com.danIT.HW.Spring_Aplication.HW4.dao.CollectionAccountJPA;
import com.danIT.HW.Spring_Aplication.HW4.dao.CollectionCustomerJPA;
import com.danIT.HW.Spring_Aplication.HW4.domain.Account;
import com.danIT.HW.Spring_Aplication.HW4.domain.Currency;
import com.danIT.HW.Spring_Aplication.HW4.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerDTORequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultCustomerServiceTest {

    @Mock
    private CollectionCustomerJPA collectionCustomerJPA;

    @Mock
    private CollectionAccountJPA collectionAccountJPA;

    @InjectMocks
    private DefaultCustomerService defaultCustomerService;

    @Test
    void save() {
        Customer customer = new Customer();
        customer.setName("Ivan Melnik");

        when(collectionCustomerJPA.save(customer)).thenReturn(customer);

        defaultCustomerService.save(customer);
        verify(collectionCustomerJPA).save(customer);
    }

    @Test
    void delete() {
        Customer customer = new Customer();
        customer.setName("Ivan Melnik");
        defaultCustomerService.delete(customer);
        verify(collectionCustomerJPA).delete(customer);
    }

    @Test
    void deleteAll() {
        defaultCustomerService.deleteAll();
        verify(collectionCustomerJPA).deleteAll();
    }

    @Test
    void saveAll() {
        Customer customer1 = new Customer();
        customer1.setName("Ivan Melnik");
        Customer customer2 = new Customer();
        customer2.setName("Sara Vazovski");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        defaultCustomerService.saveAll(customers);

        verify(collectionCustomerJPA).saveAll(customers);


    }

    @Test
    void findAll() {
        Customer testCustomer1 = new Customer();
        Customer testCustomer2 = new Customer();
        List<Customer> customersExpected = List.of(testCustomer1, testCustomer2);

        Page<Customer> page = new PageImpl<>(customersExpected);
        when(collectionCustomerJPA.findAll(PageRequest.of(0, 10))).thenReturn(page);
        Page<Customer> customerActual = defaultCustomerService.findAll(0, Integer.MAX_VALUE, PageRequest.of(0, 10));
        assertNotNull(customerActual);
        assertFalse(customerActual.isEmpty());
        assertIterableEquals(customersExpected, customerActual.getContent());

    }

    @Test
    void deleteById() {
        defaultCustomerService.deleteById(1);
        verify(collectionCustomerJPA).deleteById(1L);
    }

    @Test
    void getOne() {
        Customer customer = new Customer();
        customer.setName("Ivan Melnik");
        customer.setId(1L);
        when(collectionCustomerJPA.getOne(1L)).thenReturn(customer);
        Customer CurrentCustomer = defaultCustomerService.getOne(1L);
        assertEquals(customer.getName(), CurrentCustomer.getName());
    }


    @Test
    void changeCustomerData() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Ivan Melnik");
        customer.setEmail("ivenmelnik@gmail.com");
        customer.setAge(23);

        CustomerDTORequest updatedCustomer = new CustomerDTORequest();
        updatedCustomer.setName("Sara Vazovski");
        updatedCustomer.setEmail("sarik@gmail.com");
        updatedCustomer.setAge(40);

        when(collectionCustomerJPA.getOne(1L)).thenReturn(customer);

        Customer result = defaultCustomerService.changeCustomerData(updatedCustomer, 1L);

        assertNotNull(result);
        assertEquals(updatedCustomer.getName(), result.getName());
        assertEquals(updatedCustomer.getEmail(), result.getEmail());
        assertEquals(updatedCustomer.getAge(), result.getAge());
    }

    @Test
    void createAnAccountForASpecificCustomer() {
        Customer customer = new Customer();
        customer.setName("Ivan Melnik");
        customer.setId(1L);
        when(collectionCustomerJPA.getOne(1L)).thenReturn(customer);

        defaultCustomerService.createAnAccountForASpecificCustomer(1L, Currency.UAH);

        assertEquals(customer.getAccounts().size(), 1);

    }

    @Test
    void deleteTheAccountFromTheCustomer() {
        UUID accountNumber = UUID.randomUUID();
        Account account = new Account();
        account.setNumber(accountNumber);

        Customer customer = new Customer();
        customer.setName("Ivan Melnik");
        customer.setId(1L);

        List<Account> accounts = new ArrayList<>();
        accounts.add(account);

        customer.setAccounts(accounts);

        when(collectionCustomerJPA.getOne(1L)).thenReturn(customer);


       boolean deleteSuccess = defaultCustomerService.deleteTheAccountFromTheCustomer(1L, accountNumber);
        assertTrue(deleteSuccess);
        assertEquals(customer.getAccounts().size(), 0);
    }

}