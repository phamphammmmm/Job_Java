package org.datdev.job.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.datdev.job.utils.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(Const.Bearer))
                .components(new Components()
                        .addSecuritySchemes(Const.Bearer,
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                )
                .info(new Info().title("JOB").version("Version 1.0").description("Phạm Tiến Đạt")
                        .termsOfService("https:www.datdev.com")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")));
    }
}
