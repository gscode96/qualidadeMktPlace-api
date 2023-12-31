package br.com.senai.qualidademltplaceapi.controller;

import java.net.URI;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.senai.qualidademltplaceapi.dto.AvaliacaoGeral;
import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.enums.TipoAvaliacao;
import br.com.senai.qualidademltplaceapi.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacao")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

	@Autowired
	@Qualifier("avaliacaoServiceProxy")
	private AvaliacaoService service;

	@Autowired
	private MapConverter converter;

	@GetMapping("idAvaliacao/{id}")
	public ResponseEntity<?> BuscarPorIdAvaliacao(@PathVariable("id") Integer id) {
		AvaliacaoCliente avaliacaoSalva = service.buscarPorAvaliacao(id);
		return ResponseEntity.ok(converter.toJsonMap(avaliacaoSalva));
	}

	@GetMapping("idPedido/{id}")
	public ResponseEntity<?> BuscarPorIdPedido(@PathVariable("id") Integer id) {
		Pageable paginacao = PageRequest.of(0, 3);
		Page<AvaliacaoCliente> avaliacaoSalva = service.buscarPorPedido(id, paginacao);
		return ResponseEntity.ok(converter.toJsonList(avaliacaoSalva));

	}

	@GetMapping
	public ResponseEntity<?> Listar() {
		Pageable paginacao = PageRequest.of(0, 15);

		Page<AvaliacaoCliente> avaliacaoSalva = service.listarPor(paginacao);

		return ResponseEntity.ok(converter.toJsonList(avaliacaoSalva));

	}

	@GetMapping("tipo/{tipo}")
	public ResponseEntity<?> ListarPorTipo(@PathVariable("tipo") TipoAvaliacao tipo) {
		Pageable paginacao = PageRequest.of(0, 15);
		Page<AvaliacaoCliente> avaliacaoSalva = service.listarPorTipo(paginacao, tipo);
		return ResponseEntity.ok(converter.toJsonList(avaliacaoSalva));
	}

	@GetMapping("restaurante/{nome}")
	public ResponseEntity<?> listarPorRestaurante(@PathVariable("nome") String nome) {
		
		try {
			Pageable paginacao = PageRequest.of(0, 15);
			Page<AvaliacaoCliente> avaliacaoSalva = service.buscarPorRestaurante(nome, paginacao);
			return ResponseEntity.ok(converter.toJsonList(avaliacaoSalva));
		} catch (Exception e) {
		 return ResponseEntity.badRequest().body("Ocorreu um erro ao buscar a avaliação. Motivo:" + e.getMessage());
		}
	
	}

	@PostMapping
	public ResponseEntity<?> Inserir(@RequestBody String Jsonavaliacao) {

		try {
			ObjectMapper object = new ObjectMapper();
			AvaliacaoGeral avaliacaoGeral = object.readValue(Jsonavaliacao, AvaliacaoGeral.class);
			AvaliacaoCliente avaliacaoRestaurante = extrairAvaliacao(avaliacaoGeral, TipoAvaliacao.RESTAURANTE);
			AvaliacaoCliente avaliacaoPedido = extrairAvaliacao(avaliacaoGeral, TipoAvaliacao.PEDIDO);
			AvaliacaoCliente avaliacaoEntrega = extrairAvaliacao(avaliacaoGeral, TipoAvaliacao.ENTREGA);
			service.Salvar(avaliacaoRestaurante);
			service.Salvar(avaliacaoPedido);
			service.Salvar(avaliacaoEntrega);

			return ResponseEntity.created(URI.create("/avaliacao/id/" + avaliacaoEntrega.getId())).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Ocorreu um erro ao inserir a avaliação. Motivo:" + e.getMessage());
		}

	}

	private AvaliacaoCliente extrairAvaliacao(AvaliacaoGeral avaliacaoGeral, TipoAvaliacao tipoAvaliacao) {
		AvaliacaoCliente avaliacaoCliente = new AvaliacaoCliente();
		avaliacaoCliente.setIdPedido(avaliacaoGeral.getIdDoPedido());
		avaliacaoCliente.setIdRestaurante(avaliacaoGeral.getIdDoRestaurante());
		avaliacaoCliente.setNomeRestaurante(avaliacaoGeral.getNomeRestaurante().toUpperCase());

		switch (tipoAvaliacao) {
		case RESTAURANTE:
			avaliacaoCliente.setAvaliacao(avaliacaoGeral.getAvaliacaoDoRestaurante().getComentario());
			avaliacaoCliente.setQtdEstrelas(avaliacaoGeral.getAvaliacaoDoRestaurante().getEstrelas());
			break;
		case PEDIDO:
			avaliacaoCliente.setAvaliacao(avaliacaoGeral.getAvaliacaoDoPedido().getComentario());
			avaliacaoCliente.setQtdEstrelas(avaliacaoGeral.getAvaliacaoDoPedido().getEstrelas());
			break;
		case ENTREGA:
			avaliacaoCliente.setAvaliacao(avaliacaoGeral.getAvaliacaoDoEntregador().getComentario());
			avaliacaoCliente.setQtdEstrelas(avaliacaoGeral.getAvaliacaoDoEntregador().getEstrelas());
			break;
		default:
			throw new IllegalArgumentException("O tipo de avaliação é inválido.");
		}
		avaliacaoCliente.setDtAvalicao(LocalDateTime.now());
		avaliacaoCliente.setTipoAvaliacao(tipoAvaliacao);

		return avaliacaoCliente;
	}

}
