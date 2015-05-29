package reservaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*; 

public class Sql {
    
    String driver = "com.mysql.jdbc.Driver";
    String host = "jdbc:mysql://sql3.freesqldatabase.com:3306/sql378796";
    String user = "sql378796";
    String pass = "gG6!jZ8*";

    String query;
    Connection con;
    Statement sql;
    ResultSet ans;

    Sql(){
        
        query = "SELECT * FROM Restaurantes";

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(host, user, pass);
            sql = con.createStatement();
            ans = sql.executeQuery(query);
            
            while (ans.next()) {
            int codigo = ans.getInt("Codigo");
            String nombre = ans.getString("Nombre");
            String direccion = ans.getString("Direccion");
            String capacidad = ans.getString("Capacidad");
            String imagen = ans.getString("imagen");
            
            System.out.println(codigo + " " + nombre + " " + direccion + " " + capacidad + " " + imagen);
            }
            
        }catch( SQLException er ) {
            System.err.println(er);        
        }
        catch(ClassNotFoundException a){
            System.err.println(a);
        }
    }
}
