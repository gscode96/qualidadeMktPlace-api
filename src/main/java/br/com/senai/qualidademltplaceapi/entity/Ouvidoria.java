package br.com.senai.qualidademltplaceapi.entity;


import java.time.LocalDateTime;

import br.com.senai.qualidademltplaceapi.entity.enums.FormaContato;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "ouvidoria")
@Entity(name = "Ouvidoria")
public class Ouvidoria {
	
	@Id
	@Column(name = "id_ouvidoria")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descricao")
	@NotBlank(message = "A descrição é obrigatória!")
	private String descricao;
	
	@Column
	@NotBlank(message = "A data é obrigatoria" )
	private LocalDateTime dtAvalicao ;
	
	@Column(name = "forma_contato")
	@Enumerated(value = EnumType.STRING)
	private FormaContato formaContato;
}
