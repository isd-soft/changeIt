package com.internship.changeit.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Getter
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app")
public class AppConfigBean {

    @NotNull
    private String appUrl;
}
