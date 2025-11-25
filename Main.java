import View.Clientes;
import View.Login;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        frame.setContentPane(new Login().Loginprincipal);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}