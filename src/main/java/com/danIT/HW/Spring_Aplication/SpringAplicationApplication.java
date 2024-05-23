package com.danIT.HW.Spring_Aplication;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAplicationApplication implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("http://localhost:9000/swagger-ui/index.html \n");
    }

    public static void main(String[] args) {
		SpringApplication.run(SpringAplicationApplication.class, args);
    }



}
