package com.internship.changeit.service.impl.registrationService;

import com.internship.changeit.model.User;
import com.internship.changeit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final String resourceUri = "/api/v1/user/registrationConfirm";

    private final UserService userService;
    private final JavaMailSender mailSender;


    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        this.confirmRegistration(onRegistrationCompleteEvent);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();

        userService.createVerificationToken(user, token);

        final SimpleMailMessage email = constructEmailMessage(event, user, token);
        mailSender.send(email);
    }

    private SimpleMailMessage constructEmailMessage(final OnRegistrationCompleteEvent event, final User user, final String token) {
        final String address = user.getEmail();
        final String subject = "Registration Confirmation";
        final String appUrl = event.getAppUrl() + resourceUri + "?token="+token;

        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(address);
        email.setSubject(subject);
        email.setText("Please open following URL to verify your account: \r\n" + appUrl);

        return email;
    }
}
