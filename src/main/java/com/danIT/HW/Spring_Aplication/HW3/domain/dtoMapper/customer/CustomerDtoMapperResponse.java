package com.danIT.HW.Spring_Aplication.HW3.domain.dtoMapper.customer;


import com.danIT.HW.Spring_Aplication.HW3.domain.Account;
import com.danIT.HW.Spring_Aplication.HW3.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW3.domain.Employer;
import com.danIT.HW.Spring_Aplication.HW3.domain.dto.CustomerDTOResponse;
import com.danIT.HW.Spring_Aplication.HW3.domain.dtoMapper.DtoMapperFacade;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerDtoMapperResponse extends DtoMapperFacade<Customer, CustomerDTOResponse> {
    public CustomerDtoMapperResponse() {
        super(Customer.class, CustomerDTOResponse.class);
    }

    @Override
    protected void decorateDto(CustomerDTOResponse dto, Customer entity) {
        if (entity.getAccounts() != null) {
            Set<UUID> accountNumbers = new HashSet<>();
            for (Account account : entity.getAccounts()) {
                UUID number = account.getNumber();
                accountNumbers.add(number);
            }
            dto.setAccountNumbers(accountNumbers);
        }

        if (entity.getEmployers() != null) {
            List<String> employerNames = new ArrayList<>();
            for (Employer employer : entity.getEmployers()) {
                String name = employer.getName();
                employerNames.add(name);
            }
            dto.setEmployerNames(employerNames);
        }
    }
}
