import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import Atividade.Atividade;
import Atividade.Repeticoes.Abdominais;
import Excessoes.EmailExisteException;
import Excessoes.UtilizadorExisteException;
import Utilizador.Genero;
import Utilizador.Utilizador;
import Utilizador.TiposUtilizador.PraticanteOcasional;

public class FitnessModel implements Serializable {
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

    // ----------------- Getters e Setters ---------------- //

    public Map<String, Utilizador> getUtilizadores() {
        return this.utilizadores.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    public void setUtilizadores(Map<String, Utilizador> utilizadores) {
        this.utilizadores = utilizadores.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    // ----------------- Utilizador ---------------- //

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



    // ----------------- Atividades ---------------- //

    public boolean atividadeExiste(String codigoUtilizador, String codigoAtividade) {
        return this.utilizadores.get(codigoUtilizador).atividadeExiste(codigoAtividade);
    }

    public void addAtividade(String codigoUtilizador, Atividade atividade) {
        this.utilizadores.get(codigoUtilizador).addAtividade(atividade);
    }

    public void criaAtividade(String codigoUtilizador, String codigoAtividade, String descricao, LocalDate data, int duracao) {
        Atividade a = new Abdominais(codigoAtividade, descricao, data, duracao, this.utilizadores.get(codigoUtilizador), 10, 45.2);
        this.addAtividade(codigoUtilizador, a);
    }

    public void removerAtividade(String codigoUtilizador, String codigoAtividade) {
        this.utilizadores.get(codigoUtilizador).removeAtividade(codigoAtividade);
    }


    // ----------------- Plano de treinos ---------------- //

    // ----------------- Gerais ---------------- //
    
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

    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException, IOException {
        FileOutputStream file_output = new FileOutputStream(nomeFicheiro);
        ObjectOutputStream object_output = new ObjectOutputStream(file_output);

        object_output.writeObject(this);

        object_output.flush();
        object_output.close();
    }

    public static FitnessModel carregaEstado(String nomeFicheiro) throws IOException, ClassNotFoundException {
        FileInputStream file_input = new FileInputStream(nomeFicheiro);
        ObjectInputStream object_input = new ObjectInputStream(file_input);

        FitnessModel novoModel = (FitnessModel) object_input.readObject();

        object_input.close();

        return novoModel;
    }
}
