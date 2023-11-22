package br.com.senai.qualidademltplaceapi.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.senai.qualidademltplaceapi.service.EmailService;
import br.com.senai.qualidademltplaceapi.service.proxy.EmailServiceProxy;

public class Timer {
	
	 private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	    public Timer() {
	    	
	    	//EmailService emailService = new EmailServiceProxy();
			
	        // Inicia a tarefa agendada para ser executada a cada 24 horas
	    	
	    //    scheduler.scheduleAtFixedRate(emailService.sendEmail() , 0, 24, TimeUnit.HOURS);
	    }


}
