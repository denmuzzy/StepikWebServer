package ru.zhedgehog.stepikorg.webserver.chatService;

import ru.zhedgehog.stepikorg.webserver.chatService.webSockets.ChatWebSocket;

/**
 * Created by Папа on 03.11.2016.
 */
public interface ChatService {
    void sendMessage(String data);
    void add(ChatWebSocket webSocket);
    void remove(ChatWebSocket webSocket);
}
