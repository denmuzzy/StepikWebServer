package ru.zhedgehog.stepikorg.webserver.model;

import ru.zhedgehog.stepikorg.webserver.dbService.dataSets.UsersDataSet;

/**
 * Created by Папа on 03.11.2016.
 */
public interface AccountService {
    boolean addNewUser(String name, String pass);
    UsersDataSet getUserByLogin(String login);
    UsersDataSet getUserBySessionId(String sessionId);
    void addSession(String sessionId, UsersDataSet userProfile);
    void deleteSession(String sessionId);
}
