package br.com.senai.qualidademltplaceapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senai.qualidademltplaceapi.entity.Ouvidoria;

@Repository
public interface OuvidoriaRepository extends JpaRepository<Ouvidoria, Integer> {
	
	
	@Query(value = "SELECT o FROM Ouvidoria o WHERE o.id = :idOuvidoria")
	public Ouvidoria buscarPorOuvidoria(Integer idOuvidoria);

	
	@Query(value = "SELECT o FROM Ouvidoria o WHERE o.nome = :nome")
	public Page<Ouvidoria> buscarPorNome(String nome, Pageable paginacao);
	
	@Query(value = "SELECT o FROM Ouvidoria o ORDER BY o.nome")
	public Page<Ouvidoria> listarPor(Pageable paginacao);
	
	
}
