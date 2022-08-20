package com.mycompany.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FraudCheckHistoryService {

    @Autowired
    private FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Long customerId) {
         Optional<FraudCheckHistory> fraudCheckHistory = fraudCheckHistoryRepository.findById(customerId);
            if (fraudCheckHistory.isEmpty()) {
                var newFraudCheckHistory=FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .build();
                fraudCheckHistoryRepository.save(newFraudCheckHistory);
                return false;
            }

            return fraudCheckHistory.get().getIsFraudster();
    }
}
