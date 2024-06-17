package com.danIT.HW.Spring_Aplication.HW3.domain.dtoMapper.account;

import com.danIT.HW.Spring_Aplication.HW3.domain.Account;

import com.danIT.HW.Spring_Aplication.HW3.domain.dto.AccountDTORequest;
import com.danIT.HW.Spring_Aplication.HW3.domain.dtoMapper.DtoMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class AccountDtoMapperRequest extends DtoMapperFacade<Account, AccountDTORequest> {
    public AccountDtoMapperRequest() {
        super(Account.class, AccountDTORequest.class);
    }
}
