package com.danIT.HW.Spring_Aplication.HW2.dao;

import com.danIT.HW.Spring_Aplication.HW2.domain.Account;
import com.danIT.HW.Spring_Aplication.HW2.domain.Currency;
import com.danIT.HW.Spring_Aplication.HW2.domain.Customer;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CollectionAccountDAO implements DAO<Account> {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Account save(Account account) {
        if (account.getId() == null) {
            entityManager.persist(account);
        } else {
            entityManager.merge(account);
        }
        return account;
    }

    @Override
    @Transactional
    public boolean delete(Account account) {
        Account searchAccount = entityManager.find(Account.class, account.getId());
        if (searchAccount != null) {
            entityManager.remove(searchAccount);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM Account").executeUpdate();
    }

    @Override
    @Transactional
    public void saveAll(List<Account> entities) {
        entities.forEach(this::save);
    }

    @Override
    public List<Account> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM Account a", Account.class);
        return query.getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        Account searchAccount = entityManager.find(Account.class, id);
        if (searchAccount != null) {
            entityManager.remove(searchAccount);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Account getOne(long id) {
        return entityManager.find(Account.class, id);
    }

    public Account findByNumber(String number) {
        try {
            Query query = entityManager.createQuery("SELECT a FROM Account a WHERE a.number = :number", Account.class);
            return (Account) query.setParameter("number", number).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
