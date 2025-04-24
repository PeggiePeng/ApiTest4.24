package com.example.demo;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
运行入口
 */
@EnableScheduling
@SpringBootApplication
@ComponentScan("com.example")
public class Application {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {

        Application.context = SpringApplication.run(Application.class,args);
    }

    @PreDestroy
    public void close(){
        Application.context.close();
    }
}
