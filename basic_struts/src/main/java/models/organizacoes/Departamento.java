package models.organizacoes;

import models.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Departamento extends Model {

    private String nome;
    private int faculdade_id;

    public Departamento() {
        super();
        table = "Departamentos";
    }

    public Departamento(ResultSet resultSet) {
        super(resultSet);
        try {
            table = "Departamentos";
            nome = resultSet.getString("nome");
            faculdade_id = resultSet.getInt("faculdade_id");
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


    public void setFaculdade_id(int faculdade_id) {
        this.faculdade_id = faculdade_id;
    }

    public int getFaculdade_id() {
        return faculdade_id;
    }

    public String sqlInsert() {
        return sqlInsert("faculdade_id, nome", "'" + faculdade_id + "','" + nome + "'");
    }

    @Override
    public String toString() {
        return "ID: " + id + " ID_Faculdade : " + faculdade_id + " Nome: " + nome + "\n";
    }

}
