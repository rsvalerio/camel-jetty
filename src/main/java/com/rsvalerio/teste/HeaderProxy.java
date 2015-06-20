package com.rsvalerio.teste;

import org.apache.camel.builder.RouteBuilder;

public class HeaderProxy extends RouteBuilder {

    public void configure() {

    	from("jetty:http://0.0.0.0:9091/abcd")

                //Se for um header do request
                .removeHeader("nomeDoHeader")

                //Se for atributo da tag xml
                .setBody(regexReplaceAll(body(), " namespace=\"blabla\" ", ""))

                //Se quiser mexer direto em java
                .bean(MeuBean.class)

                .to("http://enderecodoserver:8080/abcd")
        .end();


    }

}
