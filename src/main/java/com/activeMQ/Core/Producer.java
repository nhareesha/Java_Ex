package com.activeMQ.Core;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;


public class Producer {
	
	private ConnectionFactory factory;
	private Connection conn;
	private Session session;
	private TextMessage msg;
	private Destination destination;
	private MessageProducer producer;
	
	public Producer() {
		
	}
	public void sendMessage(){
		try {
			
			//Creating ConnectionFactory instance
			factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			
			//creating connection
			conn = factory.createConnection();
			
			//starting connection
			conn.start();
			
			//creating session
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//creating a Destination of type Queue
			destination = session.createQueue("MYQUEUE");
			producer = session.createProducer(destination);
			
			//creating Message instance
			msg = session.createTextMessage();
			msg.setText("This is the First Message on ActiveMQ JMS provider");
			
			//sending message
			producer.send(msg);
			
			//closing producer connection stream
			producer.close();			
		}catch(JMSException ex){
			ex.printStackTrace();	
		}
		finally{
			if(conn != null){
				conn = null;
			}
		}
	
	}
/*	public static void main(String args[]){
		
		Producer prod = new Producer();
		prod.sendMessage();
	}*/
}
