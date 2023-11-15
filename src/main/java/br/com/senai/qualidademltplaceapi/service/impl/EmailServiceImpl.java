package br.com.senai.qualidademltplaceapi.service.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.senai.qualidademltplaceapi.service.EmailService;
import br.com.senai.qualidademltplaceapi.util.Timer;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	SendGrid sendGrid;

	public Response sendemail(EmailRequest emailRequest) {

		LocalTime now = LocalTime.now();

		// Formata a hora de acordo com o padrão desejado

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedHour = now.format(formatter);

		if (formattedHour.equals("24:00:00")) {

			List<PedidoSalvo> listaDePedidos1 = new ArrayList<>();
			Timer timer = new Timer();

			listaDePedidos1.addAll(timer.chamodoDeCliente());

			for (PedidoSalvo pedido : listaDePedidos1) {

				// "vai fazer a busca no banco e ver qual pedido ja foi enviado"
				// avaliacaoSeviceImpl.

				String link = "https://localhost:5173/avaliacao/{ " + pedido.getIdPedido() + "}";

				Mail mail = new Mail(new Email("luuiz.pereira.correa@gmail.com"), "Avaliação de satisfação Pede ai ",
						new Email(pedido.getEmail()), new Content("text/plain", emailRequest.getBody() + link));
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

				return response;

			}

		}
		return null;

	}

}
