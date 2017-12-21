package models.eleicoes;

import java.sql.ResultSet;

public class ConselhoGeral extends Eleicao {

    public ConselhoGeral() {
        super();
        tipo = "Conselho Geral";
    }

    public ConselhoGeral(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    public String toString() {
        return super.toString() + "\n";
    }
}
