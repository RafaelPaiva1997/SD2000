package actions.managers.organizacoes;

import actions.ActionModel;
import com.sun.media.sound.InvalidFormatException;
import exceptions.EmptyQueryException;
import models.Model;
import models.organizacoes.Departamento;
import models.organizacoes.Faculdade;
import rmi.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Departamentos extends ActionModel {

    private int id;
    private int faculdade_id;
    private String nome;
    private String faculdadeError;
    private String nomeError;
    private String faculdade;
    private String defultFaculdade;
    private ArrayList<String> faculdades;
    private ArrayList<Departamento> departamentos;

    public Departamentos() {
        nomeError="";
    }

    public String manage() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            departamentos = new ArrayList(RMI.rmi.getMany("departamentos", ""));
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            faculdades = new ArrayList<>();
            return SUCCESS;
        }
    }

    public String add() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        Departamento departamento = new Departamento();

        Faculdade faculdade;

        if (!departamento.setNome(nome))
            nomeError = "Por favor insira um nome só com letras!";

        try {
            if ((faculdade = (Faculdade) RMI.rmi.get("departamentos", "nome=" + this.faculdade)) == null)
                faculdadeError = "Erro ao selecionar a faculdade!";
            else
                departamento.setFaculdade_id(faculdade.getId());

            if (nomeError.equals("") && faculdadeError.equals("")) {
                RMI.rmi.insert(departamento);
                return SUCCESS;
            }
            return INPUT;
        } catch (RemoteException re) {
            addActionError(re.getMessage());
            return "rmi-error";
        }
    }

    public String fetchFaculdades() {
        return SUCCESS;
    }

    public String update() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;
        return SUCCESS;
    }

    public String remove() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            Model model;
            if ((model = RMI.rmi.get("departamentos", "ID=" + id)) == null)
                return INPUT;
            RMI.rmi.delete(model);
            return SUCCESS;
        } catch (RemoteException re) {
            addActionError(re.getMessage());
            return "rmi-error";
        }
    }

    public ArrayList<String> getFaculdades() {
        return faculdades;
    }

    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFaculdade_id() {
        return faculdade_id;
    }

    public void setFaculdade_id(int faculdade_id) {
        this.faculdade_id = faculdade_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(String faculdade) {
        this.faculdade = faculdade;
    }

    public String getNomeError() {
        return nomeError;
    }

    public String getFaculdadeError() {
        return faculdadeError;
    }

    public String getDefultFaculdade() {
        return defultFaculdade;
    }
}
