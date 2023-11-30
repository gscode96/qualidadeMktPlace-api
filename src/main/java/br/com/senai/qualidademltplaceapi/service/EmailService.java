package br.com.senai.qualidademltplaceapi.service;

import java.util.List;

import br.com.senai.qualidademltplaceapi.dto.PedidoSalvo;

public interface EmailService {
	
	
	 public Runnable sendEmail();
	
	 public List<PedidoSalvo> getPedido();
	 

	
}
