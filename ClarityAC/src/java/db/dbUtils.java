/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;

/**
 *
 * @author joshrandall
 */
public class dbUtils {
    
    public static PreparedStatement aqm_request_process(String sql) throws ClassNotFoundException, SQLException{
        PreparedStatement ps =  null;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:8889/AC";
        String user = "root";
        String pass = "root";
        
        Connection con = DriverManager.getConnection(url, user, pass);
        ps = con.prepareStatement(sql);
        
        return ps;
    }
    
    //Check connections.
    //public static void main(String[] args) throws ClassNotFoundException, SQLException {
    //   Connection conn = null;
    //  String url = "jdbc:mysql://localhost:8889/AC";
    //    String user = "root";
    //    String pass = "root";
    //   try{
    //       conn = DriverManager.getConnection(url, user, pass);
    //       System.out.println("Connected!");
    //       Statement stmt = (Statement) conn.createStatement();
    //       String insert = "INSERT into AIRCRAFT values (0, NOW(), \"CARGO\", \"SMALL\")";
    //       stmt.executeUpdate(insert);
    //              
    //   } catch (SQLException e) {
    //       System.err.println(e);
    //   }
    //   
    //}
}
