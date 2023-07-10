package com.estudo.springboot;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Springboot REST API Documentation J",
                description = "Springboot REST API Documentation J",
                version = "v1.0",
                contact = @Contact(
                        name = "Jorge",
                        email = "jorge,mello@valtech.com",
                        url = "https://github.com/jsan/SalesManagement"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://github.com/jsan"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Springboot user management Documentation",
                url = "https://github.com/jsan/SalesManagement"
        )
)
public class SpringbootRestfullWebservicesApplication
{
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

    public static void main(String[] args)
    {
        SpringApplication.run(SpringbootRestfullWebservicesApplication.class, args);
    }
}
