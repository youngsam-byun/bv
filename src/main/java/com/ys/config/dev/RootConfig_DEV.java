package com.ys.config.dev;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;

/**
 * Created by byun.ys on 6/14/2017.
 */


@Configuration
@PropertySource(value = {"classpath:conf/property/app.properties", "classpath:conf/labels/label.properties", "classpath:conf/messages/message.properties", "classpath:conf/validations/validation.properties", "classpath:conf/locale/locale.properties"})
@ComponentScan(basePackages = {"com.ys.app"})
@Import(value = {DataSourceConfig_DEV.class})
public class RootConfig_DEV {

    public static final String DEFAULT_ENCODING = "UTF-8";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:conf/labels", "classpath:conf/messages", "classpath:conf/validations");
        messageSource.setDefaultEncoding(DEFAULT_ENCODING);
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

}
