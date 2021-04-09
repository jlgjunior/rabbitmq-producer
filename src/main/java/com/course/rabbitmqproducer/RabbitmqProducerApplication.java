package com.course.rabbitmqproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.rabbitmqproducer.producer.HelloRabbitProducer;

@SpringBootApplication
public class RabbitmqProducerApplication implements CommandLineRunner{

	@Autowired
	private HelloRabbitProducer helloRabbitProducer;
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		helloRabbitProducer.sendHello("João 23");
	}

}
