package com.course.rabbitmqproducer;

import java.time.LocalDate;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.rabbitmqproducer.entity.Employee;
import com.course.rabbitmqproducer.producer.SpringRetryEmloyeeProducer;
import com.fasterxml.jackson.core.JsonProcessingException;


@SpringBootApplication
public class RabbitmqProducerApplication implements CommandLineRunner{

	@Autowired
	private SpringRetryEmloyeeProducer employeeProducer;
	
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}
	@Override
	public void run(String... args) {
		IntStream
			.range(0, 1)
			.forEach(
					index ->{ 
						Employee employee = new Employee(
								"emp" + index, 
								null, 
								LocalDate.now());
						try {
							employeeProducer.sendMessage(employee);
						} catch (JsonProcessingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			);
	}

}
