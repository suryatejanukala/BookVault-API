package com.thinkconstructive.book_store.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BookStore API")
                        .version("1.0")
                        .description("Bookstore REST API Documentation")
                        .termsOfService("Bookstore Service Terms")
                        .contact(new Contact()
                                .name("Surya")
                                .email("suryatejanukala2@gamil.com")
                                .url("http://thinkconstructive.com"))
                        .license(new License()
                                .name("Think Constructive License")
                                .url("http://thinkconstructive.com")));
    }
}
