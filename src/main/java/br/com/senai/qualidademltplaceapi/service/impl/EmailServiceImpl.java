package br.com.senai.qualidademltplaceapi.service.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import br.com.senai.qualidademltplaceapi.dto.EmailRequest;
import br.com.senai.qualidademltplaceapi.dto.PedidoSalvo;
import br.com.senai.qualidademltplaceapi.service.AvaliacaoService;
import br.com.senai.qualidademltplaceapi.service.EmailService;
import jakarta.validation.constraints.NotNull;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	SendGrid sendGrid;

	@Autowired
	@Qualifier("avaliacaoServiceProxy")
	AvaliacaoService service;

	public void sendEmails() {

		// faz a busca na api de pedidos
		List<PedidoSalvo> listaDePedidos = service.getPedido();

		for (PedidoSalvo pedido : listaDePedidos) {
			String link = "https://localhost:5173/avaliacao/" + pedido.getIdPedido();

			// monta o email para envio
			Mail mail = new Mail(new Email("luuiz.pereira.correa@gmail.com"), "Avaliação de satisfação Pede ai ",
					new Email(pedido.getEmail()), new Content("text/plain", "Faça sua avaliação no link =>" + link));
			mail.setReplyTo(new Email("luiz_h_correa@estudante.sc.senai.br"));

			Request request = new Request();
			Response response = new Response();

			try {
				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
				request.setBody(mail.build());
				response = this.sendGrid.api(request);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	@Override
	public Response sendemail(@NotNull(message = "O dto de email é obrigatorio") EmailRequest emailRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}
