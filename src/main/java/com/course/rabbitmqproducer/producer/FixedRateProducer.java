package com.course.rabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FixedRateProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	private Logger logger = LoggerFactory.getLogger(FixedRateProducer.class);
	private int index = 0;
	
	@Scheduled(fixedRate = 500)
	public void sendMessage(){
		rabbitTemplate.convertAndSend("course.fixedrate", "Fixed Rate: " + index);
	}
}
