package br.com.senai.qualidademltplaceapi.dto;

import lombok.Data;

@Data
public class AvaliacaoGeral {

	private Integer idDoPedido;
	
	private Integer idDoRestaurante;
	
	private String nomeRestaurante;
	
	private Avaliacao avaliacaoDoPedido;
	
	private Avaliacao avaliacaoDoRestaurante;
	
	private Avaliacao avaliacaoDoEntregador;
	
}
