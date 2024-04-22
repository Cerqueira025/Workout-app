package Utilizador.TiposUtilizador;

import Atividade.GestorAtividades;
import Utilizador.Utilizador;

public class PraticanteOcasional extends Utilizador {

    public PraticanteOcasional() {
        super();
    }

    public PraticanteOcasional(String codigo, int bpmMedio, double peso, int altura,
                  String nome, String morada, String email, String password,
                  GestorAtividades atividades) {
        super(codigo, bpmMedio, peso, altura, nome, morada, email, password, atividades);
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
