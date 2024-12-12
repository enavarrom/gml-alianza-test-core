package com.enavarrom.tests.gml.alianza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AlianzaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlianzaApplication.class, args);
	}

}
