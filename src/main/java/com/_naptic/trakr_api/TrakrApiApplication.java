package com._naptic.trakr_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TrakrApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrakrApiApplication.class, args);
    }

}
