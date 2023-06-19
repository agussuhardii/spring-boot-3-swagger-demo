package com.agussuhardi.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
/**
 * @author agussuhardi
 * @created 19/06/23/06/2023 :19.31
 * @project spring-boot-3-swagger-demo
 */
@Component
@Configuration
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String appName;

    /**
     * Swagger config page and input Bearer token
     * @return Swagger config Bean
     */
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        final String apiTitle = String.format("%s API", StringUtils.capitalize(appName));
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")))
                .info(new Info().title(apiTitle).version("Dev"));
    }

    /**
     * any rest api with prefix /api
     * @return prefix /api
     */
    @Bean
    public GroupedOpenApi anyApi() {
        String[] paths = {"/**"};
        return GroupedOpenApi.builder().group("any").pathsToMatch(paths).build();
    }


    /**
     * grouping api authentication
     * @return grouping api Bean
     */
    @Bean
    public GroupedOpenApi demoApi() {
        String[] paths = {"/user/**"};
        return GroupedOpenApi.builder().group("user").pathsToMatch(paths).build();
    }

}