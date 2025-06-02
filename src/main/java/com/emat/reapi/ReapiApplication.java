package com.emat.reapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ReapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReapiApplication.class, args);
    }

}
