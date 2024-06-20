package com.danIT.HW.Spring_Aplication.HW5.exceptions;

public class CustomerForEmployerNotFoundException extends RuntimeException {
    public CustomerForEmployerNotFoundException(String message) {
        super(message);
    }
}