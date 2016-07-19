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
	private TextMessage msg1;
	private TextMessage msg2;
	private Destination destination1;
	private Destination destination2;
	private MessageProducer producer1; //sync
	private MessageProducer producer2;//Async
	
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
			destination1 = session.createQueue("MYQ1");
			producer1 = session.createProducer(destination1);
			
			//creating Message instance
			msg1 = session.createTextMessage();
			msg1.setText("This is the First Message on ActiveMQ JMS provider");
			
			//sending message to sync consumer
			producer1.send(msg1);
			
			//closing producer connection stream
			producer1.close();			
		}catch(JMSException ex){
			ex.printStackTrace();	
		}
		finally{
			if(conn != null){
				conn = null;
			}
		}
	
	}
	
	public void sendMessageToAsynConsumer(){
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
			destination2 = session.createQueue("AyncQueue");
			producer2 = session.createProducer(destination2);
			
			//creating message instance
			msg2 = session.createTextMessage();
			msg2.setText("This a message to async consumer");
			
			//sending to async consumer
			producer2.send(msg2);
			
			producer2.close();			
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
