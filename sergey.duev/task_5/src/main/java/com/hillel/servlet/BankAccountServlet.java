package com.hillel.servlet;

import com.hillel.model.BankAccount;
import com.hillel.model.Status;
import com.hillel.model.User;
import com.hillel.model.request.CreateBankAccountRequest;
import com.hillel.model.request.DeleteBankAccountRequest;
import com.hillel.model.request.UpdateBankAccountRequest;
import com.hillel.service.BankService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BankAccount", urlPatterns = {Constants.PATH_BANK_ACCOUNT})
public class BankAccountServlet extends BaseHttpServlet {

    private BankService bankService = BankService.init();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        if (req.getParameter(Constants.USER_ID) != null) {
            Long userId = Long.parseLong(req.getParameter(Constants.USER_ID));
            User user = bankService.findUser(userId);
            if (user != null) {
                if(req.getParameter(Constants.BANK_ACCOUNT_ID) != null) {
                    Long bankAccountId = Long.parseLong(req.getParameter(Constants.BANK_ACCOUNT_ID));
                    BankAccount bankAccount = bankService.findBankAccount(userId, bankAccountId);
                    if(bankAccount != null) {
                        printGson(resp, bankAccount);
                    } else {
                        error404(resp, Constants.ERROR_BANK_ACCOUNT_NOT_FOUND);
                    }
                } else {
                    printGson(resp, bankService.getBankAccounts(user));
                }
            } else {
                error404(resp, Constants.ERROR_USER_NOT_FOUND);
            }
        } else {
            printGson(resp, bankService.getAllBankAccounts());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Status status = new Status();
        try {
            CreateBankAccountRequest createBankAccountRequest = parseJson(req, CreateBankAccountRequest.class);
            User user = bankService.findUser(createBankAccountRequest.getUserId());
            if (user != null) {
                bankService.createNewBankAccount(createBankAccountRequest.getUserId(), createBankAccountRequest.getAmount());
                status.setSuccess(true);
                status.setDescription(Constants.INFO_BANK_ACCOUNT_CREATED);
            } else {
                error404(resp, Constants.ERROR_USER_NOT_FOUND);
            }
        } catch (Exception ex) {
            status.setSuccess(false);
            status.setDescription(ex.getMessage());
        }
        printGson(resp, status);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Status status = new Status();
        try {
            UpdateBankAccountRequest updateBankAccountRequest = parseJson(req, UpdateBankAccountRequest.class);
            User user = bankService.findUser(updateBankAccountRequest.getUserId());
            if (user != null) {
                BankAccount bankAccount = bankService.findBankAccount(updateBankAccountRequest.getUserId(), updateBankAccountRequest.getBankAccountId());
                if (bankAccount != null) {
                    bankService.updateBankAccount(user, bankAccount, updateBankAccountRequest.getAmount());
                    status.setSuccess(true);
                    status.setDescription(Constants.INFO_BANK_ACCOUNT_UPDATED);
                } else {
                    error404(resp, Constants.ERROR_BANK_ACCOUNT_NOT_FOUND);
                }
            } else {
                error404(resp, Constants.ERROR_USER_NOT_FOUND);
            }
        } catch (Exception ex) {
            status.setSuccess(false);
            status.setDescription(ex.getMessage());
        }
        printGson(resp, status);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Status status = new Status();
        try {
            DeleteBankAccountRequest deleteBankAccountRequest = parseJson(req, DeleteBankAccountRequest.class);
            User user = bankService.findUser(deleteBankAccountRequest.getUserId());
            if (user != null) {
                BankAccount bankAccount = bankService.findBankAccount(deleteBankAccountRequest.getUserId(), deleteBankAccountRequest.getBankAccountId());
                if (bankAccount != null) {
                    bankService.deleteBankAccount(user, bankAccount);
                    status.setSuccess(true);
                    status.setDescription(Constants.INFO_BANK_ACCOUNT_DELETED);
                } else {
                    error404(resp, Constants.ERROR_BANK_ACCOUNT_NOT_FOUND);
                }
            } else {
                error404(resp, Constants.ERROR_USER_NOT_FOUND);
            }
        } catch (Exception ex) {
            status.setSuccess(false);
            status.setDescription(ex.getMessage());
        }
        printGson(resp, status);
    }

}
