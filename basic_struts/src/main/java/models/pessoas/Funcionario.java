package models.pessoas;

import models.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Funcionario extends Model implements Serializable {

    private int pessoa_id;
    private String funcao;

    public Funcionario() {
        super();
        table = "Funcionarios";
    }

    public Funcionario(int pessoa_id) {
        super();
        table = "Funcionarios";
        this.pessoa_id = pessoa_id;
    }

    public String getFuncao() {
        return funcao;
    }

    public Funcionario(ResultSet resultSet) {
        super(resultSet);
        try {
            table = "Alunos";
            pessoa_id = resultSet.getInt("pessoa_id");
            funcao = resultSet.getString("funcao");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean setFuncao(String funcao) {
        boolean flag;
        if (flag = (lenghtMaior(funcao, 0) &&
                isAlpha(removeAccents(funcao))))
            this.funcao = funcao;
        return flag;
    }

    @Override
    public String sqlInsert() {
        return sqlInsert("pessoa_id, funcao", pessoa_id + "," + funcao + "'");
    }

    public String print() {
        return "Função: " + funcao + "\n";
    }
}
