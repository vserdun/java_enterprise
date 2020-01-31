package com.hillel.task_9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Task9Application {

    public static void main(String[] args) {
        SpringApplication.run(Task9Application.class, args);
    }

}
