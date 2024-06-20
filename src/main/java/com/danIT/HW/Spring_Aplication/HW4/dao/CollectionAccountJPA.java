package com.danIT.HW.Spring_Aplication.HW4.dao;

import com.danIT.HW.Spring_Aplication.HW4.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;



public interface CollectionAccountJPA extends JpaRepository<Account, Long> {

    Account findByNumber(UUID accountNumber);

}
