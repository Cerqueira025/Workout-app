package Utilizador.TiposUtilizador;

import java.util.Map;
import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Genero;
import Utilizador.Utilizador;

public class Profissional extends Utilizador {


    // ----------------- Construtores ---------------- //

    public Profissional() {
        super();
    }

    public Profissional(String codigo, int bpmMedio, double peso, double caloriasGastas, int altura,
                        String nome, Genero genero, String morada, String email, String password,
                        Map<String, Atividade> atividades, Map<String, Double> recordes, PlanoDeTreino plano) {
        super(codigo, bpmMedio, peso, caloriasGastas, altura, nome, genero, morada, email, password, atividades, recordes, plano);
    }

    public Profissional(String codigo, int bpmMedio, double peso, double caloriasGastas, int altura,
                        String nome, Genero genero, String morada, String email, String password) {
        super(codigo, bpmMedio, peso, caloriasGastas, altura, nome, genero, morada, email, password);
    }


    public Profissional(Profissional outro) {
        super(outro);
    }

    // ------------------- Métodos ------------------- //
     
    public Profissional clone() {
        return new Profissional(this);
    }

     
    public double fatorMultiplicativo() {
      double tmb;
      if (this.getGenero() == Genero.Masculino) {
        tmb = 66 + (13.8 * this.getPeso()) + (5 * this.getAltura()) - (6.8 * 20);
      } else {
        tmb = 655 + (9.6 * this.getPeso()) + (1.8 * this.getAltura()) - (4.7 * 20);
      }
      return (tmb/1000)*1.5;
    }
}
