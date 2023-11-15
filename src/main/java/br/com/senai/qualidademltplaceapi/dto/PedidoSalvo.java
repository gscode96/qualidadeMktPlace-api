package br.com.senai.qualidademltplaceapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PedidoSalvo {

	@NotNull(message = "O id é obrigatório!")
	@Positive(message = "O id do pedido deve ser positivo!")
	Integer idPedido;

	@NotNull(message = "O id do cliente é obrigatório!")
	@Positive(message = "O id do cliente deve ser positivo!")
	Integer idCliente;

	@NotNull(message = "O id do restaurante é obrigatório!")
	@Positive(message = "O id do restaurante deve ser positivo!")
	Integer idRestaurante;

	@NotNull(message = "O status do pedido é obrigatório!")
	String StatusPedido;
	
	@NotNull(message = "O email do cliente é obrigatório!")
	String email;


}
