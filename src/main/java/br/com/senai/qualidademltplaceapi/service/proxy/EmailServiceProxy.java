package br.com.senai.qualidademltplaceapi.service.proxy;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.ProducerTemplate;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import br.com.senai.qualidademltplaceapi.dto.PedidoSalvo;
import br.com.senai.qualidademltplaceapi.repository.AvaliacaoRepository;
import br.com.senai.qualidademltplaceapi.service.EmailService;

@Service
@Qualifier("emailServiceProxy")
public class EmailServiceProxy implements EmailService {

	@Autowired
	AvaliacaoRepository repository;

	@Autowired
	SendGrid sendGrid;

	@Autowired
	private ProducerTemplate toApiPedidos;

	private Integer idpedido;

	// Metodo para envio de email ,Runnable devido a class time
	public Runnable sendEmail() {

		// faz a busca na api de pedidos

		List<PedidoSalvo> listaDePedidos = this.getPedido();

		for (PedidoSalvo pedido : listaDePedidos) {

			Integer idCliente = pedido.getIdPedido();
			Integer idRestaurante = pedido.getIdRestaurante();
			String nomeRestaurante = pedido.getNomeDoRestaurante();

			String link = "http://localhost:5173/avaliacao/?idCliente=" + idCliente + "&nomeRestaurante="
					+ nomeRestaurante + "&idRestaurante=" + idRestaurante;
			String templateId = "d-dc960c666c034de6a7f7f1b4af1c9a1c";

			Mail mail = new Mail();
			Email from = new Email("luuiz.pereira.correa@gmail.com");
			Email to = new Email(pedido.getEmail());

			mail.setFrom(from);

			Personalization personalization = new Personalization();
			personalization.addTo(to);
			personalization.addDynamicTemplateData("link", link);
			mail.addPersonalization(personalization);

			mail.setTemplateId(templateId);

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

		return null;
	}

	// Faz a busca na api de pedidos (integração)
	@Override
	public List<PedidoSalvo> getPedido() {

		Integer primeiroId = idpedido;
		if (idpedido == null) {
			primeiroId = 0;
		}

		JSONObject bodyRequest = new JSONObject();
		bodyRequest.put("statusDoPedido", "ENTREGUE");

		List<PedidoSalvo> pedidos = new ArrayList<>();

		// Chama integração
		JSONObject pedidoSalvos = this.toApiPedidos.requestBody("direct:toApiPedidos", bodyRequest.toString(),
				JSONObject.class);
		System.out.println("estamos aq 123");
		JSONArray listagem = pedidoSalvos.getJSONArray("listagem");

		// Faz o requerimento dos atributos especificos

		for (int i = 0; i < listagem.length(); i++) {
			JSONObject pedidoJson = listagem.getJSONObject(i);

			PedidoSalvo pedidoSalvo = new PedidoSalvo();
			pedidoSalvo.setIdPedido(pedidoJson.getInt("id_pedido"));
			pedidoSalvo.setIdCliente(pedidoJson.getJSONObject("cliente").getInt("id_cliente"));
			pedidoSalvo.setIdRestaurante(pedidoJson.getJSONObject("restaurante").getInt("id_restaurante"));
			pedidoSalvo.setNomeDoRestaurante(pedidoJson.getJSONObject("restaurante").getString("nome"));
			pedidoSalvo.setStatusPedido(pedidoJson.getString("status"));
			pedidoSalvo.setEmail(pedidoJson.getJSONObject("cliente").getString("email"));

			pedidos.add(pedidoSalvo);
		}
		List<PedidoSalvo> list = new ArrayList<PedidoSalvo>();

		for (PedidoSalvo pedidoSalvo : list) {
			Integer idpedido = pedidoSalvo.getIdPedido();
		}
		System.out.println(pedidos);

		return pedidos;
	}

}
