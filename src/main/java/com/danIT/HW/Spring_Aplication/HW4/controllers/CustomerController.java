package com.danIT.HW.Spring_Aplication.HW4.controllers;

import com.danIT.HW.Spring_Aplication.HW4.domain.Currency;
import com.danIT.HW.Spring_Aplication.HW4.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerDTORequest;
import com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerDTOResponse;
import com.danIT.HW.Spring_Aplication.HW4.domain.dtoMapper.customer.CustomerDtoMapperResponse;
import com.danIT.HW.Spring_Aplication.HW4.services.DefaultCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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


    @Operation(summary = "Get all customers")
    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize
    )
    {
        Page<CustomerDTOResponse> customersPage = customerService.findAll(0, Integer.MAX_VALUE, PageRequest.of(page, pageSize)).map(customerDtoMapperResponse::convertToDto);
        List<CustomerDTOResponse> customers = customersPage.getContent();

        return ResponseEntity.ok().body(customers);
    }

    @Operation(summary = "Get customer")
    @GetMapping("{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(customerService.getOne(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "Create customer")
    @PostMapping
    public Customer createCustomer(@RequestBody CustomerDTORequest customer) {
        return customerService.createACustomer(customer);
    }

    @Operation(summary = "Change customer")
    @PostMapping("/{id}")
    public ResponseEntity<?> changeCustomer(@PathVariable Long id, @RequestBody CustomerDTORequest customer) {
        try {
            return ResponseEntity.ok(customerService.changeCustomerData(customer, id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete customer")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {

        try{
            customerService.deleteById(id);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
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
        if(customerService.deleteTheAccountFromTheCustomer(id, UUID.fromString(accountNumber))) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }

}
