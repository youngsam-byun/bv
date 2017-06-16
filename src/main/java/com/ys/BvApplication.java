package com.ys;

import config.dev.RootConfig_DEV;
import config.dev.ChildConfig_DEV;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BvApplication {

	public static void main(String[] args) {

		SpringApplicationBuilder springApplicationBuilder= new SpringApplicationBuilder(BvApplication.class)
				.parent(RootConfig_DEV.class).child(ChildConfig_DEV.class);

		springApplicationBuilder.run();

	}

}
