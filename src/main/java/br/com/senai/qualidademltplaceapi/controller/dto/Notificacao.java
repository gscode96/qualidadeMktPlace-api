package br.com.senai.qualidademltplaceapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Notificacao {

	@Size(max = 100, message = "O email não deve conter mais de 100 caracteres")
	@Email(message = "O email é inválido")
	@NotBlank(message = "O destinatário é obrigatório")
	private String destinatario;
	
	@Size(max = 100, message = "O título não deve conter mais de 100 caracteres")
	@NotBlank(message = "O título é obrigatório")
	private String titulo;
	
	@NotBlank(message = "A mensagem é obrigatória")
	private String mensagem;
}
