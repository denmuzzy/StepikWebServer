package ru.zhedgehog.stepikorg.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.zhedgehog.stepikorg.webserver.dbService.DBService;
import ru.zhedgehog.stepikorg.webserver.dbService.DBServiceImpl;
import ru.zhedgehog.stepikorg.webserver.model.AccountService;
import ru.zhedgehog.stepikorg.webserver.model.AccountServiceImpl;
import ru.zhedgehog.stepikorg.webserver.servlets.SignInRequest;
import ru.zhedgehog.stepikorg.webserver.servlets.SignUpRequest;
import ru.zhedgehog.stepikorg.webserver.servlets.WebSocketChatServlet;

public class Main{
    public static void main(String[] args) throws Exception{
        DBService dbService = new DBServiceImpl();
        dbService.printConnectInfo();
        AccountService accountService = new AccountServiceImpl(dbService);

        SignInRequest sir = new SignInRequest(accountService);
        SignUpRequest sur = new SignUpRequest(accountService);
        WebSocketChatServlet chat = new WebSocketChatServlet();

        Server server = new Server(8080);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(sir),   "/signin");
        contextHandler.addServlet(new ServletHolder(sur),   "/signup");
        contextHandler.addServlet(new ServletHolder(chat),  "/chat");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.addHandler(resourceHandler);
        handlers.addHandler(contextHandler);
        //handlers.setHandlers(new Handler[]{resourceHandler, contextHandler});
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}