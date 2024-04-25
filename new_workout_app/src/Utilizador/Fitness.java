package Utilizador;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Fitness {
    private Map<String, Utilizador> utilizadores;


    // ----------------- Construtores ---------------- //

    public Fitness() {
        this.utilizadores = new HashMap<>();
    }

    public Fitness(Map<String, Utilizador> utilizadores) {
        this.utilizadores = utilizadores.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    public Fitness(Fitness outro) {
        this.utilizadores = outro.getUtilizadores();
    }

    public Map<String, Utilizador> getUtilizadores() {
        return this.utilizadores.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    public void setUtilizadores(Map<String, Utilizador> utilizadores) {
        this.utilizadores = utilizadores.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    public void addUtilizador(Utilizador utilizador) {
        this.utilizadores.put(utilizador.getCodigo(), utilizador.clone());
    }

    public void removeUtilizador(String codigo) {
        this.utilizadores.remove(codigo);
    }
    public Utilizador getUtilizador(String codigo) {
        if(!this.utilizadores.containsKey(codigo)) return null;
        return this.utilizadores.get(codigo).clone();
    }

    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        Fitness gu = (Fitness) o;
        return this.utilizadores.equals(gu.getUtilizadores());
    }

    public String toString() {
        String a = "";
        for(Utilizador utilizador : this.utilizadores.values()) {
            a += utilizador.toString() + " ";
        }
        return a;
    }

    public Fitness clone() {
        return new Fitness(this);
    }

}
