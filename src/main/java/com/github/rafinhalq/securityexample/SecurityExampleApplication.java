package com.github.rafinhalq.securityexample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

@Slf4j
@EnableFeignClients
@SpringBootApplication
@RequiredArgsConstructor
public class SecurityExampleApplication implements CommandLineRunner {
	private final Environment env;

	@Override
	public void run(String... args) throws Exception {
		log.info("{}:{}", env.getProperty("info.app.name"), env.getProperty("info.app.version"));
	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityExampleApplication.class, args);
	}

}
