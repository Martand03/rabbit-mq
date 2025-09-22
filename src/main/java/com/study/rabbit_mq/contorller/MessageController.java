package com.study.rabbit_mq.contorller;

import com.study.rabbit_mq.dto.User;
import com.study.rabbit_mq.publisher.RabbitJsonProducer;
import com.study.rabbit_mq.publisher.RabbitProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private RabbitProducer rabbitProducer;
    private RabbitJsonProducer rabbitJsonProducer;

    public MessageController(RabbitProducer rabbitProducer, RabbitJsonProducer rabbitJsonProducer){
        this.rabbitProducer = rabbitProducer;
        this.rabbitJsonProducer = rabbitJsonProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> message(@RequestParam("message") String message){
        rabbitProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent successfully!! for message: " + message);
    }

    @PostMapping("/json/publish")
    public ResponseEntity<String> jsonMessage(@RequestBody User user){
        rabbitJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Message sent successfully !!! for User: " + user.toString());
    }

}
