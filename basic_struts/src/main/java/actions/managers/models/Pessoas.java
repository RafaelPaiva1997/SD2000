package actions.managers.models;

import actions.ActionModel;
import com.sun.media.sound.InvalidFormatException;
import exceptions.EmptyQueryException;
import models.pessoas.Pessoa;
import rmi.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class Pessoas extends ActionModel {

    private int id;
    private String tipo;
    private String nome;
    private String nomeERROR;
    private String username;
    private String usernameERROR;
    private String password;
    private String passwordERROR;
    private int departamento_id;
    private String departamento;
    private String departamentoDefault;
    private String departamentoError;
    private String telemovel;
    private String telemovelERROR;
    private String morada;
    private String moradaERROR;
    private String codigo_postal;
    private String codigo_postalERROR;
    private String localidade;
    private String localidadeERROR;
    private String numero_cc;
    private String numero_ccERROR;
    private Date validade_cc;
    private String validade_ccERROR;
    private String genero;
    private String generoERROR;
    private Date data_nascimento;
    private String data_nascimentoERROR;
    private boolean admin;
    private String numero_aluno;
    private String numero_alunoERROR;
    private String curso;
    private String cursoERROR;
    private String cargo;
    private String cargoERROR;
    private String funcao;
    private String funcaoERROR;
    private ArrayList<String> departamentos;
    private ArrayList<Pessoa> pessoas;


    public Pessoas() {
        nomeERROR= "";
        usernameERROR= "";
        passwordERROR= "";
        departamento = "";
        departamentoDefault = "";
        departamentoError= "";
        telemovelERROR= "";
        moradaERROR= "";
        codigo_postalERROR= "";
        localidadeERROR= "";
        numero_ccERROR= "";
        validade_ccERROR= "";
        generoERROR= "";
        data_nascimentoERROR= "";
        numero_alunoERROR= "";
        cursoERROR= "";
        cargoERROR= "";
        funcaoERROR= "";

    }

    public String fillPessoas() {
        try {
            pessoas = new ArrayList(RMI.rmi.getMany("pessoas", "*", ""));
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            pessoas = new ArrayList<>();
            return SUCCESS;
        }
    }

    public String manage() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        return fillPessoas();
    }

    public String add() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        Pessoa pessoa = new Pessoa();

        if (!pessoa.setNome(nome))
            nomeERROR = "Por favor insira um nome só com letras!";

        if (!pessoa.setUsername(username))
            usernameERROR = "Por favor insira um username válido!";

        if (!pessoa.setPassword(password))
            passwordERROR = "Por favor insira um username válido!";

        if (!pessoa.setTelemovel(telemovel))
            telemovelERROR = "Por favor insira um numero de telemovel válido!";

        if (!pessoa.setMorada(morada))
            moradaERROR = "Por favor insira uma morada valida!";

        if (!pessoa.setCodigo_postal(codigo_postal))
            codigo_postalERROR = "Por favor insira um codigo postal válido!";

        if (!pessoa.setLocalidade(localidade))
            localidadeERROR = "Por favor insira uma localidade válida!";

        if (!pessoa.setNumero_cc(numero_cc))
            numero_ccERROR = "Por favor insira um numero de CC válida!";


        if (!pessoa.setGenero(genero))
            generoERROR = "Por favor insira um genero válido!";

        if (tipo.equals("Aluno")) {

            if (!pessoa.setCurso(curso))
                cursoERROR = "Por favor insira um curso só com letras!";

            if (!pessoa.setNumero_aluno(numero_aluno))
                numero_alunoERROR = "Por favor insira um numero de aluno com 10 numeros!";

        } else if (tipo.equals("Docente")){

            if (!pessoa.setCargo(cargo))
                cargoERROR = "Por favor insira um cargo só com letras!";

        }else{

            if (!pessoa.setFuncao(funcao))
                funcaoERROR = "Por favor insira uma funcao só com letras!";
        }

        try {

            pessoa.setDepartamento_id(RMI.rmi.get("faculdades",  "ID=" + departamento.split(" - ")[0]).getId());

            if (    nomeERROR.equals("") &&
                    usernameERROR.equals("") &&
                    passwordERROR.equals("") &&
                    moradaERROR.equals("")  &&
                    telemovelERROR.equals("") &&
                    codigo_postalERROR.equals("") &&
                    localidadeERROR.equals("") &&
                    numero_ccERROR.equals("") &&
                    validade_ccERROR.equals("") &&
                    generoERROR.equals("") &&
                    data_nascimentoERROR.equals("") &&
                    cursoERROR.equals("") &&
                    numero_alunoERROR.equals("") &&
                    cargoERROR.equals("") &&
                    funcaoERROR.equals("")) {

                RMI.rmi.insert(pessoa);
                return SUCCESS;
            }
            fillDepartamentos();
            return INPUT;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            departamentoError = "Erro ao seleccionar a faculdade!";
            fillPessoas();
            return INPUT;
        }
    }

    public String update() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            System.out.print("asd" + id);
            Pessoa pessoa = (Pessoa) RMI.rmi.get("pessoas", "ID=" + id);

            if (!pessoa.getNome().equals(nome) && !pessoa.setNome(nome))
                nomeERROR = "Por favor insira um nome só com letras!";

            if (!pessoa.getUsername().equals(username) && !pessoa.setUsername(username))
                usernameERROR = "Por favor insira um username válido!";

            if (!pessoa.getPassword().equals(password) && !pessoa.setPassword(password))
                passwordERROR = "Por favor insira um password válido!";

            if (!String.valueOf(pessoa.getTelemovel()).equals(telemovel) && !pessoa.setTelemovel(telemovel))
                telemovelERROR = "Por favor insira um numero de telemovel válido!";

            if (!pessoa.getMorada().equals(morada) && !pessoa.setMorada(morada))
                moradaERROR = "Por favor insira uma morada valida!";

            if (!pessoa.getCodigo_postal().equals(codigo_postal) && !pessoa.setCodigo_postal(codigo_postal))
                codigo_postalERROR = "Por favor insira um codigo postal válido!";

            if (!pessoa.getLocalidade().equals(localidade) && !pessoa.setLocalidade(localidade))
                localidadeERROR = "Por favor insira uma localidade válida!";

            if (!String.valueOf(pessoa.getNumero_cc()).equals(numero_cc) && !pessoa.setNumero_cc(numero_cc))
                numero_ccERROR = "Por favor insira um numero de CC válida!";

            if (!pessoa.getGenero().equals(genero) && !pessoa.setGenero(genero))
                generoERROR = "Por favor insira um genero válido!";



            try {
                pessoa.setDepartamento_id(RMI.rmi.get("faculdades", "ID=" + departamento.split(" - ")[0]).getId());

                if (    nomeERROR.equals("") &&
                        usernameERROR.equals("") &&
                        passwordERROR.equals("") &&
                        moradaERROR.equals("")  &&
                        telemovelERROR.equals("") &&
                        codigo_postalERROR.equals("") &&
                        localidadeERROR.equals("") &&
                        numero_ccERROR.equals("") &&
                        validade_ccERROR.equals("") &&
                        generoERROR.equals("") &&
                        data_nascimentoERROR.equals("") &&
                        cursoERROR.equals("") &&
                        numero_alunoERROR.equals("") &&
                        cargoERROR.equals("") &&
                        funcaoERROR.equals("")) {

                    RMI.rmi.insert(pessoa);
                    return SUCCESS;
                }

                pessoa.updateClear();
                fillDepartamentos();
                return INPUT;
            } catch (RemoteException | InvalidFormatException e) {
                addActionError(e.getMessage());
                e.printStackTrace();
                return "rmi-error";
            } catch (EmptyQueryException eqe) {
                departamentoError = "Erro ao seleccionar a faculdade!";
                fillDepartamentos();
                return INPUT;
            }
        } catch (RemoteException | InvalidFormatException | EmptyQueryException e) {
            addActionError(e.getMessage());
            e.printStackTrace();
            return "rmi-error";
        }
    }



    public String getDepartamentoById(int id) {
        for (String f : departamentos)
            if (id == Integer.parseInt(f.split(" - ")[0]))
                return f;
        return "";
    }

    public String fillDepartamentos() {
        try {
            departamentos = RMI.rmi.getOptions("departamentos", "");

            departamentoDefault = getDepartamentoById(departamento_id);

            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            addActionError("Não existem departamentos, por favor adicione um!");
            fillDepartamentos();
            return INPUT;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeERROR() {
        return nomeERROR;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumero_aluno() {
        return numero_aluno;
    }

    public void setNumero_aluno(String numero_aluno) {
        this.numero_aluno = numero_aluno;
    }

    public String getNumero_alunoERROR() {
        return numero_alunoERROR;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCursoERROR() {
        return cursoERROR;
    }

    public void setCursoERROR(String cursoERROR) {
        this.cursoERROR = cursoERROR;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargoERROR() {
        return cargoERROR;
    }

    public void setCargoERROR(String cargoERROR) {
        this.cargoERROR = cargoERROR;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getFuncaoERROR() {
        return funcaoERROR;
    }

    public void setFuncaoERROR(String funcaoERROR) {
        this.funcaoERROR = funcaoERROR;
    }

    public ArrayList<String> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(ArrayList<String> departamentos) {
        this.departamentos = departamentos;
    }

    public String getUsernameERROR() {
        return usernameERROR;
    }

    public void setUsernameERROR(String usernameERROR) {
        this.usernameERROR = usernameERROR;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordERROR() {
        return passwordERROR;
    }

    public void setPasswordERROR(String passwordERROR) {
        this.passwordERROR = passwordERROR;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamentoDefault() {
        return departamentoDefault;
    }

    public void setDepartamentoDefault(String departamentoDefault) {
        this.departamentoDefault = departamentoDefault;
    }

    public String getDepartamentoError() {
        return departamentoError;
    }

    public void setDepartamentoError(String departamentoError) {
        this.departamentoError = departamentoError;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public String getTelemovelERROR() {
        return telemovelERROR;
    }

    public void setTelemovelERROR(String telemovelERROR) {
        this.telemovelERROR = telemovelERROR;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getMoradaERROR() {
        return moradaERROR;
    }

    public void setMoradaERROR(String moradaERROR) {
        this.moradaERROR = moradaERROR;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getCodigo_postalERROR() {
        return codigo_postalERROR;
    }

    public void setCodigo_postalERROR(String codigo_postalERROR) {
        this.codigo_postalERROR = codigo_postalERROR;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getLocalidadeERROR() {
        return localidadeERROR;
    }

    public void setLocalidadeERROR(String localidadeERROR) {
        this.localidadeERROR = localidadeERROR;
    }

    public String getNumero_cc() {
        return numero_cc;
    }

    public void setNumero_cc(String numero_cc) {
        this.numero_cc = numero_cc;
    }

    public String getNumero_ccERROR() {
        return numero_ccERROR;
    }

    public Date getValidade_cc() {
        return validade_cc;
    }

    public void setValidade_cc(Date validade_cc) {
        this.validade_cc = validade_cc;
    }

    public String getValidade_ccERROR() {
        return validade_ccERROR;
    }

    public void setValidade_ccERROR(String validade_ccERROR) {
        this.validade_ccERROR = validade_ccERROR;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGeneroERROR() {
        return generoERROR;
    }

    public void setGeneroERROR(String generoERROR) {
        this.generoERROR = generoERROR;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getData_nascimentoERROR() {
        return data_nascimentoERROR;
    }


    public void setData_nascimentoERROR(String data_nascimentoERROR) {
        this.data_nascimentoERROR = data_nascimentoERROR;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
}
