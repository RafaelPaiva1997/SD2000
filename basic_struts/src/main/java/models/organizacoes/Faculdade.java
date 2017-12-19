package models.organizacoes;

import models.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Faculdade extends Model implements Serializable {

    private String nome;

    public Faculdade() {
        super();
        table = "Faculdades";
    }

    public Faculdade(ResultSet resultSet) {
        super(resultSet);
        try {
            table = "Faculdades";
            nome = resultSet.getString("nome");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean setNome(String nome) {
        boolean flag = true;
        if (lenghtMaior(nome, 0) &&
                isAlpha(nome))
            this.nome = nome;
        else
            flag = false;
        return flag;
    }

    @Override
    public String sqlInsert() {
        return sqlInsert("nome", "'" + nome + "'");
    }

    @Override
    public String toString() {
        return "ID: " + id + " Nome: " + nome + "\n";
    }
}
