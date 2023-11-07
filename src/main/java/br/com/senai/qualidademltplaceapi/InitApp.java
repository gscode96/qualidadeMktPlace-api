package br.com.senai.qualidademltplaceapi;

import java.util.Scanner;

import javax.swing.JOptionPane;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import com.sendgrid.Response;

import br.com.senai.qualidademltplaceapi.dto.EmailRequest;
import br.com.senai.qualidademltplaceapi.service.EmailService;



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
			
			 Scanner scanner = new Scanner(System.in);
		        System.out.print("Deseja enviar o email? (Digite 'sim' para confirmar): ");
		        String resposta = scanner.nextLine().toLowerCase();
		        
		        if ("sim".equals(resposta)) {
		            String to = "luuiz.pereira.correa@gmail.com";
		            String subject = "aqui estamos";
		            String body = "helow";
		            
		            EmailRequest emailRequest = new EmailRequest(to, subject, body);
		            
		            EmailService service = new EmailService();
		            
		            service.sendemail(emailRequest);
		            
		            Response response = service.sendemail(emailRequest) ;
		            System.out.println(response);
		        } else {
		            System.out.println("Envio de email cancelado.");
		            
		        }
			System.out.println("Subiu");
		};

	}

}
