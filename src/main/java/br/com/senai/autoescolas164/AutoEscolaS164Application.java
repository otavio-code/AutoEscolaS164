package br.com.senai.autoescolas164;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class
AutoEscolaS164Application {
    public static void main(String[] args) {
        SpringApplication.run(AutoEscolaS164Application.class, args);
    }
}