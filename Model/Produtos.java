package Model;

import java.util.Objects;

public class Produtos {
    // Atributos (campos do produto)
    private int id;
    private String descricao;
    private double precoVenda;


    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
    @Override
    public String toString() {
        return this.descricao; // Deve retornar apenas a descrição
    }

}