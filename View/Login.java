package View;

import Controller.LoginDAO;
import Model.Usuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame{
    public JPanel Loginprincipal;
    private JPanel painelTopo;
    private JTextField jtxtLogin;
    private JPasswordField jptxtSenha;
    private JComboBox jcomboPerfil;
    private JButton btnLogar;
    private JButton btnSair;

    public Login() {

        btnLogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // recebe o que foi digitado pelo usuario
                String login = jtxtLogin.getText();
                String senha = new String(jptxtSenha.getPassword());
                String perfil = String.valueOf(jcomboPerfil.getSelectedItem());
                // cria objeto da classe onde contem o metodo de validação
                LoginDAO loginDAO = new LoginDAO();
                // criando um objeto da classe modelo usuarios
                // esse objeto recebe o resultado do metodo validalogin
                // ao mesmo tempo que passa os dados pro metodo ja retorna o resultado do banco
                Usuarios resultado = loginDAO.validarLogin(login, senha,perfil);
                // se o objeto resultado NÃO for nulo, quer dizer que o resultset
                // encontrou o login a senha e o perfil que foi digitado e da acesso a aplicação
                if (resultado!=null) {
                    System.out.println("Login bem-sucedido!");
                    // Obtém a janela (JFrame) que contém este painel
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Loginprincipal);
                    // Fecha a janela de login
                    frame.dispose();
                    // Abre a tela principal
                    SwingUtilities.invokeLater(() -> {
                        Home home = new Home();
                        // ao chamar a tela Principal - que aqui dei o nome de home
                        // verifico antes o perfil do usuario entregue pelo resultset

                        if (resultado.getPerfil().equals("user")) {
                            home.jmiVenda.setEnabled(false);
                            home.jmiPlano.setEnabled(false);
                            home.jmiEstoque.setEnabled(false);
                            home.jmiRelatorio.setEnabled(false);
                            home.jmiEmail.setEnabled(false);
                            home.jmiFluxoCaixa.setEnabled(false);
                            home.jmiContasReceber.setEnabled(false);
                            home.jmiContasPagar.setEnabled(false);

                            home.setVisible(true);
                        }else{
                            home.setVisible(true);
                        }

                    });

                } else {
                    System.out.println("Usuário ou senha inválidos.");
                    jtxtLogin.requestFocus();
                    jtxtLogin.setText("");
                    jptxtSenha.setText("");
                    JOptionPane.showMessageDialog(null, "Login ou senha Incorretos!");
                }
            }
        });
        jtxtLogin.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                jtxtLogin.setBackground(Color.getHSBColor(255 ,218 ,185));
            }
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                jtxtLogin.setBackground(Color.getHSBColor(55, 255, 55));
            }
        });
        jptxtSenha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                jptxtSenha.setBackground(Color.getHSBColor(255 ,218 ,185));
            }
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                jptxtSenha.setBackground(Color.getHSBColor(55, 255, 55));
            }
        });
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Saindo da Aplicação!");

                System.exit(0);
            }
        });
    }


}


