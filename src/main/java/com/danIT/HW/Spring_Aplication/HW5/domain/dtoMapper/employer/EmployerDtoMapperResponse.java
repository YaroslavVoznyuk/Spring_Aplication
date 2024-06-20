package com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.employer;

import com.danIT.HW.Spring_Aplication.HW5.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW5.domain.Employer;
import com.danIT.HW.Spring_Aplication.HW5.domain.dto.EmployerDTOResponse;
import com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.DtoMapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerDtoMapperResponse extends DtoMapperFacade<Employer, EmployerDTOResponse> {

    public EmployerDtoMapperResponse() {
        super(Employer.class, EmployerDTOResponse.class);
    }

    @Override
    protected void decorateDto(EmployerDTOResponse dto, Employer entity) {
        List<String> customerNames = entity.getCustomers().stream()
                .map(Customer::getName)
                .toList();
        dto.setCustomersNames(customerNames);
    }
}
