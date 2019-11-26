package com.hillel.servlet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseHttpServlet extends HttpServlet {

    private Gson gson = new Gson();

    public void printGson(HttpServletResponse resp, Object o) throws IOException {
        resp.getWriter().print(gson.toJson(o));
    }

    public <T> T parseJson(HttpServletRequest req, Class<T> clazz) throws IOException{
        return gson.fromJson(req.getReader(), clazz);
    }

    public void error(HttpServletResponse resp, int code, String text) throws IOException{
        resp.getWriter().println(text);
        resp.setStatus(code);
    }

    public void error404(HttpServletResponse resp, String text) throws IOException{
        error(resp, 404, text);
    }
}
