package com.hillel.task_9.controller;

import com.hillel.task_9.model.ClientEntity;
import com.hillel.task_9.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;


@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;


    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ModelAndView getClients() {
        List<ClientEntity> clientsList = clientService.getClientsList();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clients", clientsList);
        modelAndView.setViewName("clientsList");

        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String addClient(Model model) {
        model.addAttribute("clientAttribute", new ClientEntity());
        return "addClient";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/addClient")
    public String saveClient(@ModelAttribute("clientAttribute") @Validated ClientEntity clientEntity) {
        clientService.saveClient(clientEntity);

        return "redirect:list";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/deleteClient")
    public String deleteClient(@PathParam("id") int id) {
        clientService.removeClient(id);

        return "redirect:list";
    }


}
