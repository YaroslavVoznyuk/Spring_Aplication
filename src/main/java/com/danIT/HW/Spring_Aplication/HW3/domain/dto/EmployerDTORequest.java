package com.danIT.HW.Spring_Aplication.HW3.domain.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployerDTORequest {
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    @Size(min = 3, message = "Address must be at least 2 characters long")
    private String address;
}
