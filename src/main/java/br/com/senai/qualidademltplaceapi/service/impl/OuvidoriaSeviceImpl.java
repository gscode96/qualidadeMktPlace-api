package br.com.senai.qualidademltplaceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.Ouvidoria;
import br.com.senai.qualidademltplaceapi.repository.OuvidoriaRepository;
import br.com.senai.qualidademltplaceapi.service.OuvidoriaSevice;
import jakarta.validation.constraints.NotNull;

@Service
public class OuvidoriaSeviceImpl implements OuvidoriaSevice {

	@Autowired
	private OuvidoriaRepository repository;

	@Override
	public Ouvidoria buscarPorOuvidoria(Integer idOuvidoria) {
		Ouvidoria ouvidoria = repository.buscarPorOuvidoria(idOuvidoria);
		Preconditions.checkNotNull(ouvidoria, "Não existe ouvidoria para o id informado");
		return ouvidoria;
	}

	@Override
	public Page<Ouvidoria> buscarPorNome(String nome) {
		Pageable paginacao = PageRequest.of(0, 3);
		Page<Ouvidoria> ouvidoria = repository.buscarPorNome(nome, paginacao);
		Preconditions.checkArgument(!ouvidoria.isEmpty(), "Não existe ouvidoria para o nome informado!");

		return ouvidoria;
	}

	@Override
	public Page<Ouvidoria> listarPor(Pageable paginacao) {

		return repository.listarPor(paginacao);
	}

	@Override
	public Ouvidoria Salvar(Ouvidoria ouvidoria) {
		Ouvidoria ouvidoriaSalva = repository.save(ouvidoria);
		return ouvidoriaSalva;
	}

}
