package br.com.senai.qualidademltplaceapi.service.proxy;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import br.com.senai.qualidademltplaceapi.dto.PedidoSalvo;
import br.com.senai.qualidademltplaceapi.service.EmailService;

@Service
public class EmailServiceProxy implements EmailService {

	@Autowired
	SendGrid sendGrid;
	
	@Autowired
	private ProducerTemplate toApiPedidos;

	// Metodo para envio de email ,Runnable devido a class time 
	public Runnable sendEmail() {
		
		// faz a busca na api de pedidos
		
		List<PedidoSalvo> listaDePedidos = this.getPedido();
		
		for (PedidoSalvo pedido : listaDePedidos) {
			String link = "https://localhost:5173/avaliacao/" + pedido.getIdPedido();

			//Monta o email para envio
			Mail mail = new Mail(new Email("luuiz.pereira.correa@gmail.com"), "Avaliação de satisfação Pede ai ",
					new Email(pedido.getEmail()), new Content("text/plain", "Faça sua avaliação no link =>" + link));
			mail.setReplyTo(new Email("luiz_h_correa@estudante.sc.senai.br"));

			Request request = new Request();
			
			try {
				
				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
				request.setBody(mail.build());
				this.sendGrid.api(request);
				
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return null ;
	}
		
	//Faz a busca na api de pedidos (integração)
	@Override
	public List<PedidoSalvo> getPedido() {
	
		JSONObject bodyRequest = new JSONObject();
		bodyRequest.put("statusDoPedido", "ENTREGUE");
		
		List<PedidoSalvo> pedidos = new ArrayList<>();
		
		//Chama integração
		JSONObject pedidoSalvos = this.toApiPedidos.requestBody(
				"direct:toApiPedidos", bodyRequest, JSONObject.class);
		System.out.println("estamos aq 123" );
		JSONArray listagem = pedidoSalvos.getJSONArray("listagem");

		//Faz o requerimento dos atributos especificos
		
		    for (int i = 0; i < listagem.length(); i++) {
		        JSONObject pedidoJson = listagem.getJSONObject(i);

		        PedidoSalvo pedidoSalvo = new PedidoSalvo();
		        pedidoSalvo.setIdPedido(pedidoJson.getInt("id_pedido"));
		        pedidoSalvo.setIdCliente(pedidoJson.getJSONObject("cliente").getInt("id_cliente"));
		        pedidoSalvo.setIdRestaurante(pedidoJson.getJSONObject("restaurante").getInt("id_restaurante"));
		        pedidoSalvo.setStatusPedido(pedidoJson.getString("status"));
		        pedidoSalvo.setEmail(pedidoJson.getJSONObject("cliente").getString("email"));

		        pedidos.add(pedidoSalvo);
		    }
		    
		    System.out.println(pedidos);

		  return pedidos;
	}

}
