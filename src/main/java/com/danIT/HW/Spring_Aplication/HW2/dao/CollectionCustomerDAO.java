package com.danIT.HW.Spring_Aplication.HW2.dao;

import com.danIT.HW.Spring_Aplication.HW2.domain.Account;
import com.danIT.HW.Spring_Aplication.HW2.domain.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CollectionCustomerDAO implements DAO<Customer> {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            entityManager.persist(customer);
        } else {
            entityManager.merge(customer);
        }
        return customer;
    }

    @Override
    @Transactional
    public boolean delete(Customer customer) {
        Customer searchCustomer = entityManager.find(Customer.class, customer.getId());
        if (searchCustomer != null) {
            entityManager.remove(searchCustomer);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Customer").executeUpdate();
    }

    @Override
    @Transactional
    public void saveAll(List<Customer> entities) {
        entities.forEach(this::save);
    }

    @Override
    public List<Customer> findAll() {
        Query query = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        Customer searchCustomer = entityManager.find(Customer.class, id);
        if (searchCustomer != null) {
            entityManager.remove(searchCustomer);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Customer getOne(long id) {
        return entityManager.find(Customer.class, id);
    }
}
