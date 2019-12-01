package com.hillel;

import com.google.gson.Gson;
import com.hillel.model.CrudEventStatus;
import com.hillel.model.User;
import com.hillel.model.request.CreateUserRequest;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@MultipartConfig
@WebServlet(name = "UserServlet", urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {
    private int currentUserId = 1;
    private Map<Integer, User> users = new ConcurrentHashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        CrudEventStatus status;
        String message;
        try{
            CreateUserRequest createUserRequest = gson.fromJson(req.getReader(), CreateUserRequest.class);
            User user = new User(createUserRequest.getName(), createUserRequest.getPhoneNumber(),
                                    createUserRequest.getAge(), currentUserId);
            users.put(currentUserId, user);
            currentUserId++;
            message = String.format("User %s was successfully added", createUserRequest.getName());
            status = new CrudEventStatus(true, message);
            log.info(message);
        }catch (Exception e) {
            message = "Exception while adding a user";
            status= new CrudEventStatus(false, message);
            log.error(message);
            resp.setStatus(500);
        }
        resp.getWriter().println(status.getDescription());
        resp.getWriter().flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        CrudEventStatus status;
        String message;
        try{
            CreateUserRequest createUserRequest = gson.fromJson(req.getReader(), CreateUserRequest.class);
            int id = createUserRequest.getId();
            if (users.containsKey(id)) {
                User user = new User(createUserRequest.getName(), createUserRequest.getPhoneNumber(),
                        createUserRequest.getAge(), id);
                users.put(id, user);
                message = String.format("User id = %d info was updated", id);
                status = new CrudEventStatus(true, message);
                log.info(message);
            }else {
                message = String.format("There is no user for id = %d", id);
                status = new CrudEventStatus(false, message);
                log.warn(message);
                resp.setStatus(404);
            }
        }catch (Exception e) {
            message = "Error, while trying to get user info";
            status = new CrudEventStatus(false, message);
            resp.setStatus(500);
        }
        resp.getWriter().println(status.getDescription());
        resp.getWriter().flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String message;
        CrudEventStatus status;
        try{
            if(req.getParameter("id") != null) {
                Integer id = Integer.parseInt(req.getParameter("id"));
                users.remove(id);
                message = String.format("User id = %d was successfully removed", id);
                status = new CrudEventStatus(true, message);
                log.info(message);
            } else {
                message = "There is no user for given id";
                status = new CrudEventStatus(false, message);
                log.info(message);
                resp.setStatus(400);
            }
        }catch (Exception e) {
            message = "Error, while trying to delete a user";
            status = new CrudEventStatus(false, message);
            resp.setStatus(500);
        }
        resp.getWriter().println(status.getDescription());
        resp.getWriter().flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String message;
        CrudEventStatus status;
        if (req.getParameter("id") != null) {
            try {
                int userId = Integer.parseInt(req.getParameter("id"));
                if (users.containsKey(userId)) {
                    message = gson.toJson(users.get(userId));
                    status = new CrudEventStatus(true, message);
                }else {
                    message = "There is no user with such id";
                    status = new CrudEventStatus(false, message);
                    resp.setStatus(404);
                }
            }catch (Exception e) {
                message = "Error while trying to get user info";
                status = new CrudEventStatus(false, message);
                resp.setStatus(500);
            }
        }else {
            message = "Bad request";
            status = new CrudEventStatus(false, message);
            resp.setStatus(500);
        }
        resp.getWriter().println(status.getDescription());
        resp.getWriter().flush();
    }
}
