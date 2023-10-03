package com.example.demotingeso.Excepciones;

public class CuotaPagoAlreadyPaidException extends RuntimeException {

    public CuotaPagoAlreadyPaidException(String message) {
        super(message);
    }

    public CuotaPagoAlreadyPaidException(String message, Throwable cause) {
        super(message, cause);
    }
}
