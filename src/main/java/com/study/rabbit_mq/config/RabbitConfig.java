package com.study.rabbit_mq.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue_name;

    @Value("${rabbitmq.queue.exchange}")
    private String queue_exchange;

    @Value("${rabbitmq.queue.name.json}")
    private String queue_json_name;

    @Value("${rabbitmq.queue.routing.key}")
    private String routing_key;

    @Value("${rabbitmq.queue.routing.key.json}")
    private String routing_key_json;

    @Bean
    public Queue queue(){
        return new Queue(queue_name);
    }

    @Bean
    public Queue jsonQueue(){
        return new Queue(queue_json_name);
    }

    @Bean
    public Exchange exchange(){
        return new TopicExchange(queue_exchange);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routing_key).noargs();
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(routing_key_json).noargs();
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    //autoconfigured beans
    /*
        ConnectionFactory
        RabbitTemplate
        RabbitAdmin
     */
}
