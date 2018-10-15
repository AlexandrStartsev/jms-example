package edu.alex;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

// Requires external broker to run http://activemq.apache.org/   activemq start 

public class Application {

	public static void main(String[] args) throws JMSException {
		QueueConnectionFactory connFactory = new ActiveMQConnectionFactory("tcp://alexandr-desktop:61616");
		
		try(QueueConnection conn = connFactory.createQueueConnection(); QueueSession session = conn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE)) {
			Destination destination = session.createQueue("QUEUE#1"); 
			// Sender 
			/*MessageProducer producer = session.createProducer(destination);
			// Text message
			TextMessage msg = session.createTextMessage();
			msg.setText("Hello there (" + new Date() + ")");
			System.out.println("Sending the message: " + msg.getText());
			producer.send(msg);*/
	
			System.out.println("waiting for message...");
			
			conn.start();
			//while(true) {
				MessageConsumer receiver = session.createConsumer(destination);
				Message m = receiver.receive();
				if (m instanceof TextMessage) {
					TextMessage txt = (TextMessage) m;
					System.out.println("Message Received: " + txt.getText());
				}
			//}
		}
	}
}
