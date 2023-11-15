package br.com.senai.qualidademltplaceapi.util;

import java.util.List;

import br.com.senai.qualidademltplaceapi.dto.PedidoSalvo;
import br.com.senai.qualidademltplaceapi.integration.rota.ToAvaliacoes;

public class Timer {

	public List<PedidoSalvo> chamodoDeCliente() {

		ToAvaliacoes toAvaliacoes = new ToAvaliacoes();

		// Executa o método configure para iniciar o processo de roteamento
		try {
			toAvaliacoes.configure();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// lista de PedidoSalvo da instância de ToAvaliacoes
		List<PedidoSalvo> listaDePedidos = toAvaliacoes.getPedidoSalvos();

		return listaDePedidos;

	}

}
