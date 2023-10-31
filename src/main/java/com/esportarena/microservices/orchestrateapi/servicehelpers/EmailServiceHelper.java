package com.esportarena.microservices.orchestrateapi.servicehelpers;

import com.esportarena.microservices.orchestrateapi.exceptions.EmailException;
import com.esportarena.microservices.orchestrateapi.utilities.StringConstants;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceHelper.class);

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailServiceHelper(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmailWithHtmlTemplate(String toEmail, String subject, String templateName, Context context) throws EmailException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            LOGGER.info("Sending mail to {} with subject: {} and template: {}", toEmail, subject, templateName);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            String htmlContent = templateEngine.process(templateName, context);
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
            LOGGER.info(StringConstants.MAIL_SENT_SUCCESSFULLY);
        } catch (MessagingException exception) {
            throw new EmailException(StringConstants.ERROR_OCCURRED_SENDING_EMAIL, exception);
        }
    }
}
