package com.epam.finalproject.connection.impl;

import com.epam.finalproject.connection.AbstractConnectionPool;
import com.epam.finalproject.connection.PropertyName;
import com.epam.finalproject.exception.PropertyReaderException;
import com.epam.finalproject.util.PropertiesPath;
import com.epam.finalproject.util.PropertiesReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool implements AbstractConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private static final int POOL_SIZE = 10;
    private final BlockingQueue<ProxyConnection> freeConnections;
    private final BlockingQueue<ProxyConnection> usedConnections;

    private ConnectionPool() {
        String propertiesPath = PropertiesPath.DB_PROPERTIES;
        Properties properties;
        try {
            properties = PropertiesReader.readProperties(propertiesPath);
        } catch (PropertyReaderException e) {
            throw new RuntimeException("Unable to read DB properties!", e);
        }
        String url = properties.getProperty(PropertyName.DB_URL);
        String driverName = properties.getProperty(PropertyName.DRIVER_NAME);
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to register DB driver!", e);
        }
        freeConnections = new LinkedBlockingQueue<ProxyConnection>(POOL_SIZE);
        usedConnections = new LinkedBlockingQueue<ProxyConnection>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, properties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            } catch (SQLException e) {
                LOGGER.error("Unable to create connection!", e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Unable to provide connection", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    @Override
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            if (usedConnections.remove(connection)) {
                freeConnections.offer((ProxyConnection) connection);
            }
        } else {
            LOGGER.error("Releasing connection is not proxy!");
        }
    }

    @Override
    public void destroyPool() {
        for (int i = 0; i < freeConnections.size(); i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                LOGGER.error("Unable to close connection!", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
    }

    //TODO catch SQLExceptoin
    private void deregisterDrivers() {
        Enumeration<Driver> driverEnumeration = DriverManager.getDrivers();
        while (driverEnumeration.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(driverEnumeration.nextElement());
            } catch (SQLException e) {
                LOGGER.error("Unable to deregister driver!", e);
            }
        }
    }
}
