package br.com.senai.qualidademltplaceapi.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.Ouvidoria;
import br.com.senai.qualidademltplaceapi.service.OuvidoriaSevice;

@Service
public class OuvidoriaSeviceImpl implements OuvidoriaSevice {

	@Override
	public Ouvidoria buscarPorOuvidoria( Integer idOuvidoria) {
		return null;
	}

	@Override
	public AvaliacaoCliente buscarPorNome( String nome) {
	
		return null;
	}

	@Override
	public Page<AvaliacaoCliente> listarPor( Pageable paginacao) {

		return null;
	}

}
