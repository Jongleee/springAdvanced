package com.project.springproject2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringProject2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringProject2Application.class, args);
    }

}
