package com.mycompany.app;

import com.mycompany.client.fraud.FraudClient;
import com.mycompany.client.notification.NotificationClient;
import com.mycompany.client.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient,NotificationClient notificationClient) {
    public void register(CustomerRegisterDTO dto) {
        Customer customer = Customer.builder()
                .email(dto.email())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .build();
        //TODO: validate customer
        //save customer
        Customer newCustomer=customerRepository.save(customer);
        //send request to fraud microservice
        log.info("Sending request to fraud microservice with customer {}", newCustomer.getFirstName());
        fraudClient.isFraudster(newCustomer.getId());
        //send request to notification microservice
        log.info("Sending notification to {}", newCustomer.getEmail());
        NotificationRequest notificationRequest=new NotificationRequest(newCustomer.getId(), newCustomer.getEmail(), "Welcome to our service");
        notificationClient.sendNotification(notificationRequest);
    }
}
