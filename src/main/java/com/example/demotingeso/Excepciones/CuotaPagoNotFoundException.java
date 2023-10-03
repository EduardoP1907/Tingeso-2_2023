package com.example.demotingeso.Excepciones;

public class CuotaPagoNotFoundException extends RuntimeException {

    public CuotaPagoNotFoundException(String message) {
        super(message);
    }

    public CuotaPagoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
