package com.github.rafinhalq.securityexample.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfiguration {
    private final Environment env;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .components(new Components().addSecuritySchemes("JWT", securityScheme()))
            .info(info());
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
            .name("JWT")
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER);
    }

    private Info info() {
        return new Info()
            .title("Security Example")
            .description("JWT Spring Security Implementation")
            .version(env.getProperty("info.app.version"))
            .contact(new Contact()
                .name("rafinhaLQ")
                .url("https://github.com/rafinhaLQ")
                .email("rafinhalq@gmail.com"));
    }

}
