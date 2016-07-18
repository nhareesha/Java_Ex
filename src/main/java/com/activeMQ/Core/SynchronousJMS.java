package com.activeMQ.Core;

import com.activeMQ.Synchronous.SyncConsumer;

public class SynchronousJMS {

	public static void main(String[] args) {
		
		Producer prod = new Producer();
		SyncConsumer cons = new SyncConsumer();
		
		prod.sendMessage();
		cons.receiveSynchronousMessage();
	}

}
