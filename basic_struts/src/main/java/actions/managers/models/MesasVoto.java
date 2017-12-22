package actions.managers.models;

import actions.ActionModel;
import com.sun.media.sound.InvalidFormatException;
import exceptions.EmptyQueryException;
import models.eleicoes.Eleicao;
import rmi.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class MesasVoto extends ActionModel {
    
    private int id;
    private int departamento_id;
    private int eleicao_id;
    private ArrayList<Eleicao> eleicoes;
    private ArrayList<Eleicoes> nao_eleicoes;
    private ArrayList<MesasVoto> mesas_voto;

    public MesasVoto() {
        eleicoes = new ArrayList<>();
        nao_eleicoes = new ArrayList<>();
        mesas_voto = new ArrayList<>();
    }

    public String fillMesasVoto() {
        try {
            mesas_voto = new ArrayList(RMI.rmi.getMany("mesa_votos", "*",  ""));
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            mesas_voto = new ArrayList<>();
            return SUCCESS;
        }
    }

    public String manage() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        return fillMesasVoto();
    }

    public String fillEleicoes() {
        try {
            eleicoes = new ArrayList(RMI.rmi.getMany("mesa_voto_eleicaos", "eleicaos.*",  "INNER JOIN eleicaos ON mesa_voto_eleicaos.mesa_voto_id=" + id + " && mesa_voto_eleicaos.eleicao_id=ID"));
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            eleicoes = new ArrayList<>();
            return SUCCESS;
        }
    }

    public String fillNaoEleicoes() {
        try {
            nao_eleicoes = new ArrayList(RMI.rmi.getMany("eleicaos", "*",  "WHERE ID NOT IN (SELECT eleicao_id FROM mesa_voto_eleicaos WHERE mesa_voto_eleicaos.mesa_voto_id=" + id + ")"));
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            nao_eleicoes = new ArrayList<>();
            return SUCCESS;
        }
    }

    public String fetchEleicoes() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        if (!(validation = fillEleicoes()).equals("success"))
            return validation;

        return fillNaoEleicoes();
    }

    public String add() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            RMI.rmi.connect(RMI.rmi.get("mesa_votos", "ID=" + id), RMI.rmi.get("eleicaos", "ID=" + eleicao_id));
            fillEleicoes();
            fillNaoEleicoes();
            return SUCCESS;
        } catch (RemoteException | EmptyQueryException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        }
    }

    public String remove() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            RMI.rmi.disconnect(RMI.rmi.get("mesa_votos", "ID=" + id), RMI.rmi.get("eleicaos", "ID=" + eleicao_id));
            fillEleicoes();
            fillNaoEleicoes();
            return SUCCESS;
        } catch (RemoteException | EmptyQueryException | InvalidFormatException e) {
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

    public int getDepartamento_id() {
        return departamento_id;
    }

    public void setDepartamento_id(int departamento_id) {
        this.departamento_id = departamento_id;
    }

    public int getEleicao_id() {
        return eleicao_id;
    }

    public void setEleicao_id(int eleicao_id) {
        this.eleicao_id = eleicao_id;
    }

    public ArrayList<Eleicao> getEleicoes() {
        return eleicoes;
    }

    public ArrayList<Eleicoes> getNao_eleicoes() {
        return nao_eleicoes;
    }

    public ArrayList<MesasVoto> getMesas_voto() {
        return mesas_voto;
    }
}
