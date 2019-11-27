package com.hillel.task_5.servlet;

import com.google.gson.Gson;
import com.hillel.task_5.model.Account;
import com.hillel.task_5.model.Client;
import com.hillel.task_5.model.ClientRepository;
import com.hillel.task_5.model.request_model.CreateAccountRequest;
import com.hillel.task_5.model.request_model.DeleteAccountRequestModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountServlet", urlPatterns = {"/client/account"})
public class AccountServlet extends HttpServlet {
    private ClientRepository clientRepository;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        clientRepository = ClientRepository.getInstance();

        try {
            Integer id = Integer.parseInt(req.getParameter("clientId"));

            if (id != null) {
                Client client = clientRepository.findClientById(id);

                if (client != null && client.getAccount() != null) {
                    Account account = client.getAccount();

                    resp.getWriter().println(gson.toJson(account));

                } else {
                    resp.getWriter().println("Account not found.");
                    resp.setStatus(404);

                }
            } else {
                resp.getWriter().println("Please, enter account's attributes");
                resp.setStatus(400);
            }

        } catch (Exception ex) {
            resp.getWriter().println("Something went wrong. Please, try again.");
            resp.setStatus(500);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        clientRepository = ClientRepository.getInstance();

        try {
            CreateAccountRequest reqeustModel = gson.fromJson(req.getReader(), CreateAccountRequest.class);

            if (reqeustModel.getBalance() != null && !reqeustModel.getBalance().equals("")
                    && reqeustModel.getCurrency() != null && !reqeustModel.getCurrency().equals("")
                    && reqeustModel.getClientId() != null && !reqeustModel.getClientId().equals("")) {
                Integer clientId = Integer.decode(reqeustModel.getClientId());
                Long balance = Long.decode(reqeustModel.getBalance());
                Account account = new Account(clientId, reqeustModel.getCurrency(), balance);
                Client client = clientRepository.findClientById(clientId);

                if (client != null
                        && (client.getAccount() == null || client.getAccount().getClientId() == null)) {
                    client.setAccount(account);

                    resp.getWriter().println("CLient's account was created \n"
                            + gson.toJson(client));

                } else {
                    resp.getWriter().println("Client not found or account is allready created.");
                    resp.setStatus(404);

                }
            } else {
                resp.getWriter().println("Please, enter account's attributes");
                resp.setStatus(400);
            }

        } catch (Exception ex) {
            resp.getWriter().println("Something went wrong. Please, try again.");
            resp.setStatus(500);
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        clientRepository = ClientRepository.getInstance();

        try {
            CreateAccountRequest reqeustModel = gson.fromJson(req.getReader(), CreateAccountRequest.class);

            if (reqeustModel.getBalance() != null && !reqeustModel.getBalance().equals("")
                    && reqeustModel.getCurrency() != null && !reqeustModel.getCurrency().equals("")
                    && reqeustModel.getClientId() != null && !reqeustModel.getClientId().equals("")) {
                Integer clientId = Integer.decode(reqeustModel.getClientId());
                Long balance = Long.decode(reqeustModel.getBalance());
                Account account = new Account(clientId, reqeustModel.getCurrency(), balance);
                Client client = clientRepository.findClientById(clientId);

                if (client != null && client.getAccount() != null) {
                    client.setAccount(account);

                    resp.getWriter().println("CLient's account was edited \n" + gson.toJson(client));

                } else {
                    resp.getWriter().println("Account not found.");
                    resp.setStatus(404);

                }
            } else {
                resp.getWriter().println("Please, enter account's attributes");
                resp.setStatus(400);
            }

        } catch (Exception ex) {
            resp.getWriter().println("Something went wrong. Please, try again.");
            resp.setStatus(500);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        clientRepository = ClientRepository.getInstance();

        try {
            DeleteAccountRequestModel reqeustModel = gson.fromJson(req.getReader(), DeleteAccountRequestModel.class);

            if (reqeustModel.getClientId() != null && !reqeustModel.getClientId().equals("")) {
                Integer clientId = Integer.decode(reqeustModel.getClientId());
                Client client = clientRepository.findClientById(clientId);

                if (client != null && client.getAccount() != null) {
                    clientRepository.addAccount(clientId, new Account());

                    resp.getWriter().println("Account was deleted \n" + gson.toJson(client));

                    } else {
                        resp.getWriter().println("Account not found.");
                        resp.setStatus(404);
                    }

            } else {
                resp.getWriter().println("Please, enter account's attributes");
                resp.setStatus(400);
            }

        } catch (Exception ex) {
            resp.getWriter().println("Something went wrong. Please, try again.");
            resp.setStatus(500);
        }
    }
}
