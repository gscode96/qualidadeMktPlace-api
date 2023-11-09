package br.com.senai.qualidademltplaceapi.service.impl;

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
import br.com.senai.qualidademltplaceapi.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

		
		@Autowired
		SendGrid sendGrid ;

		public Response sendemail(EmailRequest emailRequest ) {
			
			Mail mail = new Mail(new Email("luuiz.pereira.correa@gmail.com"),emailRequest.getSubject()
					,new Email(emailRequest.getTo()),new Content("text/plain" , emailRequest.getBody()) );
			mail.setReplyTo(new Email("luiz_h_correa@estudante.sc.senai.br"));
			
			Request request = new Request() ;
			
			Response response = new Response();
					
			try  {
				
				request.setMethod(Method.POST);
				
				request.setEndpoint("mail/send");
				
				request.setBody(mail.build());
				
				response = this.sendGrid.api(request);
				
			}catch (Exception ex) {
				
				System.out.println(ex.getMessage());
				
			}
			
			return response ;
		}


}
