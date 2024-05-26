package com.example.language_translation_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.example.language_translation_demo"})
public class LanguageTranslationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanguageTranslationDemoApplication.class, args);
	}

}
