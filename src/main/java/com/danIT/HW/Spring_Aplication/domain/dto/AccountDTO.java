package com.danIT.HW.Spring_Aplication.domain.dto;
import com.danIT.HW.Spring_Aplication.domain.Currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private Currency currency;
    private Double balance;
}
