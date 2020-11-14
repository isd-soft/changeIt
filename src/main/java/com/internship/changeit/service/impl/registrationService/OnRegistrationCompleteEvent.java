package com.internship.changeit.service.impl.registrationService;

import com.internship.changeit.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final User user;

    public OnRegistrationCompleteEvent( String appUrl, User user) {
        super(user);
        this.appUrl = appUrl;
        this.user = user;
    }
}
