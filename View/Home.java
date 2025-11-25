package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame{
    public JPanel principal;
    private JPanel painelTopo;
    private JPanel painelImagem;
    private JMenuBar jmBar;
    public JMenu jmCadastros;
    private JMenu jmAtendimentos;
    private JMenu jmFinanceiro;
    private JMenu jmOperacional;
    private JMenu jmSair;
    private JMenuItem jmiConsulta;
    private JMenuItem jmiBanhoTosa;
    public JMenuItem jmiVenda;
    public JMenuItem jmiPlano;
    private JLabel jlImagem;
    public JMenuItem jmiContasPagar;
    public JMenuItem jmiContasReceber;
    public JMenuItem jmiFluxoCaixa;
    private JMenuItem jmiAnimal;
    private JMenuItem jmiCliente;
    private JMenuItem jmiProduto;
    private JMenuItem jmiServico;
    private JMenuItem jmiFornecedor;
    private JMenuItem jmiUsuario;
    public JMenuItem jmiEstoque;
    private JMenuItem jmiAgenda;
    public JMenuItem jmiRelatorio;
    private JMenuItem jmiPedido;
    public JMenuItem jmiEmail;
    private JMenuItem jmiSair;

    public Home() {
        setTitle("Tela Principal");
        setSize(1200, 600);
        setContentPane(principal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o app ao clicar no "X"
        setLocationRelativeTo(null); // Centraliza a janela

    jmiCliente.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Clientes();
        }
    });

    jmiSair.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    });


        jmiAgenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new Agendamentos().setVisible(true);
                });
            }
        });

        jmiVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Vendas();
            }
        });
}
}
