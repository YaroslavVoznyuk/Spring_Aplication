package com.danIT.HW.Spring_Aplication.HW3.domain.dtoMapper.customer;

import com.danIT.HW.Spring_Aplication.HW3.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW3.domain.dto.CustomerDTORequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class CustomerDtoMapperRequestConfig {
    @Bean
    public ModelMapper customerDtoRequestMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        mapper.createTypeMap(CustomerDTORequest.class, Customer.class)
                .addMapping(CustomerDTORequest::getName, Customer::setName)
                .addMapping(CustomerDTORequest::getEmail, Customer::setEmail)
                .addMapping(CustomerDTORequest::getAge, Customer::setAge)
                .addMapping(CustomerDTORequest::getPhoneNumber, Customer::setPhoneNumber)
                .addMapping(CustomerDTORequest::getPassword, Customer::setPassword);

        return mapper;
    }
}
