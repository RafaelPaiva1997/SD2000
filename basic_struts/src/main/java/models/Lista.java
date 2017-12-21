package models;

import models.pessoas.Pessoa;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Lista extends Model implements Serializable {

    private String tipo;
    private String nome;
    private int eleicao_id;

    public Lista() {
        super();
        table = "Listas";
    }

    public Lista(ResultSet resultSet) {
        super(resultSet);
        try {
            table = "Listas";
            tipo = resultSet.getString("tipo");
            nome = resultSet.getString("nome");
            eleicao_id = resultSet.getInt("eleicao_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getEleicao_id() {
        return eleicao_id;
    }

    public boolean setTipo(String tipo) {
        boolean flag;
        if (flag = Arrays.toString(new String[]{"Alunos", "Docentes", "Funcionarios"}).contains(tipo))
            this.tipo = tipo;
        return flag;
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

    public void setEleicao_id(int eleicao_id) {
        this.eleicao_id = eleicao_id;
    }

    @Override
    public String sqlInsert() {
        return sqlInsert("tipo, nome, eleicao_id", "'" + tipo + "','" + nome + "'," + eleicao_id);
    }

    @Override
    public String toString() {
        return "LISTA " + tipo.toUpperCase() + " ID:" + id + " Nome: " + nome + " ID_Eleição: " + eleicao_id + "\n";
    }

    @Override
    public boolean checkAdd(String s, Model model) {
        if (s.toLowerCase().equals("pessoas")) {
            Pessoa pessoa = (Pessoa) model;
            return tipo.equals("alunos") && pessoa.getTipo().equals("aluno") ||
                    tipo.equals("docentes") && pessoa.getTipo().equals("docente") ||
                    tipo.equals("funcionarios") && pessoa.getTipo().equals("funcionario");
        }
        return true;
    }
}
