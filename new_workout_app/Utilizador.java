public class Utilizador {
    
    private String nome;


    // ------------------- Construtores ------------------- //

    
    public Utilizador() {
        this.nome = "";
    }

    public Utilizador(String nome) {
        this.nome = nome;
    }

    public Utilizador(Utilizador outro) {
        this.nome = outro.getNome();
    }


    // ------------------- Métodos ------------------- //


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Utilizador clone() {
        return new Utilizador(this);
    }




}
