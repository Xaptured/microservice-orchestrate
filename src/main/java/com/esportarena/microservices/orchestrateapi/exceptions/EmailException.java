package com.esportarena.microservices.orchestrateapi.exceptions;

public class EmailException extends  Exception{

    public EmailException(String message, Throwable cause){
        super(message, cause);
    }
}
