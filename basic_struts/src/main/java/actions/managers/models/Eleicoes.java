package actions.managers.models;

import actions.ActionModel;
import com.sun.media.sound.InvalidFormatException;
import exceptions.EmptyQueryException;
import models.Data;
import models.eleicoes.ConselhoGeral;
import models.eleicoes.Eleicao;
import models.eleicoes.NucleoEstudantes;
import rmi.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class Eleicoes extends ActionModel {

    private int id;
    private String tipo;
    private String tipoError;
    private String titulo;
    private String tituloError;
    private String descricao;
    private String descricaoError;
    private Data data_inicio;
    private String data_inicioError;
    private Data data_fim;
    private String data_fimError;
    private int departamento_id;
    private String departamento;
    private String departamentoDefault;
    private String departamentoError;
    private boolean finished;
    private ArrayList<String> departamentos;
    private ArrayList<Eleicao> eleicoes;

    public Eleicoes() {
        tipo = "";
        departamento = "";
        departamentoDefault = "";
        tipoError = "";
        tituloError = "";
        descricaoError = "";
        data_inicioError = "";
        data_fimoError = "";
        departamentoError = "";
        data_inicio = new Data();
        data_fim = new Data();
        departamentos = new ArrayList<>();
        eleicoes = new ArrayList<>();
    }

    public String fillEleicoes() {
        try {
            eleicoes = new ArrayList(RMI.rmi.getMany("eleicaos", ""));
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            eleicoes = new ArrayList<>();
            return SUCCESS;
        }
    }

    public String manage() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        return fillEleicoes();
    }

    public String add() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        Eleicao eleicao;

        if (tipo.equals("Conselho Geral"))
            eleicao = new ConselhoGeral();
        else
            eleicao = new NucleoEstudantes();

        if (!eleicao.setTitulo(titulo))
            tituloError = "Por favor insira um título só com letras!";

        if (!eleicao.setDescricao(descricao))
            descricaoError = "Por favor insira uma descrição só com letras!";

        if (!data_fim.test())
            data_inicioError = "Por favor insira uma data de início válida!";

        if (!data_fim.test())
            data_fimError = "Por favor insira uma data de fim válida!";

        try {
            if (tipo.equals("Nucleo Estudantes") &&)
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

    public void getInfo(Eleicao eleicao) {
        titulo = eleicao.getTitulo();
        descricao = eleicao.getDescricao();
        data_inicio = new Data(eleicao.getData_inicio());
        data_fim = new Data(eleicao.getData_fim());
        finished = eleicao.isFinished();
    }

    public String fetchDepartamentos() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        if (tipo.equals("Nucleo Estudantes"))
            return fillDepartamentos();

        return SUCCESS;
    }

    public String fetchEleicao() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        System.out.print(tipo);

        if (tipo.equals("Conselho Geral")) {
            try {
                ConselhoGeral conselhoGeral = (ConselhoGeral) RMI.rmi.get("eleicaos", "ID=" + id);
                getInfo(conselhoGeral);
                return SUCCESS;
            } catch (RemoteException | EmptyQueryException | InvalidFormatException e) {
                addActionMessage(e.getMessage());
                return "rmi-error";
            }
        } else if (tipo.equals("Nucleo Estudantes")) {
            try {
                NucleoEstudantes nucleoEstudantes = (NucleoEstudantes) RMI.rmi.get("eleicaos", "ID=" + id);
                getInfo(nucleoEstudantes);
                departamento_id = nucleoEstudantes.getDepartamento_id();
                return fillDepartamentos();
            } catch (RemoteException | EmptyQueryException | InvalidFormatException e) {
                addActionMessage(e.getMessage());
                return "rmi-error";
            }
        }

        fillEleicoes();
        return INPUT;
    }

    public String remove() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            Eleicao eleicao = (Eleicao) RMI.rmi.get("eleicaos", "ID=" + id);
            getInfo(eleicao);
        } catch (RemoteException | EmptyQueryException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        }

        if (data_inicio.export().before(new Date()) && data_fim.export().after(new Date())) {
            addActionError("Impossível apagar eleição enquanto ela decorre!");
            fillEleicoes();
            return INPUT;
        }

        try {
            RMI.rmi.get("listas", "eleicao_id=" + id);
            addActionError("Eleição contem listas, impossível apagar!");
            fillEleicoes();
            return INPUT;
        } catch (EmptyQueryException eqe) {
            try {
                RMI.rmi.delete("eleicaos", "ID=" + id);
                return SUCCESS;
            } catch (RemoteException re) {
                addActionError(re.getMessage());
                return "rmi-error";
            }
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getData_inicio_ano() {
        return data_inicio.getAno();
    }

    public void setData_inicio_ano(int data_inicio_ano) {
        this.data_inicio.setAno(data_inicio_ano);
    }

    public int getData_inicio_mes() {
        return data_inicio.getMes();
    }

    public void setData_inicio_mes(int data_inicio_mes) {
        this.data_inicio.setMes(data_inicio_mes);
    }

    public int getData_inicio_dia() {
        return data_inicio.getDia();
    }

    public void setData_inicio_dia(int data_inicio_dia) {
        this.data_inicio.setDia(data_inicio_dia);
    }

    public int getData_inicio_hora() {
        return data_inicio.getHora();
    }

    public void setData_inicio_hora(int data_inicio_hora) {
        this.data_inicio.setHora(data_inicio_hora);
    }

    public int getData_inicio_minuto() {
        return data_inicio.getMinuto();
    }

    public void setData_inicio_minuto(int data_inicio_minuto) {
        this.data_inicio.setMinuto(data_inicio_minuto);
    }

    public int getData_inicio_segundo() {
        return data_inicio.getSegundo();
    }

    public void setData_inicio_segundo(int data_inicio_segundo) {
        this.data_inicio.setSegundo(data_inicio_segundo);
    }

    public int getData_fim_ano() {
        return data_fim.getAno();
    }

    public void setData_fim_ano(int data_fim_ano) {
        this.data_fim.setAno(data_fim_ano);
    }

    public int getData_fim_mes() {
        return data_fim.getMes();
    }

    public void setData_fim_mes(int data_fim_mes) {
        this.data_fim.setMes(data_fim_mes);
    }

    public int getData_fim_dia() {
        return data_fim.getDia();
    }

    public void setData_fim_dia(int data_fim_dia) {
        this.data_fim.setDia(data_fim_dia);
    }

    public int getData_fim_hora() {
        return data_fim.getHora();
    }

    public void setData_fim_hora(int data_fim_hora) {
        this.data_fim.setHora(data_fim_hora);
    }

    public int getData_fim_minuto() {
        return data_fim.getMinuto();
    }

    public void setData_fim_minuto(int data_fim_minuto) {
        this.data_fim.setMinuto(data_fim_minuto);
    }

    public int getData_fim_segundo() {
        return data_fim.getSegundo();
    }

    public void setData_fim_segundo(int data_fim_segundo) {
        this.data_fim.setSegundo(data_fim_segundo);
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public String getTipoError() {
        return tipoError;
    }

    public String getTituloError() {
        return tituloError;
    }

    public String getDescricaoError() {
        return descricaoError;
    }

    public String getData_inicioError() {
        return data_inicioError;
    }

    public String getData_fimoError() {
        return data_fimError;
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

    public String getDepartamentoError() {
        return departamentoError;
    }

    public boolean isFinished() {
        return finished;
    }

    public ArrayList<String> getDepartamentos() {
        return departamentos;
    }

    public ArrayList<Eleicao> getEleicoes() {
        return eleicoes;
    }
}
