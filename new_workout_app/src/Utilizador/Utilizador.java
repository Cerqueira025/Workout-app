package Utilizador;

import Atividade.Atividade;
import Atividade.GestorAtividades;

public abstract class Utilizador {
    private int    bpmMedio;
    private int    altura; // Sem informação no guioum 
    private double peso; // Sem informação no guioum
    private String codigo;
    private String nome;
    private String morada;
    private String email;
    private String password; // Sem informação no guioum

    private GestorAtividades atividades;


    // ----------------- Construtores ---------------- //

    
    public Utilizador() {
        this.bpmMedio = 0;
        this.peso = 0;
        this.altura = 0;
        this.codigo = "";
        this.nome = "";
        this.morada = "";
        this.email = "";
        this.password = "";
        this.atividades = new GestorAtividades();
    }

    public Utilizador(String codigo, int bpmMedio, double peso, int altura,
            String nome, String morada, String email, String password,
            GestorAtividades atividades) {
        this.codigo = codigo;
        this.bpmMedio = bpmMedio;
        this.peso = peso;
        this.altura = altura;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.password = password;
        this.atividades = atividades.clone();
    }

    public Utilizador(Utilizador outro) {
        this.codigo = outro.getCodigo();
        this.bpmMedio = outro.getBpmMedio();
        this.peso = outro.getPeso();
        this.altura = outro.getAltura();
        this.nome = outro.getNome();
        this.morada = outro.getMorada();
        this.email = outro.getEmail();
        this.password = outro.getPassword();
        this.atividades = outro.getAtividades();
    }


    // ------------------- Métodos ------------------- //


    public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getBpmMedio() {
		return this.bpmMedio;
	}

	public void setBpmMedio(int bpmMedio) {
		this.bpmMedio = bpmMedio;
	}

	public double getPeso() {
		return this.peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return this.altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public String getMorada() {
		return this.morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GestorAtividades getAtividades() {
		return this.atividades.clone();
	}

	public void setAtividades(GestorAtividades atividades) {
		this.atividades = atividades.clone();
	}

	public void addAtividade(Atividade atividade) {
		this.atividades.addAtividade(atividade);
	}


    public String toString() {
        return "Utiliador{" +
                "código='" + this.codigo + '\'' +
                ", nome='" + this.nome + '\'' +
                ", morada='" + this.morada + '\'' +
                ", email='" + this.email + '\'' +
                /*", password='" + this.password + '\'' + */
                ", bpm='" + this.bpmMedio + '\'' +
                ", altura='" + this.altura + '\'' +
                ", peso='" + this.peso + '\'' +
                ", atividades='" + this.atividades.toString() +
                '}';
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Utilizador outro = (Utilizador) o;
        return this.codigo.equals(outro.getCodigo())
                && this.bpmMedio == outro.getBpmMedio()
                && Double.compare(this.peso, outro.getPeso()) == 0
                && this.altura == outro.getAltura()
                && this.nome.equals(outro.getNome())
                && this.morada.equals(outro.getMorada())
                && this.email.equals(outro.getEmail())
                && this.password.equals(outro.getPassword())
                && this.atividades.equals(outro.getAtividades());
    }

    public abstract Utilizador clone();
    public abstract double fatorMultiplicativo();



}
