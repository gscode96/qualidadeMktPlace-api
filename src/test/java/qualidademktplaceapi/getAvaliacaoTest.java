package qualidademktplaceapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.senai.qualidademltplaceapi.InitApp;
import br.com.senai.qualidademltplaceapi.entity.AvaliacaoCliente;
import br.com.senai.qualidademltplaceapi.entity.Ouvidoria;
import br.com.senai.qualidademltplaceapi.service.impl.AvaliacaoSeviceImpl;
import br.com.senai.qualidademltplaceapi.service.impl.OuvidoriaSeviceImpl;

@SpringBootTest(classes = InitApp.class)
public class getAvaliacaoTest {
	
	@Autowired
	AvaliacaoSeviceImpl avaliacaoService;
	
	@Autowired
	OuvidoriaSeviceImpl ouvidoriaService;
	
	@Test
	public void testGetPor() {
	
		AvaliacaoCliente avaliacaoCliente = avaliacaoService.buscarPorAvaliacao(10);
		Assertions.assertNotNull(avaliacaoCliente);
		
	}
	
	@Test
	public void testGetPorOuvidoria() {
		
		Ouvidoria ouvidoria = ouvidoriaService.buscarPorOuvidoria(1);
		Assertions.assertNotNull(ouvidoria);
		
	}
	
}
