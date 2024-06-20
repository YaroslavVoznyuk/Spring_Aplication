package com.danIT.HW.Spring_Aplication.HW4.controllers;

import com.danIT.HW.Spring_Aplication.HW4.domain.Account;
import com.danIT.HW.Spring_Aplication.HW4.domain.Currency;
import com.danIT.HW.Spring_Aplication.HW4.domain.dto.AccountDTORequest;
import com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerDTORequest;
import com.danIT.HW.Spring_Aplication.HW4.services.DefaultCustomerService;
import com.danIT.HW.Spring_Aplication.HW4.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerDTOResponse;
import com.danIT.HW.Spring_Aplication.HW4.domain.dtoMapper.customer.CustomerDtoMapperResponse;
import com.danIT.HW.Spring_Aplication.HW4.services.DefaultAccountService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DefaultCustomerService customerService;
    @MockBean
    private DefaultAccountService accountService;

    @MockBean
    private CustomerDtoMapperResponse customerDtoMapperResponse;

    @Test
    @WithMockUser(username = "testUser")
    void getAllCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Ivan Melnik");

        List<Customer> customers = Collections.singletonList(customer);
        Page<Customer> customerPage = new PageImpl<>(customers, PageRequest.of(0, 10), 1);

        CustomerDTOResponse customerDtoResponse = new CustomerDTOResponse();
        customerDtoResponse.setId(1L);
        customerDtoResponse.setName("Ivan Melnik");

        when(customerService.findAll(any(Integer.class), any(Integer.class), any(PageRequest.class)))
                .thenReturn(customerPage);
        when(customerDtoMapperResponse.convertToDto(any(Customer.class))).thenReturn(customerDtoResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/all")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Ivan Melnik")));
    }

    @Test
    @WithMockUser(username = "testUser")
    void getCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Ivan Melnik");

        CustomerDTOResponse customerDtoResponse = new CustomerDTOResponse();
        customerDtoResponse.setId(1L);
        customerDtoResponse.setName("Ivan Melnik");

        when(customerService.getOne(1))
                .thenReturn(customer);
        when(customerDtoMapperResponse.convertToDto(customer)).thenReturn(customerDtoResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/customers/{id}", 1)
                        .content(String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Ivan Melnik")));
    }

    @Test
    @WithMockUser(username = "testUser")
    void createCustomer() throws Exception {
        CustomerDTORequest request = new CustomerDTORequest();
        request.setName("Ivan Melnik");
        request.setEmail("ivenmelnik@gmail.com");
        request.setAge(23);

        when(customerService.save(any(Customer.class))).thenReturn(new Customer());

        mockMvc.perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Ivan Melnik\", \"email\": \"ivenmelnik@gmail.com\", \"age\": 23}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "testUser")
    void deleteCustomer() throws Exception {

        when(customerService.getOne(1)).thenReturn(new Customer());

        mockMvc.perform(MockMvcRequestBuilders.delete("/customers/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser")
    void createAnAccountForASpecificCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);

        AccountDTORequest accountDTORequest = new AccountDTORequest();

        accountDTORequest.setCurrency(Currency.CHF);
        accountDTORequest.setBalance(200.0);

        when(customerService.getOne(1)).thenReturn(customer);
        when(customerService.createAnAccountForASpecificCustomer(1L, Currency.CHF)).thenReturn(new Account());

        mockMvc.perform(MockMvcRequestBuilders.post("/customers/{id}/{currency}", 1, Currency.CHF))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithMockUser(username = "testUser")
    void deleteTheAccountFromTheCustomer() throws Exception {
        UUID accountNumber = UUID.fromString("18457405-2528-475a-b6d6-dbb09c4bfbad");

        when(customerService.getOne(1)).thenReturn(new Customer());

        when(customerService.deleteTheAccountFromTheCustomer(1L, accountNumber)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/customers/{id}/{accountNumber}", 1, accountNumber))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}