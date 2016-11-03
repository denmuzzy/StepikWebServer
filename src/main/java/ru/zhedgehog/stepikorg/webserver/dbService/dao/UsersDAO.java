package ru.zhedgehog.stepikorg.webserver.dbService.dao;

import ru.zhedgehog.stepikorg.webserver.dbService.dataSets.UsersDataSet;
import ru.zhedgehog.stepikorg.webserver.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Папа on 02.11.2016.
 */
public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UsersDataSet get(long id) throws SQLException {
        return executor.execQuery("select * from users where id=" + id, result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2), result.getString(3));
        });
    }

    public long getUserId(String name) throws SQLException {
        return executor.execQuery("select * from users where user_name='" + name + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public UsersDataSet getByUserName(String name) throws SQLException {
        return executor.execQuery("select * from users where user_name='" + name + "'", result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2), result.getString(3));
        });
    }

    public void insertUser(String name, String pass) throws SQLException {
        executor.execUpdate("insert into users (user_name, user_pass) values ('" + name + "', '" + pass + "')");
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, user_name varchar(256), user_pass varchar(256), primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
}