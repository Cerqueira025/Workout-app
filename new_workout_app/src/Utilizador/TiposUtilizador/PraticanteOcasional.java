package Utilizador.TiposUtilizador;

import java.util.Map;
import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Utilizador;

public class PraticanteOcasional extends Utilizador {

    public PraticanteOcasional() {
        super();
    }

    public PraticanteOcasional(String codigo, int bpmMedio, double peso, int altura,
                               String nome, String morada, String email, String password,
                               Map<String, Atividade> atividades, Map<String, PlanoDeTreino> planos) {
        super(codigo, bpmMedio, peso, altura, nome, morada, email, password, atividades, planos);
    }

    public PraticanteOcasional(PraticanteOcasional outro) {
        super(outro);
    }

     
    public PraticanteOcasional clone() {
        return new PraticanteOcasional(this);
    }

     
    public double fatorMultiplicativo() {
        return 1;
    }
}
