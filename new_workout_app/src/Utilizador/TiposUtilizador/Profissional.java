package Utilizador.TiposUtilizador;

import Atividade.GestorAtividades;
import Utilizador.Utilizador;

public class Profissional extends Utilizador {

    public Profissional() {
        super();
    }

    public Profissional(String codigo, int bpmMedio, double peso, int altura,
                               String nome, String morada, String email, String password,
                               GestorAtividades atividades) {
        super(codigo, bpmMedio, peso, altura, nome, morada, email, password, atividades);
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
