package com.course.rabbiqmqproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.course.rabbiqmqproducer.producer.HelloRabbitProducer;

@SpringBootApplication
public class RabbiqmqProducerApplication implements CommandLineRunner{

	@Autowired
	private HelloRabbitProducer helloRabbitProducer;
	
	public static void main(String[] args) {
		SpringApplication.run(RabbiqmqProducerApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		helloRabbitProducer.sendHello("João 23");
	}

}
