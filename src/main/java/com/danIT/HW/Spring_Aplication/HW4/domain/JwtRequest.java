package com.danIT.HW.Spring_Aplication.HW4.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {

    private String login;
    private String password;

}
