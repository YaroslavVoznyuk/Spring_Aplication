package com.danIT.HW.Spring_Aplication.HW3.domain.dtoMapper.account;


import com.danIT.HW.Spring_Aplication.HW3.domain.Account;
import com.danIT.HW.Spring_Aplication.HW3.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW3.domain.dto.AccountDTOResponse;
import com.danIT.HW.Spring_Aplication.HW3.domain.dtoMapper.DtoMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class AccountDtoMapperResponse extends DtoMapperFacade<Account, AccountDTOResponse> {
    public AccountDtoMapperResponse() {
        super(Account.class, AccountDTOResponse.class);
    }

    protected void decorateDto(AccountDTOResponse dto, Account entity) {
        Customer customer = entity.getCustomer();
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        dto.setCustomerName(customer.getName());
    }
}
