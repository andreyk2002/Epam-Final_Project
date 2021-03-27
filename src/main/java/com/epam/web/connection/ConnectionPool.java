package com.epam.web.connection;

import com.epam.web.command.CommandFactory;
import com.epam.web.dao.DaoException;
import com.mysql.cj.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final int CONNECTIONS_ALLOWED = 10;

    private final Semaphore connectionSemaphore = new Semaphore(CONNECTIONS_ALLOWED);
    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;

    private final static AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private final static Lock LOCK = new ReentrantLock();


    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = INSTANCE.get();
        if (localInstance == null) {
            try {
                LOCK.lock();
                localInstance = INSTANCE.get();
                if (localInstance == null) {
                    DriverManager.registerDriver(new Driver());
                    ConnectionPool pool = new ConnectionPool();
                    INSTANCE.set(pool);
                    addConnections();
                }
            } catch (SQLException | DaoException e) {
                throw new RuntimeException(e.getMessage(), e);
            } finally {
                LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    private static void addConnections() throws DaoException {
        ConnectionPool pool = INSTANCE.get();
        for (int i = 0; i < CONNECTIONS_ALLOWED; i++) {
            ProxyConnection connection = ConnectionFactory.create();
            pool.availableConnections.add(connection);
        }
    }

    private ConnectionPool() throws DaoException {
        availableConnections = new ArrayDeque<>();
        connectionsInUse = new ArrayDeque<>();
//        ConnectionPoolFactory factory = new ConnectionPoolFactory();
//        factory.create();
//
//        try {
//            init();
//
//        }
    }

    public void returnConnection(ProxyConnection connection) {
        try {
            LOCK.lock();
            if (connectionsInUse.contains(connection)) {
                availableConnections.offer(connection);
                connectionsInUse.remove(connection);
            }
        } finally {
            LOCK.unlock();
        }
    }

    public ProxyConnection getConnection() throws DaoException {

        try {
            connectionSemaphore.acquire();
            LOCK.lock();

            ProxyConnection connection = availableConnections.poll();
            connectionsInUse.add(connection);

            return connection;
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            LOCK.unlock();
        }
    }
}
