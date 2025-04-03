package com.backendthread.backendthread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendthreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendthreadApplication.class, args);
	}

}
