package com.activeMQ.springJms;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ApplicationContext appCtx = null;
	private SpringJmsProducer producer = null;
	private SpringJmsConsumer consumer= null;
	private BrokerService broker = null;
	
	
	public void init() throws ServletException{
		try{
			System.out.println("Inside init");
			//WebapplicationContext
			appCtx = WebApplicationContextUtils
				        .getRequiredWebApplicationContext(getServletContext());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
/*	
	public void service(ServletRequest req, ServletResponse res) throws ServletException,IOException{
		doGet(req,res);
	}*/

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException,IOException{
		System.out.println("Inside service");
		
		try{
			//to escape the JVM_Bind exception , instantiating the broker 
			//object inside service method..Here broker is instantiated for each and every request
			broker = BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)"));
	        broker.start();
			
			//Producer
			producer = (SpringJmsProducer) appCtx.getBean("springJmsProducer", SpringJmsProducer.class);
			String msg = "This is from Spring JMS producer";
			producer.sendMessage(msg);
			System.out.println("Message sent check ActiveMQ queue");
			
			//Consumer
			consumer = (SpringJmsConsumer)appCtx.getBean("springJmsConsumer", SpringJmsConsumer.class);
			consumer.receiveMsg();
			System.out.println("end of service");
			broker.stop();
		}catch(JMSException e){
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void destroy(){
		/*try {
			broker.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}
