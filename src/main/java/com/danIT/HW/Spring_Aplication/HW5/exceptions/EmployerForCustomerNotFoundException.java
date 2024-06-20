package com.danIT.HW.Spring_Aplication.HW5.exceptions;

public class EmployerForCustomerNotFoundException extends RuntimeException {
    public EmployerForCustomerNotFoundException(String message) {
        super(message);
    }
}