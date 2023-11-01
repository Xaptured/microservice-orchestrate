package com.esportarena.microservices.orchestrateapi.controllers;

import com.esportarena.microservices.orchestrateapi.exceptions.EmailException;
import com.esportarena.microservices.orchestrateapi.models.EmailRequest;
import com.esportarena.microservices.orchestrateapi.models.EmailResponse;
import com.esportarena.microservices.orchestrateapi.services.EmailService;
import com.esportarena.microservices.orchestrateapi.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

@Tag(name = "Orchestrate", description = "Orchestrate management APIs")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService service;

    @Operation(
            summary = "Send email to client",
            description = "Send verification email to client"
    )
    @PostMapping("/send-verification-email")
    public ResponseEntity<EmailResponse> sendVerificationEmailToClient(@RequestBody EmailRequest request) {
        EmailResponse response = new EmailResponse();
        try{
            service.sendMailToClient(request, StringConstants.VERIFICATION_EMAIL_TEMPLATE, StringConstants.VERIFY_YOUR_EMAIL);
            response.setMessage(StringConstants.MAIL_SENT_SUCCESSFULLY);
        } catch (EmailException exception) {
            response.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
