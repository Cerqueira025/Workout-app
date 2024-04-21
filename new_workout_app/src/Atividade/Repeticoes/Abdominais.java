package Atividade.Repeticoes;

import Utilizador.Utilizador;

public class Abdominais extends Repeticoes {
    
    private double amplitude;

    
    // ------------------- Construtores ------------------- //


    public Abdominais() {
        super();
        this.amplitude = 0;
    }

    public Abdominais(String codigo, String descricao, int duracao, Utilizador user, int repeticoes, double amplitude) {
        super(codigo, descricao, duracao, user, repeticoes);
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

    @Override
    public String toString() {
        return "Abdominais{" +
                super.toString()+
                "amplitude=" + this.amplitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Abdominais abdominais = (Abdominais) o;
        return super.equals(abdominais)
                && Double.compare(this.amplitude, abdominais.getAmplitude()) == 0;
    }

    @Override
    public double calorias() { // ESTE MÉTODO ESTÁ TOTALMENTE INCORRETO *********************************
        return 218.2;
    }

    @Override
    public Abdominais clone() {
        return new Abdominais(this);
    }
}
