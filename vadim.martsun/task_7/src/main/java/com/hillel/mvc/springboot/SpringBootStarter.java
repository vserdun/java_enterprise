package com.hillel.mvc.springboot;

import com.hillel.mvc.springboot.controllers.MainPageController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;;

@SpringBootApplication
public class SpringBootStarter{
    public static void main(String[] args){
        SpringApplication.run(SpringBootStarter.class, args);
    }
}
