package com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.employer;


import com.danIT.HW.Spring_Aplication.HW5.domain.Employer;
import com.danIT.HW.Spring_Aplication.HW5.domain.dto.EmployerDTORequest;
import com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.DtoMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class EmployerDtoMapperRequest extends DtoMapperFacade<Employer, EmployerDTORequest> {
    public EmployerDtoMapperRequest() {
        super(Employer.class, EmployerDTORequest.class);
    }
}
