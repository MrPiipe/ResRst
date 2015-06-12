package reservaciones;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.toedter.calendar.JDateChooser;
import java.beans.*;

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

    Frame frame;

    Panel(Frame frame){

        this.frame = frame;

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

        boxRestaurante.setActionCommand("RESTAURANTE");
        boxLugar.setActionCommand("LUGAR");
        boxHora.setActionCommand("HORA");
        btnReservar.setActionCommand("RESERVAR");

        btnReservar.setBounds(480,110,100,20);

        lblRestaurante.setBounds(30,40,100,20);
        lblFecha.setBounds(180,40,50,20);
        lblHora.setBounds(350,40,50,20);
        lblLugar.setBounds(470,40,50,20);
        lblNombre.setBounds(10,10,70,20);
        lblCedula.setBounds(260,10,70,20);

        txtNombre.setBounds(80,10,150,25);
        txtCedula.setBounds(320,10,150,25);

        boxRestaurante.setBounds(30, 70, 140, 20);
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
        calendario.setEnabled(false);

        try{
            final Sql database = new Sql(this);

            calendario.getDateEditor().addPropertyChangeListener(
                new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent e) {
                    if ("date".equals(e.getPropertyName())) {
                        cleanComboBox(2);
                        int idrest = database.getIDRestaurante();
                        database.readTime(idrest);
                        enableDisable(0,false);
                        enableDisable(1,false);
                        enableDisable(2,true);
                    }
                }
            });

            btnReservar.addActionListener(database);
            boxRestaurante.addActionListener(database);
            boxLugar.addActionListener(database);
            boxHora.addActionListener(database);
        }
        catch(Exception a){
            error("error al conectar con la base de datos");
        }
    }

    public void calcular(long t1, long t2) {
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal.setTimeInMillis(t1);
        cal2.setTimeInMillis(t2);

        do {
            addItem(2, getFormatCal(cal));
            cal.add(Calendar.HOUR_OF_DAY, 1);
        }
        while (cal.before(cal2));

        addItem(2, getFormatCal(cal));
        cal.add(Calendar.HOUR_OF_DAY,1);
    }

   public String getFormatCal(Calendar cal) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String formatted = format.format(cal.getTime());
        return formatted;
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

    public void enableDisable(int pos, boolean enable){ //0 -> Lugar, 1 -> Boton, 2 -> Hora
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
            case 3:
                calendario.setEnabled(enable);
        }
    }

    public void error(String error){
       JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void msg(String mensaje){
       JOptionPane.showMessageDialog(null, mensaje);
    }
}
