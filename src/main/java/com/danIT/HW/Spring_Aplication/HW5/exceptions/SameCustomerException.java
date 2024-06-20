package com.danIT.HW.Spring_Aplication.HW5.exceptions;

public class SameCustomerException extends RuntimeException {
    public SameCustomerException(String message) {
        super(message);
    }
}