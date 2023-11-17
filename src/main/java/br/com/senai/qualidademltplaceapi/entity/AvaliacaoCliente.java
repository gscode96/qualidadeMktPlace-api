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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	
	@Column(name = "qtde_estrelas")
	@Max(5)
	@Min(0)
	@NotNull(message = "A quantidade de estrelas é obrigatória!")
	private Integer qtdEstrelas;
	
	@Column(name = "avaliacao")
	@Size(min = 5, max = 200, message = "A avaliação deve conter entre 5 e 200 caracteres")
	@NotBlank(message = "A avaliação é obrigatória!")
	private String avaliacao;
	
	@Column(name = "data_avaliacao")
	@NotNull(message = "A data é obrigatoria" )
	private LocalDateTime dtAvalicao ;
	
	@Enumerated(value = EnumType.STRING)
	private TipoAvaliacao tipoAvaliacao;

	

}
