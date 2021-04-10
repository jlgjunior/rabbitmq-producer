package com.course.rabbitmqproducer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.course.rabbitmqproducer.entity.Picture;
import com.course.rabbitmqproducer.producer.PictureErrorProducer;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication
@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner{

	@Autowired
	private PictureErrorProducer pictureProducer;
	private final List<String> SOURCES = List.of("mobile", "web");
	private final List<String> TYPES = List.of("jpg", "png", "svg");
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		IntStream.range(0, 1).
			forEach(i -> {
				try {
					pictureProducer.sendMessage(new Picture("Picture "+i, TYPES.get(i%TYPES.size()), SOURCES.get(i%SOURCES.size()), ThreadLocalRandom.current().nextLong(9001, 10001)));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		
	}

}
