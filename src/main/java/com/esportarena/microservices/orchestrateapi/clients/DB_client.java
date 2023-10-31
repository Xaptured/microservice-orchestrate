package com.esportarena.microservices.orchestrateapi.clients;

import com.esportarena.microservices.orchestrateapi.models.ClientCredential;
import com.esportarena.microservices.orchestrateapi.models.EmailValidationDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "DATABASE-SERVICE")
public interface DB_client {

    @GetMapping("/clients/get-client-id/{email}")
    public ResponseEntity<EmailValidationDetails> findClientId(@PathVariable String email);

    @PostMapping("/clients/verify-account/{clientId}")
    public ResponseEntity<String> verifyClientAccount(@PathVariable Integer clientId);

    @PostMapping("/clients/save-secret-code")
    public ResponseEntity<String> saveSecretCode(@RequestBody EmailValidationDetails details);

    @PostMapping("/clients/save-secret-code/{id}")
    public ResponseEntity<EmailValidationDetails> findDetailsById(@PathVariable Integer id);
}
