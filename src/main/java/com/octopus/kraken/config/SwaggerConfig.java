package com.octopus.kraken.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    private static final String TITLE = "Energetiq API";
    private static final String DESCRIPTION = "Description API";
    private static final String BASE_PACKAGE = "com.octopus.kraken.controller";
    private static final String VERSION = "v1";

    @Bean
    public OpenAPI registrationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(TITLE)
                        .description(DESCRIPTION)
                        .version(VERSION))
                        .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));

    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }
}

