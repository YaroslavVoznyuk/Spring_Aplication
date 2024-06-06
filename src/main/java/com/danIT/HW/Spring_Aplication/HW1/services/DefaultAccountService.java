package com.danIT.HW.Spring_Aplication.HW1.services;

import com.danIT.HW.Spring_Aplication.HW1.dao.AccountDAO;
import com.danIT.HW.Spring_Aplication.HW1.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultAccountService implements AccountService {

    private AccountDAO accountDAO;

    @Autowired
    public DefaultAccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public Account save(Account account) {
        return accountDAO.save(account);
    }

    @Override
    public boolean delete(Account account) {
        return accountDAO.delete(account);
    }

    @Override
    public void deleteAll(List<Account> entities) {
        accountDAO.deleteAll(entities);
    }

    @Override
    public void saveAll(List<Account> entities) {
        accountDAO.saveAll(entities);
    }

    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public boolean deleteById(long id) {
        return accountDAO.deleteById(id);
    }

    @Override
    public Optional<Account> getOne(long id) {
        return accountDAO.getOne(id);
    }

    @Override
    public Optional<Account> findByNumber(String number) {
        return accountDAO.findByNumber(number);
    }

    public int getIndex(Account account) {
        return accountDAO.findAll().indexOf(account);
    }

    @Override
    public boolean topUpTheAccount(String number, double balance) {
        Double currentBalance = findByNumber(number).get().getBalance();

        if (findByNumber(number).isPresent() && balance > 0) {
            findAll().get(getIndex(findByNumber(number).get())).setBalance(currentBalance + balance);
            return true;
        } else {
            throw new IllegalArgumentException("No such account");
        }
    }

    @Override
    public boolean withdrawMoneyFromTheAccount(String number, double balance) {
        Double currentBalance = findByNumber(number).get().getBalance();

        if (findByNumber(number).isPresent() && currentBalance >= balance && balance > 0) {
            findAll().get(getIndex(findByNumber(number)
                    .get())).setBalance(currentBalance - balance);
            return true;
        } else {
            throw new IllegalArgumentException("No such account");
        }
    }

    @Override
    public boolean transferMoneyToAnotherAccount(String numberPayer, String numberRecipient, double balance) {
        Double currentBalancePayer = findByNumber(numberPayer).get().getBalance();
        Double currentBalanceRecipient = findByNumber(numberPayer).get().getBalance();

        if (findByNumber(numberPayer).isPresent()
                && findByNumber(numberRecipient).isPresent() && currentBalancePayer >= balance && balance > 0) {
            findAll().get(getIndex(findByNumber(numberPayer)
                    .get())).setBalance(currentBalancePayer - balance);
            findAll().get(getIndex(findByNumber(numberRecipient)
                    .get())).setBalance(currentBalanceRecipient + balance);
            return true;
        } else {
            throw new IllegalArgumentException("No such account");
        }
    }



}
