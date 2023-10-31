/*
 * Copyright (c) 2023.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.esportarena.microservices.orchestrateapi.models;

public class EmailValidationDetails {

    private Integer clientId;
    private Integer secretCode;

    public EmailValidationDetails() {
    }

    public EmailValidationDetails(Integer clientId, Integer secretCode) {
        this.clientId = clientId;
        this.secretCode = secretCode;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(Integer secretCode) {
        this.secretCode = secretCode;
    }
}
