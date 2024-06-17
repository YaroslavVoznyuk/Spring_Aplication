package com.danIT.HW.Spring_Aplication.HW3.controllers;


import com.danIT.HW.Spring_Aplication.HW3.domain.dto.AccountDTOResponse;
import com.danIT.HW.Spring_Aplication.HW3.domain.dtoMapper.account.AccountDtoMapperResponse;
import com.danIT.HW.Spring_Aplication.HW3.services.DefaultAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@Slf4j
@RequiredArgsConstructor
public class AccountController {

    private final DefaultAccountService accountService;
    private final AccountDtoMapperResponse accountDtoMapperResponse;



    @Operation(summary = "Get all accounts")
    @GetMapping("/all")
    public List<AccountDTOResponse> getAllAccounts() {
        return accountService.findAll().stream().map(accountDtoMapperResponse::convertToDto).toList();
    }

    @Operation(summary = "account replenishment")
    @PutMapping("/topUp/{number}")
    public ResponseEntity<?> topUpTheAccount(@PathVariable String number,@RequestBody double balance) {

        try {
            boolean confirmed = accountService.topUpTheAccount(UUID.fromString(number), balance);
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
            boolean confirmed = accountService.withdrawMoneyFromTheAccount(UUID.fromString(number), balance);
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
            boolean confirmed = accountService.transferMoneyToAnotherAccount(UUID.fromString(numberPayer), UUID.fromString(numberRecipient), balance);
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
