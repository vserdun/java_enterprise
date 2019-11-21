package com.hillel.task_5.model.request_model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientPutRequestModel {
    private String clientId;
    private String clientName;
}
