package com.eventflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class EventFlowGoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventFlowGoApplication.class, args);
    }

}
