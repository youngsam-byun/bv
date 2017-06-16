package com.ys;

import config.dev.AppInitializer_DEV;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("config")
@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class BvApplication {

	public static void main(String[] args) {

		//SpringApplicationBuilder springApplicationBuilder= new SpringApplicationBuilder(BvApplication.class);

				//.parent(RootConfig_DEV.class).child(ServletConfig_DEV.class);
		//		springApplicationBuilder.run();


		SpringApplication.run(BvApplication.class,args);

	}

}
