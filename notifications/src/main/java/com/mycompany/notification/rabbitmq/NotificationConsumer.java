package com.mycompany.notification.rabbitmq;

import com.mycompany.client.notification.NotificationRequest;
import com.mycompany.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    @Autowired
    NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void receiveAndConsume(NotificationRequest message) {
        log.info("Received message: {}", message);
        notificationService.send(message);
    }

}
