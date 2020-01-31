package com.hillel.svidovyi_task4.model;

import lombok.Data;

import java.util.HashMap;

@Data
public class ClientRepository {
    private HashMap<Integer, BankClient> clients;

}
