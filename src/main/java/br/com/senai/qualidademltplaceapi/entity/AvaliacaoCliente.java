package br.com.senai.qualidademltplaceapi.entity;

import br.com.senai.qualidademltplaceapi.entity.enums.TipoAvaliacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "avaliacao_cliente")
@Entity(name = "AvaliacaoCliente")
public class AvaliacaoCliente {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "id_pedido")
	@NotBlank(message = "O id do pedido é obrigatório!")
	private Integer idPedido;
	
	@Column(name = "qtde_estrelas")
	@NotBlank(message = "A quantidade de estrelas é obrigatória!")
	private Integer qtdEstrelas;
	
	@Column(name = "avaliacao")
	@NotBlank(message = "A avalição é obrigatória!")
	private String avaliacao;
	
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O tipo de avaliação da pedido é obrigatório!")
	private TipoAvaliacao tipoAvaliacao;

}
