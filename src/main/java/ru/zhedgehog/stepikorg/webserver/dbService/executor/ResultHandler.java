package ru.zhedgehog.stepikorg.webserver.dbService.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Папа on 02.11.2016.
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
