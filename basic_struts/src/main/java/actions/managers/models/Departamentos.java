package actions.managers.models;

import actions.ActionModel;
import com.sun.media.sound.InvalidFormatException;
import exceptions.EmptyQueryException;
import models.MesadeVoto;
import models.organizacoes.Departamento;
import rmi.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Departamentos extends ActionModel {

    private int id;
    private int faculdade_id;
    private String nome;
    private String nomeError;
    private String faculdade;
    private String faculdadeDefault;
    private String faculdadeError;
    private ArrayList<String> faculdades;
    private ArrayList<Departamento> departamentos;

    public Departamentos() {
        faculdade = "";
        nomeError = "";
        faculdadeError = "";
        faculdades = new ArrayList<>();
        departamentos = new ArrayList<>();
    }

    public String fillDepartamentos() {
        try {
            departamentos = new ArrayList(RMI.rmi.getMany("departamentos","*", ""));
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            departamentos = new ArrayList<>();
            return SUCCESS;
        }
    }

    public String manage() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        return fillDepartamentos();
    }

    public String add() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        Departamento departamento = new Departamento();

        if (!departamento.setNome(nome))
            nomeError = "Por favor insira um nome só com letras!";

        try {
            departamento.setFaculdade_id(RMI.rmi.get("faculdades", "ID=" + faculdade.split(" - ")[0]).getId());

            if (nomeError.equals("")) {
                RMI.rmi.insert(departamento);
                RMI.rmi.insert(new MesadeVoto(id));
                return SUCCESS;
            }

            fillFaculdades();
            return INPUT;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            faculdadeError = "Erro ao seleccionar a faculdade!";
            fillFaculdades();
            return INPUT;
        }
    }

    public String getFaculdadeById(int id) {
        for (String f : faculdades)
            if (id == Integer.parseInt(f.split(" - ")[0]))
                return f;
        return "";
    }

    public String fillFaculdades() {
        try {
            faculdades = RMI.rmi.getOptions("faculdades", "");

            faculdadeDefault = getFaculdadeById(faculdade_id);

            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            addActionError("Não existem faculdades, por favor adicione uma!");
            fillDepartamentos();
            return INPUT;
        }
    }

    public String fetchFaculdades() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        return fillFaculdades();
    }

    public String update() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            Departamento departamento = (Departamento) RMI.rmi.get("departamentos", "ID=" + id);

            if (!departamento.getNome().equals(nome) && !departamento.update("nome", nome))
                nomeError = "Por favor insira um nome só com letras!";

            try {
                if (departamento.getFaculdade_id() != Integer.parseInt(faculdade.split(" - ")[0]))
                    departamento.update("faculdade_id", String.valueOf(RMI.rmi.get("faculdades", "ID=" + this.faculdade.split(" - ")[0]).getId()));

                if (nomeError.equals("")) {
                    RMI.rmi.update(departamento);
                    return SUCCESS;
                }

                departamento.updateClear();
                fillFaculdades();
                return INPUT;
            } catch (RemoteException | InvalidFormatException e) {
                addActionError(e.getMessage());
                return "rmi-error";
            } catch (EmptyQueryException eqe) {
                faculdadeError = "Erro ao seleccionar a faculdade!";
                fillFaculdades();
                return INPUT;
            }

        } catch (RemoteException | InvalidFormatException | EmptyQueryException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        }
    }

    public String remove() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        try {
            RMI.rmi.get("pessoas", "departamento_id=" + id);
            addActionError("Departamento contem pessoas, impossível apagar!");
            fillDepartamentos();
            return INPUT;
        } catch (EmptyQueryException eqe) {
            try {
                RMI.rmi.get("eleicaos", "departamento_id=" + id);
                addActionError("Departamento é referenciado por eleições, impossível apagar!");
                fillDepartamentos();
                return INPUT;
            } catch (EmptyQueryException eqe2) {
                try {
                    RMI.rmi.delete("mesa_votos", "departamento_id=" + id);
                    RMI.rmi.delete("departamentos", "ID=" + id);
                    return SUCCESS;
                } catch (RemoteException re) {
                    addActionError(re.getMessage());
                    return "rmi-error";
                }
            } catch (RemoteException | InvalidFormatException e) {
                addActionError(e.getMessage());
                return "rmi-error";
            }
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        }
    }

    public ArrayList<String> getFaculdades() {
        return  faculdades;
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

    public String getFaculdadeDefault() {
        return faculdadeDefault;
    }
}
