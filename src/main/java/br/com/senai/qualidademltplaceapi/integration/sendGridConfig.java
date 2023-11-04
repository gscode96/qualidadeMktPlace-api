package br.com.senai.qualidademltplaceapi.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class sendGridConfig {

	@Value("${sendgrid.key}")
	private String key;

	@Bean
	public SendGrid getSendGrid() {
		return new SendGrid(key);
	}

}
