package com.activeMQ.springJms;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
/**
 * Synchronous consumer
 * @author Hareesha
 *
 */
public class SpringJmsConsumer {
	private JmsTemplate jmsTemplate;
	private Destination destination ;
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	public void receiveMsg(){
		try{
			Message msg = jmsTemplate.receive(destination);
			if(msg!= null && (msg instanceof TextMessage)){
				System.out.println("Messages received from Consumer : "+((TextMessage)msg).getText());
				
			}else{
				System.out.println();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
