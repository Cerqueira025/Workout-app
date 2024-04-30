import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Atividade.Atividade;
import Atividade.Repeticoes.Abdominais;
import Excessoes.EmailExisteException;
import Excessoes.UtilizadorExisteException;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Genero;
import Utilizador.Utilizador;
import Utilizador.TiposUtilizador.PraticanteOcasional;

public class FitnessModel implements Serializable {
    private LocalDate dataAtual;
    private Map<String, Utilizador> utilizadores;


    // ----------------- Construtores ---------------- //

    public FitnessModel() {
        this.dataAtual = LocalDate.EPOCH;
        this.utilizadores = new HashMap<>();
    }

    public FitnessModel(LocalDate data, Map<String, Utilizador> utilizadores) {
        this.dataAtual = data;
        this.utilizadores = utilizadores.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue().clone()));
    }

    public FitnessModel(FitnessModel outro) {
        this.dataAtual = outro.getData();
        this.utilizadores = outro.getUtilizadores();
    }

    // ----------------- Getters e Setters ---------------- //

    public LocalDate getData() {
        return this.dataAtual;
    }

    public void setData(LocalDate data) {
        this.dataAtual = data;
    }
    
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

    private void addUtilizador(Utilizador utilizador) throws UtilizadorExisteException, EmailExisteException {
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

    // ESTE MÉTODO ESTÁ ERRADO
    public void criaAtividade(String codigoUtilizador, String codigoAtividade, String descricao, LocalDateTime data, int duracao) {
        Atividade a = new Abdominais(codigoAtividade, descricao, data, duracao, -100, this.utilizadores.get(codigoUtilizador), 10, 45.2); // -100 tem de ser substituido por nIterações
        this.addAtividade(codigoUtilizador, a);
    }

    public void removerAtividade(String codigoUtilizador, String codigoAtividade) {
        this.utilizadores.get(codigoUtilizador).removeAtividade(codigoAtividade);
    }

    public List<Atividade> getAtividadesRealizadas(String codUtilizador) {
        return this.utilizadores.get(codUtilizador).getAtividadesList();
    }


    // ----------------- Plano de treinos ---------------- //

    public void setPlanoDeTreino(String codUtilizador, PlanoDeTreino planoDeTreino) {
        this.utilizadores.get(codUtilizador).setPlanoDeTreino(planoDeTreino);
    }

    public void limparPlanoDeTreino(String codUtilizador) {
        this.utilizadores.get(codUtilizador).limparPlanoDeTreino();
    }

    public void addAtividadePlanoDeTreino(String codUtilizador, Atividade atividade) {
        this.utilizadores.get(codUtilizador).addAtividadePlanoDeTreino(atividade);
    }

    public void removeAtividadePlanoDeTreino(String codUtilizador, String codAtividade) {
        this.utilizadores.get(codUtilizador).removeAtividadePlanoDeTreino(codAtividade);
    }

    public PlanoDeTreino getPlanoDeTreino(String codUtilizador) {
        return this.utilizadores.get(codUtilizador).getPlanoDeTreino();
    }

    // ----------------- Salto no tempo ---------------- //

    public double saltoNoTempo(LocalDate proximaData, String codUtilizador) {
        this.dataAtual = proximaData;
        return this.utilizadores.get(codUtilizador).saltoNoTempo(proximaData);
    }


    // ----------------- Gerais ---------------- //
    
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        FitnessModel fm = (FitnessModel) o;
        return this.dataAtual.equals(fm.getData())
            && this.utilizadores.equals(fm.getUtilizadores());
    }

    public String toString() {
        String a = "Data atual='" + this.dataAtual + '\'';
        for(Utilizador utilizador : this.utilizadores.values()) {
            a += utilizador.toString() + " ";
        }
        return a;
    }

    public FitnessModel clone() {
        return new FitnessModel(this);
    }

    
    // ----------------- Guardar e carregar estados ---------------- //

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
