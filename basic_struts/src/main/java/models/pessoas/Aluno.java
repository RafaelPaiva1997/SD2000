package models.pessoas;

import models.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Aluno extends Model implements Serializable {

    private int pessoa_id;
    private long numero_aluno;
    private String curso;

    public Aluno() {
        super();
        table = "Alunos";
    }

    public Aluno(int pessoa_id) {
        super();
        table = "Alunos";
        this.pessoa_id = pessoa_id;
    }

    public Aluno(ResultSet resultSet) {
        super(resultSet);
        try {
            table = "Alunos";
            pessoa_id = resultSet.getInt("pessoa_id");
            numero_aluno = resultSet.getLong("numero_aluno");
            curso = resultSet.getString("curso");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getNumero_aluno() {
        return numero_aluno;
    }

    public String getCurso() {
        return curso;
    }

    @Override
    public boolean setNumero_aluno(String numero_aluno) {
        boolean flag = true;
        if (lenghtIgual(numero_aluno, 10) &&
                isNumber(numero_aluno))
            this.numero_aluno = Integer.parseInt(numero_aluno);
        else
            flag = false;
        ;
        return flag;

    }

    @Override
    public boolean setCurso(String curso) {
        boolean flag;
        if (flag = (lenghtMaior(curso, 0) &&
                isAlpha(removeAccents(curso))))
            this.curso = curso;
        return flag;
    }

    @Override
    public String sqlInsert() {
        return sqlInsert("pessoa_id, numero_aluno, curso", pessoa_id + "," + numero_aluno + ",'" + curso + "'");
    }

    public String print() {
        return "Nº Aluno: " + numero_aluno + "\n" +
                "Curso: " + curso + "\n";
    }
}
