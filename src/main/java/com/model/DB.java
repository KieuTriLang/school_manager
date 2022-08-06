package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DB {

	static String url       = "jdbc:mysql://localhost:3306/smdb?useUnicode=yes&characterEncoding=UTF-8";
    static String user      = "root";
    static String password  = "1234";
    
    static Connection dbConnection = null;

    public static void open() 
            throws SQLException, ClassNotFoundException 
    {
        Class.forName("com.mysql.jdbc.Driver"); // 
        dbConnection = DriverManager.getConnection(url, user, password);
        
        Statement stmt = dbConnection.createStatement();
        stmt.execute("SET CHARACTER SET 'UTF8'");
    }
    
    public static void close() 
            throws SQLException 
    {
        if(dbConnection != null)
            dbConnection.close();
    }
    
    
    public static ResultSet q(String sql) 
            throws SQLException, ClassNotFoundException 
    {
        // Thực thi câu truy vấn
        Statement stmt = dbConnection.createStatement();
        
        ResultSet rs = stmt.executeQuery(sql);
        
        return rs;
    }
    public static boolean exec(String sql) 
            throws SQLException, ClassNotFoundException 
    {
        open(); // Mở kết nối
        
        PreparedStatement preparedSQL = dbConnection.prepareStatement(sql);
        
        int isSuccess = preparedSQL.executeUpdate();
        
        close();
        if(isSuccess >= 0 ? true : false) {
        	return true;
        };        
        return false;
    }
}
