package com.danIT.HW.Spring_Aplication.dao;

import com.danIT.HW.Spring_Aplication.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDAO {

    Account save(Account account);

    boolean delete(Account account);

    void deleteAll(List<Account> entities);

    void saveAll(List<Account> entities);

    List<Account> findAll();

    boolean deleteById(long id);

    Optional<Account> getOne(long id);

    Optional<Account> findByNumber(String number);

}
