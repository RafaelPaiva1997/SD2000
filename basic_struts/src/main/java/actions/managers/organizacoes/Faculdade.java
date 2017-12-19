package actions.managers.organizacoes;

import actions.ActionModel;
import com.sun.media.sound.InvalidFormatException;
import exceptions.EmptyQueryException;
import rmi.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Faculdade extends ActionModel {

    private long id;
    private String nome;
    private String nomeError;
    private ArrayList<models.organizacoes.Faculdade> faculdades;

    public Faculdade() {
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

        models.organizacoes.Faculdade faculdade = new models.organizacoes.Faculdade();

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

        models.organizacoes.Faculdade faculdade = new models.organizacoes.Faculdade();

        if (!faculdade.update("faculdade", nome))
            nomeError = "Por favor insira um nome só com letras!";

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
            RMI.rmi.delete(RMI.rmi.get("faculdades", "ID=" + id));
            return SUCCESS;
        } catch (RemoteException re) {
            addActionError(re.getMessage());
            return "rmi-error";
        }
    }

    public ArrayList<models.organizacoes.Faculdade> getFaculdades() {
        return faculdades;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
