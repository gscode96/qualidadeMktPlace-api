package br.com.senai.qualidademltplaceapi.service.proxy;

import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.util.json.JsonObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.qualidademltplaceapi.dto.PedidoSalvo;
import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.enums.TipoAvaliacao;
import br.com.senai.qualidademltplaceapi.service.AvaliacaoService;

@Service
public class AvaliacaoServiceProxy implements AvaliacaoService {

	@Autowired
	@Qualifier("avaliacaoServiceImpl")
	private AvaliacaoService service;

	@Autowired
	private ProducerTemplate toApiPedidos;

	@Override
	public List<PedidoSalvo> getPedido() {
		
		List<PedidoSalvo> pedidoSalvos = this.toApiPedidos.requestBody("direct:toApiPedidos", null, List.class);
		
		return pedidoSalvos;
	}

	@Override
	public AvaliacaoCliente buscarPorAvaliacao(Integer idAvaliacao) {
		return service.buscarPorAvaliacao(idAvaliacao);
	}

	@Override
	public Page<AvaliacaoCliente> buscarPorPedido(Integer idPedido, Pageable paginacao) {
		return service.buscarPorPedido(idPedido, paginacao);
	}

	@Override
	public Page<AvaliacaoCliente> listarPor(Pageable paginacao) {
		return service.listarPor(paginacao);
	}

	@Override
	public Page<AvaliacaoCliente> listarPorTipo(Pageable paginacao, TipoAvaliacao tipoAvaliacao) {
		return service.listarPorTipo(paginacao, tipoAvaliacao);
	}

	@Override
	public AvaliacaoCliente Salvar(AvaliacaoCliente avaliacao) {
		return service.Salvar(avaliacao);
	}

}
