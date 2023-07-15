package com.georgivelev.demoapprestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DemoAppRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAppRestApiApplication.class, args);
    }

}
