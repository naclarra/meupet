package View;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Agendamentos extends JFrame{
    private JPanel principal;
    private JPanel painelTop;
    private JPanel painelbotom;
    private JComboBox comboBox1;
    private JTextField txtValor;



    public Agendamentos() {
        setTitle("Tela aGENDAMENTOS");
        setSize(800, 600);
        setContentPane(principal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o app ao clicar no "X"
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);

        

        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (comboBox1.getSelectedItem().equals("Somente Banho")) {
                        txtValor.setText("R$50,00");
                    } else if (comboBox1.getSelectedItem().equals("Somente Tosa")) {
                        txtValor.setText("R$70,00");
                    }else if (comboBox1.getSelectedItem().equals("Banho e Tosa")) {
                        txtValor.setText("R$100,00");
                    }else if (comboBox1.getSelectedItem().equals("Vacinação")) {
                        txtValor.setText("R$50,00");
                    }else {
                        txtValor.setText("R$150,00");
                    }

                }
            }
        });
    }
}
