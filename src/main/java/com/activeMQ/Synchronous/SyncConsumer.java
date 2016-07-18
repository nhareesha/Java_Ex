package com.activeMQ.Synchronous;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class SyncConsumer {
	
	private ConnectionFactory factory;
	private Connection conn;
	private Session session;
	private Destination destination;
	private TextMessage msg;
	private MessageConsumer consumer;
	
	public SyncConsumer() {
		
	}
	
	public void receiveSynchronousMessage(){
		
		try{
			
		factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		
		conn = factory.createConnection();
		
		conn.start();
		
		session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE );
		
		destination = session.createQueue("MYQUEUE");
		
		consumer = session.createConsumer(destination);
		
		//in synchronous way , consumer consumes as soon as message is sent by the producer
		msg = (TextMessage) consumer.receive();
		
		if(msg != null && msg instanceof TextMessage){
			
			System.out.println(msg.getText());
			
		}else{
			System.out.println("Empty message or null object");
		}
		
		}
		catch(Exception ex){
			ex.printStackTrace();		
		}
		/*finally{
			if(conn != null)
				conn = null;
		}	*/
		
	}
	
	public static void main(String args[]){
		SyncConsumer consumer = new SyncConsumer();
		consumer.receiveSynchronousMessage();
	}

}
