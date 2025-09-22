package com.study.rabbit_mq.consumer;

import com.study.rabbit_mq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitJsonConsumer {

    private static final Logger log = LoggerFactory.getLogger(RabbitJsonConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.name.json}")
    public void jsonConsume(User user){
        log.info("Received json for User: {}", user.toString());
    }
}
