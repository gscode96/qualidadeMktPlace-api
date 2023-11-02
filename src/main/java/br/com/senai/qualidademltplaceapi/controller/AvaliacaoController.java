package br.com.senai.qualidademltplaceapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
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

}
