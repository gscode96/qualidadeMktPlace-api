package br.com.senai.qualidademltplaceapi.dto;

import lombok.Data;

@Data
public class AvaliacaoGeral {

	private Integer idDoPedido;
	
	private Avaliacao avaliacaoDoPedido;
	
	private Avaliacao avaliacaoDoRestaurante;
	
	private Avaliacao avaliacaoDoEntregado;
	
}
