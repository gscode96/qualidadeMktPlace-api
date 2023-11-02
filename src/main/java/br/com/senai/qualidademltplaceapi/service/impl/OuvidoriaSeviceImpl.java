package br.com.senai.qualidademltplaceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.Ouvidoria;
import br.com.senai.qualidademltplaceapi.repository.OuvidoriaRepository;
import br.com.senai.qualidademltplaceapi.service.OuvidoriaSevice;

@Service
public class OuvidoriaSeviceImpl implements OuvidoriaSevice {
	
	@Autowired
	private OuvidoriaRepository repository;

	@Override
	public Ouvidoria buscarPorOuvidoria( Integer idOuvidoria) {
		Ouvidoria ouvidoria = repository.buscarPorOuvidoria(idOuvidoria);
		Preconditions.checkNotNull(ouvidoria, "O id da ouvidoria não existe");
		return ouvidoria;
	}

	@Override
	public Ouvidoria buscarPorNome( String nome) {
		Ouvidoria ouvidoria = repository.buscarPorNome(nome);
		Preconditions.checkNotNull(ouvidoria, "O nome informado não existe");
		return ouvidoria;
	}

	@Override
	public Page<Ouvidoria> listarPor( Pageable paginacao) {

		return repository.listarPor(paginacao);
	}

}
