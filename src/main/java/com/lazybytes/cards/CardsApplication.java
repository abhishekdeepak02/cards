package com.lazybytes.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
        info = @Info(
                title = "Cards Service API",
                version = "v1",
                description = "API Documentation for Cards Service",
                contact = @Contact(
                        name = "Abhishek Deepak",
                    email = "learner@lazybytes.com",
                    url = "https://www.lazybytes.com"
                ),
                license = @License(
                    name = "Apache 2.0",
                    url = "https://www.lazybytes.com"
                )),
                externalDocs = @ExternalDocumentation(
                        description = "LazyBank Cards microservice REST API Documentation",
                        url = "http://localhost:9000/swagger-ui.html"

        )
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
