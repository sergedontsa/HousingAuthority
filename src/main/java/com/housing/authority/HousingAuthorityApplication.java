package com.housing.authority;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class HousingAuthorityApplication {


    public static void main(String[] args) {
        SpringApplication.run(HousingAuthorityApplication.class, args);
    }



}
