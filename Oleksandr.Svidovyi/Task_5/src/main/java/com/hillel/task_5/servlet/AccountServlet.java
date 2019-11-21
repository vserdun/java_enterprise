package com.hillel.task_5.servlet;

import com.google.gson.Gson;
import com.hillel.task_5.model.Account;
import com.hillel.task_5.model.ClientRepository;
import com.hillel.task_5.model.request_model.AccountGetRequestModel;
import com.hillel.task_5.model.request_model.AccountPostRequestModel;

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
            AccountGetRequestModel reqeustModel = gson.fromJson(req.getReader(), AccountGetRequestModel.class);

            if (reqeustModel.getClientId() != null && !reqeustModel.getClientId().equals("")) {
                Integer clientId = Integer.decode(reqeustModel.getClientId());

                if (clientRepository.getClients().get(clientId) != null
                        && clientRepository.getClients().get(clientId).getAccount() != null) {
                    Account account = clientRepository.getClients().get(clientId).getAccount();

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
            resp.setStatus(400);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        clientRepository = ClientRepository.getInstance();

        try {
            AccountPostRequestModel reqeustModel = gson.fromJson(req.getReader(), AccountPostRequestModel.class);

            if (reqeustModel.getBalance() != null && !reqeustModel.getBalance().equals("")
                    && reqeustModel.getCurrency() != null && !reqeustModel.getCurrency().equals("")
                    && reqeustModel.getClientId() != null && !reqeustModel.getClientId().equals("")) {
                Integer clientId = Integer.decode(reqeustModel.getClientId());
                Long balance = Long.decode(reqeustModel.getBalance());
                Account account = new Account(clientId, reqeustModel.getCurrency(), balance);

                if (clientRepository.getClients().get(clientId) != null
                        && (clientRepository.getClients().get(clientId).getAccount() == null
                        || clientRepository.getClients().get(clientId).getAccount().getClientId() == null)) {
                    clientRepository.getClients().get(clientId).setAccount(account);

                    resp.getWriter().println("CLient's account was created \n"
                            + gson.toJson(clientRepository.getClients().get(clientId)));

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
            resp.setStatus(400);
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        clientRepository = ClientRepository.getInstance();

        try {
            AccountPostRequestModel reqeustModel = gson.fromJson(req.getReader(), AccountPostRequestModel.class);

            if (reqeustModel.getBalance() != null && !reqeustModel.getBalance().equals("")
                    && reqeustModel.getCurrency() != null && !reqeustModel.getCurrency().equals("")
                    && reqeustModel.getClientId() != null && !reqeustModel.getClientId().equals("")) {
                Integer clientId = Integer.decode(reqeustModel.getClientId());
                Long balance = Long.decode(reqeustModel.getBalance());
                Account account = new Account(clientId, reqeustModel.getCurrency(), balance);

                if (clientRepository.getClients().get(clientId) != null
                        && clientRepository.getClients().get(clientId).getAccount() != null) {
                    clientRepository.getClients().get(clientId).setAccount(account);

                    resp.getWriter().println("CLient's account was edited \n"
                            + gson.toJson(clientRepository.getClients().get(clientId)));

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
            resp.setStatus(400);
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        clientRepository = ClientRepository.getInstance();

        try {
            AccountGetRequestModel reqeustModel = gson.fromJson(req.getReader(), AccountGetRequestModel.class);

            if (reqeustModel.getClientId() != null && !reqeustModel.getClientId().equals("")) {
                Integer clientId = Integer.decode(reqeustModel.getClientId());

                if (clientId <= clientRepository.getClients().size()) {

                    if (clientRepository.getClients().get(clientId) != null
                            && clientRepository.getClients().get(clientId).getAccount() != null) {
                        clientRepository.getClients().get(clientId).setAccount(new Account());

                        resp.getWriter().println("Account was deleted \n" + gson.toJson(clientRepository.getClients().get(clientId)));

                    } else {
                        resp.getWriter().println("Account not found.");
                        resp.setStatus(404);
                    }

                } else {
                    resp.getWriter().println("The client's id is not valid");
                    resp.setStatus(400);
                }

            } else {
                resp.getWriter().println("Please, enter account's attributes");
                resp.setStatus(400);
            }

        } catch (Exception ex) {
            resp.getWriter().println("Something went wrong. Please, try again.");
            resp.setStatus(400);
        }
    }
}
