package com.hillel.task_5.servlet;

import com.google.gson.Gson;
import com.hillel.task_5.model.Client;
import com.hillel.task_5.model.ClientRepository;
import com.hillel.task_5.model.request_model.ClientGetRequestModel;
import com.hillel.task_5.model.request_model.ClientPostRequestModel;
import com.hillel.task_5.model.request_model.ClientPutRequestModel;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClientServlet", urlPatterns = {"/client"})
@Slf4j
public class ClientServlet extends HttpServlet {
    private ClientRepository clientRepository;
    private int currentId = 1;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        clientRepository = ClientRepository.getInstance();

        try {
            ClientGetRequestModel requestModel = gson.fromJson(req.getReader(), ClientGetRequestModel.class);

            if (requestModel.getClientId() != null && !requestModel.getClientId().equals("")) {
                Integer id = Integer.decode(requestModel.getClientId());

                if (id <= clientRepository.getClients().size()) {
                    Client client = clientRepository.getClients().get(id);

                    if (client != null) {
                        resp.getWriter().println(gson.toJson(client));
                    } else {
                        resp.getWriter().println("Client not found.");
                        resp.setStatus(404);
                    }
                } else {
                    resp.getWriter().println("The client's id is not valid");
                    resp.setStatus(400);
                }
            } else {
                resp.getWriter().println("Please, enter client's name");
                resp.setStatus(400);
            }
        } catch (Exception e) {
            log.error("Exception while trying creating a client");
            resp.getWriter().println("Something went wrong. Please, try again.");
            resp.setStatus(400);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        clientRepository = ClientRepository.getInstance();

        try {
            ClientPostRequestModel requestModel = gson.fromJson(req.getReader(), ClientPostRequestModel.class);

            if (requestModel.getClientName() != null && !requestModel.getClientName().equals("")) {
                Integer clientId = currentId;
                Client client = new Client(clientId, requestModel.getClientName());
                clientRepository.getClients().put(clientId, client);
                currentId++;

                resp.getWriter().println("CLient was added \n" + gson.toJson(clientRepository.getClients().get(clientId)));
            } else {
                resp.getWriter().println("Please, enter client's name");
                resp.setStatus(400);
            }
        } catch (Exception e) {
            log.error("Exception while trying creating a client");
            resp.getWriter().println("Something went wrong. Please, try again.");
            resp.setStatus(400);
        }

    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        clientRepository = ClientRepository.getInstance();

        try {
            ClientPutRequestModel requestModel = gson.fromJson(req.getReader(), ClientPutRequestModel.class);

            if (requestModel.getClientId() != null && !requestModel.getClientId().equals("")
                    && requestModel.getClientName() != null && !requestModel.getClientName().equals("")) {
                Integer clientId = Integer.decode(requestModel.getClientId());

                if (clientId <= clientRepository.getClients().size()) {
                    Client client = clientRepository.getClients().get(clientId);
                    client.setName(requestModel.getClientName());

                    if (client != null) {
                        resp.getWriter().println("CLient was edited \n" + gson.toJson(clientRepository.getClients().get(clientId)));
                    } else {
                        resp.getWriter().println("Client not found.");
                        resp.setStatus(404);
                    }
                } else {
                    resp.getWriter().println("The client's id is not valid");
                    resp.setStatus(400);
                }
            } else {
                resp.getWriter().println("Please, enter client's name");
                resp.setStatus(400);
            }
        } catch (Exception e) {
            log.error("Exception while trying creating a client");
            resp.getWriter().println("Something went wrong. Please, try again.");
            resp.setStatus(400);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        clientRepository = ClientRepository.getInstance();

        try {
            ClientGetRequestModel requestModel = gson.fromJson(req.getReader(), ClientGetRequestModel.class);

            if (requestModel.getClientId() != null && !requestModel.getClientId().equals("")) {
                Integer id = Integer.decode(requestModel.getClientId());

                if (id <= clientRepository.getClients().size()) {
                    Client client = clientRepository.getClients().get(id);
                    if (client != null) {
                        clientRepository.getClients().remove(id);
                        resp.getWriter().println("Client was removed.");
                    } else {
                        resp.getWriter().println("Client not found.");
                        resp.setStatus(404);
                    }
                } else {
                    resp.getWriter().println("The client's id is not valid");
                    resp.setStatus(400);
                }
            } else {
                resp.getWriter().println("Please, enter client's name");
                resp.setStatus(400);
            }
        } catch (Exception e) {
            log.error("Exception while trying creating a client");
            resp.getWriter().println("Something went wrong. Please, try again.");
            resp.setStatus(400);
        }
    }
}
