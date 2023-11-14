package br.com.senai.qualidademltplaceapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.enums.TipoAvaliacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
public interface AvaliacaoService {

	public AvaliacaoCliente buscarPorAvaliacao(

			@NotNull(message = "O id é obrigatorio !") @Positive(message = "O id deve ser positivo !") Integer idAvaliacao);

	public Page<AvaliacaoCliente> buscarPorPedido(

			@NotNull(message = "O id é obrigatorio !") @Positive(message = "O id deve ser positivo !") Integer idPedido,
			Pageable paginacao);

	public Page<AvaliacaoCliente> listarPor(

			@NotNull(message = "A quantidade de paginas é obrigatorio !") Pageable paginacao);

	public Page<AvaliacaoCliente> listarPorTipo(

			@NotNull(message = "A quantidade de paginas é obrigatorio !") Pageable paginacao,
			@NotNull(message = "O Tipo de avaliação é obrigatorio !") TipoAvaliacao tipoAvaliacao

	);

	public AvaliacaoCliente Salvar(@NotNull(message = "A avaliação é obrigatória") AvaliacaoCliente avaliacao);

}
