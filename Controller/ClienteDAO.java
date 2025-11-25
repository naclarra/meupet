package Controller;

import DAO.FabricaConexao;
import Model.ModelClientes;
import java.sql.PreparedStatement;

import java.sql.*;

public class ClienteDAO {

    public ResultSet ListaClientes() {
        String sql = "SELECT * FROM Clientes";
        try {
        Connection conn = FabricaConexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro ao validar login: " + e.getMessage());
        }
        return null;
    }

    public void  inserirCliente(ModelClientes clientes){
        String sql = "INSERT INTO clientes (nome, cpf, endereco, telefone, email, sexo, dataNascimento) Values (?, ?, ?, ?, ?, ?, ?)";
        System.out.println(" Sucesso!!");
        System.out.println(clientes.getNome());
        try
                (Connection conn = FabricaConexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, clientes.getNome());
            stmt.setString(2, clientes.getCpf());
            stmt.setString(3, clientes.getEndereco());
            stmt.setString(4, clientes.getTelefone());
            stmt.setString(5, clientes.getEmail());
            stmt.setString(6, clientes.getSexo());
            stmt.setString(7, clientes.getDataNascimento());
            //int linhasAfetadas = stmt.executeUpdate();
            stmt.executeUpdate();
            //System.out.println("dados gravados com Sucesso!!"+linhasAfetadas);
            // conn.close();
        }catch(SQLException e){
            e.getMessage();
        }
    }


    public void updateClientes(ModelClientes c) {
        // Definir a query SQL de atualização
        String query = "UPDATE clientes SET nome = ?,cpf = ?, endereco = ?, telefone = ?, email = ?, sexo = ?, dataNascimento = ? WHERE id = ?";



        // Criar a conexão e preparar o statement
        try (Connection connection = FabricaConexao.conectar();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Definir os parâmetros do PreparedStatement
            pstmt.setString(1, c.getNome());
            pstmt.setString(2, c.getCpf());
            pstmt.setString(3, c.getEndereco());
            pstmt.setString(4, c.getTelefone());
            pstmt.setString(5, c.getEmail());
            pstmt.setString(6, c.getSexo());
            pstmt.setString(7, c.getDataNascimento());
            pstmt.setString(8, c.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }


}
