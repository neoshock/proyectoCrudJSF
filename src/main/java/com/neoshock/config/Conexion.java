package com.neoshock.config;

import java.sql.*;

public class Conexion {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Conexion() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User", 
                    "postgres", "123456789");
            statement = connection.createStatement();
        } catch (Exception e) {
            
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
}
