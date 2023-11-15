package br.com.senai.qualidademltplaceapi.integration.rota;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.senai.qualidademltplaceapi.integration.processor.ErrorProcessor;

@Component
public class ToAvaliacoes extends RouteBuilder {

	 @Value("${cadastros.url}")
	    private String urlDeEnvio;

	    @Value("${cadastros.url.token}")
	    private String urlDeToken;

	    @Value("${cadastros.login}")
	    private String login;

	    @Value("${cadastros.password}")
	    private String senha;

	    @Autowired
	    private ErrorProcessor errorProcessor;

	    @Override
	    public void configure() throws Exception {
	        from("direct:toApiPedidos")
	            .doTry()
	                .process(new Processor() {
	                    @Override
	                    public void process(Exchange exchange) throws Exception {
	                        String responseJson = exchange.getIn().getBody(String.class);
	                        JSONObject jsonObject = new JSONObject(responseJson);
	                        Integer idCliente = jsonObject.getInt("idCliente");
	                        exchange.setProperty("idCliente", idCliente);
	                    }
	                })
	                .to("direct:autenticarToken") // Call token verification route
	                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
	                .setHeader(Exchange.CONTENT_TYPE, constant("application/json;charset=UTF-8"))
	                .setHeader("Authorization", simple("Bearer ${exchangeProperty.token}"))
	                //indica a rota a onde sera feita o get
	                .toD(urlDeEnvio + "/pedidos ")
	                .process(new Processor() {
	                    @Override
	                    public void process(Exchange exchange) throws Exception {
	                        String responseJson = exchange.getIn().getBody(String.class);
	                        JSONObject jsonObject = new JSONObject(responseJson);
	                        exchange.getMessage().setBody(jsonObject.toString());
	                    }
	                })
	            .doCatch(Exception.class)
	                .setProperty("error", simple("${exception}"))
	                .process(errorProcessor)
	            .end();

	        // verificação da rota do token
	        from("direct:autenticarToken")
	            .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
	            .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
	            .setBody(simple("{\"login\":\"${property.login}\",\"senha\":\"${property.senha}\"}"))
	            .to(urlDeToken)
	            .process(new Processor() {
	                @Override
	                public void process(Exchange exchange) throws Exception {
	                    String responseJson = exchange.getIn().getBody(String.class);
	                    JSONObject jsonObject = new JSONObject(responseJson);
	                    String token = jsonObject.getString("token");
	                    exchange.setProperty("token", token);
	                }
	            });
	    }
}
