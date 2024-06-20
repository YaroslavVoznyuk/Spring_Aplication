package com.danIT.HW.Spring_Aplication.HW5.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployerDTOResponse {
    private Long id;
    private String name;
    private String address;
    private List<String> customersNames;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
}
