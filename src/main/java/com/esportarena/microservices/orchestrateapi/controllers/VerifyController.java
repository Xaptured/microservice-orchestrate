package com.esportarena.microservices.orchestrateapi.controllers;

import com.esportarena.microservices.orchestrateapi.clients.DB_client;
import com.esportarena.microservices.orchestrateapi.models.EmailValidationDetails;
import com.esportarena.microservices.orchestrateapi.utilities.StringConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Verify", description = "Orchestrate management APIs")
@Controller
public class VerifyController {

    @Autowired
    private DB_client client;

    @Operation(
            summary = "Verify the email",
            description = "Verify the email"
    )
    @GetMapping("/verify")
    public String verifyEmail(@RequestParam("id") Integer id,@RequestParam("code") Integer code, Model model){
        try{
            model.addAttribute("isServerError", false);
            model.addAttribute("isUnAuthorized", false);
            ResponseEntity<EmailValidationDetails> details = client.findDetailsById(id);
            if(details.getBody().getSecretCode().equals(code)) {
                ResponseEntity<String> response = client.verifyClientAccount(id);
                if(response.getBody().equals(StringConstants.REQUEST_PROCESSED)){
                    model.addAttribute("isVerified", true);
                }
                else {
                    model.addAttribute("isVerified", false);
                }
            } else {
                model.addAttribute("isUnAuthorized", true);
            }
        } catch (Exception exception) {
            model.addAttribute("isServerError", true);
        }
        return "verification-message-template";
    }
}
