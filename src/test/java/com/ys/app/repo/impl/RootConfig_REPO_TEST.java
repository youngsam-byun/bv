package com.ys.app.repo.impl;


import com.ys.config.dev.DataSourceConfig_DEV;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@Import(value = {DataSourceConfig_DEV.class})
@ComponentScan(basePackages = { "com.ys.app.repo" })
@TestPropertySource(value = {"classpath:conf/property/app.properties", "classpath:conf/labels/label.properties", "classpath:conf/messages/message.properties", "classpath:conf/validations/validation.properties"})
public class RootConfig_REPO_TEST extends WebMvcConfigurationSupport {

	private static final String DEFAULT_ENCODING = "UTF-8";

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:conf.labels","classpath:conf.messages","classpath:conf.validations");
		messageSource.setDefaultEncoding(DEFAULT_ENCODING);
		return messageSource;
	}


}
