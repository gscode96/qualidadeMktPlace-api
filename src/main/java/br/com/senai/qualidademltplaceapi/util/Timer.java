package br.com.senai.qualidademltplaceapi.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.senai.qualidademltplaceapi.service.impl.EmailServiceImpl;

public class Timer {
	
	 private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	    public Timer() {
	    	
	    	EmailServiceImpl emailServiceImpl = null ;
			
	        // Inicia a tarefa agendada para ser executada a cada 24 horas
	    	
	        scheduler.scheduleAtFixedRate(emailServiceImpl.sendEmails(), 0, 24, TimeUnit.HOURS);
	    }


}
