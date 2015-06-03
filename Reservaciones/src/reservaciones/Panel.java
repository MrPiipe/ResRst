package reservaciones;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import com.toedter.calendar.JDateChooser;

public class Panel extends JPanel {
    
    JButton btnReservar;

    JLabel lblRestaurante;
    JLabel lblLugar;
    JLabel lblFecha;
    JLabel lblHora;
    JLabel lblNombre;
    JLabel lblCedula;

    JTextField txtNombre;
    JTextField txtCedula;

    JComboBox boxRestaurante;
    JComboBox boxLugar;
    JComboBox boxHora;
    
    JDateChooser calendario;
    
    Panel(){
        setLayout(null);
        
        btnReservar = new JButton("Reservar");

        lblRestaurante = new JLabel("Restaurante");
        lblLugar = new JLabel("Lugar");    
        lblFecha = new JLabel("Fecha");
        lblHora = new JLabel("Hora");
        lblNombre = new JLabel("Nombre: ");
        lblCedula = new JLabel("Cédula: ");

        txtNombre = new JTextField();
        txtCedula = new JTextField();

        boxRestaurante = new JComboBox();
        boxLugar = new JComboBox();
        boxHora = new JComboBox();
        
        calendario = new JDateChooser();
        
        Sql database = new Sql(this);

        btnReservar.addActionListener(database);

        boxRestaurante.addActionListener(database);
        boxLugar.addActionListener(database);
        boxHora.addActionListener(database);

        boxRestaurante.setActionCommand("RESTAURANTE");
        boxLugar.setActionCommand("LUGAR");
        boxHora.setActionCommand("HORA");
        btnReservar.setActionCommand("RESERVAR");

        btnReservar.setBounds(480,110,100,20);

        lblRestaurante.setBounds(50,40,100,20);
        lblFecha.setBounds(180,40,50,20);
        lblHora.setBounds(350,40,50,20);
        lblLugar.setBounds(470,40,50,20);
        lblNombre.setBounds(10,10,70,20);
        lblCedula.setBounds(260,10,70,20);

        txtNombre.setBounds(80,10,150,25);
        txtCedula.setBounds(320,10,150,25);

        boxRestaurante.setBounds(50, 70, 110, 20);
        calendario.setBounds(180,70,150,25);
        boxHora.setBounds(350,70,110,20);
        boxLugar.setBounds(470,70,110,20);

        add(btnReservar);

        add(lblRestaurante);
        add(lblLugar);
        add(lblFecha);
        add(lblHora);
        add(lblNombre);
        add(lblCedula);

        add(txtNombre);
        add(txtCedula);

        add(boxRestaurante);
        add(boxLugar);
        add(calendario);
        add(boxHora);
        
        boxLugar.setEnabled(false);
        boxHora.setEnabled(false);
        btnReservar.setEnabled(false);
    }

    public long getDateMillis(String hora){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        long ms = 0;

        try {
            ms = sdf.parse(hora).getTime();
        } catch (ParseException ex) {
            error("error al combertir la hora");
        }

        return ms;
    }

    public String getDateFormat(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(calendario.getDate());
    }

    public JComboBox getComboBox(int pos){
        switch(pos){
            case 0:
                return boxLugar;
            case 1:
                return boxRestaurante;
            case 2: 
                return boxHora;
        }
        return null;
    } 

    public String getText(int pos){
        switch(pos){
            case 0:
                return txtNombre.getText();
            case 1:
                return txtCedula.getText();
            case 2: 

        }
        return null;
    }

    public void addItem(int pos, String string){ //0 -> Lugar, 1 -> Fecha, 2 -> Hora
        switch(pos){
            case 0:
                boxLugar.addItem(string);
                break;
            case 1:
                boxRestaurante.addItem(string);
                break;
            case 2:
                boxHora.addItem(string);
                break;
        }
    }

    public void cleanComboBox(int pos){ //0 -> Lugar, 1 -> Fecha, 2 -> Hora
        switch(pos){
            case 0:
                boxLugar.removeAllItems();
                break;
            case 1:
                boxRestaurante.removeAllItems();
                break;
            case 2:
                boxHora.removeAllItems();
                break;
        }
    }

    public void enebleDisable(int pos, boolean enable){ //0 -> Lugar, 1 -> Boton, 2 -> Hora
        switch(pos){
            case 0:
                boxLugar.setEnabled(enable);
                break;
            case 1:
                btnReservar.setEnabled(enable);
                break;
            case 2:
                boxHora.setEnabled(enable);
                break;
        }
    }

    public void error(String error){
       JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void msg(String mensaje){
       JOptionPane.showMessageDialog(null, mensaje);
    }
}
