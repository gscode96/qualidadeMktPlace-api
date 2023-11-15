package br.com.senai.qualidademltplaceapi.service;

import com.sendgrid.Response;

import br.com.senai.qualidademltplaceapi.dto.EmailRequest;
import jakarta.validation.constraints.NotNull;

public interface EmailService {
	
	
	public Response sendemail(
			@NotNull(message = "O dto de email é obrigatorio")
			EmailRequest emailRequest 
			
			);

}
