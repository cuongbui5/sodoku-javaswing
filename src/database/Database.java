package database;

import exceptions.DbException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.*;
@NoArgsConstructor
@Getter
public class Database {
    public static final String DB_URL="jdbc:mysql://localhost:3306/java";
    public static final String USER_NAME="root";
    public static final String PASSWORD="";
    public static final String DRIVER_CLASS="com.mysql.cj.jdbc.Driver";
    private Connection connection;
    public void connectionDB(){
        try {
            Class.forName(DRIVER_CLASS);
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Connected!");
        } catch (ClassNotFoundException | SQLException e) {
           throw new DbException("Error connect db!");
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DbException("Can not close connection!");
        }
    }



}
