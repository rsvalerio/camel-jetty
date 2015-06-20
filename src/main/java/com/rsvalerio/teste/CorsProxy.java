package com.rsvalerio.teste;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.jetty.http.HttpException;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CorsProxy extends RouteBuilder {

    public void configure() {

    	from("jetty:http://localhost:9090/")
	    	.process(new Processor() { @Override public void process(Exchange exchange) throws Exception {

	    		exchange.getOut().setHeader("Access-Control-Allow-Origin", "*");
	    		exchange.getOut().setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
	    		exchange.getOut().setHeader("Access-Control-Allow-Headers", "X-Requested-With, Accept, Origin, Referer, User-Agent, Content-Type, Authorization, X-Mindflash-SessionID");

	    		HttpServletRequest req = exchange.getIn().getBody(HttpServletRequest.class);

	    		Integer sleep = 0;
	    		boolean error = false;

	    		if(req.getParameter("sleep")!=null){
	    			sleep = Integer.valueOf( req.getParameter("sleep") );
	    		}

	    		if(req.getParameter("error")!=null && req.getParameter("error").equalsIgnoreCase("S")){
	    			throw new HttpException(500);
	    		}

	    		if(exchange.getIn().getHeader("Content-Type")==null){
	    			exchange.getIn().setHeader("Content-Type", "text/plain");
	    		}

//	    	    res.header('Access-Control-Allow-Origin', '*');
//	    	    res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
//	    	    res.header('Access-Control-Allow-Headers', 'X-Requested-With, Accept, Origin, Referer, User-Agent, Content-Type, Authorization, X-Mindflash-SessionID');


	    		Thread.sleep(sleep);
	    		exchange.getOut().setBody(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+"\n");

			}});

    }

}
