package ru.zhedgehog.stepikorg.webserver.servlets;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import ru.zhedgehog.stepikorg.webserver.chatService.ChatService;
import ru.zhedgehog.stepikorg.webserver.chatService.ChatServiceImpl;
import ru.zhedgehog.stepikorg.webserver.chatService.webSockets.ChatWebSocketImpl;

import javax.servlet.annotation.WebServlet;

/**
 * Created by Папа on 03.11.2016.
 */
@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    private final ChatService chatService;

    public WebSocketChatServlet() {
        this.chatService = new ChatServiceImpl();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new ChatWebSocketImpl(chatService));
    }
}
