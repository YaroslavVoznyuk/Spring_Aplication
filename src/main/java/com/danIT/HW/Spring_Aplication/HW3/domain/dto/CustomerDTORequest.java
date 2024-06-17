package com.danIT.HW.Spring_Aplication.HW3.domain.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTORequest {

    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String name;

    @Email(message = "invalid email format", regexp = "^[\\w-\\.] +@[\\w-]+(\\. [\\w-]+)*\\. [a-z]{2,}$")
    private String email;

    @Min(value = 18, message = "age cannot be less than 18")
    private Integer age;

    @Pattern(regexp = "(\\+38|0)[0-9]{9}", message = "invalid phone number format")
    private String phoneNumber;

    @Size(min = 8, message = "Name must be at least 8 characters long")
    private String password;
}
