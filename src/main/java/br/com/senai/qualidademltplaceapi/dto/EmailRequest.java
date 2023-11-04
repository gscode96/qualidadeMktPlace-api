package br.com.senai.qualidademltplaceapi.dto;

import lombok.Data;

@Data
public class EmailRequest {
	
	private String to;
	private String subject;
	private String body;
	
	public EmailRequest(String to, String subject, String body) {
		super();
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	

}
