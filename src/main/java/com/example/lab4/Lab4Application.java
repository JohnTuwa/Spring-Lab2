package com.example.lab4;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Lab 4",
                contact = @Contact(
                        name = "Tyshniuk Ivan",
                        email = "tvo1805@gmail.com"
                ),

                description = "Lab work 4 for spring",
                version = "1.0",
                license = @License(name = "Apache 2.0", url =
                        "https://www.apache.org/licenses/LICENSE-2.0.html")

        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "test server")
        }

)

@SpringBootApplication
public class Lab4Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab4Application.class, args);
    }

}
