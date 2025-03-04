package com.exercise.project.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                description = "OpenApi documentation for Trackerciser application",
                title = "Trackerciser OpenApi documentation",
                version = "1.0",
                contact = @Contact(
                        name = "Arturas",
                        email = "arpul97@gmail.com",
                        url = "https://github.com/ArturKa97/Trackerciser"

                )
        )
        ,
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080/swagger-ui/index.html"
                ),
                @Server(
                        description = "Prod ENV(Coming soon)",
                        url = "http://localhost:8080/swagger-ui/index.html"
                )
        }
        ,security = {
                @SecurityRequirement(
                        name = "BearerAuth"
                )
}
)
@SecurityScheme(
        name = "BearerAuth",
        description = "JWT token authentication",
        scheme = "bearer",
        type = SecuritySchemeType.APIKEY,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.QUERY
)

public class OpenApiConfig {

}
