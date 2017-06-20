package com.ys.config.dev;


import com.ys.web.aop.WebAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by byun.ys on 6/19/2017.
 */
@Configuration
@EnableAspectJAutoProxy
public class AOPConfig {

    @Bean
    public WebAspect webAspect(){
        return  new WebAspect();
    }

}
