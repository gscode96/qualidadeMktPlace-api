package br.com.senai.qualidademltplaceapi.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.enums.TipoAvaliacao;
import br.com.senai.qualidademltplaceapi.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

	@Autowired
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
			Page<AvaliacaoCliente> avaliacaoSalva = service.buscarPorPedido(id,paginacao);
			return ResponseEntity.ok(converter.toJsonList(avaliacaoSalva));

	}
		@GetMapping
		public ResponseEntity<?> Listar () {
			Pageable paginacao = PageRequest.of(0, 15);
			
			Page<AvaliacaoCliente> avaliacaoSalva = service.listarPor(paginacao);
			
			return ResponseEntity.ok(converter.toJsonList(avaliacaoSalva));
			
		}
		
		@GetMapping("tipo/{tipo}")
		public ResponseEntity<?> ListarPorTipo(@PathVariable("tipo")TipoAvaliacao tipo) {
			Pageable paginacao = PageRequest.of(0, 15);
			Page<AvaliacaoCliente> avaliacaoSalva = service.listarPorTipo(paginacao, tipo);
			return ResponseEntity.ok(converter.toJsonList(avaliacaoSalva));
		}
		
		@PostMapping
		public ResponseEntity<?> Inserir(@RequestBody AvaliacaoCliente avaliacao) {
			AvaliacaoCliente avaliacaoSalva = service.Salvar(avaliacao);
			return ResponseEntity.created(URI.create("/avaliacao/id/" + avaliacaoSalva.getId())).build();
		}

}
