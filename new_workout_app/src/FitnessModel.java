import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;


import Atividade.Atividade;
import Atividade.Hard;
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
    private Map<String, Utilizador> recordesGerais;
    
    private Map<String, Predicate<Atividade>> predicatesAtividade;
    private Map<String, ToDoubleFunction<Atividade>> funcoesAtividade;
    

    // ----------------- Construtores ---------------- //

    public FitnessModel() {
        this.dataAtual = LocalDate.EPOCH;
        this.utilizadores = new HashMap<>();
        this.recordesGerais = new HashMap<>();
        this.predicatesAtividade = new HashMap<>();
        this.funcoesAtividade = new HashMap<>();
    }

    public FitnessModel(LocalDate data, Map<String, Utilizador> utilizadores, Map<String, Utilizador> recordesGerais,
                        Map<String, Predicate<Atividade>> predicatesAtividade, Map<String, ToDoubleFunction<Atividade>> funcoesAtividade) {
        this.dataAtual = data;
        this.utilizadores = utilizadores.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
        this.recordesGerais = recordesGerais.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
        this.predicatesAtividade = predicatesAtividade.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
        this.funcoesAtividade = funcoesAtividade.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
    }

    public FitnessModel(FitnessModel outro) {
        this.dataAtual = outro.getData();
        this.utilizadores = outro.getUtilizadores();
        this.recordesGerais = outro.getRecordesGerais();
        this.predicatesAtividade = outro.getPredicatesAtividade();
        this.funcoesAtividade = outro.getFuncoesAtividade();
    }

    // ----------------- Getters e Setters ---------------- //

    public LocalDate getData() {
        return this.dataAtual;
    }

    public void setData(LocalDate data) {
        this.dataAtual = data;
    }
    
    public Map<String, Utilizador> getUtilizadores() {
        return this.utilizadores.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
    }

    public void setUtilizadores(Map<String, Utilizador> utilizadores) {
        this.utilizadores = utilizadores.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
    }

    public Map<String, Utilizador> getRecordesGerais() {
        return this.recordesGerais.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
    }

    public void setRecordesGerais(Map<String, Utilizador> recordesGerais) {
        this.recordesGerais = recordesGerais.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
    }
    
    public Map<String, Predicate<Atividade>> getPredicatesAtividade() {
        return this.predicatesAtividade.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
    } 

    public void setPredicatesAtividade(Map<String, Predicate<Atividade>> predicatesAtividade) {
        this.predicatesAtividade = predicatesAtividade.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
    } 
    
    public Map<String, ToDoubleFunction<Atividade>> getFuncoesAtividade() {
        return this.funcoesAtividade.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
    }

    public void setFuncoesAtividade(Map<String, ToDoubleFunction<Atividade>> funcoesAtividade) {
        this.funcoesAtividade = funcoesAtividade.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
    } 
     
    // ----------------- Métodos para Predicates e ToDoubleFunctions ---------------- //

    public void addToDoubleFunction(String nomeFuncao, ToDoubleFunction<Atividade> tdf) {
        this.funcoesAtividade.put(nomeFuncao, tdf);
    } 
    
    public ToDoubleFunction<Atividade> getToDoubleFunction(String nomeFuncao) {
        return this.funcoesAtividade.get(nomeFuncao);
    } 

    public void addPredicate(String nomePredicate, Predicate<Atividade> p) {
        this.predicatesAtividade.put(nomePredicate, p);
    } 
    
    public Predicate<Atividade> getPredicate(String nomePredicate) {
        return this.predicatesAtividade.get(nomePredicate);
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
        this.utilizadores.put(utilizador.getCodigo(), utilizador);
    }

    /*
    public void criaUtilizador(String codigo, int bpmMedio, double peso, double caloriasGastas, int altura,
                        String nome, Genero genero, String morada, String email, String password) throws UtilizadorExisteException, EmailExisteException {
        
        Utilizador utilizador = new PraticanteOcasional(codigo, bpmMedio, peso, caloriasGastas, altura,
                                                        nome, genero, morada, email, password);
        this.addUtilizador(utilizador);
    }
    */

    // colocar throw UtilizadorNaoExisteException
    public void removeUtilizador(String codigo) {
        this.utilizadores.remove(codigo);
    }

    // colocar throw UtilizadorNaoExisteException
    public Utilizador getUtilizador(String codigo) {
        return this.utilizadores.get(codigo);
    }


    // ----------------- Atividades ---------------- //

    public boolean existeAtividade(String codigoUtilizador, String codigoAtividade) {
        return this.utilizadores.get(codigoUtilizador).existeAtividade(codigoAtividade);
    }

    // colocar throw AtividadeExisteException e UtilizadorNaoExisteException
    public void addAtividade(String codigoUtilizador, Atividade atividade) {
        this.utilizadores.get(codigoUtilizador).addAtividade(atividade);
    }

    // colocar throw AtividadeExisteException e UtilizadorNaoExisteException
    public void addAtividadeRealizada(String codigoUtilizador, Atividade atividade) {
        double caloriasAtividade = atividade.calorias();
        Utilizador utilizador = this.getUtilizadores().get(codigoUtilizador);

        atividade.setBpm(atividade.bpm());
        atividade.setCalorias(caloriasAtividade);
        
        this.addAtividade(codigoUtilizador, atividade);
        
        utilizador.atualizaCaloriasGastas(caloriasAtividade);
        utilizador.atualizaPeso(caloriasAtividade);
        utilizador.atualizaRecordesCalorias(atividade);
        this.atualizaRecordesGerais(utilizador);
    }

    /*
    public void criaAtividade(String codigoUtilizador, String codigoAtividade, String descricao, LocalDateTime data, int duracao) {
        Atividade a = new Abdominais(codigoAtividade, descricao, data, duracao, -100, this.utilizadores.get(codigoUtilizador), 10, 45.2); // -100 tem de ser substituido por nIterações
        this.addAtividade(codigoUtilizador, a);
    }
    */

    // colocar throw AtividadeNaoExisteException e UtilizadorNaoExisteException
    public void removerAtividade(String codigoUtilizador, String codigoAtividade) {
        this.utilizadores.get(codigoUtilizador).removeAtividade(codigoAtividade);
    }

    // colocar throw UtilizadorNaoExisteException e NaoExistemAtividadesRealizadasException
    public List<Atividade> getAtividadesRealizadas(String codUtilizador) {
        return this.utilizadores.get(codUtilizador).getAtividadesList();
    }

    // ----------------- Plano de treino ---------------- //

    // colocar throw UtilizadorNaoExisteException
    public void setPlanoDeTreino(String codUtilizador, PlanoDeTreino planoDeTreino) {
        this.utilizadores.get(codUtilizador).setPlanoDeTreino(planoDeTreino);
    }

    // colocar throw UtilizadorNaoExisteException
    public void limparPlanoDeTreino(String codUtilizador) {
        this.utilizadores.get(codUtilizador).limparPlanoDeTreino();
    }

    // colocar throw UtilizadorNaoExisteException e AtividadeExisteException
    public void addAtividadePlanoDeTreino(String codUtilizador, Atividade atividade) {
        atividade.setBpm(atividade.bpm());
        atividade.setCalorias(atividade.calorias());
        this.utilizadores.get(codUtilizador).addAtividadePlanoDeTreino(atividade);
    }

    // colocar throw UtilizadorNaoExisteException e AtividadeNaoExisteException
    public void removeAtividadePlanoDeTreino(String codUtilizador, String codAtividade) {
        this.utilizadores.get(codUtilizador).removeAtividadePlanoDeTreino(codAtividade);
    }

    // colocar throw UtilizadorNaoExisteException
    public PlanoDeTreino getPlanoDeTreino(String codUtilizador) {
        return this.utilizadores.get(codUtilizador).getPlanoDeTreino();
    }

    // colocar throw UtilizadorNaoExisteException e PlanoDeTreinoVazioException
    public Map<String, Atividade> getAtividadesPlanoDeTreino(String codUtilizador) {
        return this.utilizadores.get(codUtilizador).getAtividadesPlanoDeTreino();
    }

    // ----------------- Outras queries e auxiliares ---------------- //

    // colocar throw UtilizadorNaoExisteException e NaoExistemAtividadesRealizadasException *****VALIDAR DATAS NO DELEGATE*****
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
    
    // usar predicates e toDoubleFunctions
    /* 
    public double caloriasGastasPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        return this.estatisticaAcumulacaoPeriodo(codigoUtilizador, inicio, fim, (a -> a instanceof Atividade), a -> a.calorias());
    }

    
    public int repeticoesAcumuladaPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        return (int) this.estatisticaAcumulacaoPeriodo(codigoUtilizador, inicio, fim, (a -> a instanceof Repeticoes), a -> ((Repeticoes) a).getRepeticoes());
    }

    public double pesoAcumuladaPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        return this.estatisticaAcumulacaoPeriodo(codigoUtilizador, inicio, fim, (a -> a instanceof Pesos), a -> ((Pesos) a).getPeso());
    }
    */

    // colocar throw UtilizadorNaoExisteException e NaoExistemAtividadesRealizadasException *****VALIDAR DATAS NO DELEGATE*****
    public int numeroAtividadesPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        Predicate<Atividade> predicate = a -> (a.getData().toLocalDate().compareTo(inicio) >= 0 && a.getData().toLocalDate().compareTo(fim) <= 0);
        return (int) this.utilizadores.get(codigoUtilizador).getAtividades().values().stream().filter(predicate).count();
    }

    // colocar throw NomeAtividadeNaoExisteException
    public double caloriasRecordeAtividade(String nomeAtividade) {
        return this.recordesGerais.get(nomeAtividade).caloriasRecordeAtividade(nomeAtividade);
    }

    // colocar throw NomeAtividadeNaoExisteException e NaoExistemRecordesException
    public void atualizaRecordesGerais(Utilizador utilizador) {
        for(Map.Entry<String,Double> parRecordeCalorias : utilizador.getRecordesAtividades().entrySet()) {
            String nomeAtividade = parRecordeCalorias.getKey();
            Double caloriasAtividade = parRecordeCalorias.getValue();

            if(!this.recordesGerais.containsKey(nomeAtividade)) 
                this.recordesGerais.put(nomeAtividade, utilizador);
            else if(caloriasAtividade > caloriasRecordeAtividade(nomeAtividade)) 
                this.recordesGerais.replace(nomeAtividade, utilizador);
        }
    }
    
    // ----------------- Queries ---------------- //


    // *****VALIDAR DATAS NO DELEGATE*****
    // 1. qual é o utilizador que mais calorias dispendeu num período ou desde sempre
    public Utilizador utilizadorMaisCalorias(LocalDate inicio, LocalDate fim) { // referência no relatório
        Comparator<Utilizador> comparator = (u1, u2) -> {
                                                            double dif = this.estatisticaAcumulacaoPeriodo(u2.getCodigo(), inicio, fim, a -> a instanceof Atividade, a -> a.calorias())
                                                                       - this.estatisticaAcumulacaoPeriodo(u1.getCodigo(), inicio, fim, a -> a instanceof Atividade, a -> a.calorias());
                                                            return Double.compare(dif,0);
                                                        };
        
        return this.utilizadores.values().stream().sorted(comparator).findFirst().orElse(null);
    }
    
    // *****VALIDAR DATAS NO DELEGATE*****
    // 2. qual o utilizador que mais actividades realizou num período ou desde sempre
    public Utilizador utilizadorComMaisAtividades(LocalDate inicio, LocalDate fim) {
        Comparator<Utilizador> comparator = (u1, u2) -> this.numeroAtividadesPeriodo(u2.getCodigo(), inicio, fim) - this.numeroAtividadesPeriodo(u1.getCodigo(), inicio, fim);
        return this.utilizadores.values().stream().sorted(comparator).findFirst().orElse(null);
    }

    // colocar throw NaoExistemAtividadesRealizadasException
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

    // usar predicates e toDoubleFunctions
    /* 
    // 4. quantos kms é que um utilizdor realizou num período ou desde sempre
    public double distanciaPercorridaPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        return this.estatisticaAcumulacaoPeriodo(codigoUtilizador, inicio, fim, (a -> a instanceof Distancia), a -> ((Distancia) a).getDistancia());
    }
    
    // 5. quantos metros de altimetria é que um utilizar totalizou num período ou desde sempre
    public int altimetriaAcumuladaPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        return (int) this.estatisticaAcumulacaoPeriodo(codigoUtilizador, inicio, fim, (a -> a instanceof Altimetria), a -> ((Altimetria) a).getAltimetria());
    }
    */
    
    // 6. qual o plano de treino mais exigente em função do dispêndio de calorias proposto
    public PlanoDeTreino planoDeTreinoMaisExigente() {
        Comparator<Utilizador> comparator = (u1, u2) -> {
                                                            double dif = u2.getPlanoDeTreino().mediaCaloriasSemanal() - u1.getPlanoDeTreino().mediaCaloriasSemanal();
                                                            return Double.compare(dif, 0);
                                                        };
        return this.utilizadores.values().stream().sorted(comparator).findFirst().orElse(null).getPlanoDeTreino();
    }    
    
    // colocar throw NaoExistemAtividadesRealizadasException e UtilizadorNaoExisteException
    // 7. listar as actividades de um utilizador
    public Map<String,Atividade> atividadesRealizadas(String codigoUtilizador) {
        return this.utilizadores.get(codigoUtilizador).getAtividades();
    }

    // colocar throw NaoExistemRecordesException e UtilizadorNaoExisteException
    // (Bónus) listar os recordes de um utilizador
    public Map<String,Double> recordesCalorias(String codigoUtilizador) {
        return this.utilizadores.get(codigoUtilizador).getRecordesAtividades();
    }

    // colocar throws PlanoDeTreinoVazioException e UtilizadorNaoExisteException
    // (Bónus) listar as actividades do plano de treino de um utilizador
    public Map<String,Atividade> atividadesPlanoDeTreino(String codigoUtilizador) {
        return this.utilizadores.get(codigoUtilizador).getAtividadesPlanoDeTreino();
    }


    // ----------------- Salto no tempo ---------------- //

    // colcar throw PlanoDeTreinoVazioException
    public void saltoNoTempo(int dias) {
        LocalDate proximaData = this.dataAtual.plusDays(dias);

        for(Utilizador utilizador : this.utilizadores.values()) {
            for(Atividade atividade : utilizador.getAtividadesPlanoDeTreino().values()) {
                if(atividade.getData().toLocalDate().isBefore(proximaData)) {
                    double caloriasAtividade = atividade.calorias();

                    // --------- "Realizar" atividade ---------- //
                    utilizador.removeAtividadePlanoDeTreino(atividade.getCodigo());
                    utilizador.addAtividade(atividade);
                    
                    // --------- Atualização de calorias e peso ---------- //
                    utilizador.atualizaCaloriasGastas(caloriasAtividade);
                    utilizador.atualizaPeso(caloriasAtividade);
                    
                    // --------- Atualizar recordes pessoais ---------- //
                    utilizador.atualizaRecordesCalorias(atividade);
                }
            }
            this.atualizaRecordesGerais(utilizador);
            // --------- Atualiza recordes gerais ---------- //
        }

        this.dataAtual = proximaData;
    }

    // ----------------- Criação de plano de treino com objetivos ---------------- // 
    private boolean existeAtividadeHardLista(List<Atividade> atividadesDoDia) {
        boolean temHard = false;
        for (Atividade atividade : atividadesDoDia) 
            if (atividade instanceof Hard) temHard = true;

        return temHard;
    }

    // como é private acho que não precisa. colocar throws PlanoDeTreinoVazioException
    private void preencheSemana(String codUtilizador, Map<Atividade, Integer> atividadesComRecorrenciaSemanal, LocalDate dataInicioSemana, int nMaximoAtividadesDia) {
        PlanoDeTreino plano = this.utilizadores.get(codUtilizador).getPlanoDeTreino();
        Map<Atividade, Integer> atividadesRestantes = atividadesComRecorrenciaSemanal.entrySet().stream().collect(Collectors.toMap(k->k.getKey().clone(), v->v.getValue()));
        
        for (Map.Entry<Atividade, Integer> e : atividadesRestantes.entrySet()) {
            Atividade atividade = e.getKey();

            while (e.getValue() > 0) {    
                boolean inseriuAtividade = false;
                while (!inseriuAtividade) {
                    boolean podeInserir = true;

                    Random random = new Random(); 
                    LocalDate dataAleatoria = dataInicioSemana.plusDays(random.nextInt(7));
                    List<Atividade> atividadesDoDia = plano.atividadesDoDia(dataAleatoria);
                    List<Atividade> atividadesDoDiaAnterior = plano.atividadesDoDia(dataAleatoria.minusDays(1));

                    if (atividade instanceof Hard) {
                        if (existeAtividadeHardLista(atividadesDoDia)) podeInserir = false;
                        if (existeAtividadeHardLista(atividadesDoDiaAnterior)) podeInserir = false;
                    }

                    if (atividadesDoDia.size() >= nMaximoAtividadesDia) podeInserir = false;
                    
                    if (podeInserir) {
                        atividade.setData(dataAleatoria.atTime(6*(atividadesDoDia.size() + 1), 0, 0));
                        plano.addAtividade(atividade);
                        inseriuAtividade = true;
                    }
                } 
                e.setValue(e.getValue() - 1);
            }
        }
    }


    /**
     * - consoante o tipo de atividades que o utilizador quer (controller)
     * - no mesmo dia não pode haver mais do que uma atividade hard √
     * - atividades hard não podem estar em dias seguidos √
     * - número máximo de atividades por dia (nunca mais do que 3) √
     * - número máximo de atividades distintas (controller)
     * - recorrência semana das ativiades (ex. 3 vezes por semana) (controller)
     * - consumo calório total que se pretende ter √
     * - nMaximoAtividadesDia*7 >= nTotalAtividadesPossíveis (Map) && nAtivHard <= 3 (controller)
     * 
     * 
     * A LISTA DE ATIVIDADES TEM DE ESTAR ORDENADA POR ATIVIDADES HARD PRIMEIRO
    */
    // colocar throws UtilizadorNaoExisteException
    public void planoDeTreinoComObjetivos(String codigoUtilizador, Map<Atividade, Integer> atividadesComRecorrenciaSemanal, int nMaximoAtividadesDia, double caloriasObjetivo) {
        LocalDate dataInicio = this.dataAtual.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        PlanoDeTreino plano = this.utilizadores.get(codigoUtilizador).getPlanoDeTreino();

        nMaximoAtividadesDia = nMaximoAtividadesDia > 3 ? 3 : nMaximoAtividadesDia;
        
        while (plano.getCaloriasTotais() < caloriasObjetivo) {
            preencheSemana(codigoUtilizador, atividadesComRecorrenciaSemanal, dataInicio, nMaximoAtividadesDia);    
            dataInicio.plusDays(7);
        }
    }

    // ----------------- Gerais ---------------- //
    
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        FitnessModel fm = (FitnessModel) o;
        return this.dataAtual.equals(fm.getData())
            && this.utilizadores.equals(fm.getUtilizadores())
            && this.recordesGerais.equals(fm.getRecordesGerais())
            && this.funcoesAtividade.equals(fm.getFuncoesAtividade())
            && this.predicatesAtividade.equals(fm.getPredicatesAtividade());
    }

    public String toString() {
        return "Data atual='" + this.dataAtual + '\'' +
               "Utilizadores ='" + this.utilizadores + '\'' + 
               "Recordes='" + this.recordesGerais + '\'';
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

    public FitnessModel carregaEstado(String nomeFicheiro) throws IOException, ClassNotFoundException {
        FileInputStream file_input = new FileInputStream(nomeFicheiro);
        ObjectInputStream object_input = new ObjectInputStream(file_input);

        FitnessModel novoModel = (FitnessModel) object_input.readObject();

        object_input.close();

        return novoModel;
    }
}
