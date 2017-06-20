package com.ys.config.dev;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 * Created by byun.ys on 6/14/2017.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ys.web"})
//@Import(value = {SecurityConfig_DEV.class})
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class,SecurityAutoConfiguration.class})
public class ServletConfig_DEV extends WebMvcConfigurerAdapter {

    private static final int MAX_UPLOAD_SIZE = 5242880;
    private static final int COOKIE_MAXAGE = 3600;
    private static final String defaultCountry = "US";
    private static final String defaultLanguage = "en";
    private static final String COOKIE_NAME = "cookie_name";

    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet);
        servletRegistrationBean.addUrlMappings("/*");
        servletRegistrationBean.setName("dispatcherServlet");
        return servletRegistrationBean;
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

    }


    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        //internalResourceViewResolver.setOrder(2);
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    // spring mobile to path /mobile
    /*
	 * @Bean public DeviceResolverHandlerInterceptor
	 * deviceResolverHandlerInterceptor() { return new
	 * DeviceResolverHandlerInterceptor(); }
	 *
	 * @Bean public SiteSwitcherHandlerInterceptor
	 * siteSwitcherHandlerInterceptor() { return
	 * SiteSwitcherHandlerInterceptor.urlPath(WebApplicationInit.MOBILE_PATH,
	 * WebApplicationInit.CONTEXT_PATH); }
	 *
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * registry.addInterceptor(deviceResolverHandlerInterceptor());
	 * registry.addInterceptor(siteSwitcherHandlerInterceptor()); }
	 */

	/*
	 * @Bean public LiteDeviceDelegatingViewResolver
	 * liteDeviceAwareViewResolver() { InternalResourceViewResolver delegate =
	 * new InternalResourceViewResolver();
	 * delegate.setPrefix("/WEB-INF/views/"); delegate.setSuffix(".jsp");
	 * delegate.setOrder(2);
	 *
	 * LiteDeviceDelegatingViewResolver resolver = new
	 * LiteDeviceDelegatingViewResolver(delegate);
	 * resolver.setMobilePrefix("mobile/");
	 *
	 * return resolver; }
	 */
}
