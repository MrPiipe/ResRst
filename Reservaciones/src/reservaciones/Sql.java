package reservaciones;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Time;

public class Sql implements ActionListener{
    
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
    Panel panel;
    int IDRestaurante=0;

    Sql(Panel panel){
        try {
            this.panel = panel;
            Class.forName(driver);
            con = DriverManager.getConnection(host+database, user, pass);
            sql = con.createStatement();        
            readingQuery();
        }catch( SQLException | ClassNotFoundException er ) {
            System.err.println(er);        
        }
    }

    @Override
    public void actionPerformed(ActionEvent eve){
        String id = eve.getActionCommand();
        switch (id) {
            case "RESTAURANTE":
                panel.cleanComboBox(2);
                panel.cleanComboBox(0); 
                getIDRestaurante();
                readTime(IDRestaurante);
                panel.enableDisable(0,false);
                panel.enableDisable(2,true);
                break;
            case "HORA":
                panel.cleanComboBox(0);
                getIDRestaurante();
                readTime(IDRestaurante);
                String horaMesa = panel.getComboBox(2).getSelectedItem().toString();
                java.sql.Time ti = java.sql.Time.valueOf(horaMesa+":00"); 
                getMesas(IDRestaurante, ti);
                panel.enableDisable(0,true);
                break;
            case "LUGAR":
               panel.enableDisable(1, true);
                break;
            case "RESERVAR":
                String nombre = panel.getText(0);
                String cedula = panel.getText(1);
                int ced = Integer.parseInt(cedula);

                String lugar = panel.getComboBox(0).getSelectedItem().toString();
                String restaurante = panel.getComboBox(1).getSelectedItem().toString();
                String hora = panel.getComboBox(2).getSelectedItem().toString();

                String date =  panel.getDateFormat();

                java.sql.Time t = java.sql.Time.valueOf(hora+":00");
                
                java.sql.Date fecha = java.sql.Date.valueOf(date);

                try{
                    executeReserva(ced, t, fecha, lugar, restaurante, nombre);
                }catch(SQLException e){
                    panel.error("Error al insertar la reserva");
                }
                panel.msg(nombre + " su reserva se ha realizado"
                        + " correctamente\n para el día: " + fecha
                        + "\n a la(s): " + hora);
                break;
        }
    } 
    
    public int getIDRestaurante(){
        String rest = panel.getComboBox(1).getSelectedItem().toString();
        try{
            rs = sql.executeQuery("SELECT IDRestaurante FROM Restaurantes WHERE `Nombre Restaurante` = " + "'"+rest+"'");
            if(rs.next()) IDRestaurante = rs.getInt("IDRestaurante");
        }catch(SQLException a){
            panel.error("Error al obtener el id del restaurante");
        }
        return IDRestaurante;
    }
    
    public  void readTime(int IDrest){
        Time horaI = null;
        Time horaF = null;
        try{
            rs = sql.executeQuery("SELECT `Hora Inicio` FROM Restaurantes WHERE IDRestaurante =" + "'"+ IDrest+"'");
            if(rs.next()){
                horaI = rs.getTime("Hora Inicio");
            }
            rs = sql.executeQuery("SELECT `Hora Fin` FROM Restaurantes WHERE IDRestaurante =" + "'"+ IDrest+"'");
            if(rs.next()){
                horaF = rs.getTime("Hora Fin");
            }
        }catch (SQLException ex) {
            //panel.error("error en la lectura del tiempo");
            ex.printStackTrace();
        }
        panel.calcular(horaI.getTime(), horaF.getTime());
    }
    
    public void readingQuery(){
        try{
            rs = sql.executeQuery("SELECT `Nombre Restaurante` FROM Restaurantes");
            while (rs.next()){
                String nombreRest = rs.getString("Nombre Restaurante");
                panel.addItem(1,nombreRest);
            }           
        } catch (SQLException ex) {
            panel.error("Por favor ingrese su nombre y cedula");
        }
        
    }
    
    public void getMesas(int IDrestaurante, java.sql.Time ti){
        try {
            rs = sql.executeQuery("SELECT Mesa FROM Sitio WHERE IDRestaurante= '"+IDrestaurante+"'AND (Mesa) NOT IN (SELECT Mesa FROM Reservas WHERE Hora='"+ ti +"')");
            while (rs.next()){
                String mesa = rs.getString("Mesa");
                panel.addItem(0,mesa);
            }
        }catch (Exception e) {
            panel.error("Error al conseguir las mesas.");
        }
    }
    
    public void executeReserva(int cedula, Time hora, java.sql.Date fecha, 
            String mesa, String restaurante, String nombre) throws SQLException{
        query="INSERT INTO `Reservas`(`Cedula`, `IDRestaurante`, `Fecha`, `Hora`, `Mesa`) VALUES (?,?,?,?,?)";
        querycliente="INSERT INTO `Cliente` (Cedula, Nombre) VALUES (?,?)";
        try {
            
//            con.setAutoCommit(false);
            getIDRestaurante();
            System.out.println(IDRestaurante);
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
            
//            con.commit();
        } catch (SQLException ex) {
//            con.rollback();
        }
    }
}
