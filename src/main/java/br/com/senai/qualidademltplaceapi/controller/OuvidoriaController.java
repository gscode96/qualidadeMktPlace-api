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
import br.com.senai.qualidademltplaceapi.entity.Ouvidoria;
import br.com.senai.qualidademltplaceapi.service.OuvidoriaSevice;

@RestController
@RequestMapping("/ouvidoria")
public class OuvidoriaController {
	
	@Autowired
	private OuvidoriaSevice service;
	
	@Autowired
	private MapConverter converter;
	
	@GetMapping("id/{id}")
	public ResponseEntity<?> buscarPorOuvidoria (@PathVariable("id")Integer id) {
		Ouvidoria ouvidoriaSalva = service.buscarPorOuvidoria(id);
		
		return ResponseEntity.ok(converter.toJsonMap(ouvidoriaSalva));
	}
	
	@GetMapping("nome/{nome}")
	public ResponseEntity<?> buscarPorOuvidoria (@PathVariable("nome")String nome) {
		Ouvidoria ouvidoriaSalva = service.buscarPorNome(nome);
		
		return ResponseEntity.ok(converter.toJsonMap(ouvidoriaSalva));
	}
	
	@GetMapping
	public ResponseEntity<?> Listar () {
		Pageable paginacao = PageRequest.of(0, 15);
		
		Page<Ouvidoria> ouvidoriaSalva = service.listarPor(paginacao);
		
		return ResponseEntity.ok(converter.toJsonList(ouvidoriaSalva));
		
	}
	
	@PostMapping
	public ResponseEntity<?> Inserir(@RequestBody Ouvidoria ouvidoria) {
		Ouvidoria ouvidoriaSalva = service.Salvar(ouvidoria);
		return ResponseEntity.created(URI.create("/ouvidoria/id/" + ouvidoriaSalva.getId())).build();
	}
	
	
}
