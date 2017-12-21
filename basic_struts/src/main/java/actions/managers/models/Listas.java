package actions.managers.models;

import actions.ActionModel;
import com.sun.media.sound.InvalidFormatException;
import exceptions.EmptyQueryException;
import models.Lista;
import models.MesadeVoto;
import models.organizacoes.Departamento;
import rmi.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Listas extends ActionModel {

    private int id;
    private String tipo;
    private String nome;
    private String nomeError;
    private int eleicao_id;
    private String eleicao;
    private String eleicaoDefault;
    private String eleicaoError;
    private ArrayList<String> eleicoes;
    private ArrayList<Listas> listas;

    public Listas() {
        tipo = "";
        nomeError = "";
        eleicaoError = "";
        eleicaoDefault = "";
        eleicoes = new ArrayList<>();
        listas = new ArrayList<>();
    }

    public String fillListas() {
        try {
            listas = new ArrayList(RMI.rmi.getMany("listas", ""));
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            listas = new ArrayList<>();
            return SUCCESS;
        }
    }

    public String manage() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        return fillListas();
    }

    public String add() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        Lista lista = new Lista();

        if (!lista.setNome(nome))
            nomeError = "Por favor insira um nome só com letras!";

        try {
            lista.setEleicao_id(RMI.rmi.get("eleicaos", "ID=" + eleicao.split(" - ")[0]).getId());

            lista.setTipo(tipo);

            if (nomeError.equals("")) {
                RMI.rmi.insert(lista);
                return SUCCESS;
            }

            fillEleicoes();
            return INPUT;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            eleicaoError = "Erro ao seleccionar a eleição!";
            fillEleicoes();
            return INPUT;
        }
    }

    public String update() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            Lista lista = (Lista) RMI.rmi.get("listas", "ID=" + id);

            if (!lista.getNome().equals(nome) && !lista.update("nome", nome))
                nomeError = "Por favor insira um nome só com letras!";

            try {
                lista.update("eleicao_id", String.valueOf(RMI.rmi.get("eleicaos", "ID=" + eleicao.split(" - ")[0]).getId()));

                lista.setTipo(tipo);

                if (nomeError.equals("")) {
                    RMI.rmi.insert(lista);
                    return SUCCESS;
                }

                fillEleicoes();
                return INPUT;
            } catch (RemoteException | InvalidFormatException e) {
                addActionError(e.getMessage());
                return "rmi-error";
            } catch (EmptyQueryException eqe) {
                eleicaoError = "Erro ao seleccionar a eleição!";
                fillEleicoes();
                return INPUT;
            }
        } catch (RemoteException | InvalidFormatException | EmptyQueryException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        }
    }

    public String getEleicaoById(int id) {
        for (String f : eleicoes)
            if (id == Integer.parseInt(f.split(" - ")[0]))
                return f;
        return "";
    }

    public String fillEleicoes() {
        try {
            if (tipo.equals("Alunos"))
                eleicoes = RMI.rmi.getOptions("eleicaos", "");
            else
                eleicoes = RMI.rmi.getOptions("eleicaos", "WHERE tipo='Conselho Geral'");


            eleicaoDefault = getEleicaoById(eleicao_id);

            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            addActionError("Não existem departamentos, por favor adicione um!");
            fillListas();
            return INPUT;
        }
    }

    public String fetchEleicoes() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        return fillEleicoes();
    }

    public String remove() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            RMI.rmi.delete("lista_pessoas", "lista_id=" + id);
            RMI.rmi.delete("listas", "ID=" + id);
            return SUCCESS;
        } catch (RemoteException e) {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEleicao_id() {
        return eleicao_id;
    }

    public void setEleicao_id(int eleicao_id) {
        this.eleicao_id = eleicao_id;
    }

    public String getEleicao() {
        return eleicao;
    }

    public void setEleicao(String eleicao) {
        this.eleicao = eleicao;
    }

    public String getNomeError() {
        return nomeError;
    }

    public String getEleicaoDefault() {
        return eleicaoDefault;
    }

    public String getEleicaoError() {
        return eleicaoError;
    }

    public ArrayList<String> getEleicoes() {
        return eleicoes;
    }

    public ArrayList<Listas> getListas() {
        return listas;
    }
}
