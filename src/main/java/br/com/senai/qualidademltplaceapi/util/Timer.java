package br.com.senai.qualidademltplaceapi.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.senai.qualidademltplaceapi.service.EmailService;

@Component
public class Timer {
	
	private final long SEGUNDO = 1000; 
    private final long MINUTO = SEGUNDO * 60; 
   // private final long HORA = MINUTO * 60;
   // private final long DIA = HORA * 24;
    
	@Autowired
	@Qualifier("emailServiceProxy")
	private EmailService emailService;
    	
    	@Scheduled(fixedDelay = MINUTO)
	    public void Timer() {
	    	
    		emailService.sendEmail();
			
	    }


}
