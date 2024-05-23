package com.danIT.HW.Spring_Aplication.dao;

import com.danIT.HW.Spring_Aplication.domain.Account;
import com.danIT.HW.Spring_Aplication.domain.Currency;
import com.danIT.HW.Spring_Aplication.domain.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {

    public static List<Customer> getMockCustomers() {
        List<Customer> customers = new ArrayList<>();

        Customer customer1 = new Customer("Ivan Melnik", "ivenmelnik@gmail.com", 23);
        Customer customer2 = new Customer("Sara Vazovski", "sarik@gmail.com", 40);
        Customer customer3 = new Customer("Igor Melnik", "bujhm@gmail.com", 43);
        Customer customer4 = new Customer("Emilia Klark", "emmy@gmail.com", 21);
        customer1.setId(1L);
        customer2.setId(2L);
        customer3.setId(3L);
        customer4.setId(4L);



        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);

        return customers;
    }



}
