/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */


import java.sql.*;
import javax.swing.JOptionPane;
public class DBConnect {
    
    Connection con = null;
    
    public static Connection Connection(){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbebanking", "root", "");
            return con;
        }
        
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return null;
    }
}
    
