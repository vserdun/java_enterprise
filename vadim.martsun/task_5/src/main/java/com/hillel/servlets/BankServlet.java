package com.hillel.servlets;

import com.google.gson.Gson;
import com.hillel.mapper.Mapper;
import com.hillel.mapper.MapperImpl;
import com.hillel.model.BankAcc;
import com.hillel.model.Database;
import com.hillel.model.Status;
import com.hillel.model.requests.BankAccRequest;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "BankServlet", urlPatterns = {"/bank"})
public class BankServlet extends HttpServlet {
    private long accountId = 0;
    private Gson gson = new Gson();
    private Mapper mapper = new MapperImpl();
    private final String ACCOUNT_ID = "bankAccountId";

    private Database database = Database.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String getParameter = request.getParameter(ACCOUNT_ID);
        Status status;
        if(getParameter == null){
            status = new Status(true, "Found " + database.getAccounts().size() + " accounts");
            response.getWriter().println(gson.toJson(database.getAccounts()));
        }else{
            BankAcc extractedAccount = database.getAccounts().get(Long.parseLong(getParameter));
            if(extractedAccount == null){
                status = new Status(false,"There is no account with such id");
                response.setStatus(404);
            }else {
                status = new Status(true, "The required account has been found");
                response.setStatus(200);
                response.getWriter().println(gson.toJson(extractedAccount));
            }
        }
        response.getWriter().println(gson.toJson(status));
    }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        Status status;
        String getParameter = request.getParameter(ACCOUNT_ID);
        if (getParameter != null) {
            BankAcc removedAccount = database.getAccounts().remove(Long.parseLong(getParameter));
            if (removedAccount == null) {
                status = new Status(true, "There is no account with such id");
                response.setStatus(404);
            } else {
                status = new Status(true, "The account has been deleted");
                response.setStatus(200);
            }
        } else {
            status = new Status(false, ACCOUNT_ID + " parameter required");
            response.setStatus(400);
        }
        response.getWriter().println(gson.toJson(status));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");

        Status status;
        String getParameter = request.getParameter(ACCOUNT_ID);
        if(getParameter == null){
            status = new Status(false, "Failed");
            response.setStatus(400);
        }else{
            BankAccRequest bankAccRequest = gson.fromJson(request.getReader(), BankAccRequest.class);
            long accId = Long.parseLong(getParameter);
            BankAcc account = database.getAccounts().get(accId);

            if(account == null){
                status = new Status(false, "Account has not been found");
                response.setStatus(404);
            }else {
                BankAcc updatedAccount = mapper.mapBankAccount(bankAccRequest);
                if(updatedAccount.getUser() == null){
                    status = new Status(false, "Bad request");
                    response.setStatus(400);
                }else {
                    database.getAccounts().put(accId, updatedAccount);
                    status = new Status(true, "Account has been successfully updated");
                    response.setStatus(200);
                }
            }
        }
        response.getWriter().println(gson.toJson(status));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");

        BankAccRequest bankAccRequest = gson.fromJson(request.getReader(), BankAccRequest.class);
        BankAcc bankAccount = mapper.mapBankAccount(bankAccRequest);

        Status status;
        if(bankAccount.getUser() != null){
            database.getAccounts().put(accountId, bankAccount);
            accountId++;
            status = new Status(true, "A bank account has been added");
            response.setStatus(200);
        }else {
            status = new Status(false, "No user with the specified id");
            response.setStatus(404);
        }

        response.getWriter().println(gson.toJson(status));
    }

}
