package com.demo.springwebflux;

import io.swagger.v3.oas.models.info.Info;
import lombok.Value;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringwebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringwebfluxApplication.class, args);
    }
}
