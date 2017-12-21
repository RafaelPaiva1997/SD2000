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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Eleicoes extends ActionModel {

    private int id;
    private String tipo;
    private String titulo;
    private String tituloError;
    private String descricao;
    private String descricaoError;
    private String data_inicio_ano;
    private String data_inicio_mes;
    private String data_inicio_dia;
    private String data_inicio_hora;
    private String data_inicio_minuto;
    private String data_inicio_segundo;
    private Data data_inicio;
    private String data_inicioError;
    private Data data_fim;
    private String data_fim_ano;
    private String data_fim_mes;
    private String data_fim_dia;
    private String data_fim_hora;
    private String data_fim_minuto;
    private String data_fim_segundo;
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
        tituloError = "";
        descricaoError = "";
        data_inicioError = "";
        data_fimError = "";
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

        try {
            data_inicio = new Data(data_inicio_ano, data_inicio_mes, data_inicio_dia, data_inicio_hora, data_inicio_minuto, data_inicio_segundo);
        } catch (NumberFormatException e) {
            data_inicioError = "Por favor insira uma data de início só com números!";
        }
        if (data_inicioError.equals("") && !data_inicio.test())
            data_inicioError = "Por favor insira uma data de início válida!";
        eleicao.setData_inicio(data_inicio.export());

        try {
            data_fim = new Data(data_fim_ano, data_fim_mes, data_fim_dia, data_fim_hora, data_fim_minuto, data_fim_segundo);
        } catch (NumberFormatException e) {
            data_fimError = "Por favor insira uma data de fim só com números!";
        }
        if (data_fimError.equals("") && !data_fim.test())
            data_fimError = "Por favor insira uma data de fim válida!";
        eleicao.setData_fim(data_fim.export());


        try {
            if (tipo.equals("Nucleo Estudantes"))
                eleicao.setDepartamento_id(RMI.rmi.get("departamentos", "ID=" + departamento.split(" - ")[0]).getId());

            if (tituloError.equals("") &&
                    descricaoError.equals("") &&
                    data_inicioError.equals("") &&
                    data_fimError.equals("")) {
                RMI.rmi.insert(eleicao);
                return SUCCESS;
            }

            fillDepartamentos();
            return INPUT;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            departamentoError = "Erro ao seleccionar a faculdade!";
            fillDepartamentos();
            return INPUT;
        }
    }

    public String update() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            Eleicao eleicao = (Eleicao) RMI.rmi.get("eleicaos", "ID=" + id);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


            if (!eleicao.getTitulo().equals(titulo) && !eleicao.update("titulo", titulo))
                tituloError = "Por favor insira um título só com letras!";

            if (!eleicao.getDescricao().equals(descricao) && !eleicao.update("descricao", descricao))
                descricaoError = "Por favor insira uma descrição só com letras!";

            try {
                data_inicio = new Data(data_inicio_ano, data_inicio_mes, data_inicio_dia, data_inicio_hora, data_inicio_minuto, data_inicio_segundo);
            } catch (NumberFormatException e) {
                data_inicioError = "Por favor insira uma data de início só com números!";
            }
            if (data_inicioError.equals("") && !eleicao.getData_inicio().equals(data_inicio) && !data_inicio.test())
                data_inicioError = "Por favor insira uma data de início válida!";
            eleicao.update("data_inicio", f.format(data_inicio.export()));

            try {
                data_fim = new Data(data_fim_ano, data_fim_mes, data_fim_dia, data_fim_hora, data_fim_minuto, data_fim_segundo);
            } catch (NumberFormatException e) {
                data_fimError = "Por favor insira uma data de fim só com números!";
            }
            if (data_fimError.equals("") && !eleicao.getData_fim().equals(data_fim) && !data_fim.test())
                data_fimError = "Por favor insira uma data de fim válida!";
            eleicao.update("data_fim", f.format(data_fim.export()));


            try {
                if (tipo.equals("Nucleo Estudantes") && !(eleicao.getDepartamento_id() == Integer.parseInt(departamento.split(" - ")[0])))
                    eleicao.update("departamento_id", String.valueOf(RMI.rmi.get("departamentos", "ID=" + departamento.split(" - ")[0]).getId()));

                if (tituloError.equals("") &&
                        descricaoError.equals("") &&
                        data_inicioError.equals("") &&
                        data_fimError.equals("")) {
                    RMI.rmi.update(eleicao);
                    return SUCCESS;
                }

                eleicao.updateClear();
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

    public void getInfo(Eleicao eleicao) {
        titulo = eleicao.getTitulo();
        descricao = eleicao.getDescricao();
        data_inicio = new Data(eleicao.getData_inicio());
        data_inicio_ano = String.valueOf(data_inicio.getAno());
        data_inicio_mes = String.valueOf(data_inicio.getMes());
        data_inicio_dia = String.valueOf(data_inicio.getDia());
        data_inicio_hora = String.valueOf(data_inicio.getHora());
        data_inicio_minuto = String.valueOf(data_inicio.getMinuto());
        data_inicio_segundo = String.valueOf(data_inicio.getSegundo());
        data_fim = new Data(eleicao.getData_fim());
        data_fim_ano = String.valueOf(data_fim.getAno());
        data_fim_mes = String.valueOf(data_fim.getMes());
        data_fim_dia = String.valueOf(data_fim.getDia());
        data_fim_hora = String.valueOf(data_fim.getHora());
        data_fim_minuto = String.valueOf(data_fim.getMinuto());
        data_fim_segundo = String.valueOf(data_fim.getSegundo());
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

    public String getData_inicio_ano() {
        return data_inicio_ano;
    }

    public void setData_inicio_ano(String data_inicio_ano) {
        this.data_inicio_ano = data_inicio_ano;
    }

    public String getData_inicio_mes() {
        return data_inicio_mes;
    }

    public void setData_inicio_mes(String data_inicio_mes) {
        this.data_inicio_mes = data_inicio_mes;
    }

    public String getData_inicio_dia() {
        return data_inicio_dia;
    }

    public void setData_inicio_dia(String data_inicio_dia) {
        this.data_inicio_dia = data_inicio_dia;
    }

    public String getData_inicio_hora() {
        return data_inicio_hora;
    }

    public void setData_inicio_hora(String data_inicio_hora) {
        this.data_inicio_hora = data_inicio_hora;
    }

    public String getData_inicio_minuto() {
        return data_inicio_minuto;
    }

    public void setData_inicio_minuto(String data_inicio_minuto) {
        this.data_inicio_minuto = data_inicio_minuto;
    }

    public String getData_inicio_segundo() {
        return data_inicio_segundo;
    }

    public void setData_inicio_segundo(String data_inicio_segundo) {
        this.data_inicio_segundo = data_inicio_segundo;
    }

    public String getData_fim_ano() {
        return data_fim_ano;
    }

    public void setData_fim_ano(String data_fim_ano) {
        this.data_fim_ano = data_fim_ano;
    }

    public String getData_fim_mes() {
        return data_fim_mes;
    }

    public void setData_fim_mes(String data_fim_mes) {
        this.data_fim_mes = data_fim_mes;
    }

    public String getData_fim_dia() {
        return data_fim_dia;
    }

    public void setData_fim_dia(String data_fim_dia) {
        this.data_fim_dia = data_fim_dia;
    }

    public String getData_fim_hora() {
        return data_fim_hora;
    }

    public void setData_fim_hora(String data_fim_hora) {
        this.data_fim_hora = data_fim_hora;
    }

    public String getData_fim_minuto() {
        return data_fim_minuto;
    }

    public void setData_fim_minuto(String data_fim_minuto) {
        this.data_fim_minuto = data_fim_minuto;
    }

    public String getData_fim_segundo() {
        return data_fim_segundo;
    }

    public void setData_fim_segundo(String data_fim_segundo) {
        this.data_fim_segundo = data_fim_segundo;
    }

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
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

    public String getData_fimError() {
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
