package Utilizador.TiposUtilizador;

import Atividade.GestorAtividades;
import Utilizador.Utilizador;

public class Amador extends Utilizador {

    public Amador() {
        super();
    }

    public Amador(String codigo, int bpmMedio, double peso, int altura,
                  String nome, String morada, String email, String password,
                  GestorAtividades atividades) {
        super(codigo, bpmMedio, peso, altura, nome, morada, email, password, atividades);
    }

    public Amador(Amador outro) {
        super(outro);
    }

    @Override
    public Amador clone() {
        return new Amador(this);
    }

    @Override
    public double fatorMultiplicativo() {
        return 1.5;
    }
}