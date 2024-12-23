package com.esportarena.microservices.orchestrateapi.controllers;

import com.esportarena.microservices.orchestrateapi.exceptions.EmailException;
import com.esportarena.microservices.orchestrateapi.models.EmailDetails;
import com.esportarena.microservices.orchestrateapi.models.EmailRequest;
import com.esportarena.microservices.orchestrateapi.models.EmailResponse;
import com.esportarena.microservices.orchestrateapi.services.EmailService;
import com.esportarena.microservices.orchestrateapi.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Operation(
            summary = "Send acknowledgement email to client",
            description = "Send acknowledgement email to client"
    )
    @PostMapping("/send-acknowledgement-email")
    public ResponseEntity<EmailResponse> sendAcknowledgementEmailToClient(@RequestBody EmailDetails details){
        EmailResponse response = new EmailResponse();
        try {
            service.sendAcknowledgementEmailToClient(details, true);
            response.setMessage(StringConstants.MAIL_SENT_SUCCESSFULLY);
        } catch (EmailException exception) {
            response.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Send onboarding email to client",
            description = "Send onboarding email to client"
    )
    @PostMapping("/send-onboarding-email")
    public ResponseEntity<EmailResponse> sendOnboardingEmailToClient(@RequestBody EmailDetails details){
        EmailResponse response = new EmailResponse();
        try {
            service.sendOnboardingEmailToClient(details);
            response.setMessage(StringConstants.MAIL_SENT_SUCCESSFULLY);
        } catch (EmailException exception) {
            response.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Send onboarding complete email to client",
            description = "Send onboarding complete email to client"
    )
    @PostMapping("/send-onboarding-complete-email")
    public ResponseEntity<EmailResponse> sendOnboardingCompleteEmailToClient(@RequestBody EmailDetails details){
        EmailResponse response = new EmailResponse();
        try {
            service.sendOnboardingCompleteEmailToClient(details);
            response.setMessage(StringConstants.MAIL_SENT_SUCCESSFULLY);
        } catch (EmailException exception) {
            response.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Send robo email to client",
            description = "Send robo email to client"
    )
    @PostMapping("/send-robo-email")
    public ResponseEntity<EmailResponse> sendRoboEmailToMe(@RequestBody EmailDetails details){
        EmailResponse response = new EmailResponse();
        try {
            service.sendAcknowledgementEmailToClient(details, false);
            response.setMessage(StringConstants.MAIL_SENT_SUCCESSFULLY);
        } catch (EmailException exception) {
            response.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Send response email to client",
            description = "Send response email to client"
    )
    @PostMapping("/send-response-email")
    public ResponseEntity<EmailResponse> sendResponseEmailToClient(@RequestBody EmailDetails details){
        EmailResponse response = new EmailResponse();
        try {
            service.sendAcknowledgementEmailToClient(details, false);
            response.setMessage(StringConstants.MAIL_SENT_SUCCESSFULLY);
        } catch (EmailException exception) {
            response.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            summary = "Send booking email to client",
            description = "Send booking email to client"
    )
    @PostMapping("/send-booking-email")
    public ResponseEntity<Void> sendBookingTicketEmailToClient(@RequestBody EmailDetails details){
        service.sendBookingTicketEmailToClient(details);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
