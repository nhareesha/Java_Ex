package com.activeMQ.springJms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SpringJmsProducer {

	private JmsTemplate jmsTemplate;
	
	private Destination destination;
	
	
	public Destination getDestination() {
		return destination;
	}


	public void setDestination(Destination destination) {
		this.destination = destination;
	}


	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}


	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}



	public void sendMessage(final String msg){
		jmsTemplate.send(destination, new MessageCreator(){
			public Message createMessage(Session session) throws JMSException{
				return session.createTextMessage(msg);
			}
		});
	}
}
