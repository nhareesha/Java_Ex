package com.activeMQ.springJms;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TestAsyncConsumerServlet extends HttpServlet{
	
	
	private static final long serialVersionUID = 1L;
	
	private ApplicationContext appCtx = null;
	private BrokerService broker = null;
	private SpringAsyncConsumer asyncConsumer = null;
	private SpringJmsProducer producer = null;
	
	public void init() throws ServletException{
		appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	}

	public void doGet() throws ServletException,IOException{
		System.out.println("Inside service -Async");
		try{
			
			broker = BrokerFactory.createBroker(new URI("broker:(tcp://0.0.0.0:61616)"));
	        broker.start();
	        producer = (SpringJmsProducer)appCtx.getBean("springJmsProducer",SpringJmsProducer.class);
	        producer.sendMessage("Message to async consumer implemented using spring framework");
	        
	        System.out.println("Message sent");
			broker.stop();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
