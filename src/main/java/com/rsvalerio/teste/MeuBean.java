package com.rsvalerio.teste;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Headers;

import java.util.List;
import java.util.Map;

public class MeuBean {

    public void processar (
            Exchange exchange,
            @Body String body,
            @Headers List<Map<String,Object>> headers ) {
        exchange.getIn().removeHeader("name");
        exchange.getIn().setHeader("name", "bla");
    }

}
