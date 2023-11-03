package br.com.senai.qualidademltplaceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.enums.TipoAvaliacao;
import br.com.senai.qualidademltplaceapi.repository.AvaliacaoRepository;
import br.com.senai.qualidademltplaceapi.service.AvaliacaoService;
import jakarta.validation.constraints.NotNull;

@Service
public class AvaliacaoSeviceImpl implements AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	@Override
	public AvaliacaoCliente buscarPorAvaliacao(Integer idAvaliacao) {

		AvaliacaoCliente AvaliacaoEncontrada = avaliacaoRepository.buscarPorAvaliacao(idAvaliacao);
		Preconditions.checkNotNull(AvaliacaoEncontrada, "não foi encontrado avaliação para o id informado ");

		return AvaliacaoEncontrada;

	}

	@Override
	public Page<AvaliacaoCliente> buscarPorPedido(Integer idPedido, Pageable paginacao) {
		Page<AvaliacaoCliente> avaliacoes = avaliacaoRepository.buscarPorPedido(idPedido, paginacao);
		Preconditions.checkNotNull(avaliacoes, "O id do pedido informado não existe!");

		return avaliacoes;
	}

	@Override
	public Page<AvaliacaoCliente> listarPor(Pageable paginacao) {

		return avaliacaoRepository.listarPor(paginacao);
	}

	@Override
	public Page<AvaliacaoCliente> listarPorTipo(Pageable paginacao, TipoAvaliacao tipoAvaliacao) {
		return avaliacaoRepository.listarPorTipo(tipoAvaliacao, paginacao);
	}

	@Override
	public AvaliacaoCliente Salvar(AvaliacaoCliente avaliacao) {
		AvaliacaoCliente avaliacaoSalva = avaliacaoRepository.save(avaliacao);

		return avaliacaoSalva;
	}

}
