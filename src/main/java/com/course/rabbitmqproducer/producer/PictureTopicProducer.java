package com.course.rabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
	
import com.course.rabbitmqproducer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class PictureTopicProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void sendMessage(Picture picture) throws JsonProcessingException {
			
			StringBuilder routingKey = new StringBuilder();
			routingKey.append(picture.getSource() + ".");
			routingKey.append(picture.getSize() > 4000 ? "large." : "small.");
			routingKey.append(picture.getType());
			String pictureJson = objectMapper.writeValueAsString(picture);
			rabbitTemplate.convertAndSend("x.picture2", routingKey.toString(), pictureJson);
		
	}
	
}
