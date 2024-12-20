package com.esportarena.microservices.orchestrateapi.exceptions;

public class LANEmailException extends RuntimeException{
    public LANEmailException(String message, Throwable cause){
        super(message, cause);
    }

    public LANEmailException(String message){
        super(message);
    }
}
