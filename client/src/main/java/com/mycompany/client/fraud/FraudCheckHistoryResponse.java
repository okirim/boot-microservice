package com.mycompany.client.fraud;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

@Data
@NoArgsConstructor
public class FraudCheckHistoryResponse {
    private final HttpStatus httpStatus=HttpStatus.OK;
    private HashMap<String, Boolean> data;

    public void setData(Boolean isFraudster) {
        HashMap<String, Boolean> data= new HashMap<>();
        data.put("isFraudster", isFraudster);
    }
}