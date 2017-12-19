package actions.managers.organizacoes;

import actions.ActionModel;
import com.sun.media.sound.InvalidFormatException;
import exceptions.EmptyQueryException;
import exceptions.UserNotLoggedException;
import rmi.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public class Faculdade extends ActionModel {

    private models.organizacoes.Faculdade faculdade;
    private ArrayList<Faculdade> faculdades;

    public String manage() {
        String validation;
        if (!(validation = validation()).equals("success"))
            return validation;

        try {
            faculdades = new ArrayList(RMI.rmi.getMany("faculdades", ""));
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            faculdades = new ArrayList<>();
            return SUCCESS;
        }
    }

    public String remove() {
        String validation;
        if (!(validation = validation()).equals("success"))
            return validation;

        try {
            RMI.rmi.delete(RMI.rmi.get("faculdades", "ID=" + faculdade.getId()));
            return SUCCESS;
        } catch (RemoteException re) {
            return "rmi-error";
        }
    }

    public ArrayList<Faculdade> getFaculdades() {
        return faculdades;
    }

    public models.organizacoes.Faculdade getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(models.organizacoes.Faculdade faculdade) {
        this.faculdade = faculdade;
    }
}
