package ru.zhedgehog.stepikorg.webserver.servlets;

import ru.zhedgehog.stepikorg.webserver.dbService.dataSets.UsersDataSet;
import ru.zhedgehog.stepikorg.webserver.model.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Папа on 01.11.2016.
 */
public class SignInRequest extends HttpServlet {
    private final AccountService accountService;

    public SignInRequest(AccountService accountService){ this.accountService = accountService; }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        UsersDataSet signInUser;

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        signInUser = accountService.getUserByLogin(login);

        if (signInUser != null && pass.equals(signInUser.getPass())){
            System.out.println("Session=" + request.getSession().toString());
            accountService.addSession(request.getSession().toString(),signInUser);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Authorized: " + signInUser.getName());
            return;
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().println("Unauthorized");

    }
}
