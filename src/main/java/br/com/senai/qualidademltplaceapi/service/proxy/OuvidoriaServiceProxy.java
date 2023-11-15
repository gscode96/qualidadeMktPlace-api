package br.com.senai.qualidademltplaceapi.service.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.qualidademltplaceapi.entity.Ouvidoria;
import br.com.senai.qualidademltplaceapi.service.OuvidoriaSevice;

@Service
public class OuvidoriaServiceProxy implements OuvidoriaSevice{
	
	@Autowired  
	@Qualifier("ouvidoriaServiceImpl")
	private OuvidoriaSevice service;

	@Override
	public Ouvidoria buscarPorOuvidoria( Integer idOuvidoria) {
		return service.buscarPorOuvidoria(idOuvidoria);
	}

	@Override
	public Ouvidoria buscarPorNome(String nome) {
		return service.buscarPorNome(nome);
	}

	@Override
	public Page<Ouvidoria> listarPor( Pageable paginacao) {
		return service.listarPor(paginacao);
	}

	@Override
	public Ouvidoria Salvar(Ouvidoria ouvidoria) {
		return service.Salvar(ouvidoria);
	}

}
