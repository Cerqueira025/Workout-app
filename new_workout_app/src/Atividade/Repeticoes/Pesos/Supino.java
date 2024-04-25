package Atividade.Repeticoes.Pesos;

import java.time.LocalDate;

import Utilizador.Utilizador;

public class Supino extends Pesos {
    
    private double inclinacao;

    // ------------------- Construtores ------------------- //
    
    public Supino() {
        super();
        this.inclinacao = 0;
    }

    public Supino(String codigo, String descricao, LocalDate data,int duracao, Utilizador user, int repeticoes, double peso, double inclinacao) {
        super(codigo, descricao, data, duracao, user, repeticoes, peso);
        this.inclinacao = 0;
    }

    public Supino(Supino outro) {
        super(outro);
        this.inclinacao = outro.getInclinacao();
    }


    // ------------------- Construtores ------------------- //


    public double getInclinacao() {
        return this.inclinacao;
    }

    public void setInclinacao(double inclinacao) {
        this.inclinacao = inclinacao;
    }

     
    public String toString() {
        return "Supino{" +
                super.toString() +
                "inclinação=" + this.inclinacao +
                '}';
    }

     
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Supino supino = (Supino) o;
        return super.equals(supino)
                && Double.compare(this.inclinacao,supino.getInclinacao()) == 0;
    }

     
    public Supino clone() {
        return new Supino(this);
    }
    
    public double calorias() {
        return this.getUser().fatorMultiplicativo() * (this.inclinacao/3) * this.getDuracao() * (this.getBpm()/100);
    }

    public int getBpm(){
      return (int) (this.getUser().getBpmMedio() + 10 * this.getUser().fatorMultiplicativo());
    }
}
