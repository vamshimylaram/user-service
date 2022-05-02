package com.sony.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class ApiDocsConfig {

  @Bean
  public OpenAPI apiInfo() {
    return new OpenAPI()
    	    .info(new Info().title("Example User Service").version("1.0")
            .contact(new Contact().email("nathan.noble@sony.com").name("Nathan Noble")));
  }
}
