package com.danIT.HW.Spring_Aplication.HW1.services;

import com.danIT.HW.Spring_Aplication.HW1.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account save(Account account);

    boolean delete(Account account);

    void deleteAll(List<Account> entities);

    void saveAll(List<Account> entities);

    List<Account> findAll();

    boolean deleteById(long id);

    Optional<Account> getOne(long id);

    Optional<Account> findByNumber(String number);

    public boolean topUpTheAccount(String number, double balance);

    public boolean withdrawMoneyFromTheAccount(String number, double balance);

    public boolean transferMoneyToAnotherAccount(String numberPayer, String numberRecipient, double balance);

}
