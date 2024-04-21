package Atividade;

import Utilizador.Utilizador;


public abstract class Atividade {
    private String codigo;
    private String descricao;
    //private String local;
    // private LocalDate data; // não sabemos se tem utilidade
    private int duracao;
    private Utilizador user;


    // ------------------- Construtores ------------------- //

    public Atividade() {
        this.codigo = "";
        this.descricao = "";
        this.duracao = 0;
        this.user = new Utilizador();
    }

    public Atividade(String codigo, String descricao, int duracao, Utilizador user) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.user = user.clone();
    }

    public Atividade(Atividade outro){
        this.codigo = outro.getCodigo();
        this.descricao = outro.getDescricao();
        this.duracao = outro.getDuracao();
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
    
    @Override
    public String toString() {
        return "Atividade{" +
                "código='" + this.codigo + '\'' +
                ", descrição='" + this.descricao + '\'' +
                ", duração=" + this.duracao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return this.duracao == atividade.getDuracao()
                && this.codigo.equals(atividade.getCodigo())
                && this.descricao.equals(atividade.getDescricao())
                && this.user.equals(atividade.getUser());
    }

    public abstract double calorias();
    public abstract Atividade clone();
}

