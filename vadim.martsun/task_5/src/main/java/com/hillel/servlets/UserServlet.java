package com.hillel.servlets;

import com.google.gson.Gson;
import com.hillel.mapper.Mapper;
import com.hillel.mapper.MapperImpl;
import com.hillel.model.BankAcc;
import com.hillel.model.Database;
import com.hillel.model.Status;
import com.hillel.model.User;
import com.hillel.model.requests.UserRequest;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@WebServlet(name = "UserServlet", urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {
    private Database database = Database.getInstance();
    private long userId = 0;
    private Gson gson = new Gson();
    private Mapper mapper = new MapperImpl();
    private final String GET_PARAM = "userId";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String getParameter = req.getParameter(GET_PARAM);
        Status status;
        if(getParameter == null){
            status = new Status(true, "Found " + database.getUsers().size() + " users");
            resp.getWriter().println(gson.toJson(database.getUsers()));
            log.info(database.getUsers().toString());
        }else{
            User extractedUser = database.getUsers().get(Long.parseLong(getParameter));
            if(extractedUser == null){
                status = new Status(false, "User not found");
                resp.setStatus(404);
            }else {
                status = new Status(true, "The required user has been found");
                resp.setStatus(200);
                resp.getWriter().println(gson.toJson(extractedUser));
            }
        }
        resp.getWriter().println(gson.toJson(status));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        UserRequest userRequest = gson.fromJson(req.getReader(), UserRequest.class);
        log.info(userRequest.toString());
        User user = mapper.mapUser(userRequest);
        database.getUsers().put(userId, user);
        userId++;

        Status status = new Status(true, "A user has been added");
        resp.setStatus(200);
        resp.getWriter().println(gson.toJson(status));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        Status status;
        String getParameter = req.getParameter(GET_PARAM);
        if (getParameter == null) {
            status = new Status(false, "Bad request");
            resp.setStatus(400);
        } else {
            UserRequest userRequest = gson.fromJson(req.getReader(), UserRequest.class);
            long userId = Long.parseLong(getParameter);
            User extractedUser = database.getUsers().get(userId);

            if (extractedUser == null) {
                status = new Status(false, "User not found");
                resp.setStatus(404);
            } else {
                User updatedUser = mapper.mapUser(userRequest);
                database.getUsers().put(userId, updatedUser);

                for(Map.Entry<Long,BankAcc> entry : database.getAccounts().entrySet()){
                    BankAcc account = entry.getValue();
                    if(account.getUser().equals(extractedUser)){
                        account.setUser(updatedUser);
                        database.getAccounts().put(entry.getKey(), account);
                    }
                }
                status = new Status(true, "User has been successfully updated");
                resp.setStatus(200);
            }
        }
        resp.getWriter().println(gson.toJson(status));
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Status status;
        String getParameter = req.getParameter(GET_PARAM);
        if (getParameter != null) {
            User removedUser = database.getUsers().remove(Long.parseLong(getParameter));
            if (removedUser == null) {
                status = new Status(true, "User not found");
                resp.setStatus(404);
            } else {
                for(Map.Entry<Long, BankAcc> entry : database.getAccounts().entrySet()){
                    BankAcc account = entry.getValue();
                    if(account.getUser().equals(removedUser)){
                        database.getAccounts().remove(entry.getKey());
                    }
                }
                status = new Status(true, "The user has been deleted");
                resp.setStatus(200);
            }
        } else {
            status = new Status(false, GET_PARAM + " parameter required");
            resp.setStatus(400);
        }
        resp.getWriter().println(gson.toJson(status));
    }
}
