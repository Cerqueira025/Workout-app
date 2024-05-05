package Utilizador;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;

public abstract class Utilizador implements Serializable {
    private int    bpmMedio;
    private int    altura; 
    private double peso;
    private double caloriasGastas;
    private String codigo;
    private String nome;
    private Genero genero;
    private String morada;
    private String email;
    private String password;
    private Map<String, Atividade> atividades;
    private PlanoDeTreino plano;


    // ----------------- Construtores ---------------- //
    
    public Utilizador() {
        this.bpmMedio = 0;
        this.peso = 0;
        this.caloriasGastas = 0;
        this.altura = 0;
        this.codigo = "";
        this.nome = "";
        this.genero = Genero.Outro;
        this.morada = "";
        this.email = "";
        this.password = "";
        this.atividades = new HashMap<>();
        this.plano = new PlanoDeTreino();
    }

    public Utilizador(String codigo, int bpmMedio, double peso, double caloriasGastas, int altura,
            String nome, Genero genero, String morada, String email, String password,
            Map<String, Atividade> atividades, PlanoDeTreino plano) {
        this.codigo = codigo;
        this.bpmMedio = bpmMedio;
        this.peso = peso;
        this.caloriasGastas = caloriasGastas;
        this.altura = altura;
        this.nome = nome;
        this.genero = genero;
        this.morada = morada;
        this.email = email;
        this.password = password;
        this.atividades = atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
        this.plano = plano; //verificar clone
    }

    public Utilizador(String codigo, int bpmMedio, double peso, double caloriasGastas, int altura,
            String nome, Genero genero, String morada, String email, String password) {
        this.codigo = codigo;
        this.bpmMedio = bpmMedio;
        this.peso = peso;
        this.caloriasGastas = caloriasGastas;
        this.altura = altura;
        this.nome = nome;
        this.genero = genero;
        this.morada = morada;
        this.email = email;
        this.password = password;
        this.atividades = new HashMap<>();
        this.plano = new PlanoDeTreino();
    }

    public Utilizador(Utilizador outro) {
        this.codigo = outro.getCodigo();
        this.bpmMedio = outro.getBpmMedio();
        this.peso = outro.getPeso();
        this.caloriasGastas = outro.getCaloriasGastas();
        this.altura = outro.getAltura();
        this.nome = outro.getNome();
        this.genero = outro.getGenero();
        this.morada = outro.getMorada();
        this.email = outro.getEmail();
        this.password = outro.getPassword();
        this.atividades = outro.getAtividades();
        this.plano = outro.getPlanoDeTreino();
    }


    // ----------------- Métodos getters e setters ----------------- //

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

    public double getCaloriasGastas() {
        return this.caloriasGastas;
    }

    public void setCaloriasGastas(double caloriasGastas) {
        this.caloriasGastas = caloriasGastas;
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

    public Genero getGenero() {
        return this.genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
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
  
    public PlanoDeTreino getPlanoDeTreino() {
        return this.plano;
    }

    public void setPlanoDeTreino(PlanoDeTreino plano) {
        this.plano = plano;
    }

	public Map<String, Atividade> getAtividades() {
		return this.atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
	}

	public void setAtividades(Map<String, Atividade> atividades) {
        this.atividades = atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
	}

    public List<Atividade> getAtividadesList() {
        return this.atividades.values().stream().map(a -> a).collect(Collectors.toList());
    }
    
    public Atividade getAtividade(String codigo_atividade) {
        if(!existeAtividade(codigo_atividade)) return null;
        return this.atividades.get(codigo_atividade);
    }
    
    public Map<String, Atividade> getAtividadesPlanoDeTreino() {
        return this.plano.getAtividades();
    }

    public Atividade getAtividadePlanoDeTreino(String codigoAtividade) {
        return this.plano.getAtividade(codigoAtividade);
    }

    // ----------------- outros métodos ----------------- //


	public void addAtividade(Atividade atividade) {
        this.atividades.put(atividade.getCodigo(), atividade);
    }

    public void removeAtividade(String codigo_atividade) {
        this.atividades.remove(codigo_atividade);
    }

    public boolean existeAtividade(String codigo) {
        return this.atividades.containsKey(codigo);
    }

    public void addAtividadePlanoDeTreino(Atividade atividade) {
        this.plano.addAtividade(atividade);
    }

    public void removeAtividadePlanoDeTreino(String codAtividade) {
        this.plano.removeAtividade(codAtividade);
    }

    public boolean existeAtividadePlanoDeTreino(String codigo) {
        return this.plano.existeAtividade(codigo);
    }

    public void limparPlanoDeTreino() {
        this.plano = new PlanoDeTreino();
    }

    public void atualizaCaloriasGastas(double caloriasAtividade) {
        this.caloriasGastas += caloriasAtividade;
    }

    public void atualizaPeso(double caloriasAtividade) {
        this.peso -= caloriasAtividade/7000;
    }



    public String toString() {
        String a = "Utiliador{" +
                "código='" + this.codigo + '\'' +
                ", nome='" + this.nome + '\'' +
                ", genero='" + this.genero + '\'' +
                ", morada='" + this.morada + '\'' +
                ", email='" + this.email + '\'' +
                /*", password='" + this.password + '\'' + */
                ", bpm='" + this.bpmMedio + '\'' +
                ", altura='" + this.altura + '\'' +
                ", peso='" + this.peso + '\'' +
                ", calorias gastas='" + this.caloriasGastas + '\'' +
                ", plano de treino=' " + this.plano.toString() + '\'' +
                ", atividades={";
        for(Atividade atividade : this.atividades.values()) {
           a += atividade.toString() + ",";
        }   
        a += "}";
        return a;
    }
    

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Utilizador outro = (Utilizador) o;
        return this.codigo.equals(outro.getCodigo())
                && this.bpmMedio == outro.getBpmMedio()
                && Double.compare(this.peso, outro.getPeso()) == 0
                && Double.compare(this.caloriasGastas, outro.getCaloriasGastas()) == 0
                && this.altura == outro.getAltura()
                && this.nome.equals(outro.getNome())
                && this.genero == outro.getGenero()
                && this.morada.equals(outro.getMorada())
                && this.email.equals(outro.getEmail())
                && this.password.equals(outro.getPassword())
                && this.atividades.equals(outro.getAtividades())
                && this.plano.equals(outro.getPlanoDeTreino());
    }

    public abstract Utilizador clone();
    public abstract double fatorMultiplicativo();



}
