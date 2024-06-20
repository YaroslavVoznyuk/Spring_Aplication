package com.danIT.HW.Spring_Aplication.HW5.exceptions;

public class SameAccountException extends RuntimeException {
    public SameAccountException(String message) {
        super(message);
    }
}
