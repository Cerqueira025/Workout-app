package Atividade;

import java.time.LocalDate;
import Utilizador.Utilizador;
import Utilizador.TiposUtilizador.Amador;


public abstract class Atividade {
    private String codigo;
    private String descricao;
    //private String local;
    private LocalDate data;
    private int duracao;
    private int bpmAtividade; //------------ bpmAtividade = user.bpmMedio*user.peso*(fator de atividade)
    private Utilizador user;


    // ------------------- Construtores ------------------- //

    public Atividade() {
        this.codigo = "";
        this.descricao = "";
        this.data = LocalDate.EPOCH;
        this.duracao = 0;
        this.bpmAtividade = 0;
        this.user = new Amador(); /* UTILIZADOR PRATICANTE OCASIONAL*/
    }

    public Atividade(String codigo, String descricao, LocalDate data, int duracao, Utilizador user) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.data = data;
        this.duracao = duracao;
        this.bpmAtividade = this.getBpm();
        this.user = user.clone();
    }

    public Atividade(Atividade outro){
        this.codigo = outro.getCodigo();
        this.descricao = outro.getDescricao();
        this.data = outro.getData();
        this.duracao = outro.getDuracao();
        this.bpmAtividade = outro.getBpm();
        this.user = outro.getUser();
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

    public LocalDate getData(){
        return this.data;
    }

    public void setData(LocalDate data){
        this.data = data;
    }

    public int getDuracao() {
        return this.duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Utilizador getUser() {
        return this.user.clone();
    }
    
    public void setUser(Utilizador user) {
        this.user = user.clone();
    }
    
    public String toString() {
        return "Atividade{" +
                "código='" + this.codigo + '\'' +
                ", descrição='" + this.descricao + '\'' +
                ", data= '" + this.data + '\'' +
                ", duração='" + this.duracao +
                ", bpm médio='" + this.bpmAtividade +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return this.duracao == atividade.getDuracao()
                && this.codigo.equals(atividade.getCodigo())
                && this.descricao.equals(atividade.getDescricao())
                && this.data.equals(atividade.getData())
                && this.bpmAtividade == atividade.getBpm()
                && this.user.equals(atividade.getUser());
    }

    public abstract Atividade clone();
    public abstract double calorias();
    public abstract int getBpm();
}

