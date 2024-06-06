package com.danIT.HW.Spring_Aplication.HW2.services;

import com.danIT.HW.Spring_Aplication.HW2.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account save(Account account);

    boolean delete(Account account);

    void deleteAll();

    void saveAll(List<Account> entities);

    List<Account> findAll();

    boolean deleteById(long id);

    Account getOne(long id);

    Account findByNumber(String number);

    public boolean topUpTheAccount(String number, double balance);

    public boolean withdrawMoneyFromTheAccount(String number, double balance);

    public boolean transferMoneyToAnotherAccount(String numberPayer, String numberRecipient, double balance);

}
