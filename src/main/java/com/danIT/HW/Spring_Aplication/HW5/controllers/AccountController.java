package com.danIT.HW.Spring_Aplication.HW5.controllers;


import com.danIT.HW.Spring_Aplication.HW5.domain.dto.AccountDTOResponse;
import com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.account.AccountDtoMapperResponse;
import com.danIT.HW.Spring_Aplication.HW5.exceptions.AccountNotFoundException;
import com.danIT.HW.Spring_Aplication.HW5.exceptions.InsufficientBalanceException;
import com.danIT.HW.Spring_Aplication.HW5.exceptions.InvalidTransferAmountException;
import com.danIT.HW.Spring_Aplication.HW5.exceptions.SameAccountException;
import com.danIT.HW.Spring_Aplication.HW5.services.DefaultAccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final SimpMessagingTemplate messagingTemplate;



    @Operation(summary = "Get all accounts")
    @GetMapping("/all")
    public List<AccountDTOResponse> getAllAccounts() {
        return accountService.findAll().stream().map(accountDtoMapperResponse::convertToDto).toList();
    }

    @Operation(summary = "account replenishment")
    @PutMapping("/topUp/{number}")
    public ResponseEntity<?> topUpTheAccount(@PathVariable String number,@RequestBody double balance) {
        log.info("Received request to deposit amount {} to account {}", balance, number);
        try {
            boolean confirmed = accountService.topUpTheAccount(UUID.fromString(number), balance);
            if (confirmed)
            {
                log.info("top up successful");
                changeBalanceMassage(UUID.fromString(number));
                return ResponseEntity.ok("top up successful");
            } else {
                log.info("top up failed");
                return ResponseEntity.badRequest().body("top up failed");
            }
        } catch (IllegalArgumentException e) {
            log.error("Invalid account number format: {}", number, e);
            return ResponseEntity.badRequest().body("Invalid account number format: " + number);
        } catch (AccountNotFoundException e) {
            log.error("Account with number {} not found", number, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account with number " + number + " not found");
        } catch (Exception e) {
            log.error("Unexpected error during deposit", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }

    }

    @Operation(summary = "withdrawal of money from the account")
    @PutMapping("/withdraw/{number}")
    public ResponseEntity<?> withdrawMoneyFromTheAccount(@PathVariable String number, @RequestBody double balance) {
        try {
            boolean confirmed = accountService.withdrawMoneyFromTheAccount(UUID.fromString(number), balance);
            if (confirmed) {
                log.info("withdraw successful");
                changeBalanceMassage(UUID.fromString(number));
                return ResponseEntity.ok("withdraw successful");
            } else {
                log.info("withdraw failed");
                return ResponseEntity.badRequest().body("withdraw failed");
            }
        } catch (IllegalArgumentException e) {
            log.error("Invalid account number format: {}", number, e);
            return ResponseEntity.badRequest().body("Invalid account number format: " + number);
        } catch (AccountNotFoundException e) {
            log.error("Account with number {} not found", number, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account with number " + number + " not found");
        } catch (InsufficientBalanceException e) {
            log.warn("Insufficient balance for account {}: {}", number, e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error during withdrawal", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
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
                log.info("transfer successful");
                changeBalanceMassage(UUID.fromString(numberPayer));
                changeBalanceMassage(UUID.fromString(numberRecipient));
                return ResponseEntity.ok("transfer successful");
            } else {
                log.info("transfer failed");
                return ResponseEntity.badRequest().body("transfer failed");
            }
        } catch (IllegalArgumentException e) {
            log.error("Invalid account number format", e);
            return ResponseEntity.badRequest().body("Invalid account number format");
        } catch (AccountNotFoundException e) {
            log.error("One of the accounts not found: fromAccountNumber={}, toAccountNumber={}", numberPayer, numberRecipient, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (SameAccountException | InvalidTransferAmountException | InsufficientBalanceException e) {
            log.warn("Transfer failed: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error transferring amount: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    private void changeBalanceMassage(UUID accountNumber) {
        messagingTemplate.convertAndSend("/topic/accountBalance/" + accountNumber, "Balance changed");
    }

}
