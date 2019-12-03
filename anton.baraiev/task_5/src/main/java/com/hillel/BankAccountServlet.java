package com.hillel;

import com.google.gson.Gson;
import com.hillel.model.BankAccount;
import com.hillel.model.CrudEventStatus;
import com.hillel.model.request.CreateBankAccountRequest;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@WebServlet(name = "BankAccountServlet", urlPatterns = {"/bankaccs"})
public class BankAccountServlet extends HttpServlet {
    private Map<Long, BankAccount> accounts = new ConcurrentHashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        CrudEventStatus status;
        String message;
        try {
            CreateBankAccountRequest createBankAccountRequest = gson.fromJson(req.getReader(), CreateBankAccountRequest.class);
            BankAccount bankAccount = new BankAccount(createBankAccountRequest.getId(), createBankAccountRequest.getAmount(),
                                                        createBankAccountRequest.getUser());
            accounts.put(createBankAccountRequest.getId(), bankAccount);
            message = String.format("Account %s was successfully added", createBankAccountRequest.getId());
            status = new CrudEventStatus(true, message);
            log.info(message);
        }catch (Exception e) {
            message = "Exception while adding an account";
            status= new CrudEventStatus(false, message);
            log.error(message);
            resp.setStatus(500);
        }
        resp.getWriter().println(status.getDescription());
        resp.getWriter().flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        CrudEventStatus status;
        String message;
        try{
            CreateBankAccountRequest createBankAccountRequest = gson.fromJson(req.getReader(), CreateBankAccountRequest.class);
            long id = createBankAccountRequest.getId();
            if (accounts.containsKey(id)) {
                BankAccount bankAccount = new BankAccount(id, createBankAccountRequest.getAmount(), createBankAccountRequest.getUser());
                accounts.put(id, bankAccount);
                message = String.format("Bank account id = %d info was updated", id);
                status = new CrudEventStatus(true, message);
                log.info(message);
            }else {
                message = String.format("There is no bank account w/ given id = %d", id);
                status = new CrudEventStatus(false, message);
                log.warn(message);
                resp.setStatus(404);
            }
        }catch (Exception e) {
            message = "Error, while trying to update account info";
            status = new CrudEventStatus(false, message);
            resp.setStatus(500);
        }
        resp.getWriter().println(status.getDescription());
        resp.getWriter().flush();
    }
}
