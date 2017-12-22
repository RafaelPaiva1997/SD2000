package models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Model implements Serializable {

    protected int id;
    protected String table;
    protected ArrayList<String[]> update;

    public Model() {
        update = new ArrayList<>();
    }

    public Model(ResultSet resultSet) {
        try {
            id = resultSet.getInt("ID");
            update = new ArrayList<>();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTable() {
        return table;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String sqlInsert();

    public String sqlInsert(String dataTypes, String data) {
        return "INSERT INTO " + table + "(" + dataTypes + ") VALUES (" + data + ")";
    }

    public String sqlUpdate() throws EmptyStackException {
        if (update.isEmpty())
            throw new EmptyStackException();

        StringBuilder s = new StringBuilder();
        for (String[] u : update)
            s.append(u[0] + "=" + u[1] + ",");

        return "UPDATE " + table + " SET " + s.toString().substring(0, s.length() - 1) + " WHERE ID = " + id;
    }

    public String sqlDelete() {
        return "DELETE FROM " + table + " WHERE ID = " + id;
    }

    public String connect(Model model) {
        return "INSERT INTO " + table.substring(0, table.length() - 1) + "_" + model.table + " VALUES (" + id + "," + model.id + ")";
    }

    public String disconnect(Model model) {
        return "DELETE FROM " + table.substring(0, table.length() - 1) + "_" + model.table + " WHERE " + table.substring(0, table.length() - 1).toLowerCase() + "_id = " + id + " && " + model.table.substring(0, model.table.length() - 1).toLowerCase() + "_id = " + model.id;
    }

    protected boolean lenghtIgual(String s, int i) {
        return s.length() == i;
    }

    protected boolean lenghtMaior(String s, int i) {
        return s.length() > i;
    }

    protected boolean lenghtEntre(String s, int a, int b) {
        return s.length() >= a && s.length() <= b;
    }

    protected boolean isAlpha(String s) {
        Pattern p = Pattern.compile("^[ A-Za-z]+$");
        Matcher m = p.matcher(s);
        return !s.contains(";") && !s.contains("|") && m.matches();
    }

    protected boolean isNumber(String s) {
        return s.matches("^[0-9]*$");
    }

    public String removeAccents(String text) {
        return text == null ? null :
                Normalizer.normalize(text, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    protected String dateToSqlDate(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + f.format(date) + "'";
    }

    protected String dateToSqlDateTime(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "'" + f.format(date) + "'";
    }

    public boolean checkAdd(String s, Model model) {
        return true;
    }

    public void addProtection(String param) {
        for (String[] u : update)
            if (u[0].equals(param))
                update.remove(u);
    }

    public void addString(String param, String value) {
        addProtection(param);
        update.add(new String[]{param, "'" + value + "'"});
    }

    public void addValue(String param, String value) {
        addProtection(param);
        update.add(new String[]{param, value});
    }

    public boolean update(String param, String value) {
        boolean flag;
        switch (param) {
            case "faculdade_id":
            case "departamento_id":
            case "eleicao_id":
                addValue(param, value);
                flag = true;
                break;
            case "tipo":
                if (flag = setTipo(value))
                    addString(param, value);
                break;
            case "nome":
                if (flag = setNome(value))
                    addString(param, value);
                break;
            case "username":
                if (flag = setUsername(value))
                    addString(param, value);
                break;
            case "password":
                if (flag = setPassword(value))
                    addString(param, value);
                break;
            case "telemovel":
                if (flag = setTelemovel(value))
                    addValue(param, value);
                break;
            case "morada":
                if (flag = setMorada(value))
                    addString(param, value);
                break;
            case "codigo_postal":
                if (flag = setCodigo_postal(value))
                    addString(param, value);
                break;
            case "localidade":
                if (flag = setLocalidade(value))
                    addString(param, value);
                break;
            case "numero_cc":
                if (flag = setNumero_cc(value))
                    addValue(param, value);
                break;
            case "genero":
                if (flag = setGenero(value))
                    addString(param, value);
                break;
            case "numero_aluno":
                if (flag = setNumero_aluno(value))
                    addValue(param, value);
                break;
            case "curso":
                if (flag = setCurso(value))
                    addString(param, value);
                break;
            case "cargo":
                if (flag = setCargo(value))
                    addString(param, value);
                break;
            case "funcao":
                if (flag = setFuncao(value))
                    addString(param, value);
                break;
            case "titulo":
                if (flag = setTitulo(value))
                    addString(param, value);
                break;
            case "descricao":
                if (flag = setDescricao(value))
                    addString(param, value);
                break;
            case "validade_cc":
            case "data_nascimento":
            case "data_inicio":
            case "data_fim":
                flag = true;
                addString(param, value);
                break;
            case "admin":
                flag = true;
                if (value.equals("true"))
                    addValue(param, "1");
                else
                    addValue(param, "0");
                break;
            default:
                flag = false;
                break;
        }
        return flag;
    }

    public void updateClear() {
        update.clear();
    }

    public void setAdmin(boolean admin) {

    }

    public boolean setTipo(String tipo) {
        return false;
    }

    public boolean setNome(String nome) {
        return false;
    }

    public boolean setUsername(String username) {
        return false;
    }

    public boolean setPassword(String password) {
        return false;
    }

    public boolean setTelemovel(String telemovel) {
        return false;
    }

    public boolean setMorada(String morada) {
        return false;
    }

    public boolean setCodigo_postal(String codigo_postal) {
        return false;
    }

    public boolean setLocalidade(String localidade) {
        return false;
    }

    public boolean setNumero_cc(String numero_cc) {
        return false;
    }

    public boolean setGenero(String genero) {
        return false;
    }

    public boolean setNumero_aluno(String numero_aluno) {
        return false;
    }

    public boolean setCurso(String curso) {
        return false;
    }

    public boolean setFuncao(String funcao) {
        return false;
    }

    public boolean setCargo(String cargo) {
        return false;
    }

    public boolean setTitulo(String titulo) {
        return false;
    }

    public boolean setDescricao(String descricao) {
        return false;
    }
}
