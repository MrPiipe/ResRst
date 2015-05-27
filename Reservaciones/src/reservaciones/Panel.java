/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservaciones;

import javax.swing.*;
import java.awt.event.*;

public class Panel extends JPanel implements ActionListener {

    JButton btnReservar;
    JLabel lblLugar;
    JLabel lblFecha;
    JLabel lblHora;

    JComboBox boxLugar;
    JComboBox boxFecha;
    JComboBox boxHora;

    Panel(){
        setLayout(null);

        boxLugar = new JComboBox();
        boxFecha = new JComboBox();
        boxHora = new JComboBox();

        boxLugar.addActionListener(this);
        boxFecha.addActionListener(this);
        boxHora.addActionListener(this);

        boxLugar.setActionCommand("LUGAR");
        boxFecha.setActionCommand("FECHA");
        boxHora.setActionCommand("HORA");

        btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(this);

        lblLugar = new JLabel("Lugar");    
        lblFecha = new JLabel("Fecha");
        lblHora = new JLabel("Hora");

        lblLugar.setBounds(50,40,50,20);
        lblFecha.setBounds(180,40,50,20);
        lblHora.setBounds(310,40,50,20);
        btnReservar.setBounds(350,110,100,20);
        boxLugar.setBounds(50,70,110,20);
        boxFecha.setBounds(180,70,110,20);
        boxHora.setBounds(310,70,110,20);

        add(boxLugar);
        add(boxFecha);
        add(boxHora);
        add(lblLugar);
        add(lblFecha);
        add(lblHora);
        add(btnReservar);

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
