package reservaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sql {
    
    String driver = "com.mysql.jdbc.Driver";
    String host = "jdbc:mysql://sql3.freesqldatabase.com:3306/";
    String database = "sql379612";
    String user = "sql379612";
    String pass = "vJ5!tG2*";
    String query;
    String querycliente;
    String queryread;
    Connection con;
    Statement sql;
    PreparedStatement ans;
    ResultSet rs;

    Sql(){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(host+database, user, pass);
            sql = con.createStatement();        
        }catch( SQLException | ClassNotFoundException er ) {
            System.err.println(er);        
        }
    }
    
    void readTime(int IDrest, Panel panel){
        Time horaI;
        Time horaF;
        try{
            rs = sql.executeQuery("SELECT `Hora Inicio` FROM Restaurantes WHERE IDRestaurante =" + "'"+ IDrest+"'");
            if(rs.next()){
                horaI = rs.getTime("`Hora Inicio`");
            }
            rs = sql.executeQuery("SELECT `Hora Fin` FROM Restaurantes WHERE IDRestaurante =" + "'"+ IDrest+"'");
            if(rs.next()){
                horaF = rs.getTime("`Hora Fin`");
            }
        }catch (SQLException ex) {
            //Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
            //panel.error("Por favor ingrese su nombre y cedula");
        }
        calcular(horaI,horaF, panel);
    }
    
    void readingQuery(Panel panel){
        try{
            rs = sql.executeQuery("SELECT `Nombre Restaurante` FROM Restaurantes");
            while (rs.next()){
                String nombreRest = rs.getString("Nombre Restaurante");
                panel.addItem(1,nombreRest);
            }
//            rs = sql.executeQuery("SELECT Mesa FROM Capacidad");
//            while (rs.next()){
//                String mesa = rs.getString("Mesa");
//                panel.addItem(0,mesa);
//            }
//            rs = sql.executeQuery("SELECT Hora FROM Restaurantes");
//            while (rs.next()){
//                String hora = rs.getString("Hora");
//                panel.addItem(0,hora);
//            }
            
        } catch (SQLException ex) {
            //Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);

            panel.error("Por favor ingrese su nombre y cedula");
        }
        
    }
    
    void executeQuery(int cedula, Time hora, java.sql.Date fecha, 
            String mesa, String restaurante, String nombre){
        query="INSERT INTO `Reservas`(`Cedula`, `IDRestaurante`, `Fecha`, `Hora`, `Mesa`) VALUES (?,?,?,?,?)";
        querycliente="INSERT INTO `Cliente` (Cedula, Nombre) VALUES (?,?)";
        int IDRestaurante=0;
        try {
            rs = sql.executeQuery("SELECT IDRestaurante FROM Restaurantes WHERE `Nombre Restaurante` = " + "'"+restaurante+"'");
            if(rs.next()){
                IDRestaurante = rs.getInt("IDRestaurante");
            }else{
                System.out.println("Error");
            }
            ans = con.prepareStatement(query);
            ans.setInt(1, cedula); 
            ans.setInt(2, IDRestaurante);
            ans.setDate(3, fecha);
            ans.setTime(4, hora); //Cambiar a formato time
            ans.setString(5, mesa);
            ans.executeUpdate();
            ans = con.prepareStatement(querycliente);
            ans.setInt(1, cedula);
            ans.setString(2, nombre);
            ans.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
