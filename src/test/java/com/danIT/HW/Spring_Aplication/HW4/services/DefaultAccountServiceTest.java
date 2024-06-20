package com.danIT.HW.Spring_Aplication.HW4.services;

import com.danIT.HW.Spring_Aplication.HW4.dao.CollectionAccountJPA;
import com.danIT.HW.Spring_Aplication.HW4.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultAccountServiceTest {

    @Mock
    private CollectionAccountJPA collectionAccountJPA;

    @InjectMocks
    private DefaultAccountService defaultAccountService;

    @Captor
    private ArgumentCaptor<Long> argumentCaptor;

    @Test
    void save() {
        Account account = new Account();
        account.setBalance(123.0);

        when(collectionAccountJPA.save(account)).thenReturn(account);

        defaultAccountService.save(account);
        verify(collectionAccountJPA).save(account);
    }

    @Test
    void delete() {
        Account account = new Account();
        account.setBalance(123.0);
        defaultAccountService.delete(account);
        verify(collectionAccountJPA).delete(account);
    }

    @Test
    void deleteAll() {
        defaultAccountService.deleteAll();
        verify(collectionAccountJPA).deleteAll();
    }

    @Test
    void saveAll() {
        Account account1 = new Account();
        account1.setBalance(1000.0);
        Account account2 = new Account();
        account2.setBalance(2000.0);

        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        defaultAccountService.saveAll(accounts);


        verify(collectionAccountJPA).saveAll(accounts);
    }

    @Test
    void findAll() {
        Account account1 = new Account();
        account1.setBalance(1000.0);
        Account account2 = new Account();
        account2.setBalance(2000.0);

        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        when(collectionAccountJPA.findAll()).thenReturn(accounts);

        List<Account> result = defaultAccountService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertIterableEquals(accounts, result);
    }

    @Test
    void deleteById() {
        defaultAccountService.deleteById(1);
        verify(collectionAccountJPA).deleteById(argumentCaptor.capture());
    }

    @Test
    void getOne() {
        Account account = new Account();

        when(collectionAccountJPA.getOne(0L)).thenReturn(account);

        Account currentAccount = defaultAccountService.getOne(0L);
        assertEquals(account, currentAccount);

    }

    @Test
    void findByNumber() {
        Account account = new Account();
        UUID number = UUID.randomUUID();
        account.setNumber(number);
        when(collectionAccountJPA.findByNumber(number)).thenReturn(account);

        Account currentAccount = defaultAccountService.findByNumber(number);

        assertEquals(number, currentAccount.getNumber());
    }

    @Test
    void topUpTheAccount() {
        Account account = new Account();
        UUID number = UUID.randomUUID();
        account.setNumber(number);
        account.setBalance(123.0);

        double depositAmount = 200.0;

        when(collectionAccountJPA.findByNumber(number)).thenReturn(account);

        boolean topUpSuccess = defaultAccountService.topUpTheAccount(number, depositAmount);

        assertTrue(topUpSuccess);
        assertEquals(323.0, account.getBalance());
    }

    @Test
    void withdrawMoneyFromTheAccount() {
        Account account = new Account();
        UUID number = UUID.randomUUID();
        account.setNumber(number);
        account.setBalance(1230.0);

        double withdrawAmount = 200.0;

        when(collectionAccountJPA.findByNumber(number)).thenReturn(account);

        boolean withdrawSuccess = defaultAccountService.withdrawMoneyFromTheAccount(number, withdrawAmount);

        assertTrue(withdrawSuccess);
        assertEquals(1030.0, account.getBalance());
    }

    @Test
    void transferMoneyToAnotherAccount() {
        Account accountPayer = new Account();
        Account accountRecipient = new Account();


        UUID numberPayer = UUID.randomUUID();
        accountPayer.setNumber(numberPayer);
        accountPayer.setBalance(1230.0);

        UUID numberRecipient = UUID.randomUUID();
        accountRecipient.setNumber(numberRecipient);
        accountRecipient.setBalance(2000.0);

        double transferAmount = 200.0;

        when(collectionAccountJPA.findByNumber(numberPayer)).thenReturn(accountPayer);
        when(collectionAccountJPA.findByNumber(numberRecipient)).thenReturn(accountRecipient);

        boolean transferSuccess = defaultAccountService.transferMoneyToAnotherAccount(numberPayer, numberRecipient, transferAmount);

        assertTrue(transferSuccess);
        assertEquals(1030.0, accountPayer.getBalance());
        assertEquals(2200.0, accountRecipient.getBalance());


    }
}