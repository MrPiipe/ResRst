import java.sql.*;
 
/**
 *
 * @author Familia
 */
public class TestConnectionDB {
 
    /**
     * @param args the command line arguments
     */
    Connection con;
    public Connection getConexion() {
        
        try {
            System.out.println("Conectando con la Base de Datos");
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost/findfood", "root", "jonNBZ96");
            System.out.println("Conexión Exitosa");
            //obtener datos de la BD
//            Statement estado = con.createStatement();
//            ResultSet resultado = estado.executeQuery("select name, continent, population from country");
//            System.out.println("NAME \t\t CONTINENT \t\t POPULATION");
//            while(resultado.next()){
//                System.out.println(resultado.getString("name")+ "\t\t" 
//                        + resultado.getString("continent")+ "\t\t" + resultado.getInt("population"));
//            }
        } 
       // catch(SQLException e){
         //   System.out.println("Error de MySQL");
        //} 
        catch(ClassNotFoundException e){
            e.printStackTrace();
        } 
        catch (Exception e) {
            System.out.println("Se encontró el siguiente error: "+ e.getMessage());
        }
        return con;
    }
    
}
