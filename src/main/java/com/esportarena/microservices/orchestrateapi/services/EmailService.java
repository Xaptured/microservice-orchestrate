package com.esportarena.microservices.orchestrateapi.services;

import com.esportarena.microservices.orchestrateapi.clients.DB_client;
import com.esportarena.microservices.orchestrateapi.exceptions.EmailException;
import com.esportarena.microservices.orchestrateapi.models.ClientCredential;
import com.esportarena.microservices.orchestrateapi.models.EmailDetails;
import com.esportarena.microservices.orchestrateapi.models.EmailRequest;
import com.esportarena.microservices.orchestrateapi.models.EmailValidationDetails;
import com.esportarena.microservices.orchestrateapi.servicehelpers.EmailServiceHelper;
import com.esportarena.microservices.orchestrateapi.utilities.IntegerGenerator;
import com.esportarena.microservices.orchestrateapi.utilities.PropertiesReader;
import com.esportarena.microservices.orchestrateapi.utilities.StringConstants;
import com.netflix.discovery.converters.Auto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private static final String SENDER_EMAIL = PropertiesReader.getProperty(StringConstants.SENDER);
    @Autowired
    private DB_client client;
    @Autowired
    private EmailServiceHelper helper;
    @Autowired
    private IntegerGenerator generator;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMailToClient(EmailRequest request, String templateName, String subject) throws EmailException {
        ResponseEntity<EmailValidationDetails> response = client.findClientId(request.getClientEmail());
        EmailValidationDetails details = response.getBody();
        if(details != null && details.getClientId() != null) {
            Integer randomInteger = generator.generateFiveDigitRandomInteger();
            details.setSecretCode(randomInteger);
            ResponseEntity<String> dbResponse = client.saveSecretCode(details);
            if(dbResponse.getBody().equals(StringConstants.REQUEST_PROCESSED)) {
                Context context = new Context();
                context.setVariable("id",details.getClientId());
                context.setVariable("code",randomInteger);
                helper.sendEmailWithHtmlTemplate(request.getClientEmail(), subject, templateName, context);
            } else {
                LOGGER.error(StringConstants.INTERNAL_SERVER_ERROR);
                throw new EmailException(StringConstants.INTERNAL_SERVER_ERROR);
            }
        } else {
            LOGGER.error(StringConstants.CLIENT_NOT_EXIST);
            throw new EmailException(StringConstants.CLIENT_NOT_EXIST);
        }
    }

    public void sendAcknowledgementEmailToClient(EmailDetails details, boolean isAcknowledgement) throws EmailException {
        try {
            SimpleMailMessage mailMessage = getMailMessage(details, isAcknowledgement);

            javaMailSender.send(mailMessage);
            LOGGER.info(StringConstants.MAIL_SENT_SUCCESSFULLY);
        } catch (Exception exception) {
            LOGGER.info(StringConstants.ERROR_OCCURRED_SENDING_EMAIL);
            throw new EmailException(StringConstants.ERROR_OCCURRED_SENDING_EMAIL, exception);
        }
    }

    private SimpleMailMessage getMailMessage(EmailDetails details, boolean isAcknowledgement) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(SENDER_EMAIL);
        mailMessage.setTo(details.getRecipient());
        if(isAcknowledgement){
            mailMessage.setText(StringConstants.ACKNOWLEDGE_BODY);
            mailMessage.setSubject(StringConstants.ACKNOWLEDGE_SUBJECT);
        } else {
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
        }
        return mailMessage;
    }
}
