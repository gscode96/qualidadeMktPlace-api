package br.com.senai.qualidademltplaceapi.dto;

import jakarta.validation.constraints.NotNull;

public class RestauranteSalvo {

	@NotNull(message = "O nome do restaurante é obrigatório!")
	String nome;
	
	
}
