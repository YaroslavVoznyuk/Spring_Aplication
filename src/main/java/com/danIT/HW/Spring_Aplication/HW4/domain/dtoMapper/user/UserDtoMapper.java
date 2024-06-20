package com.danIT.HW.Spring_Aplication.HW4.domain.dtoMapper.user;

import com.danIT.HW.Spring_Aplication.HW3.domain.dtoMapper.DtoMapperFacade;
import com.danIT.HW.Spring_Aplication.HW4.domain.User;
import com.danIT.HW.Spring_Aplication.HW4.domain.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserDtoMapper extends DtoMapperFacade<User, UserDTO> {

    public UserDtoMapper() {
        super(User.class, UserDTO.class);
    }

    @Override
    protected void decorateDto(UserDTO dto, User user) {
        dto.setUserName(user.getUserName());
        dto.setRoles(String.valueOf(user.getRoles()));
    }
}

