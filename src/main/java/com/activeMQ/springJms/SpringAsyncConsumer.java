package com.activeMQ.springJms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class SpringAsyncConsumer implements MessageListener {

	public void onMessage(Message message) {
		if(message != null  && message instanceof TextMessage){
			TextMessage msg = (TextMessage)message;
			try {
				System.out.println("Message recived by Async Consumer : "+msg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("Unexpected message instance");
		}
	}

}
