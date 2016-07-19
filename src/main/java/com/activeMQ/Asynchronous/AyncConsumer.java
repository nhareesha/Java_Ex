package com.activeMQ.Asynchronous;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class AyncConsumer implements MessageListener {
	private ConnectionFactory factory;
	private Connection conn;
	private Session session;
	private MessageConsumer consumer;
	private TextMessage msg;
	private Destination destination;
	private MessageListener listener;

	 /**
	    This method is called asynchronously by JMS when a message arrives
	    at the queue. Client applications must not throw any exceptions in
	    the onMessage method.
	    @param message A JMS message.
	  */
	public void onMessage(Message message) {
		System.out.println("In onMessage() method of Listener");
		if(message instanceof TextMessage){
			String msg;
			try {
				msg = ((TextMessage) message).getText();
				System.out.println(msg);} catch (JMSException e) {
				e.printStackTrace();
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Asynchronous Consumer
	 */
	public void asyncConsumer(){
		try{
			//creating ActiveMQConnectionFactory instance
			factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			
			//creating a connection instance
			conn = factory.createConnection();
			
			//creation a session instance
			session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			//creating queue instance
			destination = session.createQueue("AyncQueue");
			
			//creating  MessageConsumer instance
			consumer = session.createConsumer(destination);
			
			//Creating listener instance ..A listener class that implements MessageListener interface
			//overrides the onMessage() method.
			
			listener = new AyncConsumer();
			
			//registering Listener with the MessageConsumer
			consumer.setMessageListener(listener);
			
			//starting connection
			conn.start();
			
			System.out.println("wating for message");
					
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
