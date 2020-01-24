package com.hillel.mvc.bank.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorControllerImp implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message = "";
        switch (status) {
            case 400: {
                message = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                message = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                message = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                message = "Http Error Code: 500. Internal Server Error";
                break;
            }
        }
        modelAndView.addObject("status", status);
        modelAndView.addObject("message", message);
        modelAndView.setViewName("error");
        return modelAndView;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
