package org.marcio.demospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.marcio.demospringboot")
@EnableAutoConfiguration
public class GestionFormationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionFormationsApplication.class, args);
    }
}
