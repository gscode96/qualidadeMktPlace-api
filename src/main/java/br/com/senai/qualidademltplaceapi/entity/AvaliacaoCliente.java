package br.com.senai.qualidademltplaceapi.entity;

import java.time.LocalDateTime;

import br.com.senai.qualidademltplaceapi.entity.enums.TipoAvaliacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
	@NotNull(message = "O id do pedido é obrigatório!")
	private Integer idPedido;
	
	@Column(name = "id_restaurante")
	@NotNull(message = "O id do Restaurante é obrigatório!")
	private Integer idRestaurante;

	@Column(name = "qtde_estrelas")
	@Min(value = 1, message = "A quantidade de estrelas deve ser entre 1 e 5!")
	@Max(value = 5, message = "A quantidade de estrelas deve ser entre 1 e 5!")
	@NotNull(message = "A quantidade de estrelas é obrigatória!")
	private Integer qtdEstrelas;

	@Column(name = "avaliacao")
	@NotBlank(message = "A avaliação é obrigatória!")
	private String avaliacao;

	@Column(name = "data_avaliacao")
	@NotNull(message = "A data da avaliação é obrigatoria!")
	private LocalDateTime dtAvalicao;

	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "O tipo de avaliação do pedido é obrigatório!")
	private TipoAvaliacao tipoAvaliacao;
	
	@Transient
	public boolean isAvaliado () {
		return getId() != null && getId() > 0;
		
	}

}
