package com.epam.web.connection;

import com.epam.web.dao.DaoException;
import com.mysql.cj.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;

    private final static AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private final static Lock LOCK = new ReentrantLock();
//    private final static Lock CONNECTIONS_LOCK = new ReentrantLock();

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
                }
                //TODO: throw
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    private ConnectionPool() {

        availableConnections = new ArrayDeque<>();
        connectionsInUse = new ArrayDeque<>();
    }

    public void returnConnection(ProxyConnection connection){
        try{
            LOCK.lock();
            if(connectionsInUse.contains(connection)){
                availableConnections.offer(connection);
            }
        } finally {
            LOCK.unlock();
        }
    }

    public ProxyConnection getConnection() throws DaoException {
        return ConnectionFactory.create();
    }

}
