package ru.zhedgehog.stepikorg.webserver.servlets;

import ru.zhedgehog.stepikorg.webserver.model.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Папа on 01.11.2016.
 */
public class SignUpRequest extends HttpServlet {
    private final AccountService accountService;

    public SignUpRequest(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.toString());
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        else if ( login != null && pass == null){ pass = login;}


        accountService.addNewUser(login,pass);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
