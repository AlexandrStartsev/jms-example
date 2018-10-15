package edu.alex;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SpringClient {

	public static void main(String[] args) {
		
		try(ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
			context.getBean(JmsTemplate.class).send(new MessageCreator() {
	            @Override
				public javax.jms.Message createMessage(javax.jms.Session session) throws javax.jms.JMSException {
	            	try {
		            	System.out.println("sending message...");
		            	return session.createTextMessage("Sending some message " + new java.util.Date());
	            	} finally {
	            		System.out.println("exitting client application");
	            	}
	            }
	        });
		}
	}
}
