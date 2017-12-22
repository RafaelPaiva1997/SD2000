package actions.managers;

import actions.ActionModel;
import com.sun.media.sound.InvalidFormatException;
import exceptions.EmptyQueryException;
import models.Data;
import models.Lista;
import models.Voto;
import models.eleicoes.Eleicao;
import rmi.RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Votar extends ActionModel {

    private String tipo;
    private int eleicao_id;
    private int pessoa_id;
    private String pessoa_tipo;
    private int lista_id;
    private String data;
    private ArrayList<Lista> listas;
    private ArrayList<Eleicao> eleicoes;
    private ArrayList<Eleicao> eleicoes_passadas;

    public Votar() {
        listas = new ArrayList<>();
        eleicoes = new ArrayList<>();
        eleicoes_passadas = new ArrayList<>();
    }

    public String fillListas() {
        try {
            listas = new ArrayList(RMI.rmi.getMany("listas", "*", "WHERE tipo='" + pessoa_tipo + "s' && eleicao_id=" + eleicao_id));
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            listas = new ArrayList<>();
            return SUCCESS;
        }
    }

    public String fetchListas() {
        String validation;
        if (!(validation = validateUser()).equals("success"))
            return validation;

        return fillListas();
    }

    public String votar() {
        String validation;
        if (!(validation = validateUser()).equals("success"))
            return validation;

        Voto voto = new Voto();
        try {
            voto.setPessoa_id(pessoa_id);
            voto.setEleicao_id(eleicao_id);
            voto.setTipo(tipo);
            String[] datas = data.split("-");
            Data data = new Data(datas[0],datas[1],datas[2],datas[3],datas[4],datas[5]);
            voto.setData(data.export());
            RMI.rmi.get("votos", "pessoa_id=" + pessoa_id + " && eleicao_id=" + eleicao_id);
            addActionError("Voto concorrente detectado!");
            fillEleicoes();
            fillEleicoesPassadas();
            return INPUT;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            e.printStackTrace();
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            try {
                RMI.rmi.insert(voto);
                if (tipo.equals("Normal")) {
                    voto = (Voto) RMI.rmi.get("votos", "pessoa_id=" + pessoa_id + " && eleicao_id=" + eleicao_id);
                    Lista lista = (Lista) RMI.rmi.get("listas", "ID=" + lista_id);
                    RMI.rmi.connect(lista, voto);
                }
            } catch (RemoteException | InvalidFormatException | EmptyQueryException e) {
                addActionError(e.getMessage());
                e.printStackTrace();
                return "rmi-error";
            }
            return SUCCESS;
        }
    }

    public String fillEleicoes() {
        try {
            eleicoes = new ArrayList(RMI.rmi.getMany("pessoas", "DISTINCT eleicaos.*", "INNER JOIN eleicaos ON (eleicaos.tipo='Conselho Geral' || eleicaos.tipo='Nucleo Estudantes' && eleicaos.departamento_id = " + user.getDepartamento_id() + ") && (eleicaos.ID NOT IN (SELECT eleicaos.ID FROM votos INNER JOIN eleicaos ON votos.eleicao_id=eleicaos.ID && votos.pessoa_id="+ user.getId() +"))"));
            LinkedList<Eleicao> es = new LinkedList<>(eleicoes);
            for (Eleicao e : es) {
                if (e.getTipo().equals("Nucleo Estudantes") && !user.getTipo().equals("Aluno"))
                    es.remove(e);
                if (!(e.getData_inicio().before(new Date()) && e.getData_fim().after(new Date())))
                    es.remove(e);
            }
            eleicoes = new ArrayList<>(es);
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            eleicoes = new ArrayList<>();
            return SUCCESS;
        }
    }

    public String fillEleicoesPassadas() {
        try {
            eleicoes_passadas = new ArrayList(RMI.rmi.getMany("votos", "eleicaos.*", "INNER JOIN eleicaos ON votos.eleicao_id=eleicaos.ID && votos.pessoa_id=" + user.getId()));
            return SUCCESS;
        } catch (RemoteException | InvalidFormatException e) {
            addActionError(e.getMessage());
            return "rmi-error";
        } catch (EmptyQueryException eqe) {
            eleicoes_passadas = new ArrayList<>();
            return SUCCESS;
        }
    }

    public String fetchInfo() {
        String validation;
        if (!(validation = validateUser()).equals("success"))
            return validation;

        if (!(validation = fillEleicoes()).equals("success"))
            return validation;

        return fillEleicoesPassadas();
    }

    public ArrayList<Eleicao> getEleicoes() {
        return eleicoes;
    }

    public ArrayList<Eleicao> getEleicoes_passadas() {
        return eleicoes_passadas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEleicao_id() {
        return eleicao_id;
    }

    public void setEleicao_id(int eleicao_id) {
        this.eleicao_id = eleicao_id;
    }

    public int getPessoa_id() {
        return pessoa_id;
    }

    public void setPessoa_id(int pessoa_id) {
        this.pessoa_id = pessoa_id;
    }

    public int getLista_id() {
        return lista_id;
    }

    public void setLista_id(int lista_id) {
        this.lista_id = lista_id;
    }

    public String getPessoa_tipo() {
        return pessoa_tipo;
    }

    public void setPessoa_tipo(String pessoa_tipo) {
        this.pessoa_tipo = pessoa_tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<Lista> getListas() {
        return listas;
    }
}
