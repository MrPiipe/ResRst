package reservaciones;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Panel extends JPanel implements ActionListener {
    
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
    
    Sql database;

    Panel(){
        setLayout(null);
        database= new Sql();
        
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
        
        btnReservar.addActionListener(this);

        boxRestaurante.addActionListener(this);
        boxLugar.addActionListener(this);
        boxHora.addActionListener(this);

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
        
        database.readingQuery(this);

        boxLugar.setEnabled(false);
        //calendario.setEnabled(false);
        boxHora.setEnabled(false);
        btnReservar.setEnabled(false);
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

    //boolean segundo = false;

    @Override
    public void actionPerformed(ActionEvent eve){
        String id = eve.getActionCommand();
        switch (id) {
            case "RESTAURANTE":
                boxLugar.removeAllItems();
                boxHora.setEnabled(true);
                break;
            case "HORA":
                boxLugar.setEnabled(true);
                btnReservar.setEnabled(true);
                break;
            case "LUGAR":
                //calendario.setEnabled(true);
                //if(segundo) boxFecha.setEnabled(true);
                //else segundo = true;
                break;
            case "RESERVAR":
                int cedula = Integer.parseInt(txtCedula.getText());
                String lugar = boxLugar.getSelectedItem().toString();
                String hora = boxHora.getSelectedItem().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                long ms = 0;
                try {
                    ms = sdf.parse(hora).getTime();
                } catch (ParseException ex) {
                    Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                }
                Time t = new Time(ms);
                String restaurante =boxRestaurante.getSelectedItem().toString();
                String nombre = txtNombre.getText();
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String date = formato.format(calendario.getDate());
                java.sql.Date fecha = java.sql.Date.valueOf(date);
                database.executeQuery(cedula, t, fecha, lugar, restaurante, nombre);
                error(txtNombre.getText() + " su reserva se ha realizado"
                        + " correctamente\n para el día: " + fecha
                        + "\n a la(s): " + hora);
                break;
        }
    }

    public void error(String error){
       JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
