package com.hillel.servlets;

import com.google.gson.Gson;
import com.hillel.model.Status;
import com.hillel.model.User;
import com.hillel.request.CreateUserRequest;
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
@WebServlet(name = "userServlet", urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {
    private int currentUserId = 1;
    Map<Integer, User> userMap = new ConcurrentHashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application'json");
        Gson gson = new Gson();
        String userId = req.getParameter("userId");

        if (userId == null) {
            resp.getOutputStream().println(gson.toJson(userMap));
        } else {
            try {
                if (userMap.get(Integer.valueOf(userId)) != null) {
                    resp.getOutputStream().println(gson.toJson(userMap.get(Integer.valueOf(userId))));
                } else {
                    Status status = new Status(false, "User not found");
                    resp.getOutputStream().print(gson.toJson(status));
                    resp.setStatus(404);
                }
            } catch (Exception e) {
                log.error("Exception while tried to get user: ", e);
                Status status = new Status(false, "Bad request");
                resp.getOutputStream().print(gson.toJson(status));
                resp.setStatus(400);
            }
        }
        resp.getOutputStream().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        Status status;

        try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = req.getReader().readLine()) != null) {
                sb.append(s);
            }

            CreateUserRequest createUserRequest = gson.fromJson(sb.toString(), CreateUserRequest.class);

            User user = new User(currentUserId, createUserRequest.getFirstName(), createUserRequest.getLastName());
            userMap.put(user.getId(), user);
            currentUserId++;
            status = new Status(true, "User was added");
            resp.getOutputStream().print(gson.toJson(user));
        } catch (Exception e) {
            log.error("Exception while tried to add user: ", e);
            status = new Status(false, "Failed to add user");
            resp.setStatus(500);
        }
        resp.getOutputStream().print(gson.toJson(status));
        resp.getOutputStream().flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        Status status;

        try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = req.getReader().readLine()) != null) {
                sb.append(s);
            }

            User user = gson.fromJson(sb.toString(), User.class);
            userMap.put(user.getId(), user);
            currentUserId++;
            status = new Status(true, "User was updated");
            resp.getOutputStream().print(gson.toJson(user));
        } catch (Exception e) {
            log.error("Exception while tried to update user: ", e);
            status = new Status(false, "Failed to update user");
            resp.setStatus(500);
        }
        resp.getOutputStream().print(gson.toJson(status));
        resp.getOutputStream().flush();

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String userId = req.getParameter("userId");

        if (userId == null) {
            Status status = new Status(false, "Bad request");
            resp.getOutputStream().print(gson.toJson(status));
            resp.setStatus(400);
        } else {
            try {
                if (userMap.get(Integer.valueOf(userId)) != null) {
                    userMap.remove(Integer.valueOf(userId));
                    Status status = new Status(true, "User " + userId + " was deleted");
                    resp.getOutputStream().print(gson.toJson(status));
                } else {
                    Status status = new Status(false, "User not found");
                    resp.getOutputStream().print(gson.toJson(status));
                    resp.setStatus(404);
                }
            } catch (Exception e) {
                log.error("Exception while tried to get user: ", e);
                Status status = new Status(false, "Bad request");
                resp.getOutputStream().print(gson.toJson(status));
                resp.setStatus(400);
            }
        }

    }
}
