package com.senac.taskagile.taskagileback.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info( new Info()
                .title("Task Agile")
                .version("1.0")
                .description("Api responsavel por documentar Task Agile")
                .termsOfService("https://www.linkedin.com/in/renato-fraga-249718314/")
        );
    }


}
