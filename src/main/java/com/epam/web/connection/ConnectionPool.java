package com.epam.web.connection;

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
    private final ConnectionFactory connectionFactory;

    private final static AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private final static Lock LOCK = new ReentrantLock();


    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = INSTANCE.get();
        if (localInstance == null) {
            try {
                LOCK.lock();
                localInstance = INSTANCE.get();
                if (localInstance == null) {
                    ConnectionPool pool = new ConnectionPool();
                    INSTANCE.getAndSet(pool);
                }
            } catch (SQLException | DaoException e) {
                throw new ConnectionPoolException(e.getMessage(), e);
            } finally {
                LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    private ConnectionPool() throws SQLException, DaoException {
        connectionFactory = new ConnectionFactory();
        connectionsInUse = new ArrayDeque<>();
        availableConnections = new ArrayDeque<>();
        DriverManager.registerDriver(new Driver());
        addConnections();
    }

    private void addConnections() throws DaoException {
        for (int i = 0; i < CONNECTIONS_ALLOWED; i++) {
            ProxyConnection connection = connectionFactory.create(this);
            availableConnections.add(connection);
        }
    }

    public void returnConnection(ProxyConnection connection) {
        try {
            LOCK.lock();
            if (connectionsInUse.contains(connection)) {
                availableConnections.offer(connection);
                connectionsInUse.remove(connection);
                connectionSemaphore.release();
            }

        } finally {
            LOCK.unlock();
        }
    }

    public ProxyConnection getConnection() {

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
