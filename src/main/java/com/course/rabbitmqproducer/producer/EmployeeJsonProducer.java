package com.course.rabbitmqproducer.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.rabbitmqproducer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class EmployeeJsonProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	
	public void sendMessage(Employee employee) {
		try {
			String employeeJson = objectMapper.writeValueAsString(employee);
			rabbitTemplate.convertAndSend("course.employee", employeeJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
}
