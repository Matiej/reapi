package com.emat.reapi.global;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("PROFILER BACKEND")
                        .description("Finance profiler backend app")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .email("myEmail@email.com")
                                .name("Maciek")));

    }
}
