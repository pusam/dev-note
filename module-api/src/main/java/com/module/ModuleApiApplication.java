package com.module;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModuleApiApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ModuleApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
