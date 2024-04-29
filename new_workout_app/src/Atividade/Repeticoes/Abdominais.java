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

    public Abdominais(String codigo, String descricao, LocalDate data, int duracao, Utilizador utilizador, int repeticoes, double amplitude) {
        super(codigo, descricao, data, duracao, utilizador, repeticoes);
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
    
    public double calorias() { 
      return this.getUtilizador().fatorMultiplicativo() * (this.amplitude/4) * this.getDuracao() * (this.getBpm()/100);
    }
    
    public int bpm(){
      return (int) (this.getUtilizador().getBpmMedio() + 10 * this.getUtilizador().fatorMultiplicativo());
    }
}
