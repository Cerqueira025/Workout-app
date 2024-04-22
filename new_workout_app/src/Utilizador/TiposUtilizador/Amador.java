package Utilizador.TiposUtilizador;

import java.util.Map;
import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Utilizador;

public class Amador extends Utilizador {

    public Amador() {
        super();
    }

    public Amador(String codigo, int bpmMedio, double peso, int altura,
                  String nome, String morada, String email, String password,
                  Map<String, Atividade> atividades, Map<String, PlanoDeTreino> planos) {
        super(codigo, bpmMedio, peso, altura, nome, morada, email, password, atividades, planos);
    }

    public Amador(Amador outro) {
        super(outro);
    }

     
    public Amador clone() {
        return new Amador(this);
    }

     
    public double fatorMultiplicativo() {
        return 1.5;
    }
}