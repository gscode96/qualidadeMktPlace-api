package br.com.senai.qualidademltplaceapi;


import java.util.Timer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;

import br.com.senai.qualidademltplaceapi.service.EmailService;
import br.com.senai.qualidademltplaceapi.service.proxy.EmailServiceProxy;



@SpringBootApplication
public class InitApp {

	public static void main(String[] args) {
		SpringApplication.run(InitApp.class, args);
	}
	
	@Bean
	public Hibernate5JakartaModule jsonHibernate5Module() {

		return new Hibernate5JakartaModule();
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
	//		new Timer();
			
	    	EmailService emailService = new EmailServiceProxy();
	    	emailService.sendEmail();
			
			System.out.println("Subiu");
		};

	}

}
