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
    Connection con;
    Statement sql;
    PreparedStatement ans;

    Sql(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(host+database, user, pass);
            sql = con.createStatement();        
        }catch( SQLException | ClassNotFoundException er ) {
            System.err.println(er);        
        }
    }
    
    
    void executeQuery(int cedula, String hora, java.sql.Date fecha, 
            String lugar, String restaurante){
        query="INSERT INTO `Reservas` (Hora, Fecha, Lugar, Restaurante, "
                + "Cliente) VALUES (?,?,?,?,?)";
        try {
            ans = con.prepareStatement(query);
            ans.setString(1, hora);
            ans.setDate(2, fecha);
            ans.setString(3, lugar);
            ans.setString(4, restaurante);
            ans.setInt(5, cedula);
            ans.executeUpdate();
//            
//            while (ans.next()) {
//                int codigo = ans.getInt("Codigo");
//                String nombre = ans.getString("Nombre");
//                String direccion = ans.getString("Direccion");
//                String capacidad = ans.getString("Capacidad");
//                String imagen = ans.getString("imagen");
//                
//                System.out.println(codigo + " " + nombre + " " + direccion + " " + capacidad + " " + imagen);
//            }
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
