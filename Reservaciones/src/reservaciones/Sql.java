package reservaciones;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sql {
    
    String driver = "com.mysql.jdbc.Driver";
    String host = "jdbc:mysql://sql3.freesqldatabase.com:3306/";
    String database = "sql378796";
    String user = "sql378796";
    String pass = "gG6!jZ8*";

    String query;
    String querycliente;
    String queryread;
    Connection con;
    Statement sql;
    PreparedStatement ans;
    ResultSet rs;
    Panel panel;

    Sql(){
        panel = new Panel();
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(host+database, user, pass);
            sql = con.createStatement();        
        }catch( SQLException | ClassNotFoundException er ) {
            System.err.println(er);        
        }
    }
    void readingQuery(){
        try{
            rs = sql.executeQuery("SELECT Nombre FROM Restaurantes");
            while (rs.next()){
                String nombreRest = rs.getString("Nombre");
                panel.addItem(0,nombreRest);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    void executeQuery(int cedula, String hora, java.sql.Date fecha, 
            String lugar, String restaurante, String nombre){
        query="INSERT INTO `Reservas` (Hora, Fecha, Lugar, Restaurante, "
                + "Cliente) VALUES (?,?,?,?,?)";
        querycliente="INSERT INTO `Cliente` (Cedula, Nombre) VALUES (?,?)";
        try {
            ans = con.prepareStatement(query);
            ans.setString(1, hora);
            ans.setDate(2, fecha);
            ans.setString(3, lugar);
            ans.setString(4, restaurante);
            ans.setInt(5, cedula);
            ans = con.prepareStatement(querycliente);
            ans.setInt(1, cedula);
            ans.setString(2, nombre);
            ans.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
