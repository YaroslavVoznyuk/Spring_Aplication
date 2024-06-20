package com.danIT.HW.Spring_Aplication.HW4.controllers;

import com.danIT.HW.Spring_Aplication.HW4.domain.Account;
import com.danIT.HW.Spring_Aplication.HW4.domain.Currency;
import com.danIT.HW.Spring_Aplication.HW4.domain.dto.AccountDTOResponse;
import com.danIT.HW.Spring_Aplication.HW4.domain.dtoMapper.account.AccountDtoMapperResponse;
import com.danIT.HW.Spring_Aplication.HW4.services.DefaultAccountService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DefaultAccountService accountService;

    @MockBean
    private AccountDtoMapperResponse accountDtoMapperResponse;

    @Test
    @WithMockUser(username = "testUser")
    void getAllAccounts() throws Exception {
        Account account = new Account();
        AccountDTOResponse accountDTOResponse = new AccountDTOResponse();

        accountDTOResponse.setId(1L);
        accountDTOResponse.setCurrency(Currency.UAH);
        accountDTOResponse.setNumber("18457405-2528-475a-b6d6-dbb09c4bfbad");
        accountDTOResponse.setBalance(2000000.0);

        when(accountService.findAll()).thenReturn(List.of(account));
        when(accountDtoMapperResponse.convertToDto(account)).thenReturn(accountDTOResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/all").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currency", Matchers.is(Currency.UAH.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].number", Matchers.is("18457405-2528-475a-b6d6-dbb09c4bfbad")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].balance", Matchers.is(2000000.0)));
    }

    @Test
    @WithMockUser(username = "testUser")
    void topUpTheAccount() throws Exception {
        Account account = new Account();

        UUID accountNumber = UUID.fromString("18457405-2528-475a-b6d6-dbb09c4bfbad");
        double topUpAmount = 300.0;

        account.setId(1L);
        account.setCurrency(Currency.UAH);
        account.setNumber(accountNumber);
        account.setBalance(2000000.0);

        when(accountService.topUpTheAccount(accountNumber, topUpAmount))
                .thenReturn(true);

        mockMvc.perform(put("/accounts/topUp/{number}", accountNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(topUpAmount)))
                .andExpect(status().isOk())
                .andExpect(content().string("top up successful"));
    }

    @Test
    @WithMockUser(username = "testUser")
    void withdrawMoneyFromTheAccount() throws Exception {
        Account account = new Account();

        UUID accountNumber = UUID.fromString("18457405-2528-475a-b6d6-dbb09c4bfbad");
        double withdrawAmount = 300.0;

        account.setId(1L);
        account.setCurrency(Currency.UAH);
        account.setNumber(accountNumber);
        account.setBalance(2000000.0);

        when(accountService.withdrawMoneyFromTheAccount(accountNumber, withdrawAmount))
                .thenReturn(true);

        mockMvc.perform(put("/accounts/withdraw/{number}", accountNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(withdrawAmount)))
                .andExpect(status().isOk())
                .andExpect(content().string("withdraw successful"));
    }

    @Test
    @WithMockUser(username = "testUser")
    void transferMoneyToAnotherAccount() throws Exception {
        Account accountPayer = new Account();
        Account accountRecipient = new Account();

        UUID accountNumberPayer = UUID.fromString("18457405-2528-475a-b6d6-dbb09c4bfbad");
        UUID accountNumberRecipient = UUID.fromString("55396c09-4ee6-4ef2-a695-b719d253ef16");
        double transferAmount = 300.0;

        accountPayer.setId(1L);
        accountPayer.setCurrency(Currency.UAH);
        accountPayer.setNumber(accountNumberPayer);
        accountPayer.setBalance(2000000.0);

        accountRecipient.setId(2L);
        accountRecipient.setCurrency(Currency.UAH);
        accountRecipient.setNumber(accountNumberRecipient);
        accountRecipient.setBalance(123.0);

        when(accountService.transferMoneyToAnotherAccount(accountNumberPayer, accountNumberRecipient, transferAmount))
                .thenReturn(true);

        mockMvc.perform(put("/accounts/transfer/{numberPayer}/{numberRecipient}", accountNumberPayer, accountNumberRecipient)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(transferAmount)))
                .andExpect(status().isOk())
                .andExpect(content().string("transfer successful"));
    }

}
