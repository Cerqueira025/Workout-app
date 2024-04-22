package Utilizador.TiposUtilizador;

import java.util.Map;
import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Utilizador;

public class Profissional extends Utilizador {

    public Profissional() {
        super();
    }

    public Profissional(String codigo, int bpmMedio, double peso, int altura,
                        String nome, String morada, String email, String password,
                        Map<String, Atividade> atividades, Map<String, PlanoDeTreino> planos) {
        super(codigo, bpmMedio, peso, altura, nome, morada, email, password, atividades, planos);
    }

    public Profissional(Profissional outro) {
        super(outro);
    }

     
    public Profissional clone() {
        return new Profissional(this);
    }

     
    public double fatorMultiplicativo() {
        return 2;
    }
}
