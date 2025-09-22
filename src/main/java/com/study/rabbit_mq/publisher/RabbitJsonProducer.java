package com.study.rabbit_mq.publisher;

import com.study.rabbit_mq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitJsonProducer {

    private static final Logger log = LoggerFactory.getLogger(RabbitJsonProducer.class);

    @Value("${rabbitmq.queue.routing.key.json}")
    private String routing_key_json;

    @Value("${rabbitmq.queue.exchange}")
    private String queue_exchange;

    private RabbitTemplate rabbitTemplate;

    public RabbitJsonProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        log.info("User sent: {}", user.toString());
        rabbitTemplate.convertAndSend(queue_exchange, routing_key_json, user);
    }

}
