package com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.customer;


import com.danIT.HW.Spring_Aplication.HW5.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW5.domain.dto.CustomerDTORequest;
import com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.DtoMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class CustomerDtoMapperRequest extends DtoMapperFacade<Customer, CustomerDTORequest> {

    public CustomerDtoMapperRequest() {
        super(Customer.class, CustomerDTORequest.class);
    }
}
