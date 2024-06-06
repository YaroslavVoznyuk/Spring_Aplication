package com.danIT.HW.Spring_Aplication.HW2.controllers;


import com.danIT.HW.Spring_Aplication.HW2.domain.Account;
import com.danIT.HW.Spring_Aplication.HW2.services.DefaultAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    private final DefaultAccountService accountService;
    private final ObjectMapper objectMapper;

    @Autowired
    public AccountController(DefaultAccountService accountService, ObjectMapper objectMapper) {
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }

    @Operation(summary = "Get all accounts")
    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @Operation(summary = "account replenishment")
    @PutMapping("/topUp/{number}")
    public ResponseEntity<?> topUpTheAccount(@PathVariable String number,@RequestBody double balance) {

        try {
            boolean confirmed = accountService.topUpTheAccount(number, balance);
            if (confirmed) {
                return ResponseEntity.ok("top up successful");
            } else {
                return ResponseEntity.badRequest().body("top up failed");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @Operation(summary = "withdrawal of money from the account")
    @PutMapping("/withdraw/{number}")
    public ResponseEntity<?> withdrawMoneyFromTheAccount(@PathVariable String number, @RequestBody double balance) {
        try {
            boolean confirmed = accountService.withdrawMoneyFromTheAccount(number, balance);
            if (confirmed) {
                return ResponseEntity.ok("withdraw successful");
            } else {
                return ResponseEntity.badRequest().body("withdraw failed");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "money transfer from one account to another")
    @PutMapping("/transfer/{numberPayer}/{numberRecipient}")
    public ResponseEntity<?> transferMoneyToAnotherAccount(@PathVariable String numberPayer,
                                                           @PathVariable String numberRecipient,
                                                           @RequestBody double balance) {
        try {
            boolean confirmed = accountService.transferMoneyToAnotherAccount(numberPayer, numberRecipient, balance);
            if (confirmed) {
                return ResponseEntity.ok("transfer successful");
            } else {
                return ResponseEntity.badRequest().body("transfer failed");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
