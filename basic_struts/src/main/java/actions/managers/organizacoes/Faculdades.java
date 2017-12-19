package actions.managers.organizacoes;

import actions.ActionModel;
import com.sun.media.sound.InvalidFormatException;
import exceptions.EmptyQueryException;
import models.Model;
import models.organizacoes.Faculdade;
import rmi.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Faculdades extends ActionModel {

    private int id;
    private String nome;
    private String nomeError;
    private ArrayList<Faculdade> faculdades;

    public Faculdades() {
        nomeError="";
    }

    public String manage() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            faculdades = new ArrayList(RMI.rmi.getMany("faculdades", ""));
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

        Faculdade faculdade = new Faculdade();

        if (!faculdade.setNome(nome))
            nomeError = "Por favor insira um nome só com letras!";

        try {
            if (nomeError.equals("")) {
                RMI.rmi.insert(faculdade);
                return SUCCESS;
            }
            return INPUT;
        } catch (RemoteException re) {
            addActionError(re.getMessage());
            return "rmi-error";
        }
    }

    public String update() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        Faculdade faculdade = new Faculdade();

        if (!faculdade.update("nome", nome))
            nomeError = "Por favor insira um nome só com letras!";

        faculdade.setId(id);

        try {
            if (nomeError.equals("")) {
                RMI.rmi.update(faculdade);
                return SUCCESS;
            }
            return INPUT;
        } catch (RemoteException re) {
            addActionError(re.getMessage());
            return "rmi-error";
        }
    }

    public String remove() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            Model model;
            if ((model = RMI.rmi.get("faculdades", "ID=" + id)) == null)
                return INPUT;
            RMI.rmi.delete(model);
            return SUCCESS;
        } catch (RemoteException re) {
            addActionError(re.getMessage());
            return "rmi-error";
        }
    }

    public ArrayList<Faculdade> getFaculdades() {
        return faculdades;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
