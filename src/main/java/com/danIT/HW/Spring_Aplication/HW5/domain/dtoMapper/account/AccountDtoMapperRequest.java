package com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.account;

import com.danIT.HW.Spring_Aplication.HW5.domain.Account;

import com.danIT.HW.Spring_Aplication.HW5.domain.dto.AccountDTORequest;
import com.danIT.HW.Spring_Aplication.HW5.domain.dtoMapper.DtoMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class AccountDtoMapperRequest extends DtoMapperFacade<Account, AccountDTORequest> {
    public AccountDtoMapperRequest() {
        super(Account.class, AccountDTORequest.class);
    }
}
