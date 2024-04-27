import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import Excessoes.AtividadeNaoExisteException;
import Excessoes.EmailExisteException;
import Excessoes.UtilizadorExisteException;
import Excessoes.UtilizadorNaoExisteException;
import Utilizador.Genero;
import Utilizador.Utilizador;
import Utilizador.TiposUtilizador.PraticanteOcasional;

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

    public boolean codigoUtilizadorExiste(String codigo) {
        return this.utilizadores.containsKey(codigo);
    }

    public boolean emailUtilizadorExiste(String email) {
        return this.utilizadores.values().stream().map(u -> u.getEmail()).anyMatch(email::equals);
    }

    public boolean credenciaisCoincidem(String codigo, String password) {
        return this.utilizadores.get(codigo).getPassword().equals(password);
    }

    public void addUtilizador(Utilizador utilizador) throws UtilizadorExisteException, EmailExisteException {
        if (this.codigoUtilizadorExiste(utilizador.getCodigo())) throw new UtilizadorExisteException(utilizador.getCodigo());
        if (this.emailUtilizadorExiste(utilizador.getEmail())) throw new EmailExisteException(utilizador.getEmail());
        this.utilizadores.put(utilizador.getCodigo(), utilizador.clone());
    }

    public void criaUtilizador(String codigo, int bpmMedio, double peso, int altura,
                        String nome, Genero genero, String morada, String email, String password) throws UtilizadorExisteException, EmailExisteException {
        Utilizador utilizador = new PraticanteOcasional(codigo, bpmMedio, peso, altura,
                                                        nome, genero, morada, email, password);
        this.addUtilizador(utilizador);
    }

    public void removeUtilizador(String codigo) {
        this.utilizadores.remove(codigo);
    }

    public Utilizador getUtilizador(String codigo) {
        if(!this.utilizadores.containsKey(codigo)) return null;
        return this.utilizadores.get(codigo).clone();
    }

    public boolean atividadeExiste(String codigoUtilizador, String codigoAtividade) {
        return this.utilizadores.get(codigoUtilizador).atividadeExiste(codigoAtividade);
    }

    public void removerAtividade(String codigoUtilizador, String codigoAtividade) {
        this.utilizadores.get(codigoUtilizador).removeAtividade(codigoAtividade);
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
