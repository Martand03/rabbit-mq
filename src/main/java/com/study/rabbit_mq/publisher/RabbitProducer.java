package com.study.rabbit_mq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitProducer {

    private static final Logger log = LoggerFactory.getLogger(RabbitProducer.class);

    @Value("${rabbitmq.queue.exchange}")
    private String exchange;

    @Value("${rabbitmq.queue.routing.key}")
    private String r_key;

    private RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        log.info("Message sent: {}", message);
        rabbitTemplate.convertAndSend(exchange, r_key, message);
    }
}
