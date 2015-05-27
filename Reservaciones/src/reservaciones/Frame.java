/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservaciones;

import javax.swing.*;

public class Frame extends JFrame {

    JPanel panel;

    Frame(){
        super("Reservaciones");
        setSize(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new Panel();

        add(panel);

        setVisible(true);
    }
}
