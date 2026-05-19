package com.example.quanlidaotao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.quanlidaotao.repository")
public class QuanlidaotaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuanlidaotaoApplication.class, args);
    }
}