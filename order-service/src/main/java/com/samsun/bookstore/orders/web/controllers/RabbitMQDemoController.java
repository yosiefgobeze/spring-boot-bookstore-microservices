// package com.samsun.bookstore.orders.web.controllers;
//
// import com.samsun.bookstore.orders.ApplicationProperties;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
//
// @RestController
// public class RabbitMQDemoController {
//    private final RabbitTemplate rabbitTemplate;
//    private final ApplicationProperties properties;
//
//    public RabbitMQDemoController(RabbitTemplate rabbitTemplate, ApplicationProperties properties) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.properties = properties;
//    }
//
//    @PostMapping(path = "/send")
//    public void sendMessage(@RequestBody MyMessage message) {
//        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), message.routingKey(), message.payload());
//    }
// }
//
// record MyMessage(String routingKey, MyPayload payload) {}
//
// record MyPayload(String content) {}
