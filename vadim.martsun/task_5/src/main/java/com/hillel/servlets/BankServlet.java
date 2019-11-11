package com.hillel.servlets;

import com.google.gson.Gson;
import com.hillel.model.BankAcc;
import com.hillel.model.Status;
import com.hillel.model.User;
import com.hillel.model.requests.CreateBankAccRequest;
import com.hillel.model.requests.UpdateBankAccRequest;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebServlet(name = "BankServlet", urlPatterns = {"/bank"})
public class BankServlet extends HttpServlet {
    private Map<Long, BankAcc> accounts = new HashMap<>();
    private long accountId = 0;
    private Gson gson = new Gson();
    private final String ACCOUNT_ID = "bankAccountId";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String bankAccountParam = request.getParameter(ACCOUNT_ID);
        if(bankAccountParam == null){
            response.getWriter().println(gson.toJson(accounts));
        }else{
            BankAcc extractedAccount = accounts.get(Long.parseLong(bankAccountParam));
            Status status;
            if(extractedAccount == null){
                status = new Status(false, "There is no account with such id");
                response.setStatus(404);
            }else {
                status = new Status(true, "The required account has been found");
                response.setStatus(200);
                response.getWriter().println(gson.toJson(extractedAccount));
            }
            response.getWriter().println(gson.toJson(status));
            log.info(status.toString());
        }
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        try{
            Status status;
            if(request.getParameter(ACCOUNT_ID) != null) {
                long accountIdParam = Integer.parseInt(request.getParameter(ACCOUNT_ID));
                accounts.remove(accountIdParam);
                status = new Status(true, "The account has been deleted");
                response.setStatus(200);
                response.getWriter().println(status);
            } else {
                response.getWriter().println(ACCOUNT_ID + " parameter required");
                response.setStatus(400);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");

        UpdateBankAccRequest updateBankAccRequest = gson.fromJson(request.getReader(), UpdateBankAccRequest.class);
        long accountIdParam = Long.parseLong(updateBankAccRequest.getId());
        BankAcc account = accounts.get(accountIdParam);
        log.info(account.toString());
        try {
            if (account != null) {
                Date userBirthDate = new SimpleDateFormat("dd.MM.yyyy").parse(updateBankAccRequest.getUserBirthDate());
                User user = new User(updateBankAccRequest.getUserFirstName(),
                        updateBankAccRequest.getUserLastName(),
                        userBirthDate);
                BankAcc bankAccount = new BankAcc(updateBankAccRequest.getBalance(), user);
                accounts.put(accountIdParam, bankAccount);

                response.getWriter().println(gson.toJson(updateBankAccRequest));
            } else {
                response.getWriter().println("The account has not been found");
                response.setStatus(404);
            }
            log.info(updateBankAccRequest.toString());
        }catch (ParseException e){
            log.info(e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        CreateBankAccRequest createBankAccRequest = gson.fromJson(request.getReader(), CreateBankAccRequest.class);
        try {
            Date userBirthDate = new SimpleDateFormat("dd.MM.yyyy").parse(createBankAccRequest.getUserBirthDate());
            User user = new User(createBankAccRequest.getUserFirstName(),
                    createBankAccRequest.getUserLastName(),
                    userBirthDate);
            BankAcc bankAccount = new BankAcc(createBankAccRequest.getBalance(), user);
            accounts.put(accountId, bankAccount);
            accountId++;
            Status status = new Status(true, "A bank account has been added");
            response.getWriter().println(gson.toJson(status));

        } catch (ParseException e) {
            Status status = new Status(false, "Something went wrong");
            response.setStatus(500);
            response.getWriter().println(gson.toJson(status));
            log.info(e.getMessage());
        }

    }

}
