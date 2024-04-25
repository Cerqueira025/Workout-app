package Atividade.Repeticoes;

import java.time.LocalDate;

import Utilizador.Utilizador;

public class Abdominais extends Repeticoes {
    
    private double amplitude;

    
    // ------------------- Construtores ------------------- //


    public Abdominais() {
        super();
        this.amplitude = 0;
    }

    public Abdominais(String codigo, String descricao, LocalDate data, int duracao, Utilizador user, int repeticoes, double amplitude) {
        super(codigo, descricao, data, duracao, user, repeticoes);
        this.amplitude = amplitude;
    }

    public Abdominais(Abdominais outro) {
        super(outro);
        this.amplitude = outro.getAmplitude();
    }


    // ------------------- Métodos ------------------- //


    public double getAmplitude() {
        return this.amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

     
    public String toString() {
        return "Abdominais{" +
                super.toString() +
                "amplitude=" + this.amplitude +
                '}';
    }

     
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Abdominais abdominais = (Abdominais) o;
        return super.equals(abdominais)
                && Double.compare(this.amplitude, abdominais.getAmplitude()) == 0;
    }

    public Abdominais clone() {
      return new Abdominais(this);
    }
    
    public int getBpm(){
      return (int) (this.getUser().getBpmMedio() + 10 * this.getUser().fatorMultiplicativo());
    }

    public double calorias() { // ESTE MÉTODO ESTÁ TOTALMENTE INCORRETO *********************************o
      return this.getUser().fatorMultiplicativo() * (this.getAmplitude()/4) * this.getDuracao() * (this.getBpm()/100);
    }
  }
