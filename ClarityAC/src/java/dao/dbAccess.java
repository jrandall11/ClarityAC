/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.dbUtils;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aircraft;
/**
 *
 * @author joshrandall
 */
public class dbAccess {
    
    public void enqueue(Aircraft ac){
        try {
            PreparedStatement ps = dbUtils.aqm_request_process("insert into AIRCRAFT (Date, Type, Size) values (NOW(),?,?)");
            //ps.setString(1, ac.getDate());
            ps.setString(1, ac.getType());
            ps.setString(2, ac.getSize());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbAccess.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error caught: " + ex);
        }
        
    }
    
    //public static void main(String[] args) {
    //    PreparedStatement ps;
    //    Aircraft ac;
    //    ac = new Aircraft(-1, "NULL", "NULL", "NULL");
    //    try {
    //        ps = dbUtils.aqm_request_process("SELECT * FROM AIRCRAFT AS a \n" +
    //        "WHERE (a.Type=\"PASSENGER\" AND a.Size=\"LARGE\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"PASSENGER\" AND a.Size=\"LARGE\")) OR (a.Type=\"PASSENGER\" AND a.Size=\"SMALL\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"PASSENGER\" AND a.Size=\"SMALL\")) OR (a.Type=\"CARGO\" AND a.Size=\"LARGE\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"CARGO\" AND a.Size=\"LARGE\")) OR (a.Type=\"CARGO\" AND a.Size=\"SMALL\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"CARGO\" AND a.Size=\"SMALL\")) \n" +
    //        "ORDER BY (CASE WHEN (a.Type=\"PASSENGER\" AND a.Size=\"LARGE\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"PASSENGER\" AND a.Size=\"LARGE\")) THEN 1\n" +
    //        " WHEN (a.Type=\"PASSENGER\" AND a.Size=\"SMALL\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"PASSENGER\" AND a.Size=\"SMALL\")) THEN 2\n" +
    //        " WHEN (a.Type=\"CARGO\" AND a.Size=\"LARGE\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"CARGO\" AND a.Size=\"LARGE\")) THEN 3\n" +
    //        " WHEN (a.Type=\"CARGO\" AND a.Size=\"SMALL\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"CARGO\" AND a.Size=\"SMALL\")) THEN 4 END)\n" +
    //        "Limit 1");
    //        ResultSet rs = ps.executeQuery();
    //        while (rs.next()) {
    //            ac = new Aircraft(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
    //        }
    //        
    //        ps = dbUtils.getPreparedStatement("DELETE FROM AIRCRAFT\n" +
    //                "WHERE AIRCRAFT.Id = (?)");
    //        ps.setInt(1, ac.getId());
    //        ps.executeUpdate();
    //    } catch (ClassNotFoundException | SQLException ex) {
    //        Logger.getLogger(dbAccess.class.getName()).log(Level.SEVERE, null, ex);
    //    }
    //}
    
    public Aircraft dequeue(){
        PreparedStatement ps;
        Aircraft ac;
        ac = new Aircraft(-1, "NULL", "NULL", "NULL");
        try {
            ps = dbUtils.aqm_request_process("SELECT * FROM AIRCRAFT AS a \n" +
            "WHERE (a.Type=\"PASSENGER\" AND a.Size=\"LARGE\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"PASSENGER\" AND a.Size=\"LARGE\")) OR (a.Type=\"PASSENGER\" AND a.Size=\"SMALL\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"PASSENGER\" AND a.Size=\"SMALL\")) OR (a.Type=\"CARGO\" AND a.Size=\"LARGE\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"CARGO\" AND a.Size=\"LARGE\")) OR (a.Type=\"CARGO\" AND a.Size=\"SMALL\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"CARGO\" AND a.Size=\"SMALL\")) \n" +
            "ORDER BY (CASE WHEN (a.Type=\"PASSENGER\" AND a.Size=\"LARGE\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"PASSENGER\" AND a.Size=\"LARGE\")) THEN 1\n" +
            " WHEN (a.Type=\"PASSENGER\" AND a.Size=\"SMALL\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"PASSENGER\" AND a.Size=\"SMALL\")) THEN 2\n" +
            " WHEN (a.Type=\"CARGO\" AND a.Size=\"LARGE\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"CARGO\" AND a.Size=\"LARGE\")) THEN 3\n" +
            " WHEN (a.Type=\"CARGO\" AND a.Size=\"SMALL\" AND a.Date = (SELECT min(a.Date) FROM AIRCRAFT AS a WHERE a.Type = \"CARGO\" AND a.Size=\"SMALL\")) THEN 4 END)\n" +
            "Limit 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ac = new Aircraft(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            
            ps = dbUtils.aqm_request_process("DELETE FROM AIRCRAFT\n" +
                    "WHERE AIRCRAFT.Id = (?)");
            ps.setInt(1, ac.getId());
            ps.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ac;
    }
    
    public static List<Aircraft> getAll(){
        List<Aircraft> ls = new LinkedList<>();
        
        try {
            ResultSet rs = dbUtils.aqm_request_process("select * from AIRCRAFT").executeQuery();
            while(rs.next()){
                Aircraft a = new Aircraft(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                ls.add(a);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(dbAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return ls;
    }
}
