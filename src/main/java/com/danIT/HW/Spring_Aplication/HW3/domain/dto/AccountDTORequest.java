package com.danIT.HW.Spring_Aplication.HW3.domain.dto;

import com.danIT.HW.Spring_Aplication.HW2.domain.Currency;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTORequest {

    @NotNull(message = "currency is not specified")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull(message = "balance is not specified, it it cannot be less than zero")
    @Digits(integer = 16, message = "balance must be a valid number of up to 16 characters", fraction = 0)
    private Double balance;
}
