package com.hillel.model;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

public class Database {

    @Getter
    private Map<Long,User> users;

    @Getter
    private Map<Long, BankAcc> accounts;

    private Database(){
        users = new HashMap<>();
        accounts = new HashMap<>();
    }
    private static Database instance;

    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }
}
