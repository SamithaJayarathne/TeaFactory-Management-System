package model;

import java.sql.*;

public class MySQL {
    
    private static java.sql.Connection connection;
    
    private static void createConnection() throws Exception{
    
        if(connection == null){
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");
        }
        
    }
    
    public static ResultSet executeSearch(String query) throws Exception{
        
        createConnection();
        return (ResultSet) connection.createStatement().executeQuery(query);
        
    }
    
    public static Integer executeIUD(String query) throws Exception{
        createConnection();
        return connection.createStatement().executeUpdate(query);
    }
    
}
