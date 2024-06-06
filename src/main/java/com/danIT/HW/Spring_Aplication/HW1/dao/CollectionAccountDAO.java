package com.danIT.HW.Spring_Aplication.HW1.dao;

import com.danIT.HW.Spring_Aplication.HW1.domain.Account;
import com.danIT.HW.Spring_Aplication.HW1.domain.Currency;
import com.danIT.HW.Spring_Aplication.HW1.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CollectionAccountDAO implements AccountDAO {
    private static Long idAccountCounter = 0L;
    private List<Account> databaseOfAccounts;

    public CollectionAccountDAO() {
        databaseOfAccounts = new ArrayList<>();
        mockAccounts();
    }

    private void mockAccounts() {
        List<Customer> customers = MockData.getMockCustomers();
        for (Customer customer : customers) {
            Account account = new Account(Currency.values()[(int) (Math.random() * 5)], customer);
            save(account);
        }
    }

    @Override
    public Account save(Account account) {
        int userIndex = databaseOfAccounts.indexOf(account);
        if (userIndex != -1) {
            databaseOfAccounts.set(userIndex, account);
        } else {
            account.setId(++idAccountCounter);
            databaseOfAccounts.add(account);
        }
        return account;
    }

    @Override
    public boolean delete(Account account) {
        int userIndex = databaseOfAccounts.indexOf(account);
        if (userIndex != -1) {
            return false;
        } else {
            databaseOfAccounts.remove(account);
            return true;
        }
    }

    @Override
    public void deleteAll(List<Account> entities) {
        for (int i = 0; i < entities.size(); i++) {
            if (databaseOfAccounts.contains(entities.get(i))) {
                databaseOfAccounts.remove(entities.get(i));
            } else {
                System.out.println("account not found");
            }
        }
    }

    @Override
    public void saveAll(List<Account> entities) {
        int userIndex;
        for (int i = 0; i < entities.size(); i++) {
            userIndex = databaseOfAccounts.indexOf(entities.get(i));
            if (databaseOfAccounts.contains(entities.get(i))) {
                databaseOfAccounts.set(userIndex, entities.get(i));
            } else {
                databaseOfAccounts.add(entities.get(i));
            }
        }
    }

    @Override
    public List<Account> findAll() {
        return databaseOfAccounts;
    }

    @Override
    public boolean deleteById(long id) {
        for (int i = 0; i < databaseOfAccounts.size(); i++) {
            if (databaseOfAccounts.get(i).getId() == id) {
                databaseOfAccounts.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Account> getOne(long id) {
        return databaseOfAccounts.stream()
                .filter(account -> (account.getId()).equals(id))
                .findFirst();
    }

    @Override
    public Optional<Account> findByNumber(String number) {
        return databaseOfAccounts.stream()
                .filter(account -> (account.getNumber()).equals(number))
                .findFirst();
    }
}
