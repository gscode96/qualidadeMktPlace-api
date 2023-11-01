package br.com.senai.qualidademltplaceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.enums.TipoAvaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoCliente, Integer> {

	@Query(value = "SELECT a FROM AvaliacaoCliente a WHERE a.id = :id_avaliacao")
	public AvaliacaoCliente buscarPorAvaliacao(Integer id_avaliacao);

	@Query(value = "SELECT a FROM AvaliacaoCliente a WHERE a.id_pedido = :id_pedido")
	public AvaliacaoCliente buscarPorPedido(Integer id_pedido);

	@Query(value = "SELECT a FROM AvaliacaoCliente a ORDER BY a.qtdEstrelas, a.id")
	public Page<AvaliacaoCliente> listarPor(Pageable paginacao);

	@Query(value = "SELECT a FROM AvaliacaoCliente a WHERE a.tipoAvaliacao = :tipoAvaliacao")
	public Page<AvaliacaoCliente> listarPor(TipoAvaliacao tipoAvaliacao, Pageable paginacao);

}
