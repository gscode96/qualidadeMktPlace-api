package br.com.senai.qualidademltplaceapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.enums.TipoAvaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoCliente, Integer> {


	@Query(value = "SELECT a FROM AvaliacaoCliente a WHERE a.id = :idAvaliacao")
	public AvaliacaoCliente buscarPorAvaliacao(Integer idAvaliacao);

	@Query(value = "SELECT a FROM AvaliacaoCliente a WHERE a.id_pedido = :idPedido")
	public List<AvaliacaoCliente> buscarPorPedido(Integer idPedido);
	
	@Query(value = "SELECT a FROM AvaliacaoCliente a ORDER BY a.qtdEstrelas, a.id")
	public Page<AvaliacaoCliente> listarPor(Pageable paginacao); 

	@Query(value = "SELECT a FROM AvaliacaoCliente a WHERE a.tipoAvaliacao = :tipoAvaliacao")
	public Page<AvaliacaoCliente> listarPorTipo(TipoAvaliacao tipoAvaliacao, Pageable paginacao);


}
