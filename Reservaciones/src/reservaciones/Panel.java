package reservaciones;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

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
    JComboBox boxFecha;
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
       // boxFecha = new JComboBox();
        boxHora = new JComboBox();
        
        calendario = new JDateChooser();
        
        btnReservar.addActionListener(this);

        boxRestaurante.addActionListener(this);
        boxLugar.addActionListener(this);
        //boxFecha.addActionListener(this);
        boxHora.addActionListener(this);

        boxRestaurante.setActionCommand("RESTAURANTE");
        boxLugar.setActionCommand("LUGAR");
        //boxFecha.setActionCommand("FECHA");
        boxHora.setActionCommand("HORA");
        btnReservar.setActionCommand("RESERVAR");

        btnReservar.setBounds(350,110,100,20);

        lblRestaurante.setBounds(50,40,100,20);
        lblLugar.setBounds(180,40,50,20);
        lblFecha.setBounds(310,40,50,20);
        lblHora.setBounds(440,40,50,20);
        lblNombre.setBounds(10,10,70,20);
        lblCedula.setBounds(260,10,70,20);

        txtNombre.setBounds(80,10,150,30);
        txtCedula.setBounds(320,10,150,30);

        boxRestaurante.setBounds(50, 70, 110, 20);
        boxLugar.setBounds(180,70,110,20);
        //boxFecha.setBounds(180,70,110,20);
        calendario.setBounds(310,70,110,30);
        boxHora.setBounds(440,70,110,20);

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

        addItem(0,"Norte");
        addItem(0,"Sur");
        addItem(0,"Este");
        addItem(0,"Oeste");
        addItem(1,"Frisby");
        addItem(1,"Rancherito");
        addItem(1,"Dogger");
        addItem(1,"Subway");
        addItem(2,"8:00 a.m.");
        addItem(2,"9:00 a.m.");
        addItem(2,"10:00 a.m.");
        addItem(2,"11:00 a.m.");
        addItem(2,"12:00 p.m.");
        addItem(2,"1:00 p.m.");
        addItem(2,"2:00 p.m.");
        addItem(2,"3:00 p.m.");
        addItem(2,"4:00 p.m.");
        addItem(2,"5:00 p.m.");
        addItem(2,"6:00 p.m.");
        addItem(2,"7:00 p.m.");
        addItem(2,"8:00 p.m.");
        addItem(2,"9:00 p.m.");
        addItem(2,"10:00 p.m.");
        addItem(2,"11:00 p.m.");
        addItem(2,"12:00 p.m.");

        boxLugar.setEnabled(false);
        boxHora.setEnabled(false);
        btnReservar.setEnabled(false);
    }

    private void addItem(int pos, String string){ //0 -> Lugar, 1 -> Fecha, 2 -> Hora
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
                boxLugar.setEnabled(true);
            case "LUGAR":
                boxHora.setEnabled(true);
                //if(segundo) boxFecha.setEnabled(true);
                //else segundo = true;
                break;
            case "HORA":
                btnReservar.setEnabled(true);
                break;
            case "RESERVAR":
                int cedula = Integer.parseInt(txtCedula.getText());
                String lugar = boxLugar.getSelectedItem().toString();
                String hora = boxHora.getSelectedItem().toString();
                String restaurante =boxRestaurante.getSelectedItem().toString();
                String nombre = txtNombre.getText();
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String date = formato.format(calendario.getDate());
                java.sql.Date fecha = java.sql.Date.valueOf(date);
                JOptionPane.showMessageDialog(null, txtNombre.getText()
                        + " su reserva se ha realizado"
                        + " correctamente\n para el día: " + fecha
                        + "\n a la(s): " + hora);
                database.executeQuery(cedula, hora, fecha, lugar, restaurante, nombre);
                break;
        }
    }
}
