package qualidademktplaceapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.service.AvaliacaoService;

public class getAvaliacaoTest {
	
	@Autowired
	AvaliacaoService service;
	
	@Test
	public void testGetPor(Integer id) {
		AvaliacaoCliente avaliacaoCliente = service.buscarPorAvaliacao(id);
		
	}
	
}
