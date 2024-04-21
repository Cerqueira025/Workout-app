
public abstract class Atividade {
    private String codigo;
    private String descricao;
    // private LocalDate data; // não sabemos se tem utilidade
    private int duracao;
    private Utilizador user;

    public Atividade() {
        this.codigo = "";
        this.descricao = "";
        this.duracao = 0;
        this.user = new Utilizador();
    }

    public Atividade(String codigo, String descricao, int duracao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.duracao = duracao;
    }

    public Atividade(Atividade outro){
        this.codigo = outro.getCodigo();
        this.descricao = outro.getDescricao();
        this.duracao = outro.getDuracao();
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Atividade{" +
                "codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", duracao=" + duracao +
                '}';
    }

    public Utilizador getUser() {
        return user;
    }

    public void setUser(Utilizador user) {
        this.user = user;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return this.duracao == atividade.getDuracao()
                && this.codigo.equals(atividade.getCodigo())
                && this.descricao.equals(atividade.getDescricao());
    }


    public abstract double calorias();
    public  abstract  Atividade clone();
}