package actions.managers.models;

import actions.ActionModel;
import com.sun.media.sound.InvalidFormatException;
import exceptions.EmptyQueryException;
import models.Data;
import models.eleicoes.ConselhoGeral;
import models.pessoas.Aluno;
import models.pessoas.Docente;
import models.pessoas.Funcionario;
import models.pessoas.Pessoa;
import rmi.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class Pessoas extends ActionModel {

    private int id;
    private String tipo;
    private String nome;
    private String nomeError;
    private String username;
    private String usernameError;
    private String password;
    private String passwordError;
    private int departamento_id;
    private String departamento;
    private String departamentoDefault;
    private String departamentoError;
    private String telemovel;
    private String telemovelError;
    private String morada;
    private String moradaError;
    private String codigo_postal;
    private String codigo_postalError;
    private String localidade;
    private String localidadeError;
    private String numero_cc;
    private String numero_ccError;
    private String validade_cc_ano;
    private String validade_cc_mes;
    private String validade_cc_dia;
    private Data validade_cc;
    private Date validade_cc_print;
    private String validade_ccError;
    private String genero;
    private String generoError;
    private String data_nascimento_ano;
    private String data_nascimento_mes;
    private String data_nascimento_dia;
    private Data data_nascimento;
    private Date data_nascimento_print;
    private String data_nascimentoError;
    private boolean admin;
    private String numero_aluno;
    private String numero_alunoError;
    private String curso;
    private String cursoError;
    private String cargo;
    private String cargoError;
    private String funcao;
    private String funcaoError;
    private ArrayList<String> departamentos;
    private ArrayList<Pessoa> pessoas;


    public Pessoas() {
        nomeError = "";
        usernameError = "";
        passwordError = "";
        departamento = "";
        departamentoDefault = "";
        departamentoError = "";
        telemovelError = "";
        moradaError = "";
        codigo_postalError = "";
        localidadeError = "";
        numero_ccError = "";
        validade_ccError = "";
        generoError = "";
        data_nascimentoError = "";
        numero_alunoError = "";
        cursoError = "";
        cargoError = "";
        funcaoError = "";
    }

    public String fillPessoas() {
        try {
            pessoas = new ArrayList(RMI.rmi.getMany("pessoas", "*", ""));
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-Error";
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

    public void getInfo(Pessoa pessoa) {
        nome = pessoa.getNome();
        username = pessoa.getUsername();
        password = pessoa.getPassword();
        departamento_id = pessoa.getDepartamento_id();
        telemovel = String.valueOf(pessoa.getTelemovel());
        morada = pessoa.getMorada();
        codigo_postal = pessoa.getCodigo_postal();
        localidade = pessoa.getLocalidade();
        numero_cc = String.valueOf(pessoa.getNumero_cc());
        validade_cc = new Data(pessoa.getValidade_cc());
        validade_cc_ano = String.valueOf(validade_cc.getAno());
        validade_cc_mes = String.valueOf(validade_cc.getMes());
        validade_cc_dia = String.valueOf(validade_cc.getDia());
        validade_cc_print = pessoa.getValidade_cc();
        genero = pessoa.getGenero();
        data_nascimento = new Data(pessoa.getData_nascimento());
        data_nascimento_ano = String.valueOf(data_nascimento.getAno());
        data_nascimento_mes = String.valueOf(data_nascimento.getMes());
        data_nascimento_dia = String.valueOf(data_nascimento.getDia());
        data_nascimento_print = pessoa.getData_nascimento();
        admin = pessoa.isAdmin();
    }

    public void getInfo(Pessoa pessoa, Aluno aluno) {
        getInfo(pessoa);
        numero_aluno = String.valueOf(aluno.getNumero_aluno());
        curso = aluno.getCurso();
    }

    public void getInfo(Pessoa pessoa, Docente docente) {
        getInfo(pessoa);
        cargo = docente.getCargo();
    }

    public void getInfo(Pessoa pessoa, Funcionario funcionario) {
        getInfo(pessoa);
        funcao = funcionario.getFuncao();
    }

    public String fetchPessoa() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        if (tipo.equals("Aluno")) {
            try {
                Pessoa pessoa = (Pessoa) RMI.rmi.get("pessoas", "ID=" + id);
                Aluno aluno = (Aluno) RMI.rmi.get("alunos", "pessoa_id=" + id);
                getInfo(pessoa, aluno);
                return SUCCESS;
            } catch (RemoteException | EmptyQueryException | InvalidFormatException e) {
                addActionMessage(e.getMessage());
                return "rmi-Error";
            }
        } else if (tipo.equals("Docente")) {
            try {
                Pessoa pessoa = (Pessoa) RMI.rmi.get("pessoas", "ID=" + id);
                Docente docente = (Docente) RMI.rmi.get("docentes", "pessoa_id=" + id);
                getInfo(pessoa, docente);
                return SUCCESS;
            } catch (RemoteException | EmptyQueryException | InvalidFormatException e) {
                addActionMessage(e.getMessage());
                return "rmi-Error";
            }
        } else if (tipo.equals("Funcionario")) {
            try {
                Pessoa pessoa = (Pessoa) RMI.rmi.get("pessoas", "ID=" + id);
                Funcionario funcionario = (Funcionario) RMI.rmi.get("funcionarios", "pessoa_id=" + id);
                getInfo(pessoa, funcionario);
                return SUCCESS;
            } catch (RemoteException | EmptyQueryException | InvalidFormatException e) {
                addActionMessage(e.getMessage());
                return "rmi-Error";
            }
        }

        fillPessoas();
        return INPUT;
    }

    public String add() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        Pessoa pessoa = new Pessoa();

        if (!pessoa.setNome(nome))
            nomeError = "Por favor insira um nome só com letras!";

        if (!pessoa.setUsername(username))
            usernameError = "Por favor insira um username válido!";

        if (!pessoa.setPassword(password))
            passwordError = "Por favor insira um username válido!";

        if (!pessoa.setTelemovel(telemovel))
            telemovelError = "Por favor insira um numero de telemovel válido!";

        if (!pessoa.setMorada(morada))
            moradaError = "Por favor insira uma morada valida!";

        if (!pessoa.setCodigo_postal(codigo_postal))
            codigo_postalError = "Por favor insira um codigo postal válido!";

        if (!pessoa.setLocalidade(localidade))
            localidadeError = "Por favor insira uma localidade válida!";

        if (!pessoa.setNumero_cc(numero_cc))
            numero_ccError = "Por favor insira um numero de CC válida!";

        if (!pessoa.setGenero(genero))
            generoError = "Por favor insira um genero válido!";

        try {
            validade_cc = new Data(validade_cc_ano, validade_cc_mes, validade_cc_dia);
        } catch (NumberFormatException e) {
            validade_ccError = "Por favor insira uma data de início só com números!";
        }
        if (validade_ccError.equals("") && !validade_cc.test())
            validade_ccError = "Por favor insira uma data de início válida!";
        pessoa.setValidade_cc(validade_cc.export());

        try {
            data_nascimento = new Data(data_nascimento_ano, data_nascimento_mes, data_nascimento_dia);
        } catch (NumberFormatException e) {
            data_nascimentoError = "Por favor insira uma data de início só com números!";
        }
        if (data_nascimentoError.equals("") && !data_nascimento.test())
            data_nascimentoError = "Por favor insira uma data de início válida!";
        pessoa.setData_nascimento(data_nascimento.export());
        
        pessoa.setAdmin(admin);

        Aluno aluno = new Aluno();
        Docente docente = new Docente();
        Funcionario funcionario = new Funcionario();

        if (tipo.equals("Aluno")) {
            if (!aluno.setCurso(curso))
                cursoError = "Por favor insira um curso só com letras!";

            if (!aluno.setNumero_aluno(numero_aluno))
                numero_alunoError = "Por favor insira um numero de aluno com 10 numeros!";
        } else if (tipo.equals("Docente")) {
            if (!docente.setCargo(cargo))
                cargoError = "Por favor insira um cargo só com letras!";
        } else if (!funcionario.equals("Funcionario")) {
            if (!funcionario.setFuncao(funcao))
                funcaoError = "Por favor insira uma funcao só com letras!";
        }

        try {

            pessoa.setDepartamento_id(RMI.rmi.get("faculdades", "ID=" + departamento.split(" - ")[0]).getId());

            if (nomeError.equals("") &&
                    usernameError.equals("") &&
                    passwordError.equals("") &&
                    moradaError.equals("") &&
                    telemovelError.equals("") &&
                    codigo_postalError.equals("") &&
                    localidadeError.equals("") &&
                    numero_ccError.equals("") &&
                    validade_ccError.equals("") &&
                    generoError.equals("") &&
                    data_nascimentoError.equals("") &&
                    cursoError.equals("") &&
                    numero_alunoError.equals("") &&
                    cargoError.equals("") &&
                    funcaoError.equals("")) {
                RMI.rmi.insert(pessoa);

                if (tipo.equals("Aluno")) {
                    aluno.setId(RMI.rmi.get("pessoas", "username=" + pessoa.getUsername()).getId());
                    RMI.rmi.insert(aluno);
                } else if (tipo.equals("Docente")) {
                    docente.setId(RMI.rmi.get("pessoas", "username=" + pessoa.getUsername()).getId());
                    RMI.rmi.insert(docente);
                } else if (tipo.equals("Funcionario")) {
                    funcionario.setId(RMI.rmi.get("pessoas", "username=" + pessoa.getUsername()).getId());
                    RMI.rmi.insert(funcionario);
                }

                return SUCCESS;
            }

            fillDepartamentos();
            return INPUT;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-Error";
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
            Pessoa pessoa = (Pessoa) RMI.rmi.get("pessoas", "ID=" + id);

            if (!pessoa.getNome().equals(nome) && !pessoa.setNome(nome))
                nomeError = "Por favor insira um nome só com letras!";

            if (!pessoa.getUsername().equals(username) && !pessoa.setUsername(username))
                usernameError = "Por favor insira um username válido!";

            if (!pessoa.getPassword().equals(password) && !pessoa.setPassword(password))
                passwordError = "Por favor insira um password válido!";

            if (!String.valueOf(pessoa.getTelemovel()).equals(telemovel) && !pessoa.setTelemovel(telemovel))
                telemovelError = "Por favor insira um numero de telemovel válido!";

            if (!pessoa.getMorada().equals(morada) && !pessoa.setMorada(morada))
                moradaError = "Por favor insira uma morada valida!";

            if (!pessoa.getCodigo_postal().equals(codigo_postal) && !pessoa.setCodigo_postal(codigo_postal))
                codigo_postalError = "Por favor insira um codigo postal válido!";

            if (!pessoa.getLocalidade().equals(localidade) && !pessoa.setLocalidade(localidade))
                localidadeError = "Por favor insira uma localidade válida!";

            if (!String.valueOf(pessoa.getNumero_cc()).equals(numero_cc) && !pessoa.setNumero_cc(numero_cc))
                numero_ccError = "Por favor insira um numero de CC válida!";

            if (!pessoa.getGenero().equals(genero) && !pessoa.setGenero(genero))
                generoError = "Por favor insira um genero válido!";


            try {
                pessoa.setDepartamento_id(RMI.rmi.get("faculdades", "ID=" + departamento.split(" - ")[0]).getId());

                if (nomeError.equals("") &&
                        usernameError.equals("") &&
                        passwordError.equals("") &&
                        moradaError.equals("") &&
                        telemovelError.equals("") &&
                        codigo_postalError.equals("") &&
                        localidadeError.equals("") &&
                        numero_ccError.equals("") &&
                        validade_ccError.equals("") &&
                        generoError.equals("") &&
                        data_nascimentoError.equals("") &&
                        cursoError.equals("") &&
                        numero_alunoError.equals("") &&
                        cargoError.equals("") &&
                        funcaoError.equals("")) {

                    RMI.rmi.insert(pessoa);
                    return SUCCESS;
                }

                pessoa.updateClear();
                fillDepartamentos();
                return INPUT;
            } catch (RemoteException | InvalidFormatException e) {
                addActionError(e.getMessage());
                e.printStackTrace();
                return "rmi-Error";
            } catch (EmptyQueryException eqe) {
                departamentoError = "Erro ao seleccionar a faculdade!";
                fillDepartamentos();
                return INPUT;
            }
        } catch (RemoteException | InvalidFormatException | EmptyQueryException e) {
            addActionError(e.getMessage());
            e.printStackTrace();
            return "rmi-Error";
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
            return "rmi-Error";
        } catch (EmptyQueryException eqe) {
            addActionError("Não existem departamentos, por favor adicione um!");
            fillDepartamentos();
            return INPUT;
        }
    }

    public String fetchDepartamentos() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        return fillDepartamentos();
    }

    public String remove() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            RMI.rmi.delete("lista_pessoas", "pessoa_id=" + id);
            RMI.rmi.delete("pessoas", "ID=" + id);
            return SUCCESS;
        } catch (RemoteException e) {
            addActionError(e.getMessage());
            return "rmi-Error";
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

    public String getNomeError() {
        return nomeError;
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

    public String getNumero_alunoError() {
        return numero_alunoError;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCursoError() {
        return cursoError;
    }

    public void setCursoError(String cursoError) {
        this.cursoError = cursoError;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargoError() {
        return cargoError;
    }

    public void setCargoError(String cargoError) {
        this.cargoError = cargoError;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getFuncaoError() {
        return funcaoError;
    }

    public void setFuncaoError(String funcaoError) {
        this.funcaoError = funcaoError;
    }

    public ArrayList<String> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(ArrayList<String> departamentos) {
        this.departamentos = departamentos;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
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

    public String getTelemovelError() {
        return telemovelError;
    }

    public void setTelemovelError(String telemovelError) {
        this.telemovelError = telemovelError;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getMoradaError() {
        return moradaError;
    }

    public void setMoradaError(String moradaError) {
        this.moradaError = moradaError;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getCodigo_postalError() {
        return codigo_postalError;
    }

    public void setCodigo_postalError(String codigo_postalError) {
        this.codigo_postalError = codigo_postalError;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getLocalidadeError() {
        return localidadeError;
    }

    public void setLocalidadeError(String localidadeError) {
        this.localidadeError = localidadeError;
    }

    public String getNumero_cc() {
        return numero_cc;
    }

    public void setNumero_cc(String numero_cc) {
        this.numero_cc = numero_cc;
    }

    public String getNumero_ccError() {
        return numero_ccError;
    }

    public String getValidade_ccError() {
        return validade_ccError;
    }

    public void setValidade_ccError(String validade_ccError) {
        this.validade_ccError = validade_ccError;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getGeneroError() {
        return generoError;
    }

    public void setGeneroError(String generoError) {
        this.generoError = generoError;
    }

    public String getData_nascimentoError() {
        return data_nascimentoError;
    }


    public void setData_nascimentoError(String data_nascimentoError) {
        this.data_nascimentoError = data_nascimentoError;
    }

    public String getValidade_cc_ano() {
        return validade_cc_ano;
    }

    public void setValidade_cc_ano(String validade_cc_ano) {
        this.validade_cc_ano = validade_cc_ano;
    }

    public String getValidade_cc_mes() {
        return validade_cc_mes;
    }

    public void setValidade_cc_mes(String validade_cc_mes) {
        this.validade_cc_mes = validade_cc_mes;
    }

    public String getValidade_cc_dia() {
        return validade_cc_dia;
    }

    public void setValidade_cc_dia(String validade_cc_dia) {
        this.validade_cc_dia = validade_cc_dia;
    }

    public ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void setNomeError(String nomeError) {
        this.nomeError = nomeError;
    }

    public void setNumero_ccError(String numero_ccError) {
        this.numero_ccError = numero_ccError;
    }

    public String getvalidade_cc_ano() {
        return validade_cc_ano;
    }

    public void setvalidade_cc_ano(String validade_cc_ano) {
        this.validade_cc_ano = validade_cc_ano;
    }

    public String getvalidade_cc_mes() {
        return validade_cc_mes;
    }

    public void setvalidade_cc_mes(String validade_cc_mes) {
        this.validade_cc_mes = validade_cc_mes;
    }

    public String getvalidade_cc_dia() {
        return validade_cc_dia;
    }

    public void setvalidade_cc_dia(String validade_cc_dia) {
        this.validade_cc_dia = validade_cc_dia;
    }

    public Data getValidade_cc() {
        return validade_cc;
    }

    public void setValidade_cc(Data validade_cc) {
        this.validade_cc = validade_cc;
    }

    public Date getValidade_cc_print() {
        return validade_cc_print;
    }

    public void setValidade_cc_print(Date validade_cc_print) {
        this.validade_cc_print = validade_cc_print;
    }

    public String getData_nascimento_ano() {
        return data_nascimento_ano;
    }

    public void setData_nascimento_ano(String data_nascimento_ano) {
        this.data_nascimento_ano = data_nascimento_ano;
    }

    public String getData_nascimento_mes() {
        return data_nascimento_mes;
    }

    public void setData_nascimento_mes(String data_nascimento_mes) {
        this.data_nascimento_mes = data_nascimento_mes;
    }

    public String getData_nascimento_dia() {
        return data_nascimento_dia;
    }

    public void setData_nascimento_dia(String data_nascimento_dia) {
        this.data_nascimento_dia = data_nascimento_dia;
    }

    public Data getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Data data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public Date getData_nascimento_print() {
        return data_nascimento_print;
    }

    public void setData_nascimento_print(Date data_nascimento_print) {
        this.data_nascimento_print = data_nascimento_print;
    }

    public void setNumero_alunoError(String numero_alunoError) {
        this.numero_alunoError = numero_alunoError;
    }
}
