package com.danIT.HW.Spring_Aplication.dao;

import com.danIT.HW.Spring_Aplication.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class CollectionCustomerDAO implements CustomerDAO {

    private static Long idCustomerCounter = 0L;
    private List<Customer> databaseOfCustomers;

    public CollectionCustomerDAO() {
        databaseOfCustomers = new ArrayList<>();
        mockCustomers();
    }

    private void mockCustomers() {
        List<Customer> customers = MockData.getMockCustomers();
        for (Customer customer : customers) {
            save(customer);
        }
    }

    @Override
    public Customer save(Customer customer) {
        int userIndex = databaseOfCustomers.indexOf(customer);
        if (userIndex != -1) {
            databaseOfCustomers.set(userIndex, customer);
        } else {
            customer.setId(++idCustomerCounter);
            databaseOfCustomers.add(customer);
        }
        return customer;
    }

    @Override
    public boolean delete(Customer customer) {
        int userIndex = databaseOfCustomers.indexOf(customer);
        if (userIndex != -1) {
            return false;
        } else {
            databaseOfCustomers.remove(customer);
            return true;
        }
    }

    @Override
    public void deleteAll(List<Customer> entities) {
        for (int i = 0; i < entities.size(); i++) {
            if (databaseOfCustomers.contains(entities.get(i))) {
                databaseOfCustomers.remove(entities.get(i));
            } else {
                System.out.println("account not found");
            }
        }
    }

    @Override
    public void saveAll(List<Customer> entities) {
        int userIndex;
        for (int i = 0; i < entities.size(); i++) {
            userIndex = databaseOfCustomers.indexOf(entities.get(i));
            if (databaseOfCustomers.contains(entities.get(i))) {
                databaseOfCustomers.set(userIndex, entities.get(i));
            } else {
                databaseOfCustomers.add(entities.get(i));
            }
        }
    }

    @Override
    public List<Customer> findAll() {
        return databaseOfCustomers;
    }

    @Override
    public boolean deleteById(long id) {
        for (int i = 0; i < databaseOfCustomers.size(); i++) {
            if (databaseOfCustomers.get(i).getId() == id) {
                databaseOfCustomers.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Customer> getOne(long id) {
        return databaseOfCustomers.stream()
                .filter(customer1 -> (customer1.getId()).equals(id))
                .findFirst();
    }
}
