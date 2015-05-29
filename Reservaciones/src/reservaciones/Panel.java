package reservaciones;

import javax.swing.*;
import java.awt.event.*;

public class Panel extends JPanel implements ActionListener {

    JButton btnReservar;

    JLabel lblLugar;
    JLabel lblFecha;
    JLabel lblHora;
    JLabel lblNombre;
    JLabel lblCedula;

    JTextField txtNombre;
    JTextField txtCedula;

    JComboBox boxLugar;
    JComboBox boxFecha;
    JComboBox boxHora;

    Panel(){
        setLayout(null);

        btnReservar = new JButton("Reservar");

        lblLugar = new JLabel("Lugar");    
        lblFecha = new JLabel("Fecha");
        lblHora = new JLabel("Hora");
        lblNombre = new JLabel("Nombre: ");
        lblCedula = new JLabel("Cedula: ");

        txtNombre = new JTextField();
        txtCedula = new JTextField();

        boxLugar = new JComboBox();
        boxFecha = new JComboBox();
        boxHora = new JComboBox();

        btnReservar.addActionListener(this);

        boxLugar.addActionListener(this);
        boxFecha.addActionListener(this);
        boxHora.addActionListener(this);

        boxLugar.setActionCommand("LUGAR");
        boxFecha.setActionCommand("FECHA");
        boxHora.setActionCommand("HORA");

        btnReservar.setBounds(350,110,100,20);

        lblLugar.setBounds(50,40,50,20);
        lblFecha.setBounds(180,40,50,20);
        lblHora.setBounds(310,40,50,20);
        lblNombre.setBounds(10,10,70,20);
        lblCedula.setBounds(240,10,70,20);

        txtNombre.setBounds(80,10,150,20);
        txtCedula.setBounds(320,10,150,20);

        boxLugar.setBounds(50,70,110,20);
        boxFecha.setBounds(180,70,110,20);
        boxHora.setBounds(310,70,110,20);

        add(btnReservar);

        add(lblLugar);
        add(lblFecha);
        add(lblHora);
        add(lblNombre);
        add(lblCedula);

        add(txtNombre);
        add(txtCedula);

        add(boxLugar);
        add(boxFecha);
        add(boxHora);

        addItem(0,"Norte");
        addItem(0,"Sur");
        addItem(0,"Este");
        addItem(0,"Oeste");
        addItem(1,"Hoy");
        addItem(1,"Mañana");
        addItem(1,"Pasado Mañana");
        addItem(2,"am");
        addItem(2,"pm");

        boxFecha.setEnabled(false);
        boxHora.setEnabled(false);
        btnReservar.setEnabled(false);
    }

    public void addItem(int pos, String string){ //0 -> Lugar, 1 -> Fecha, 2 -> Hora
        switch(pos){
            case 0:
                boxLugar.addItem(string);
                break;
            case 1:
                boxFecha.addItem(string);
                break;
            case 2:
                boxHora.addItem(string);
                break;
        }
    }

    boolean segundo = false;

    public void actionPerformed(ActionEvent eve){
        String id = eve.getActionCommand();
        if(id.equals("LUGAR")){ 
            if(segundo) boxFecha.setEnabled(true);
            else segundo = true;
        }
        else if(id.equals("FECHA")){
            boxHora.setEnabled(true);
        }
        else if(id.equals("HORA")){
            btnReservar.setEnabled(true);
        }
        else{
            System.out.println("Felicidades reservaste eres un genio");
        }
    }
}
