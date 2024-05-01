import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import Atividade.Atividade;
import Atividade.Distancia.Distancia;
import Atividade.Distancia.Altimetria.Altimetria;
import Atividade.Repeticoes.Abdominais;
import Atividade.Repeticoes.Repeticoes;
import Atividade.Repeticoes.Pesos.Pesos;
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

    public void criaUtilizador(String codigo, int bpmMedio, double peso, double caloriasGastas, int altura,
                        String nome, Genero genero, String morada, String email, String password) throws UtilizadorExisteException, EmailExisteException {
        Utilizador utilizador = new PraticanteOcasional(codigo, bpmMedio, peso, caloriasGastas, altura,
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

    public boolean existeAtividade(String codigoUtilizador, String codigoAtividade) {
        return this.utilizadores.get(codigoUtilizador).existeAtividade(codigoAtividade);
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


    // ----------------- Plano de treino ---------------- //

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

    // ----------------- Outras queries e auxiliares ---------------- //

    private double estatisticaAcumulacaoPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim, Predicate<Atividade> insOf, ToDoubleFunction<Atividade> fun) { // verificar se datas sao válidas
        Predicate<Atividade> predicate = a -> (a.getData().toLocalDate().compareTo(inicio) >= 0 && a.getData().toLocalDate().compareTo(fim) <= 0);
        return this.utilizadores
               .get(codigoUtilizador)
               .getAtividades()
               .values()
               .stream()
               .filter(predicate) // filtrar atividades que estão enquadradas na data limite
               .filter(insOf) // ex: a -> a instanceof Distancia
               .mapToDouble(fun) // ex: a -> ((Distancia) a).getDistancia()
               .sum();
    }
    
    public double caloriasGastasPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        return this.estatisticaAcumulacaoPeriodo(codigoUtilizador, inicio, fim, (a -> a instanceof Atividade), a -> a.calorias());
    }

    public int repeticoesAcumuladaPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        return (int) this.estatisticaAcumulacaoPeriodo(codigoUtilizador, inicio, fim, (a -> a instanceof Repeticoes), a -> ((Repeticoes) a).getRepeticoes());
    }

    public double pesoAcumuladaPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        return this.estatisticaAcumulacaoPeriodo(codigoUtilizador, inicio, fim, (a -> a instanceof Pesos), a -> ((Pesos) a).getPeso());
    }

    public int numeroAtividadesPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        Predicate<Atividade> predicate = a -> (a.getData().toLocalDate().compareTo(inicio) >= 0 && a.getData().toLocalDate().compareTo(fim) <= 0);
        return (int) this.utilizadores.get(codigoUtilizador).getAtividades().values().stream().filter(predicate).count();
    }
    
    // ----------------- Queries ---------------- //

    // 1. qual é o utilizador que mais calorias dispendeu num período ou desde sempre
    public Utilizador utilizadorMaisCalorias(LocalDate inicio, LocalDate fim) { // referência no relatório
        Comparator<Utilizador> comparator = (u1, u2) -> {
                                                            double dif = this.caloriasGastasPeriodo(u2.getCodigo(), inicio, fim) - this.caloriasGastasPeriodo(u1.getCodigo(), inicio, fim);
                                                            return Double.compare(dif,0);
                                                        };
        
        return this.utilizadores.values().stream().sorted(comparator).findFirst().orElse(null);
    }
    
    // 2. qual o utilizador que mais actividades realizou num período ou desde sempre
    public Utilizador utilizadorComMaisAtividades(LocalDate inicio, LocalDate fim) {
        Comparator<Utilizador> comparator = (u1, u2) -> this.numeroAtividadesPeriodo(u2.getCodigo(), inicio, fim) - this.numeroAtividadesPeriodo(u1.getCodigo(), inicio, fim);
        return this.utilizadores.values().stream().sorted(comparator).findFirst().orElse(null);
    }

    // 3. qual o tipo de actividade mais realizada
    public String atividadeMaisRealizada() {
        Map<String,Integer> nomeAtividades = new HashMap<>();
        for (Utilizador u : this.utilizadores.values()) {
            for (Atividade a : u.getAtividades().values()) {
                String nome = a.getClass().getSimpleName();
                if (nomeAtividades.containsKey(nome)) nomeAtividades.replace(nome,nomeAtividades.get(nome)+1);
                else nomeAtividades.put(nome, 1);
            }
        }

        String atividadeMaisRealizada = "";
        int max = -1;
        for (Map.Entry<String,Integer> e : nomeAtividades.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                atividadeMaisRealizada = e.getKey();
            }
        }

        return atividadeMaisRealizada;
    }

    
    // 4. quantos kms é que um utilizdor realizou num período ou desde sempre
    public double distanciaPercorridaPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        return this.estatisticaAcumulacaoPeriodo(codigoUtilizador, inicio, fim, (a -> a instanceof Distancia), a -> ((Distancia) a).getDistancia());
    }
    
    // 5. quantos metros de altimetria é que um utilizar totalizou num período ou desde sempre
    public int altimetriaAcumuladaPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        return (int) this.estatisticaAcumulacaoPeriodo(codigoUtilizador, inicio, fim, (a -> a instanceof Altimetria), a -> ((Altimetria) a).getAltimetria());
    }
    
    // 6. qual o plano de treino mais exigente em função do dispêndio de calorias proposto
    public PlanoDeTreino planoDeTreinoMaisExigente() {
        Comparator<Utilizador> comparator = (u1, u2) -> {
                                                            double dif = u2.getPlanoDeTreino().mediaCaloriasSemanal() - u1.getPlanoDeTreino().mediaCaloriasSemanal();
                                                            return Double.compare(dif, 0);
                                                        };
        return this.utilizadores.values().stream().sorted(comparator).findFirst().orElse(null).getPlanoDeTreino();
    }    
    
    // 7. listar as actividades de um utilizador
    public Map<String,Atividade> atividadesRealizadas(String codigoUtilizador) {
        return this.utilizadores.get(codigoUtilizador).getAtividades();
    }

    // (Bónus) listar as actividades do plano de treino de um utilizador
    public Map<String,Atividade> atividadesPlanoDeTreino(String codigoUtilizador) {
        return this.utilizadores.get(codigoUtilizador).getAtividadesPlanoDeTreino();
    }


    // ----------------- Salto no tempo ---------------- //

    public void saltoNoTempo(int dias) {
        LocalDate proximaData = this.dataAtual.plusDays(dias);

        for(Utilizador u : this.utilizadores.values()) {
            for(Atividade a : u.getAtividades().values()) {
                if(a.getData().toLocalDate().compareTo(proximaData) == -1) {
                    double caloriasAtividade = a.calorias();

                    a.setBpm(a.bpm());
                    a.setCalorias(caloriasAtividade);

                    u.removeAtividadePlanoDeTreino(a.getCodigo());
                    u.addAtividade(a);
                    
                    u.atualizaCaloriasGastas(caloriasAtividade);
                    u.atualizaPeso(caloriasAtividade);
                    // ATUALIZA RECORDES
                }
            }
        }
        this.dataAtual = proximaData;
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
