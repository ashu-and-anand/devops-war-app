package com.devops.demo_war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoWarApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWarApplication.class, args);
	}

}

// import org.springframework.boot.builder.SpringApplicationBuilder;
// import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// public class DemoWarApplication extends SpringBootServletInitializer {

//     @Override
//     protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//         return application.sources(DemoWarApplication.class);
//     }
// }