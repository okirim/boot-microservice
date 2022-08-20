package com.mycompany.app;


import com.mycompany.client.fraud.FraudCheckHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudCheckHistoryController {

    @Autowired
    private FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping("/{customerId}")
    public ResponseEntity<FraudCheckHistoryResponse> isFraudster(@PathVariable("customerId") Long customerId) {
        Boolean isFraudster= fraudCheckHistoryService.isFraudulentCustomer(customerId);
        var response= new FraudCheckHistoryResponse();
        response.setData(isFraudster);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
