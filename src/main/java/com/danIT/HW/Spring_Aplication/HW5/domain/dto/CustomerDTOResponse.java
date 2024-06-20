package com.danIT.HW.Spring_Aplication.HW5.domain.dto;

import com.danIT.HW.Spring_Aplication.HW5.domain.dto.CustomerView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTOResponse {


    @JsonView(com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerView.Summary.class)
    private Long id;

    @JsonView(com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerView.Summary.class)
    private String name;

    @JsonView(com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerView.Detailed.class)
    private String email;

    @JsonView(com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerView.Detailed.class)
    private Integer age;

    @JsonView(com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerView.Summary.class)
    private Set<UUID> accountNumbers;

    @JsonView(com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerView.Detailed.class)
    private List<String> employerNames;

    @JsonView(com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerView.Detailed.class)
    private String phoneNumber;

    @JsonView(com.danIT.HW.Spring_Aplication.HW4.domain.dto.CustomerView.Detailed.class)
    private LocalDateTime creationDate;

    @JsonView(CustomerView.Detailed.class)
    private LocalDateTime lastModifiedDate;
}
