package com.course.rabbitmqproducer.utility;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class RabbitmqScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(RabbitmqScheduler.class);
	
	@Autowired
	private RabbitmqProxyService rabbitmqProxyService;
	
	@Scheduled(fixedDelay = 90000)
	public void sweepDirtyQueues(){
		try {
			List<RabbitmqQueue> queues = rabbitmqProxyService
												.getAllQueues()
												.stream()
												.filter(queue -> queue.isDirty())
												.collect(Collectors.toList());
			queues.forEach(queue -> logger.info("Queue {} has {} unprocessed messages", queue.getName(), queue.getMessages()));
		}
		catch(Exception e) {
			logger.error("Cannot sweep queues : {}", e.getMessage());
		}
	}
}
