package br.com.senai.qualidademltplaceapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.Ouvidoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Validated
public interface OuvidoriaSevice {
	
	
	
    public Ouvidoria buscarPorOuvidoria(
			
			@NotNull(message = "O id é obrigatorio !")
			@Positive(message = "O id deve ser positivo !")
			Integer idOuvidoria 
			);
    
	public Ouvidoria buscarPorNome(
			
			@NotBlank(message = "O nome é obrigatório")
			@Size(min = 3, max = 100, message = 
			"O nome deve conter pelo menos 3 e 100 caracteres")
			String nome
			
			);
	
	public Page<Ouvidoria> listarPor(
			
			@NotNull(message = "A quantidade de paginas é obrigatorio !")
			Pageable paginacao 
			);
	
	


}
