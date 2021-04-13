package com.course.rabbitmqproducer;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.course.rabbitmqproducer.entity.Picture;
import com.course.rabbitmqproducer.producer.SpringRetryPictureProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.netty.util.internal.ThreadLocalRandom;


@SpringBootApplication
public class RabbitmqProducerApplication implements CommandLineRunner{

	@Autowired
	private SpringRetryPictureProducer pictureProducer;
	private final List<String> SOURCES = List.of("mobile", "web");
	private final List<String> TYPES = List.of("jpg", "png", "svg");
	
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}
	@Override
	public void run(String... args) {
		IntStream
			.range(0, 1)
			.forEach(
					index ->{ 
						Picture picture = new Picture(
								"Picture "+index, 
								TYPES.get(index%TYPES.size()), 
								SOURCES.get(index%SOURCES.size()), 
								ThreadLocalRandom.current().nextLong(9001, 10001));
						try {
							pictureProducer.sendMessage(picture);
						} catch (JsonProcessingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			);
	}

}
