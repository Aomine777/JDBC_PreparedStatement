package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
public class Database {
    private static Database instance;
    private Connection connection;

    private Database(){
        try{
            this.connection = DriverManager.getConnection
                    ("jdbc:postgresql://localhost:2412/postgres", "postgres", "2412");
        } catch (SQLException e){
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
