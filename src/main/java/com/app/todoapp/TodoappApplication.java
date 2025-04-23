package com.app.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class TodoappApplication {

	public static void main(String[] args) throws URISyntaxException, IOException {

		SpringApplication.run(TodoappApplication.class, args);
	}
}
