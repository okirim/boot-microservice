package com.mycompany.app;

import com.mycompany.amqp.RabbitMQMessageProducer;
import com.mycompany.client.fraud.FraudClient;
import com.mycompany.client.notification.NotificationRequest;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FraudClient fraudClient;

    @Autowired
    RabbitMQMessageProducer rabbitMQMessageProducer;

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange; // internal exchange name (Topic Exchange)

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey; // internal notification routing key (Routing Key)


    public void register(CustomerRegisterDTO dto) {
        Customer customer = Customer.builder()
                .email(dto.email())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .build();
        //TODO: validate app

        //save app
        Customer newCustomer=customerRepository.save(customer);

        //send request to fraud microservice
        log.info("Sending request to fraud microservice with app {}", newCustomer.getFirstName());
        fraudClient.isFraudster(newCustomer.getId());

        //send request to notification microservice using rabbitMQ
        log.info("Sending notification to {}", newCustomer.getEmail());
        NotificationRequest notificationRequest=new NotificationRequest(newCustomer.getId(), newCustomer.getEmail(), "Welcome to our service");
        rabbitMQMessageProducer.publish(notificationRequest,this.internalExchange,this.internalNotificationRoutingKey);

    }
}
