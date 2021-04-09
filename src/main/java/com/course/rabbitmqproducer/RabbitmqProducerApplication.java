package com.course.rabbitmqproducer;

import java.time.LocalDate;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.course.rabbitmqproducer.entity.Employee;
import com.course.rabbitmqproducer.producer.EmployeeJsonProducer;

@SpringBootApplication
@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner{

	@Autowired
	private EmployeeJsonProducer employeeProducer;
	
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		IntStream.range(1, 6).
			forEach(i -> employeeProducer.sendMessage(new Employee("emp"+i, "Employee "+i, LocalDate.now())));
		
	}

}
