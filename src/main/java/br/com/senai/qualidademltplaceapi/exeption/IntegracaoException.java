package br.com.senai.qualidademltplaceapi.exeption;

public class IntegracaoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public IntegracaoException(String mensagem) {
		super(mensagem);
	}
	
}
