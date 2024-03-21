package com.tga.product.service.events.pubsub;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tga.product.event.ProductAddedEvent;
import com.tga.product.service.dto.ProductDTO;
import com.tga.product.service.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductEventListener {

	private final ProductService productService;

	private final ProductEventPublisher eventPublisher;

	@Value("${kafka.consumer.product.topic}")
	private String orderEventTopic;
	
	/**
	 * Consumer connect issue : Kafka consumer not discoverable : map memory out exception issue 
	 * this helped me: change : export KAFKA_HEAP_OPTS="-Xmx512M -Xms512M" (originally 1G)
		in kafka-server-start script
	 * @param event
	 */

	@KafkaListener(topics = "${kafka.consumer.product.topic}", groupId = "test-consumer-group" )
	//  "${spring.kafka.consumer.group-id}") 
	public void consumeMessage(ProductAddedEvent event) { log.info("Got message <{}>",
	  event); List<ProductDTO> updateDtoList = productService.getProducts();
	  log.info("Updated inventory List <{}>", updateDtoList);
	  
	 //   eventPublisher.publishToTopic(event, orderEventTopic);
	  }
	 

//    @Override
//    public void consume(List<Event> messages, List<String> keys, List<Integer> partitions, List<Long> offsets) {
//        log.info("{} number of customer create messages received with keys {}, partitions {} and offsets {}",
//                messages.size(),
//                keys.toString(),
//                partitions.toString(),
//                offsets.toString());
//
//        inventoryService.deductInventory(messages.stream().map(message -> ((InventoryDeductedEvent) message).getInventory()).toList());
//    }
}
