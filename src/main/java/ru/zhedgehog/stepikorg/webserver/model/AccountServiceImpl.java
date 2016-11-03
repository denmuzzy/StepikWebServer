package ru.zhedgehog.stepikorg.webserver.model;

/**
 * Created by Папа on 01.11.2016.
 */

import ru.zhedgehog.stepikorg.webserver.dbService.DBException;
import ru.zhedgehog.stepikorg.webserver.dbService.DBService;
import ru.zhedgehog.stepikorg.webserver.dbService.dataSets.UsersDataSet;

import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService{
    private final Map<String, UsersDataSet> sessionIdToProfile;
    private DBService dbService;

    public AccountServiceImpl(DBService dbService) {
        this.dbService = dbService;
        sessionIdToProfile = new HashMap<>();
    }

    public boolean addNewUser(String name, String pass) {
        try {
            dbService.addUser(name,pass);
        } catch (DBException e) {
            e.printStackTrace();
        }
        return true;
    }

    public UsersDataSet getUserByLogin(String login) {
        try {
            return dbService.getUserByName(login);
        } catch (DBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UsersDataSet getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UsersDataSet userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
