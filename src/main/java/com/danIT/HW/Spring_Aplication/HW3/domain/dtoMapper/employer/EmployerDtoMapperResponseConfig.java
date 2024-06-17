package com.danIT.HW.Spring_Aplication.HW3.domain.dtoMapper.employer;


import com.danIT.HW.Spring_Aplication.HW3.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW3.domain.Employer;
import com.danIT.HW.Spring_Aplication.HW3.domain.dto.EmployerDTOResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class EmployerDtoMapperResponseConfig {

    @Bean
    public ModelMapper employerDtoResponseMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        mapper.createTypeMap(Employer.class, EmployerDTOResponse.class)
                .addMapping(Employer::getId, EmployerDTOResponse::setId)
                .addMapping(Employer::getName, EmployerDTOResponse::setName)
                .addMapping(Employer::getAddress, EmployerDTOResponse::setAddress)
                .addMapping(src -> {
                    if (src.getCustomers() != null) {
                        return src.getCustomers().stream().map(Customer::getName).collect(Collectors.toList());
                    }
                    return Collections.emptyList();
                }, EmployerDTOResponse::setCustomersNames)
                .addMapping(Employer::getCreationDate, EmployerDTOResponse::setCreationDate)
                .addMapping(Employer::getLastModifiedDate, EmployerDTOResponse::setLastModifiedDate)
        ;

        return mapper;
    }
}
