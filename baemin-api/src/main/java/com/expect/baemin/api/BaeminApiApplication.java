package com.expect.baemin.api;

import com.expect.baemin.infra.BaeminInfra;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {BaeminInfra.class})
public class BaeminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaeminApiApplication.class, args);
    }

}
