package com.ftn.aukcija;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.ftn.aukcija.services.AppService;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class, args);

		applicationContext.getBean(AppService.class).initializeGroups();
		applicationContext.getBean(AppService.class).initializeCategories();
		applicationContext.getBean(AppService.class).initializeUsers();
		applicationContext.getBean(AppService.class).initializeFirms();
		
	}

}
