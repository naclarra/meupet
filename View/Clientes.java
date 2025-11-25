package View;

import Controller.ClienteDAO;
import DAO.FabricaConexao;
import Model.ModelClientes;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;
import java.util.Vector;

public class Clientes extends JFrame{
    public JPanel painelPrincipal;
    private JPanel painelTopo;
    private JPanel painelBotoes;
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JTextField txtCpf;
    private JTextField txtDataN;
    private JTextField txtEmail;
    private JTextField txtEnd;
    private JTextField txtSexo;
    private JButton novoButton;
    private JButton fecharButton;
    private JButton salvarButton;
    private JButton editarButton;
    private JButton deletarButton;
    private JPanel painelTabela;
    private JTable tabelaClientes;
    private JTextField textId;


    public Clientes(){

        setTitle("Exemplo de JTable");
        setSize(1000, 800);
        setContentPane(painelPrincipal);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);

        carregarDados();
//******** BOTAO NOVO *******************************************************************
        novoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                habilita();
                txtNome.requestFocus();

            }
        });
//******** BOTAO FECHAR *******************************************************************
        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           dispose();
            }
        });
//******** TXTNOME *******************************************************************
        txtNome.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                txtNome.setBackground(Color.getHSBColor(206, 209, 218));
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                txtNome.setBackground(Color.getHSBColor(170, 191, 222));
            }
        });
//******** BOTAO SALVAR *******************************************************************
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             String nome = txtNome.getText().toString();
             String cpf = txtCpf.getText().toString();
             String telefone = txtTelefone.getText().toString();
             String dataN = txtDataN.getText().toString();
             String end = txtEnd.getText().toString();
             String email = txtEmail.getText().toString();
             String sexo = txtSexo.getText().toString();
                ModelClientes clientes = new ModelClientes();
                    clientes.setNome(nome);
                    clientes.setCpf(cpf);
                    clientes.setEndereco(end);
                    clientes.setTelefone(telefone);
                    clientes.setEmail(email);
                    clientes.setSexo(sexo);
                    clientes.setDataNascimento(dataN);
                ClienteDAO cliDAO = new ClienteDAO();
                    cliDAO.inserirCliente(clientes);
                //limpaCampos();
               // desabilita();
                novoButton.setEnabled(true);

            }
        });
//******** BOTAO FECHAR *******************************************************************
        fecharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
//******** CLIQUE NA LINHA DA TABELA *******************************************************************
        tabelaClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                habilita();
                if (!e.getValueIsAdjusting()) {
                    int linhaSelecionada = tabelaClientes.getSelectedRow();
                    if (linhaSelecionada != -1) {
                        // Preencher os campos de texto com os dados da linha selecionada
                        textId.setText(tabelaClientes.getValueAt(linhaSelecionada, 0).toString());
                        txtNome.setText(tabelaClientes.getValueAt(linhaSelecionada, 1).toString());
                        txtCpf.setText(tabelaClientes.getValueAt(linhaSelecionada, 2).toString());
                        txtEnd.setText(tabelaClientes.getValueAt(linhaSelecionada, 3).toString());
                        txtTelefone.setText(tabelaClientes.getValueAt(linhaSelecionada, 4).toString());
                        txtEmail.setText(tabelaClientes.getValueAt(linhaSelecionada, 5).toString());
                        txtSexo.setText(tabelaClientes.getValueAt(linhaSelecionada, 6).toString());
                        txtDataN.setText(tabelaClientes.getValueAt(linhaSelecionada, 7).toString());
                    }
                }
            }
        });
//******** BOTAR ALTERAR/EDITAR *******************************************************************
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textId.getText().toString();
                String nome = txtNome.getText().toString();
                String cpf = txtCpf.getText().toString();
                String telefone = txtTelefone.getText().toString();
                String dataN = txtDataN.getText().toString();
                String end = txtEnd.getText().toString();
                String email = txtEmail.getText().toString();
                String sexo = txtSexo.getText().toString();
                ModelClientes clientes = new ModelClientes();
                clientes.setId(id);
                clientes.setNome(nome);
                clientes.setCpf(cpf);
                clientes.setEndereco(end);
                clientes.setTelefone(telefone);
                clientes.setEmail(email);
                clientes.setSexo(sexo);
                clientes.setDataNascimento(dataN);
                ClienteDAO cliDAO = new ClienteDAO();
                cliDAO.updateClientes(clientes);
                carregarDados();
            }
        });
    }
    public void limpaCampos() {
        txtNome.setText("");
        txtTelefone.setText("");
        txtCpf.setText("");
        txtDataN.setText("");
        txtEmail.setText("");
        txtEnd.setText("");
        txtSexo.setText("");
    }
    public void desabilita(){
        txtNome.setEnabled(false);
        txtTelefone.setEnabled(false);
        txtCpf.setEnabled(false);
        txtDataN.setEnabled(false);
        txtEmail.setEnabled(false);
        txtEnd.setEnabled(false);
        txtSexo.setEnabled(false);
        editarButton.setEnabled(false);
        deletarButton.setEnabled(false);
        salvarButton.setEnabled(false);
    }
    public void habilita(){
        txtNome.setEnabled(true);
        txtTelefone.setEnabled(true);
        txtCpf.setEnabled(true);
        txtDataN.setEnabled(true);
        txtEmail.setEnabled(true);
        txtEnd.setEnabled(true);
        txtSexo.setEnabled(true);
        editarButton.setEnabled(true);
        deletarButton.setEnabled(true);
        salvarButton.setEnabled(true);
        novoButton.setEnabled(false);
    }
//******** PREENCHE TABELA *******************************************************************
    public void carregarDados() {
        // Conectar ao banco de dados e obter os dados
        try (Connection conn =FabricaConexao.conectar()) {
            String query = "SELECT id,nome, cpf, endereco, telefone, email, sexo, dataNascimento FROM clientes";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Obter as colunas da tabela
            Vector<String> colunas = new Vector<>();
            colunas.add("Id");
            colunas.add("Nome");
            colunas.add("CPF");
            colunas.add("Endereço");
            colunas.add("Telefone");
            colunas.add("Email");
            colunas.add("Sexo");
            colunas.add("Data de Nascimento");

            // Obter os dados da tabela
            Vector<Vector<Object>> dados = new Vector<>();
            while (rs.next()) {
                Vector<Object> linha = new Vector<>();
                linha.add(rs.getString("id"));
                linha.add(rs.getString("nome"));
                linha.add(rs.getString("cpf"));
                linha.add(rs.getString("endereco"));
                linha.add(rs.getString("telefone"));
                linha.add(rs.getString("email"));
                linha.add(rs.getString("sexo"));
                linha.add(rs.getString("dataNascimento"));
                dados.add(linha);
            }

            // Criar a tabela com os dados e colunas
            tabelaClientes.setModel(new DefaultTableModel(dados, colunas));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
