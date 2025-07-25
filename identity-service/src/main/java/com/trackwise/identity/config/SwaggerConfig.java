package com.trackwise.identity.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TrackWise Identity Service API")
                        .version("1.0")
                        .description("REST API for auth and user registration in TrackWise")
                        .contact(new Contact().name("Dakshin G").email("dakshin.g@outlook.com")));
    }
}
