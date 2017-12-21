package models.pessoas;

import models.Model;
import models.eleicoes.Eleicao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Pessoa extends Model implements Serializable {

    private String tipo;
    private String nome;
    private String username;
    private String password;
    private int departamento_id;
    private long telemovel;
    private String morada;
    private String codigo_postal;
    private String localidade;
    private long numero_cc;
    private Date validade_cc;
    private String genero;
    private Date data_nascimento;
    private boolean admin;

    public Pessoa() {
        super();
        table = "Pessoas";
        admin = true;
    }

    public Pessoa(ResultSet resultSet) {
        super(resultSet);
        try {
            table = "Pessoas";
            tipo = resultSet.getString("tipo");
            nome = resultSet.getString("nome");
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            departamento_id = resultSet.getInt("departamento_id");
            telemovel = resultSet.getLong("telemovel");
            morada = resultSet.getString("morada");
            codigo_postal = resultSet.getString("codigo_postal");
            localidade = resultSet.getString("localidade");
            numero_cc = resultSet.getLong("numero_cc");
            validade_cc = resultSet.getDate("validade_cc");
            genero = resultSet.getString("genero");
            data_nascimento = resultSet.getDate("data_nascimento");
            admin = resultSet.getBoolean("admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNome() {
        return nome;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public long getTelemovel() {
        return telemovel;
    }

    public String getMorada() {
        return morada;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public String getLocalidade() {
        return localidade;
    }

    public long getNumero_cc() {
        return numero_cc;
    }

    public Date getValidade_cc() {
        return validade_cc;
    }

    public String getGenero() {
        return genero;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public boolean setTipo(String tipo) {
        boolean flag;
        if (flag = Arrays.toString(new String[]{"Aluno", "Docente", "Funcionario"}).contains(tipo))
            this.tipo = tipo;
        return flag;
    }

    @Override
    public boolean setNome(String nome) {
        boolean flag ;
        if (flag = (lenghtMaior(nome, 0) &&
                isAlpha(removeAccents(nome))))
            this.nome = nome;
        return flag;
    }

    @Override
    public boolean setUsername(String username) {
        boolean flag = true;
        if (!username.contains("\\") && lenghtEntre(username, 8, 20))
            this.username = username;
        else
            flag = false;
        return flag;
    }

    @Override
    public boolean setPassword(String password) {
        boolean flag = true;
        if (lenghtEntre(password, 8, 20))
            this.password = password;
        else
            flag = false;
        return flag;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    @Override
    public boolean setTelemovel(String telemovel) {
        boolean flag = true;
        if (lenghtIgual(telemovel, 9) &&
                isNumber(telemovel))
            this.telemovel = Integer.parseInt(telemovel);
        else
            flag = false;
        return flag;
    }

    @Override
    public boolean setMorada(String morada) {
        boolean flag = true;
        if (lenghtMaior(morada, 0))
            this.morada = morada;
        else
            flag = false;
        return flag;
    }

    private boolean testeCodigo_postal(String codigo_postal) {
        if (!lenghtIgual(codigo_postal, 8) || codigo_postal.charAt(4) != '-')
            return false;
        for (int i : new int[]{0, 1, 2, 3, 5, 6, 7})
            if (Character.getNumericValue(codigo_postal.charAt(i)) == -1 ||
                    Character.getNumericValue(codigo_postal.charAt(i)) == -2 ||
                    Character.getNumericValue(codigo_postal.charAt(i)) > 9)
                return false;
        return true;
    }

    @Override
    public boolean setCodigo_postal(String codigo_postal) {
        boolean flag = true;
        if (testeCodigo_postal(codigo_postal))
            this.codigo_postal = codigo_postal;
        else
            flag = false;
        return flag;
    }

    @Override
    public boolean setLocalidade(String localidade) {
        boolean flag ;
        if (flag =(lenghtMaior(localidade, 0) &&
                isAlpha(removeAccents(localidade))))
            this.localidade=localidade;
        return flag;
    }

    @Override
    public boolean setNumero_cc(String numeroCC) {
        boolean flag = true;
        if (lenghtIgual(numeroCC, 8) &&
                isNumber(numeroCC))
            this.numero_cc = Integer.parseInt(numeroCC);
        else
            flag = false;
        return flag;
    }

    @Override
    public boolean setGenero(String genero) {
        boolean flag = true;
        if (genero.matches("Femenino") ||
                genero.matches("Masculino") ||
                genero.matches("Outro"))
            this.genero = genero;
        else
            flag = false;
        return flag;
    }

    public void setValidade_cc(Date validade_cc) {
        this.validade_cc = validade_cc;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }


    @Override
    public String sqlInsert() {
        return sqlInsert("admin," +
                        "tipo," +
                        "nome," +
                        "username," +
                        "password," +
                        "departamento_id," +
                        "telemovel," +
                        "morada," +
                        "codigo_postal," +
                        "localidade," +
                        "numero_cc," +
                        "validade_cc," +
                        "genero," +
                        "data_nascimento",
                        (admin ? 1 : 0) + "," +
                        "'" + tipo + "'," +
                        "'" + nome + "'," +
                        "'" + username + "'," +
                        "'" + password + "'," +
                        departamento_id + "," +
                        telemovel + "," +
                        "'" + morada + "'," +
                        "'" + codigo_postal + "'," +
                        "'" + localidade + "'," +
                        numero_cc + "," +
                        dateToSqlDate(validade_cc) + "," +
                        "'" + genero + "'," +
                        dateToSqlDate(data_nascimento));
    }

    @Override
    public String toString() {
        return tipo.toUpperCase() + " ID: " + id + " Nome: " + nome + " ID_Departamento: " + departamento_id + " Username: " + username + "\n";
    }

    public String print() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        return tipo.toUpperCase() + "\n" +
                "Nome: " + nome + "\n" +
                "Username: " + username + "\n" +
                "Password: " + password + "\n" +
                "ID Departamento: " + departamento_id + "\n" +
                "Nº Telemóvel: " + telemovel + "\n" +
                "Morada: " + morada + "\n" +
                "Código Postal: " + codigo_postal + "\n" +
                "Localidade: " + localidade + "\n" +
                "Numero CC: " + numero_cc + "\n" +
                "Validade CC: " + f.format(validade_cc) + "\n" +
                "Género: " + genero + "\n" +
                "Data Nascimento:" + f.format(data_nascimento) + "\n" +
                "Admin: " + admin + "\n";
    }

    public boolean check(Eleicao eleicao) {
        return eleicao.getTipo().equals("conselho geral") || eleicao.getTipo().equals("nucleo estudantes") && tipo.equals("aluno") && eleicao.getDepartamento_id() == departamento_id;
    }
}
