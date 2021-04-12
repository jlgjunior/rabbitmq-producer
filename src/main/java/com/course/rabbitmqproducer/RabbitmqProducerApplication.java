package com.course.rabbitmqproducer;

import java.time.LocalDate;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.course.rabbitmqproducer.entity.Employee;
import com.course.rabbitmqproducer.producer.RetryEmloyeeProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication
@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner{

	@Autowired
	private RetryEmloyeeProducer employeeProducer;
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		IntStream.range(0, 10).
			forEach(i -> {
				try {
					employeeProducer.sendMessage(new Employee("emp" + i, null, LocalDate.now()));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		
	}

}
