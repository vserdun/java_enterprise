package com.hillel.servlets;

import com.google.gson.Gson;
import com.hillel.model.Account;
import com.hillel.model.Status;
import com.hillel.request.CreateAccountRequest;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@WebServlet(name = "accountServlet", urlPatterns = {"/accounts"})
public class AccountServlet extends HttpServlet {
    private int currentAccountId = 1;
    private Map<Integer, Account> accountMap = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application'json");
        Gson gson = new Gson();
        String accountId = req.getParameter("accountId");

        if (accountId == null) {
            resp.getOutputStream().println(gson.toJson(accountMap));
        } else {
            try {
                if (accountMap.get(Integer.valueOf(accountId)) != null) {
                    resp.getOutputStream().println(gson.toJson(accountMap.get(Integer.valueOf(accountId))));
                } else {
                    Status status = new Status(false, "Account not found");
                    resp.getOutputStream().print(gson.toJson(status));
                    resp.setStatus(404);
                }
            } catch (Exception e) {
                log.error("Exception while tried to get account: ", e);
                Status status = new Status(false, "Bad request");
                resp.getOutputStream().print(gson.toJson(status));
                resp.setStatus(500);
            }
        }
        resp.getOutputStream().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        Status status;

        try {

            CreateAccountRequest createAccountRequest = gson.fromJson(req.getReader(), CreateAccountRequest.class);

            Account account = new Account(currentAccountId, createAccountRequest.getBalance(), createAccountRequest.getUser());
            accountMap.put(account.getId(), account);
            currentAccountId++;
            status = new Status(true, "Account was added");
            resp.getOutputStream().print(gson.toJson(account));
        } catch (Exception e) {
            log.error("Exception while tried to add account: ", e);
            status = new Status(false, "Failed to add account");
            resp.setStatus(500);
        }
        resp.getOutputStream().print(gson.toJson(status));
        resp.getOutputStream().flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        Status status;

        try {

            Account account = gson.fromJson(req.getReader(), Account.class);

            if (accountMap.containsKey(account.getId())){
                accountMap.put(account.getId(), account);
                resp.getOutputStream().print(gson.toJson(account));
            }
            else {
                status = new Status(false, "Account not found");
                resp.getOutputStream().print(gson.toJson(status));
                resp.setStatus(404);
            }

        } catch (Exception e) {
            log.error("Exception while tried to update account: ", e);
            status = new Status(false, "Failed to update account");
            resp.getOutputStream().print(gson.toJson(status));
            resp.setStatus(500);
        }

        resp.getOutputStream().flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String accountId = req.getParameter("accountId");

        if (accountId == null) {
            Status status = new Status(false, "Bad request");
            resp.getOutputStream().print(gson.toJson(status));
            resp.setStatus(500);
        } else {
            try {
                if (accountMap.get(Integer.valueOf(accountId)) != null) {
                    accountMap.remove(Integer.valueOf(accountId));
                    Status status = new Status(true, "Account " + accountId + " was deleted");
                    resp.getOutputStream().print(gson.toJson(status));
                } else {
                    Status status = new Status(false, "Account not found");
                    resp.getOutputStream().print(gson.toJson(status));
                    resp.setStatus(404);
                }
            } catch (Exception e) {
                log.error("Exception while tried to get account: ", e);
                Status status = new Status(false, "Bad request");
                resp.getOutputStream().print(gson.toJson(status));
                resp.setStatus(500);
            }
        }

    }
}
