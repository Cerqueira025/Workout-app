package Utilizador;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorUtilizadores {
    private Map<String, Utilizador> utilizadores;

    public GestorUtilizadores() {
        this.utilizadores = new HashMap<>();
    }

    public GestorUtilizadores(Map<String, Utilizador> utilizadores) {
        this.utilizadores = utilizadores.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    public GestorUtilizadores(GestorUtilizadores outro) {
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
        GestorUtilizadores gu = (GestorUtilizadores) o;
        return this.utilizadores.equals(gu.getUtilizadores());
    }

    public String toString() {
        String a = "";
        for(Utilizador utilizador : this.utilizadores.values()) {
            a += utilizador.toString() + " ";
        }
        return a;
    }

    public GestorUtilizadores clone() {
        return new GestorUtilizadores(this);
    }

}
