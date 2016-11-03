package ru.zhedgehog.stepikorg.webserver.dbService;

import ru.zhedgehog.stepikorg.webserver.dbService.dataSets.UsersDataSet;

/**
 * Created by Папа on 03.11.2016.
 */
public interface DBService {
    UsersDataSet getUser(long id) throws DBException;
    UsersDataSet getUserByName(String name) throws DBException;
    long addUser(String name,String pass) throws DBException;
    void cleanUp() throws DBException;
    void printConnectInfo();
}
