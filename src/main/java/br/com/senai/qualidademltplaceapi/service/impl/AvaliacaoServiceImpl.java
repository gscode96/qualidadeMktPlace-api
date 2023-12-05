package br.com.senai.qualidademltplaceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.enums.TipoAvaliacao;
import br.com.senai.qualidademltplaceapi.repository.AvaliacaoRepository;
import br.com.senai.qualidademltplaceapi.service.AvaliacaoService;
import jakarta.validation.constraints.NotBlank;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	@Override
	public AvaliacaoCliente buscarPorAvaliacao(Integer idAvaliacao) {

		AvaliacaoCliente AvaliacaoEncontrada = avaliacaoRepository.buscarPorAvaliacao(idAvaliacao);
		Preconditions.checkNotNull(AvaliacaoEncontrada, "Não foi encontrado avaliação para o id informado ");

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
		Pageable paginacao = PageRequest.of(0, 15);
		Page<AvaliacaoCliente> pedidoAvaliado = avaliacaoRepository.buscarPorPedido(avaliacao.getIdPedido(), paginacao);
		if (pedidoAvaliado.getTotalElements() < 3) {
			AvaliacaoCliente avaliacaoSalva = avaliacaoRepository.save(avaliacao);
			return avaliacaoSalva;
		} else {
			throw new IllegalArgumentException("O pedido informado já esta salvo");

		}

	}

	@Override
	public Page<AvaliacaoCliente> buscarPorRestaurante(String nome, Pageable paginacao) {
		if (nome.length() < 2) {
			throw new IllegalArgumentException(
					"O nome do restaurante para pesquisa deve conter mais que 2 caracteres!");
		}
		Page<AvaliacaoCliente> avaliacao = avaliacaoRepository.listarPorRestaurante(nome.toUpperCase(), paginacao);
		if (avaliacao.getTotalElements() < 1) {
			throw new IllegalArgumentException("Não foi encontrado pedidos para o restaurante informado!");
		}
		return avaliacao;
	}

}
