package com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.customer;


import com.danIT.HW.Spring_Aplication.HW5.domain.Account;
import com.danIT.HW.Spring_Aplication.HW5.domain.Customer;
import com.danIT.HW.Spring_Aplication.HW5.domain.Employer;
import com.danIT.HW.Spring_Aplication.HW5.domain.dto.CustomerDTOResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class CustomerDtoMapperResponseConfig {
    @Bean
    public ModelMapper customerDtoResponseMapperFindAll() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        mapper.createTypeMap(Customer.class, CustomerDTOResponse.class)
                .addMapping(Customer::getId, CustomerDTOResponse::setId)
                .addMapping(Customer::getName, CustomerDTOResponse::setName)
                .addMapping(Customer::getEmail, CustomerDTOResponse::setEmail)
                .addMapping(Customer::getAge, CustomerDTOResponse::setAge)
                .addMapping(Customer::getPhoneNumber, CustomerDTOResponse::setPhoneNumber)
                .addMapping(src -> {
                    if (src.getAccounts() != null) {
                        return src.getAccounts().stream().map(Account::getNumber).collect(Collectors.toSet());
                    }
                    return Collections.emptySet();
                }, CustomerDTOResponse::setAccountNumbers)
                .addMapping(src -> {
                    if (src.getEmployers() != null) {
                        return src.getEmployers().stream().map(Employer::getName).collect(Collectors.toSet());
                    }
                    return Collections.emptySet();
                }, CustomerDTOResponse::setEmployerNames)
                .addMapping(Customer::getCreationDate, CustomerDTOResponse::setCreationDate)
                .addMapping(Customer::getLastModifiedDate, CustomerDTOResponse::setLastModifiedDate)
        ;

        return mapper;
    }
}
