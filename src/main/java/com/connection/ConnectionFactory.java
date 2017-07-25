package com.connection;

import com.mysql.jdbc.Driver;
import java.sql.*;

/**
 * Created by Timur on 24.07.2017.
 */

public class ConnectionFactory {
    public static final String URL = "jdbc:mysql://localhost:3306/myDB";
    public static final String USER = "root";
    public static final String PASSWORD = "Karimovtimur27";


    public static Connection getConnection(){
        try{
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(SQLException ex){
            throw new RuntimeException("Error connectiong to the database", ex);
        }
    }
}
