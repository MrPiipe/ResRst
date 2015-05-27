/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservaciones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*; 

public class Sql {
    Sql(){
        try {
            String host = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql378796";
            String uName = "sql378796";
            String uPass = "gG6!jZ8*";
            Connection con = DriverManager.getConnection(host, uName, uPass);
            Statement stmt = con.createStatement( );
            String SQL = "SELECT * FROM Restaurantes";
            ResultSet rs = stmt.executeQuery( SQL );
            
        }catch( SQLException err ) {
            System.out.println( err.getMessage( ) );        
        }
    }
}
