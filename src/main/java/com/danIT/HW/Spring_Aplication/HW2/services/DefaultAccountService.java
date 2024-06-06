package com.danIT.HW.Spring_Aplication.HW2.services;

import com.danIT.HW.Spring_Aplication.HW2.dao.CollectionAccountDAO;
import com.danIT.HW.Spring_Aplication.HW2.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultAccountService implements AccountService {

    private CollectionAccountDAO accountDAO;

    @Autowired
    public DefaultAccountService(CollectionAccountDAO accountDAO) {
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
    public void deleteAll() {
        accountDAO.deleteAll();
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
    public Account getOne(long id) {
        return accountDAO.getOne(id);
    }

    @Override
    public Account findByNumber(String number) {
        return accountDAO.findByNumber(number);
    }

    public int getIndex(Account account) {
        return accountDAO.findAll().indexOf(account);
    }

    @Override
    public boolean topUpTheAccount(String number, double balance) {
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
    public boolean withdrawMoneyFromTheAccount(String number, double balance) {
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
    public boolean transferMoneyToAnotherAccount(String numberPayer, String numberRecipient, double balance) {
        Double currentBalancePayer = findByNumber(numberPayer).getBalance();
        Double currentBalanceRecipient = findByNumber(numberPayer).getBalance();

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
