package com.danIT.HW.Spring_Aplication.HW5.exceptions;

public class SameEmployerException extends RuntimeException {
    public SameEmployerException(String message) {
        super(message);
    }
}
