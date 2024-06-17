package com.danIT.HW.Spring_Aplication.HW3.services;

import com.danIT.HW.Spring_Aplication.HW3.domain.Account;
import com.danIT.HW.Spring_Aplication.HW3.domain.Customer;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account save(Account account);

    void delete(Account account);

    void deleteAll();

    void saveAll(List<Account> entities);

    List<Account> findAll();

    void deleteById(long id);

    Account getOne(long id);

    Account findByNumber(UUID number);

    public boolean topUpTheAccount(UUID number, double balance);

    public boolean withdrawMoneyFromTheAccount(UUID number, double balance);

    public boolean transferMoneyToAnotherAccount(UUID numberPayer, UUID numberRecipient, double balance);

    public Account update(Account account);

}
