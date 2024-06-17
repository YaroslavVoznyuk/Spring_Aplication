package com.danIT.HW.Spring_Aplication.HW3.domain.dto;

import com.danIT.HW.Spring_Aplication.HW3.domain.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDTOResponse {

    private Long id;

    private String number;

    private Currency currency;

    private double balance;

    private String customerName;

    private LocalDateTime creationDate;

    private LocalDateTime lastModifiedDate;

}
