package View;

import Controller.ClienteDAO;
import DAO.FabricaConexao;
import Model.ModelClientes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vendas extends  JFrame{
    private JPanel painelPrincipal;
    private JPanel jpTopo;
    private JPanel jpcentro;
    private JPanel jpbaixo;
    private JButton jbVenda;
    private JButton jbFinaliza;
    private JButton jbSair;
    private JPanel jpLateral;
    private JComboBox jcbVendedor;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox jcbCliente;
    private JRadioButton pixRadioButton;
    private JRadioButton cartãoCreditoRadioButton;
    private JRadioButton cartãoDébitoRadioButton;
    private JRadioButton boletoRadioButton;
    private JComboBox jcbProduto;
    private JTextField textField3;
    public JTextField textField4;
    private JTextField textField5;
    private JTextField textField7;
    private StringBuilder itensSelecionados;
    private JButton inserirButton;
    private JLabel jlabel;
    private JLabel jlTotal;

    double acumulado = 0.0;
    public Vendas() {
        setSize(1200, 700);
        setContentPane(painelPrincipal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        ListaClientes();
        preencherComboBox();
        itensSelecionados = new StringBuilder("Itens selecionados:<br>");

        jcbProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nomeSelecionado = (String) jcbProduto.getSelectedItem();

                if (nomeSelecionado != null) {
                    try {
                        String sql = "SELECT precoVenda FROM produtos WHERE descricao = ?";
                        PreparedStatement ps = FabricaConexao.conectar().prepareStatement(sql);
                        ps.setString(1, nomeSelecionado);
                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {
                            double preco = rs.getDouble("precoVenda");
                            textField4.setText(String.format("%.2f", preco));
                        } else {
                            textField4.setText("Não encontrado");
                        }

                        rs.close();
                        ps.close();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao buscar preço: " + ex.getMessage());
                    }
                }


            }
        });
//*****************************************************************************************************************

        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String item = (String) jcbProduto.getSelectedItem();
                String item2 = (String) textField4.getText();
                // Obter o valor do JTextField
                String valorTexto = textField4.getText();
                // Verifica se o campo está vazio
                if (!valorTexto.isEmpty()) {
                    // Substitui a vírgula por ponto
                    valorTexto = valorTexto.replace(",", ".");
                    // Converte para double
                    double valorDouble = Double.parseDouble(valorTexto);
                    // Soma ao valor acumulado
                    acumulado += valorDouble;
                    // Atualiza o JLabel com o valor acumulado
                    jlTotal.setText("Valor acumulado: " + acumulado);
                    // Limpa o JTextField para o próximo valor
                    textField4.setText("");
                    // Adiciona o novo item ao StringBuilder
                   // itensSelecionados.append(cliente).append("<br>");
                    itensSelecionados.append(item).append("<br>");
                    itensSelecionados.append(item2).append("<br>");
                    // itensSelecionados.append(valorTexto).append("<br>");
                    // Atualiza o JLabel (usando HTML para formatação)
                    jlabel.setText("<html>" + itensSelecionados.toString() + "</html>");
                   // jlTotal.setText("<html>" + result + "</html>");
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um valor.");

                }
            }
        });
    }

//*********************************************************************************************************************

  private void ListaClientes(){
      try {
          ModelClientes cliente = new ModelClientes();
          ClienteDAO cli = new ClienteDAO();
          ResultSet rs = cli.ListaClientes();
          ArrayList<String> listaClientes = new ArrayList<>();
          while (rs.next()) {
              cliente.setNome(rs.getNString("nome"));
              cliente.setCpf(rs.getNString("cpf"));
              listaClientes.add(cliente.getNome()+" - "+cliente.getCpf());
          }
          for (String percorrer : listaClientes) {
              jcbCliente.addItem(percorrer);
          }
      }catch (SQLException e) {
          throw new RuntimeException(e);
      }
  }


    private void preencherComboBox() {
        try {
            String sql = "SELECT descricao FROM produtos";
            PreparedStatement ps = FabricaConexao.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                jcbProduto.addItem(rs.getString("descricao"));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar produtos: " + e.getMessage());
        }
    }

    }



