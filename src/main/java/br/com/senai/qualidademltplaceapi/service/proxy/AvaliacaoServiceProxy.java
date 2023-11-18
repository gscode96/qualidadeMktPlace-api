package br.com.senai.qualidademltplaceapi.service.proxy;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.json.JSONArray;
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
		
		JSONObject bodyRequest = new JSONObject();
		bodyRequest.put("statusDoPedido", "ENTREGUE");
		
		List<PedidoSalvo> pedidos = new ArrayList<>();
		
		JSONObject pedidoSalvos = this.toApiPedidos.requestBody(
				"direct:toApiPedidos", bodyRequest, JSONObject.class);
		
		JSONArray listagem = pedidoSalvos.getJSONArray("listagem");

		    for (int i = 0; i < listagem.length(); i++) {
		        JSONObject pedidoJson = listagem.getJSONObject(i);

		        PedidoSalvo pedidoSalvo = new PedidoSalvo();
		        pedidoSalvo.setIdPedido(pedidoJson.getInt("id_pedido"));
		        pedidoSalvo.setIdCliente(pedidoJson.getInt("id_cliente"));
		        pedidoSalvo.setIdRestaurante(pedidoJson.getInt("id_restaurante"));
		        pedidoSalvo.setStatusPedido(pedidoJson.getString("status"));
		        pedidoSalvo.setEmail(pedidoJson.getJSONObject("cliente").getString("email"));

		        pedidos.add(pedidoSalvo);
		    }
		    
		    System.out.println(pedidos);

		    return pedidos;
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
