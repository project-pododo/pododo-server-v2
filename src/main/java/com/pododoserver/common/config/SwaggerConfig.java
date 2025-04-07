package com.pododoserver.common.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pododo-API Documents")
                        .version("1.0.0")
                        .description("https://github.com/project-pododo"));
    }
}
