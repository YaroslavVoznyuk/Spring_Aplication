package com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.employer;


import com.danIT.HW.Spring_Aplication.HW5.domain.Employer;
import com.danIT.HW.Spring_Aplication.HW5.domain.dto.EmployerDTORequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class EmployerDtoMapperRequestConfig {
    @Bean
    public ModelMapper employerDtoRequestMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        mapper.createTypeMap(EmployerDTORequest.class, Employer.class)
                .addMapping(EmployerDTORequest::getName, Employer::setName)
                .addMapping(EmployerDTORequest::getAddress, Employer::setAddress);

        return mapper;
    }
}
