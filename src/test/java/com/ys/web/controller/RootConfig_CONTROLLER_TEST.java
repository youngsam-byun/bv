package com.ys.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * Created by byun.ys on 4/18/2017.
 */
@Configuration
@PropertySource(value = {"classpath:conf/property/app.properties", "classpath:conf/labels/label.properties", "classpath:conf/messages/message.properties", "classpath:conf/validations/validation.properties", "classpath:conf/log4j/log4j.properties"})
@EnableWebSecurity
public class RootConfig_CONTROLLER_TEST {
    private static final String DEFAULT_ENCODING = "UTF-8";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:properties","classpath:conf.labels","classpath:conf.messages","classpath:conf.validations");
        messageSource.setDefaultEncoding(DEFAULT_ENCODING);
        return messageSource;
    }
}
