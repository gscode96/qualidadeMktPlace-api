package br.com.senai.qualidademltplaceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sendgrid.Response;

import br.com.senai.qualidademltplaceapi.dto.EmailRequest;
import br.com.senai.qualidademltplaceapi.service.EmailService;

@Controller
public class EmailController {
	
	@Autowired
	private EmailService emailservice;
	
	@PostMapping("/Sendemail")
	public ResponseEntity<String> sendemail(@RequestBody EmailRequest resquest ){
		
		Response response = emailservice.sendemail(resquest) ;
		if(response.getStatusCode() == 200 || response.getStatusCode() == 202)
			return new ResponseEntity<> ("send successfully" , HttpStatus.OK);
		return new ResponseEntity<>("failed ton sent", HttpStatus.NOT_FOUND);
		
	}

}
