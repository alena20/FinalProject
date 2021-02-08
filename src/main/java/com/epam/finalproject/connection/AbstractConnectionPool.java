package com.epam.finalproject.connection;

import java.sql.Connection;

public interface AbstractConnectionPool {

    Connection getConnection();

    void releaseConnection(Connection connection);

    void destroyPool();
}
