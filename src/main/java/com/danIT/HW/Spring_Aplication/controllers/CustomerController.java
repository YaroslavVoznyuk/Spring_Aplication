package com.danIT.HW.Spring_Aplication.controllers;

import com.danIT.HW.Spring_Aplication.domain.Account;
import com.danIT.HW.Spring_Aplication.domain.Currency;
import com.danIT.HW.Spring_Aplication.domain.Customer;
import com.danIT.HW.Spring_Aplication.domain.dto.CustomerDTO;
import com.danIT.HW.Spring_Aplication.services.DefaultCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    private final DefaultCustomerService customerService;

    public CustomerController(DefaultCustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Mock Data")
    @PutMapping
    public void mockData() {
        customerService.assignAccountsToCustomers();
    }


    @Operation(summary = "Get all customers")
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @Operation(summary = "Get customer")
    @GetMapping("{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(customerService.getOne(id).get());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "Create customer")
    @PostMapping
    public Customer createCustomer(@RequestBody CustomerDTO customer) {
        return customerService.createACustomer(customer);
    }

    @Operation(summary = "Change customer")
    @PostMapping("/{id}")
    public ResponseEntity<?> changeCustomer(@PathVariable Long id, @RequestBody CustomerDTO customer) {
        try {
            return ResponseEntity.ok(customerService.changeCustomerData(customer, id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete customer")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        if (customerService.deleteCustomer(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create An Account For A Specific Customer")
    @PostMapping("/{id}/{currency}")
    public ResponseEntity<?> createAnAccountForASpecificCustomer(@PathVariable Long id, @PathVariable Currency currency) {
        try {
            return ResponseEntity.ok(customerService.createAnAccountForASpecificCustomer(id, currency));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }


    @Operation(summary = "Delete The Account From The Customer")
    @DeleteMapping("/{id}/{accountNumber}")
    public ResponseEntity<?> deleteTheAccountFromTheCustomer(@PathVariable Long id,
                                                             @PathVariable String accountNumber) {
        if(customerService.deleteTheAccountFromTheCustomer(id,accountNumber)) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }

}
