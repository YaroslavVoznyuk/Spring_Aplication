package com.danIT.HW.Spring_Aplication.HW5.controllers;

import com.danIT.HW.Spring_Aplication.HW5.domain.Currency;
import com.danIT.HW.Spring_Aplication.HW5.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW5.domain.dto.CustomerDTORequest;
import com.danIT.HW.Spring_Aplication.HW5.domain.dto.CustomerDTOResponse;
import com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.customer.CustomerDtoMapperRequest;
import com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.customer.CustomerDtoMapperResponse;
import com.danIT.HW.Spring_Aplication.HW5.exceptions.AccountNotFoundException;
import com.danIT.HW.Spring_Aplication.HW5.exceptions.CustomerNotFoundException;
import com.danIT.HW.Spring_Aplication.HW5.exceptions.InsufficientBalanceException;
import com.danIT.HW.Spring_Aplication.HW5.exceptions.SameCustomerException;
import com.danIT.HW.Spring_Aplication.HW5.services.DefaultCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/customers")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final DefaultCustomerService customerService;

    private final CustomerDtoMapperResponse customerDtoMapperResponse;
    private final CustomerDtoMapperRequest customerDtoMapperRequest;


    @Operation(summary = "Get all customers")
    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        log.info("getting all customers");
        try {
            Page<CustomerDTOResponse> customersPage = customerService.findAll(0, Integer.MAX_VALUE, PageRequest.of(page, pageSize)).map(customerDtoMapperResponse::convertToDto);
            List<CustomerDTOResponse> customers = customersPage.getContent();
            log.info("received {} customers", customers.size());
            return ResponseEntity.ok().body(customers);
        } catch (Exception e) {
            log.error("An unexpected error occurred while retrieving customers", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }


    }

    @Operation(summary = "Get customer")
    @GetMapping("{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {
        log.info("getting customer with id {}", id);
        try {
            log.info("received customer success");
            return ResponseEntity.ok(customerService.getOne(id));
        } catch (CustomerNotFoundException e) {
            log.error("Customer not found with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer with ID " + id + " not found");
        } catch (Exception e) {
            log.error("An unexpected error occurred while fetching customer with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }

    }

    @Operation(summary = "Create customer")
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTORequest customer) {
        log.info("Creating a new customer");
        Customer newCustomer = customerDtoMapperRequest.convertToEntity(customer);
        try {
            customerService.save(newCustomer);
            log.info("Customer created successfully with ID {}", newCustomer.getId());
            return ResponseEntity.ok(customerDtoMapperResponse.convertToDto(newCustomer));
        } catch (SameCustomerException e) {
            log.error("Failed to create customer: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Change customer")
    @PostMapping("/{id}")
    public ResponseEntity<?> changeCustomer(@PathVariable Long id, @RequestBody CustomerDTORequest customer) {
        log.info("Changing a customer");
        try {
            log.info("Change customer successfully with ID {}", id);
            return ResponseEntity.ok(customerService.changeCustomerData(customer, id));
        } catch (CustomerNotFoundException e) {
            log.error("Customer not found with ID {}", id, e);
            return ResponseEntity.badRequest().body("Customer with ID " + id + " not found");
        } catch (SameCustomerException e) {
            log.error("Failed to update customer: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred while updating the customer with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the customer");
        }
    }

    @Operation(summary = "Delete customer")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        log.info("Deleting a customer");
        try {
            log.info("Deleting a customer successfully with ID {}", id);
            customerService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException e) {
            log.error("Customer not found with ID {}", id, e);
            return ResponseEntity.badRequest().body("Customer with ID " + id + " not found");
        } catch (Exception e) {
            log.error("An error occurred while deleting the customer with id {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the customer");
        }
    }

    @Operation(summary = "Create An Account For A Specific Customer")
    @PostMapping("/{id}/{currency}")
    public ResponseEntity<?> createAnAccountForASpecificCustomer(@PathVariable Long id, @PathVariable Currency currency) {
        log.info("Creating an account for a specific customer");
        try {
            log.info("Creating an account for a specific customer successfully");
            return ResponseEntity.ok(customerService.createAnAccountForASpecificCustomer(id, currency));
        } catch (CustomerNotFoundException e) {
            log.error("Customer not found with ID {}", id, e);
            return ResponseEntity.badRequest().body("Customer with ID " + id + " not found");
        } catch (AccountNotFoundException e) {
            log.error("Account cannot be null");
            return ResponseEntity.badRequest().body("Account cannot be null");
        } catch (InsufficientBalanceException e) {
            log.error("Insufficient balance for customer with ID {}", id, e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred while adding the account to the customer with id {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the account to the customer");
        }

    }


    @Operation(summary = "Delete The Account From The Customer")
    @DeleteMapping("/{id}/{accountNumber}")
    public ResponseEntity<?> deleteTheAccountFromTheCustomer(@PathVariable Long id,
                                                             @PathVariable String accountNumber) {
        log.info("Deleting an account for a specific customer");
        try{
            log.info("Deleting an account for a specific customer successfully");
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            log.error("Invalid account number format: {}", accountNumber, e);
            return ResponseEntity.badRequest().body("Invalid account number format: " + accountNumber);
        } catch (CustomerNotFoundException e) {
            log.error("Customer not found with ID {}", id, e);
            return ResponseEntity.badRequest().body("Customer with ID " + id + " not found");
        } catch (AccountNotFoundException e) {
            log.error("Account with number {} not found for customer with ID {}", accountNumber, id);
            return ResponseEntity.badRequest().body("Account with number " + accountNumber + " not found for customer with ID " + id);
        } catch (Exception e) {
            log.error("An error occurred while deleting the account of the customer with id {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the account of the customer");
        }


    }

}
