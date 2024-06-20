package com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.account;


import com.danIT.HW.Spring_Aplication.HW5.domain.Account;
import com.danIT.HW.Spring_Aplication.HW5.domain.dto.AccountDTORequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class AccountDtoMapperRequestConfig {
    @Bean
    public ModelMapper accountDtoRequestMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        mapper.createTypeMap(AccountDTORequest.class, Account.class)
                .addMapping(AccountDTORequest::getCurrency, Account::setCurrency)
                .addMapping(AccountDTORequest::getBalance, Account::setBalance);

        return mapper;
    }
}
