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
        lblLugar = new JLabel("Lugar");    
        lblFecha = new JLabel("Fecha");
        lblHora = new JLabel("Hora");

        btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(this);

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

    public void actionPerformed(ActionEvent eve){
        System.out.println("Felicidades reservaste, eres un genio");
    }
}
