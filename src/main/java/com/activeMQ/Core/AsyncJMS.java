package com.activeMQ.Core;

import com.activeMQ.Asynchronous.AyncConsumer;

public class AsyncJMS {

	public static void main(String[] args) {
		Producer prod = new Producer();
		 AyncConsumer cons = new AyncConsumer();
		
		prod.sendMessageToAsynConsumer();
		cons.asyncConsumer();
	}

}
