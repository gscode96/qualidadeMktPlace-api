package br.com.senai.qualidademltplaceapi.integration.rota;


import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.senai.qualidademltplaceapi.dto.PedidoSalvo;
import br.com.senai.qualidademltplaceapi.integration.processor.ErrorProcessor;


@Component
public class ToAvaliacoes extends RouteBuilder {

    @Value("${local da url.url}")
    private String urlDeEnvio;

    @Autowired
    private ErrorProcessor errorProcessor;

    //Adicione este atributo para armazenar a lista de PedidoSalvo
    private List<PedidoSalvo> pedidoSalvos = new ArrayList<>();

    //Getter para a lista de PedidoSalvo
    public List<PedidoSalvo> getPedidoSalvos() {
        return pedidoSalvos;
    }

    @Override
    public void configure() throws Exception {
    	//Faz o get na api de terceiro
        from("direct:Perguntar pro professor")
            .doTry()
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .setHeader(Exchange.CONTENT_TYPE, simple("application/json;charset=UTF-8"))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        PedidoSalvo pedidoSalvo = exchange.getMessage().getBody(PedidoSalvo.class);
                        JSONObject pedidoSalvoJson = new JSONObject();
                        //Adicione o pedidoSalvo à lista
                        pedidoSalvos.add(pedidoSalvo);
                    }
                })
                .toD(urlDeEnvio)
            .doCatch(Exception.class)
                .setProperty("error", simple("${exception}"))
                .process(errorProcessor)
        .end();
    }
}
