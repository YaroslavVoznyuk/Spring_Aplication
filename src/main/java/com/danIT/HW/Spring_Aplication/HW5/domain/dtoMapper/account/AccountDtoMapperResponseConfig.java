package com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.account;


import com.danIT.HW.Spring_Aplication.HW5.domain.Account;
import com.danIT.HW.Spring_Aplication.HW5.domain.dto.AccountDTOResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;
@Configuration
public class AccountDtoMapperResponseConfig {
    @Bean
    public ModelMapper accountDtoResponseMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        mapper.createTypeMap(Account.class, AccountDTOResponse.class)
                .addMapping(Account::getId, AccountDTOResponse::setId)
                .addMapping(Account::getNumber, AccountDTOResponse::setNumber)
                .addMapping(Account::getCurrency, AccountDTOResponse::setCurrency)
                .addMapping(src -> src.getCustomer().getName(), AccountDTOResponse::setCustomerName)
                .addMapping(Account::getCreationDate, AccountDTOResponse::setCreationDate)
                .addMapping(Account::getLastModifiedDate, AccountDTOResponse::setLastModifiedDate)
        ;

        return mapper;
    }
}
