package br.com.senai.qualidademltplaceapi.dto;

import jakarta.validation.constraints.NotNull;

public class ClienteSalvo {
	
	@NotNull(message = "O email do cliente é obrigatório!")
	String email;
	
}
