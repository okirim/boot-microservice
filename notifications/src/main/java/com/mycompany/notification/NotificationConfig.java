package com.mycompany.notification;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class NotificationConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange; // internal exchange name (Topic Exchange)

    @Value("${rabbitmq.queues.notification}")
    private String notificationQueue; // notification queue name (Queue)
    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey; // internal notification routing key (Routing Key)

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange); // set the Topic
    }
    @Bean
    public Queue notificationQueue() {
        return new Queue(this.notificationQueue); //set the Queue
    }

    @Bean
    public Binding internalToNotificationBinding() {
        //bind the topic with the queue
        return BindingBuilder
                .bind(notificationQueue()) //bind Queue
                .to(internalTopicExchange()) //to the Topic
                .with(this.internalNotificationRoutingKey); //with routing
    }
    public String getInternalExchange() {
        return internalExchange;
    }

    public String getNotificationQueue() {
        return notificationQueue;
    }

    public String getInternalNotificationRoutingKey() {
        return internalNotificationRoutingKey;
    }
}