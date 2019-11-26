package com.hillel.servlet;

import com.google.gson.Gson;
import com.hillel.model.*;
import com.hillel.model.request.*;
import com.hillel.service.BankService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "User", urlPatterns = {Constants.PATH_USER})
public class UsersServlet extends BaseHttpServlet {

    private BankService bankService = BankService.init();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        if(req.getParameter(Constants.USER_ID) != null) {
            Long userId = Long.parseLong(req.getParameter(Constants.USER_ID));
            User user = bankService.findUser(userId);
            if(user != null) {
                printGson(resp, gson.toJson(user));
            } else {
                error404(resp, Constants.ERROR_USER_NOT_FOUND);
            }
        } else {
            printGson(resp, bankService.getUsers());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Status status = new Status();
        try {
            CreateUserRequest createUserRequest = parseJson(req, CreateUserRequest.class);
            bankService.createNewUser(createUserRequest.getName());
            status.setSuccess(true);
            status.setDescription(Constants.INFO_USER_CREATED);
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
            UpdateUserRequest updateUserRequest = parseJson(req, UpdateUserRequest.class);
            User user = bankService.findUser(updateUserRequest.getUserId());
            if (user != null) {
                bankService.updateUser(user, updateUserRequest.getName());
                status.setSuccess(true);
                status.setDescription(Constants.INFO_USER_UPDATED);
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
            DeleteUserRequest deleteUserRequest = parseJson(req, DeleteUserRequest.class);
            User user = bankService.findUser(deleteUserRequest.getUserId());
            if (user != null) {
                bankService.deleteUser(user);
                status.setSuccess(true);
                status.setDescription(Constants.INFO_USER_DELETED);
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