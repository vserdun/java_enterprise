package com.hillel;

import com.google.gson.Gson;
import com.hillel.model.BankAccount;
import com.hillel.model.ReqStatus;
import com.hillel.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BankServlet", urlPatterns = {"/accounts"})
public class BankApp extends HttpServlet {
    private List<BankAccount> bankAccounts = fillAccountList();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String parameter = req.getParameter("acc_id");

        if (parameter != null) {
            int id = Integer.parseInt(parameter);
            for (BankAccount b : bankAccounts) {
                if (b.getId() == id) {
                    resp.getWriter().println(gson.toJson(b));
                    return;
                }
            }
            resp.setStatus(404);
            resp.getOutputStream().print(gson.toJson(new ReqStatus(false, "Account not found")));
            resp.getOutputStream().flush();
        } else
            resp.getWriter().println(gson.toJson(bankAccounts));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        ReqStatus status;

        String parId = req.getParameter("acc_id");
        String parAmount = req.getParameter("new_amount");
        if ((parId != null) && (parAmount != null)) {
            int id = Integer.parseInt(parId);
            long newAmount = Long.parseLong(parAmount);
            for (BankAccount b : bankAccounts) {
                if (b.getId() == id) {
                    b.setAmount(newAmount);
                    status = new ReqStatus(true, "Set new amount successful");
                    resp.getOutputStream().print(gson.toJson(status));
                    resp.getOutputStream().flush();
                    return;
                }
            }
            resp.setStatus(404);
            resp.getOutputStream().print(gson.toJson(new ReqStatus(false, "No book with this id")));
            resp.getOutputStream().flush();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        ReqStatus status;

        try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = req.getReader().readLine()) != null)
                sb.append(s);

            BankAccount bankAccount = gson.fromJson(sb.toString(), BankAccount.class);
            for (BankAccount b : bankAccounts) {
                if (b.getId() == bankAccount.getId()) {
                    status = new ReqStatus(false, "There is already book with this id");
                    resp.getOutputStream().print(gson.toJson(status));
                    resp.getOutputStream().flush();
                    return;
                }
            }
            bankAccounts.add(bankAccount);
            status = new ReqStatus(true, "Bank account added");
        } catch (Exception e) {
            status = new ReqStatus(false, "Failed to add account");
            resp.setStatus(500);
        }
        resp.getOutputStream().print(gson.toJson(status));
        resp.getOutputStream().flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("acc_id");
        ReqStatus status;
        Gson gson = new Gson();

        if (parameter != null) {
            int id = Integer.parseInt(parameter);
            if ((bankAccounts.size() > id) && (bankAccounts.get(id) != null)) {
                for (BankAccount b : bankAccounts) {
                    if (b.getId() == id) {
                        bankAccounts.remove(b);
                        status = new ReqStatus(true, "Account deleted");
                        resp.getOutputStream().print(gson.toJson(status));
                        resp.getOutputStream().flush();
                        return;
                    }
                }
            }
            status = new ReqStatus(false, "Account not found");
            resp.getOutputStream().print(gson.toJson(status));
            resp.getOutputStream().flush();
        }
    }

    private static List<BankAccount> fillAccountList() {
        List<BankAccount> accountList = new ArrayList<>();
        accountList.add(new BankAccount(0, new User("Oleg", "Smirnov", 30), 2300, LocalDate.now()));
        accountList.add(new BankAccount(1, new User("Denis", "Virnov", 26), 3000, LocalDate.now()));
        accountList.add(new BankAccount(2, new User("Valeriy", "Terin", 40), 12300, LocalDate.now()));
        accountList.add(new BankAccount(3, new User("Nover", "Jasem", 19), 2200, LocalDate.now()));
        accountList.add(new BankAccount(4, new User("Karry", "Stan", 27), 32200, LocalDate.now()));
        return accountList;
    }
}
