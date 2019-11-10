package com.hillel;

import com.google.gson.Gson;
import com.hillel.model.Account;
import com.hillel.model.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        User user = new User(1, "Alex", "Zhuravlov");
        Account account = new Account(2, 40, user, new ArrayList<>());
        Gson gson = new Gson();
        String s = gson.toJson(account);
        System.out.println(s);
    }
}
