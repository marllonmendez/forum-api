package com.marllonmendez.forum.infra.swagger;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ConfigSwagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .info(new Info()
                        .title("Forum Hub API")
                        .description("Documentação da API Forum Hub")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("marllonmendez")
                                .url("https://marllonmendez.vercel.app/")
                                .email("marlonmendesor@gmail.com")
                        )
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("local server")
                ))
                .addTagsItem(new Tag().name("Auth"))
                .addTagsItem(new Tag().name("Tópicos"));
    }

}
