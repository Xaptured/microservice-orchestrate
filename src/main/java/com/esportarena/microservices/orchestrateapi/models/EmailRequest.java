package com.esportarena.microservices.orchestrateapi.models;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EmailRequest {

    private String clientEmail;

    public EmailRequest() {
    }

    public EmailRequest(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

}
