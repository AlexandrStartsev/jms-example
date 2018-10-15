package edu.alex;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@ComponentScan
public class AppConfig {

	//@formatter:off
	
	/*
	   For embedded ...
	 
	   @Bean
	   public JmsListenerContainerFactory listenerFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
	 	   DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	 	   configurer.configure(factory, connectionFactory);
	 	   return factory;
	   }
	  
	   @JmsListener(destination = "QUEUE#1")
	   public void handleMessage(Message<Order> order) {
	       ...
	   }
	*/
	
	//@formatter:on

	@Bean
	ConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory("tcp://alexandr-desktop:61616");
	}

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) throws JMSException {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestinationName("QUEUE#1");
		return jmsTemplate;
	}
}
