package com.hillel.task_5.model.request_model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteClientRequestModel {
    private String clientId;
}
