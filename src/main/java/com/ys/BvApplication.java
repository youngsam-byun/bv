package com.ys;

import com.ys.config.dev.RootConfig_DEV;
import com.ys.config.dev.ServletConfig_DEV;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class BvApplication {



    static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {


//        applicationContext=SpringApplication.run(BvApplication.class,args);

        applicationContext=new SpringApplicationBuilder()
                .sources(BvApplication.class)
                .parent(RootConfig_DEV.class)
                .child(ServletConfig_DEV.class)
                .run(args);

        applicationContext.setId("child1");
        ((ConfigurableApplicationContext)applicationContext.getParent()).setId("parent");

        ApplicationContext current = applicationContext;
        while (current != null) {
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("Context: " + current.getId());
            String[] beanNames= current.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                //if (beanName.contains("bean")) {
                    System.out.println(beanName);
                //}
            }
            System.out.println("---------------------------------------------------------------------------");
            current = current.getParent();
        }
    }


}
