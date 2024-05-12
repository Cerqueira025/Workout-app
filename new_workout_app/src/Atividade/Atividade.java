package Atividade;

import java.io.Serializable;
import java.time.LocalDateTime;

import Utilizador.Utilizador;
import Utilizador.TiposUtilizador.PraticanteOcasional;


public abstract class Atividade implements Serializable {
    private String codigo;
    private String descricao;
    private LocalDateTime data;
    private int duracao;
    private int bpm; //------------ bpm = utilizador.bpmMedio*utilizador.peso*(fator de atividade)
    private int series;
    private double calorias;
    private Utilizador utilizador;


    // ------------------- Construtores ------------------- //

    public Atividade() {
        this.codigo = "";
        this.descricao = "";
        this.data = LocalDateTime.of(1970, 01, 01, 00, 00 ,00);
        this.duracao = 0;
        this.bpm = 0;
        this.series = 0;
        this.calorias = 0;
        this.utilizador = new PraticanteOcasional(); /* UTILIZADOR PRATICANTE OCASIONAL*/
    }

    public Atividade(String codigo, String descricao, LocalDateTime data, int duracao, int series, Utilizador utilizador) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.data = data;
        this.duracao = duracao;
        this.bpm = 0;
        this.series = series;
        this.calorias = 0;
        this.utilizador = utilizador;
    }

    public Atividade(Atividade outro){
        this.codigo = outro.getCodigo();
        this.descricao = outro.getDescricao();
        this.data = outro.getData();
        this.duracao = outro.getDuracao();
        this.bpm = outro.getBpm();
        this.series = outro.getSeries();
        this.calorias = outro.getCalorias();
        this.utilizador = outro.getUtilizador();
    }


    // ------------------- Métodos ------------------- //


    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData(){
        return this.data;
    }

    public void setData(LocalDateTime data){
        this.data = data;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Utilizador getUtilizador() {
        return this.utilizador;
    }
    
    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public int getBpm() {
        return this.bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }
    
    public int getSeries() {
        return this.series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public double getCalorias() {
        return this.calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }
    
    public String toString() {
        return  "código = '" + this.codigo + '\'' +
                ", descrição = '" + this.descricao + '\'' +
                ", data = '" + this.data + '\'' +
                ", duração = '" + this.duracao + '\'' +
                ", bpm médio = '" + this.bpm + '\'' +
                ", séries = '" + this.series + '\'' +
                ", calorias = '" + this.calorias + '\'';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return this.duracao == atividade.getDuracao()
                && this.codigo.equals(atividade.getCodigo())
                && this.descricao.equals(atividade.getDescricao())
                && this.data.equals(atividade.getData())
                && this.bpm == atividade.getBpm()
                && this.series == atividade.getSeries()
                && Double.compare(this.calorias, atividade.getCalorias()) == 0
                && this.utilizador.equals(atividade.getUtilizador());
    }

    public abstract Atividade clone();
    public abstract double calorias();
    public abstract int bpm();
}

