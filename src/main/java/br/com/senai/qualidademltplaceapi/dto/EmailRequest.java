package br.com.senai.qualidademltplaceapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmailRequest {

	@NotNull(message = "O email é obrigatorio")
	private String to;

	@NotNull(message = "O titulo do email é ovbrigatorio ")
	@Size(min = 3, max = 150, message = "O titulo de" + " ter entre 3 e 150 caractéres")
	private String subject;

	@NotNull(message = "O email não pode ser vazio")
	private String body;

	public EmailRequest(String to, String subject, String body) {
		super();
		this.to = to;
		this.subject = subject;
		this.body = body;
	}

}
