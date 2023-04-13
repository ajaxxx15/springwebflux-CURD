package com.demo.springwebflux.conf;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi productsOpenApi() {
        String[] paths = {"/product/**"};
        return GroupedOpenApi.builder().
                group("products")
                .addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Spring webflux functional web crud example")
                        .version("1.0").description("sample documents")))
                .pathsToMatch(paths)
                .build();
    }

}
