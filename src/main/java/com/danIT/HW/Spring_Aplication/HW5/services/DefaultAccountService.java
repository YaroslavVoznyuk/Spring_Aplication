package com.danIT.HW.Spring_Aplication.HW5.services;

import com.danIT.HW.Spring_Aplication.HW5.dao.CollectionAccountJPA;
import com.danIT.HW.Spring_Aplication.HW5.domain.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DefaultAccountService implements AccountService {

    private final CollectionAccountJPA collectionAccountJPA;

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Account save(Account account) {
        return collectionAccountJPA.save(account);
    }

    @Override
    public void delete(Account account) {
        collectionAccountJPA.delete(account);
    }

    @Override
    public void deleteAll() {
        collectionAccountJPA.deleteAll();
    }

    @Override
    public void saveAll(List<Account> entities) {
        collectionAccountJPA.saveAll(entities);
    }

    @Override
    public List<Account> findAll() {
        return collectionAccountJPA.findAll();
    }

    @Override
    public void deleteById(long id) {
         collectionAccountJPA.deleteById(id);
    }

    @Override
    public Account getOne(long id) {
        return collectionAccountJPA.getOne(id);
    }

    @Override
    public Account findByNumber(UUID number) {
        return collectionAccountJPA.findByNumber(number);
    }

    public int getIndex(Account account) {
        return collectionAccountJPA.findAll().indexOf(account);
    }

    @Override
    public boolean topUpTheAccount(UUID number, double balance) {
        Double currentBalance = findByNumber(number).getBalance();
        Account account = findByNumber(number);
        if (findByNumber(number) != null && balance > 0) {
            account.setBalance(currentBalance + balance);
            save(account);
            return true;
        } else {
            throw new IllegalArgumentException("No such account");
        }
    }

    @Override
    public boolean withdrawMoneyFromTheAccount(UUID number, double balance) {
        Double currentBalance = findByNumber(number).getBalance();
        Account account = findByNumber(number);
        if (findByNumber(number) != null && currentBalance >= balance && balance > 0) {
            account.setBalance(currentBalance - balance);
            save(account);
            return true;
        } else {
            throw new IllegalArgumentException("No such account");
        }
    }

    @Override
    public boolean transferMoneyToAnotherAccount(UUID numberPayer, UUID numberRecipient, double balance) {
        Double currentBalancePayer = findByNumber(numberPayer).getBalance();
        Double currentBalanceRecipient = findByNumber(numberRecipient).getBalance();

        Account accountPayer = findByNumber(numberPayer);
        Account accountRecipient = findByNumber(numberRecipient);

        if (findByNumber(numberPayer) != null
                && findByNumber(numberRecipient) != null && currentBalancePayer >= balance && balance > 0) {
            accountPayer.setBalance(currentBalancePayer - balance);
            accountRecipient.setBalance(currentBalanceRecipient + balance);

            save(accountPayer);
            save(accountRecipient);
            return true;
        } else {
            throw new IllegalArgumentException("No such account");
        }
    }




}
