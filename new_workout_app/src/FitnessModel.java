import Utilizador.Utilizador;
import Utilizador.Genero;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FitnessModel {
    private Map<String, Utilizador> utilizadores;


    // ----------------- Construtores ---------------- //

    public FitnessModel() {
        this.utilizadores = new HashMap<>();
    }

    public FitnessModel(Map<String, Utilizador> utilizadores) {
        this.utilizadores = utilizadores.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    public FitnessModel(FitnessModel outro) {
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

    public void createUtilizador(String codigo, int bpmMedio, double peso, 
            int altura, String nome, Genero genero, String morada, 
            String email, String password) {
        Utilizador novoUtilizador = new Utilizador(codigo, bpmMedio, peso, altura,
            nome, genero, morada, email, password);
        addUtilizador(novoUtilizador);
    }






    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        FitnessModel fm = (FitnessModel) o;
        return this.utilizadores.equals(fm.getUtilizadores());
    }

    public String toString() {
        String a = "";
        for(Utilizador utilizador : this.utilizadores.values()) {
            a += utilizador.toString() + " ";
        }
        return a;
    }

    public FitnessModel clone() {
        return new FitnessModel(this);
    }

}
