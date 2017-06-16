package config.dev;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import java.util.Locale;

/**
 * Created by byun.ys on 6/14/2017.
 */

@Configuration
@SpringBootApplication
@EnableAspectJAutoProxy
@Profile("dev")
public class ChildConfig_DEV extends WebMvcConfigurerAdapter{

    private static final int MAX_UPLOAD_SIZE = 5242880;
    private static final int COOKIE_MAXAGE = 3600;
    private static final String defaultCountry = "US";
    private static final String defaultLanguage = "en";
    private static final String COOKIE_NAME = "cookie_name";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO Auto-generated method stub
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }


    @Bean
    public ViewResolver resourceBundleViewResolver() {
        ResourceBundleViewResolver resourceBundleViewResolver = new ResourceBundleViewResolver();
        resourceBundleViewResolver.setOrder(1);
        resourceBundleViewResolver.setBasename("views");
        return resourceBundleViewResolver;
    }

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setOrder(2);
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] { "classpath:tiles/layout.xml" });
        return tilesConfigurer;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding(RootConfig_DEV.DEFAULT_ENCODING);
        commonsMultipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE);

        return commonsMultipartResolver;
    }


    // localResolver detected by name 'localResolver' compulsory
    @Bean(name = { "localResolver" })
    public LocaleResolver LocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setCookieName(COOKIE_NAME);
        cookieLocaleResolver.setDefaultLocale(new Locale(defaultLanguage, defaultCountry));
        cookieLocaleResolver.setCookieMaxAge(COOKIE_MAXAGE);
        return cookieLocaleResolver;
    }

    // localChangeInterceptor
    @Bean
    public LocaleChangeInterceptor localChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("locale");
        return localeChangeInterceptor;
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
