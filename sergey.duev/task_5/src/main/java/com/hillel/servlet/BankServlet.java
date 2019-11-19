package com.hillel.servlet;

import com.google.gson.Gson;
import com.hillel.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "Bank", urlPatterns = {BankServlet.PATH_BANK_ACCOUNT, BankServlet.PATH_USER})
public class BankServlet extends HttpServlet {

    private static final String USER_ID = "userId";
    private static final String BANK_ACCOUNT_ID = "bankAccountId";

    private static final String ERROR_USER_NOT_FOUND = "User not found";
    private static final String ERROR_BANK_ACCOUNT_NOT_FOUND = "Bank account not found";

    private static final String INFO_USER_CREATED = "User created";
    private static final String INFO_USER_DELETED = "User deleted";
    private static final String INFO_USER_UPDATED = "User updated";
    private static final String INFO_BANK_ACCOUNT_CREATED = "Bank account created";
    private static final String INFO_BANK_ACCOUNT_DELETED = "Bank account deleted";
    private static final String INFO_BANK_ACCOUNT_UPDATED = "Bank account updated";

    public static final String PATH_USER = "/bank/user";
    public static final String PATH_BANK_ACCOUNT = "/bank/bankAccount";
    private long nextUserId = 1;
    private long nextBankAccountId = 1;
    private Gson gson = new Gson();

    private Map<User, List<BankAccount>> bankAccounts = new ConcurrentHashMap<>();

    private boolean checkPath(HttpServletRequest req, String path) {
        return req.getRequestURI().endsWith(path);
    }

    private boolean checkPathUser(HttpServletRequest req) {
        return checkPath(req, PATH_USER);
    }

    private boolean checkPathBankAccount(HttpServletRequest req) {
        return checkPath(req, PATH_BANK_ACCOUNT);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGetUser(req, resp);
        doGetBankAccount(req, resp);
    }

    private void doGetUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!checkPathUser(req)) {
            return;
        }
        resp.setContentType("application/json");
        Gson gson = new Gson();
        if(req.getParameter(USER_ID) != null) {
            Long userId = Long.parseLong(req.getParameter(USER_ID));
            User user = findUser(userId);
            if(user != null) {
                printGson(resp, gson.toJson(user));
            } else {
                error404(resp, ERROR_USER_NOT_FOUND);
            }
        } else {
            printGson(resp, bankAccounts.keySet());
        }
    }

    private void doGetBankAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        if (!checkPathBankAccount(req)) {
            return;
        }
        resp.setContentType("application/json");
        if (req.getParameter(USER_ID) != null) {
            Long userId = Long.parseLong(req.getParameter(USER_ID));
            User user = findUser(userId);
            if (user != null) {
                if(req.getParameter(BANK_ACCOUNT_ID) != null) {
                    Long bankAccountId = Long.parseLong(req.getParameter(BANK_ACCOUNT_ID));
                    BankAccount bankAccount = findBankAccount(userId, bankAccountId);
                    if(bankAccount != null) {
                        printGson(resp, bankAccount);
                    } else {
                        error404(resp, ERROR_BANK_ACCOUNT_NOT_FOUND);
                    }
                } else {
                    printGson(resp, bankAccounts.get(user));
                }
            } else {
                error404(resp, ERROR_USER_NOT_FOUND);
            }
        } else {
            printGson(resp, bankAccounts);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPostUser(req, resp);
        doPostBankAccount(req, resp);
    }

    private void doPostUser(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        if (!checkPathUser(req)) {
            return;
        }
        resp.setContentType("application/json");
        Status status = new Status();
        try {
            CreateUserRequest createUserRequest = gson.fromJson(req.getReader(), CreateUserRequest.class);
            createNewUser(createUserRequest.getName());
            status.setSuccess(true);
            status.setDescription(INFO_USER_CREATED);
        } catch (Exception ex) {
            status.setSuccess(false);
            status.setDescription(ex.getMessage());
        }
        printGson(resp, status);
    }

    private void createNewUser(String name) {
        User user = new User(nextUserId, name);
        bankAccounts.put(user, new ArrayList<>());
        nextUserId++;
    }

    private void doPostBankAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!checkPathBankAccount(req)) {
            return;
        }
        resp.setContentType("application/json");
        Status status = new Status();
        try {
            CreateBankAccountRequest createBankAccountRequest = gson.fromJson(req.getReader(), CreateBankAccountRequest.class);
            User user = findUser(createBankAccountRequest.getUserId());
            if (user != null) {
                createNewBankAccount(createBankAccountRequest.getUserId(), createBankAccountRequest.getAmount());
                status.setSuccess(true);
                status.setDescription(INFO_BANK_ACCOUNT_CREATED);
            } else {
                error404(resp, ERROR_USER_NOT_FOUND);
            }
        } catch (Exception ex) {
            status.setSuccess(false);
            status.setDescription(ex.getMessage());
        }
        printGson(resp, status);
    }

    private void createNewBankAccount(long userId, double amount) {
        User user = findUser(userId);
        if (user != null) {
            bankAccounts.get(user).add(new BankAccount(nextBankAccountId, amount));
            nextBankAccountId++;
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPutUser(req, resp);
        doPutBankAccount(req, resp);
    }

    private void doPutUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!checkPathUser(req)) {
            return;
        }
        resp.setContentType("application/json");
        Status status = new Status();
        try {
            UpdateUserRequest updateUserRequest = gson.fromJson(req.getReader(), UpdateUserRequest.class);
            User user = findUser(updateUserRequest.getUserId());
            if (user != null) {
                updateUser(user, updateUserRequest.getName());
                status.setSuccess(true);
                status.setDescription(INFO_USER_UPDATED);
            } else {
                error404(resp, ERROR_USER_NOT_FOUND);
            }
        } catch (Exception ex) {
            status.setSuccess(false);
            status.setDescription(ex.getMessage());
        }
        printGson(resp, status);
    }

    private void doPutBankAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!checkPathBankAccount(req)) {
            return;
        }
        resp.setContentType("application/json");
        Status status = new Status();
        try {
            UpdateBankAccountRequest updateBankAccountRequest = gson.fromJson(req.getReader(), UpdateBankAccountRequest.class);
            User user = findUser(updateBankAccountRequest.getUserId());
            if (user != null) {
                BankAccount bankAccount = findBankAccount(updateBankAccountRequest.getUserId(), updateBankAccountRequest.getBankAccountId());
                if (bankAccount != null) {
                    updateBankAccount(user, bankAccount, updateBankAccountRequest.getAmount());
                    status.setSuccess(true);
                    status.setDescription(INFO_BANK_ACCOUNT_UPDATED);
                } else {
                    error404(resp, ERROR_BANK_ACCOUNT_NOT_FOUND);
                }
            } else {
                error404(resp, ERROR_USER_NOT_FOUND);
            }
        } catch (Exception ex) {
            status.setSuccess(false);
            status.setDescription(ex.getMessage());
        }
        printGson(resp, status);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDeleteUser(req, resp);
        doDeleteBankAccount(req, resp);
    }

    private void doDeleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!checkPathUser(req)) {
           return;
        }
        resp.setContentType("application/json");
        Status status = new Status();
        try {
            DeleteUserRequest deleteUserRequest = gson.fromJson(req.getReader(), DeleteUserRequest.class);
            User user = findUser(deleteUserRequest.getUserId());
            if (user != null) {
                deleteUser(user);
                status.setSuccess(true);
                status.setDescription(INFO_USER_DELETED);
            } else {
                error404(resp, ERROR_USER_NOT_FOUND);
            }
        } catch (Exception ex) {
            status.setSuccess(false);
            status.setDescription(ex.getMessage());
        }
        printGson(resp, status);
    }

    private void doDeleteBankAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!checkPathBankAccount(req)) {
            return;
        }
        resp.setContentType("application/json");
        Status status = new Status();
        try {
            DeleteBankAccountRequest deleteBankAccountRequest = gson.fromJson(req.getReader(), DeleteBankAccountRequest.class);
            User user = findUser(deleteBankAccountRequest.getUserId());
            if (user != null) {
                BankAccount bankAccount = findBankAccount(deleteBankAccountRequest.getUserId(), deleteBankAccountRequest.getBankAccountId());
                if (bankAccount != null) {
                    deleteBankAccount(user, bankAccount);
                    status.setSuccess(true);
                    status.setDescription(INFO_BANK_ACCOUNT_DELETED);
                } else {
                    error404(resp, ERROR_BANK_ACCOUNT_NOT_FOUND);
                }
            } else {
                error404(resp, ERROR_USER_NOT_FOUND);
            }
        } catch (Exception ex) {
            status.setSuccess(false);
            status.setDescription(ex.getMessage());
        }
        printGson(resp, status);
    }

    private void deleteUser(User user) {
        bankAccounts.remove(user);
    }

    private void deleteBankAccount(User user, BankAccount bankAccount) {
        bankAccounts.get(user).remove(bankAccount);
    }

    private void updateUser(User user, String name) {
        bankAccounts.keySet().forEach(user1 -> {
            if (user1.equals(user)) {
                user1.setName(name);
                return;
            }
        });
    }

    private void updateBankAccount(User user, BankAccount bankAccount, double amount) {
        bankAccounts.get(user).get(bankAccounts.get(user).indexOf(bankAccount)).setAmount(amount);
    }

    private User findUser(long id) {
        for (User user : bankAccounts.keySet()) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    private BankAccount findBankAccount(long userId, long bankAccountId) {
        for (User user : bankAccounts.keySet()) {
            if (user.getId() == userId) {
                for (BankAccount bankAccount : bankAccounts.get(user)) {
                    if (bankAccount.getId() == bankAccountId) {
                        return bankAccount;
                    }
                }
            }
        }
        return null;
    }

    private void printGson(HttpServletResponse resp, Object o) throws IOException {
        resp.getWriter().print(gson.toJson(o));
    }

    private void error(HttpServletResponse resp, int code, String text) throws IOException{
        resp.getWriter().println(text);
        resp.setStatus(code);
    }

    private void error404(HttpServletResponse resp, String text) throws IOException{
        error(resp, 404, text);
    }
}