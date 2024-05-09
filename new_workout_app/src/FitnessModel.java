import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
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
import Excessoes.AtividadeExisteException;
import Excessoes.AtividadeNaoExisteException;
import Excessoes.EmailExisteException;
import Excessoes.NaoExistemAtividadesRealizadasException;
import Excessoes.NaoExistemRecordesException;
import Excessoes.NomeAtividadeNaoExisteException;
import Excessoes.UtilizadorExisteException;
import Excessoes.UtilizadorNaoExisteException;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Utilizador;

public class FitnessModel implements Serializable {
    private LocalDate dataAtual;
    private Map<String, Utilizador> utilizadores;
    private Map<String, Utilizador> recordesGerais;
    
    private Map<String, Predicate<Atividade>> predicatesAtividade;
    private Map<String, ToDoubleFunction<Atividade>> funcoesAtividade;
    

    // ----------------- Construtores ---------------- //

    public FitnessModel() {
        this.dataAtual = LocalDate.now();
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

    public void removeUtilizador(String codigo) throws UtilizadorNaoExisteException {
        if (!this.codigoUtilizadorExiste(codigo)) throw new UtilizadorNaoExisteException();
        this.utilizadores.remove(codigo);
    }

    public Utilizador getUtilizador(String codigo) {
        return this.utilizadores.get(codigo);
    }


    // ----------------- Atividades ---------------- //

    public boolean existeAtividade(String codigoUtilizador, String codigoAtividade) {
        return this.utilizadores.get(codigoUtilizador).existeAtividade(codigoAtividade);
    }

    public void addAtividade(String codigoUtilizador, Atividade atividade) throws UtilizadorNaoExisteException, AtividadeExisteException {
        if (!this.codigoUtilizadorExiste(codigoUtilizador)) throw new UtilizadorNaoExisteException();
        if (this.existeAtividade(codigoUtilizador, atividade.getCodigo()) || this.existeAtividadePlanoDeTreino(codigoUtilizador, atividade.getCodigo())) throw new AtividadeExisteException();
        this.utilizadores.get(codigoUtilizador).addAtividade(atividade);
    }

    public void addAtividadeRealizada(String codigoUtilizador, Atividade atividade) throws UtilizadorNaoExisteException, AtividadeExisteException, NomeAtividadeNaoExisteException {
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

    public void removerAtividade(String codigoUtilizador, String codigoAtividade) throws UtilizadorNaoExisteException, AtividadeNaoExisteException {
        if (!this.codigoUtilizadorExiste(codigoUtilizador)) throw new UtilizadorNaoExisteException();
        if (!this.existeAtividade(codigoUtilizador, codigoAtividade)) throw new AtividadeNaoExisteException();
        this.utilizadores.get(codigoUtilizador).removeAtividade(codigoAtividade);
    }

    public List<Atividade> getAtividadesRealizadas(String codUtilizador) {
        return this.utilizadores.get(codUtilizador).getAtividadesList();
    }

    // ----------------- Plano de treino ---------------- //

    public boolean existeAtividadePlanoDeTreino(String codUtilizador, String codAtividade) {
        return this.utilizadores.get(codUtilizador).existeAtividadePlanoDeTreino(codAtividade);
    }

    public void setPlanoDeTreino(String codUtilizador, PlanoDeTreino planoDeTreino) throws UtilizadorNaoExisteException {
        if (!this.codigoUtilizadorExiste(codUtilizador)) throw new UtilizadorNaoExisteException();
        this.utilizadores.get(codUtilizador).setPlanoDeTreino(planoDeTreino);
    }

    public void limparPlanoDeTreino(String codUtilizador) {
        this.utilizadores.get(codUtilizador).limparPlanoDeTreino();
    }

    public void addAtividadePlanoDeTreino(String codUtilizador, Atividade atividade) throws UtilizadorNaoExisteException, AtividadeExisteException {
        if (!this.codigoUtilizadorExiste(codUtilizador)) throw new UtilizadorNaoExisteException();
        if (this.existeAtividade(codUtilizador, atividade.getCodigo()) || this.existeAtividadePlanoDeTreino(codUtilizador, atividade.getCodigo())) throw new AtividadeExisteException();
        atividade.setBpm(atividade.bpm());
        atividade.setCalorias(atividade.calorias());
        this.utilizadores.get(codUtilizador).addAtividadePlanoDeTreino(atividade);
    }

    public void removeAtividadePlanoDeTreino(String codUtilizador, String codAtividade) throws UtilizadorNaoExisteException, AtividadeNaoExisteException {
        if (!this.codigoUtilizadorExiste(codUtilizador)) throw new UtilizadorNaoExisteException();
        if (!this.existeAtividadePlanoDeTreino(codUtilizador, codAtividade)) throw new AtividadeNaoExisteException();
        this.utilizadores.get(codUtilizador).removeAtividadePlanoDeTreino(codAtividade);
    }

    public PlanoDeTreino getPlanoDeTreino(String codUtilizador) {
        return this.utilizadores.get(codUtilizador).getPlanoDeTreino();
    }

    public int getDuracaoPlanoDeTreino(String codUtilizador) {
        return this.getPlanoDeTreino(codUtilizador).getDuracao();
    }

    public Map<String, Atividade> getAtividadesPlanoDeTreino(String codUtilizador) {
        return this.utilizadores.get(codUtilizador).getAtividadesPlanoDeTreino();
    }

    public List<Atividade> getAtividadesPlanoDeTreinoCrescente(String codUtilizador) {
        Comparator<Atividade> comparator = (a1,a2) -> a1.getData().compareTo(a2.getData());
                                                      
        return this.getAtividadesPlanoDeTreino(codUtilizador).values().stream().sorted(comparator).collect(Collectors.toList());
    }

    // ----------------- Outras queries e auxiliares ---------------- //

    // *****VALIDAR DATAS NO DELEGATE*****
    private double estatisticaAcumulacaoPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim, Predicate<Atividade> insOf, ToDoubleFunction<Atividade> fun) {
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

    // *****VALIDAR DATAS NO DELEGATE*****
    public int numeroAtividadesPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        Predicate<Atividade> predicate = a -> (a.getData().toLocalDate().compareTo(inicio) >= 0 && a.getData().toLocalDate().compareTo(fim) <= 0);
        return (int) this.utilizadores.get(codigoUtilizador).getAtividades().values().stream().filter(predicate).count();
    }

    public boolean nomeAtividadeRecordeExiste(String nomeAtividade) {
        return this.recordesGerais.containsKey(nomeAtividade);
    }

    public double caloriasRecordeAtividade(String nomeAtividade) throws NomeAtividadeNaoExisteException {
        if (!nomeAtividadeRecordeExiste(nomeAtividade)) throw new NomeAtividadeNaoExisteException();
        return this.recordesGerais.get(nomeAtividade).caloriasRecordeAtividade(nomeAtividade);
    }

    public void atualizaRecordesGerais(Utilizador utilizador) throws NomeAtividadeNaoExisteException {
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
    
    // 7. listar as actividades de um utilizador
    public Map<String,Atividade> atividadesRealizadas(String codigoUtilizador) throws UtilizadorNaoExisteException, NaoExistemAtividadesRealizadasException {
        if (!this.codigoUtilizadorExiste(codigoUtilizador)) throw new UtilizadorNaoExisteException();
        Map<String, Atividade> atividades = this.utilizadores.get(codigoUtilizador).getAtividades();
        if (atividades.size() <= 0) throw new NaoExistemAtividadesRealizadasException();
        return atividades;
    }

    // (Bónus) listar os recordes de um utilizador
    public Map<String,Double> recordesCalorias(String codigoUtilizador) throws UtilizadorNaoExisteException, NaoExistemRecordesException {
        if (!this.codigoUtilizadorExiste(codigoUtilizador)) throw new UtilizadorNaoExisteException();
        Map<String, Double> recordes = this.utilizadores.get(codigoUtilizador).getRecordesAtividades();
        if (recordes.size() <= 0) throw new NaoExistemRecordesException();
        return this.utilizadores.get(codigoUtilizador).getRecordesAtividades();
    }

    // ----------------- Salto no tempo ---------------- //

    public void saltoNoTempo(int dias) throws NomeAtividadeNaoExisteException {
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
            // --------- Atualiza recordes gerais ---------- //
            this.atualizaRecordesGerais(utilizador);
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

    private void preencheSemana(String codUtilizador, Map<Atividade, Integer> atividadesComRecorrenciaSemanal, LocalDate dataInicioSemana, int nMaximoAtividadesDia) {
        PlanoDeTreino plano = this.utilizadores.get(codUtilizador).getPlanoDeTreino();
        Map<Atividade, Integer> atividadesRestantes = atividadesComRecorrenciaSemanal.entrySet().stream().collect(Collectors.toMap(k->k.getKey().clone(), v->v.getValue()));
        
        for (Map.Entry<Atividade, Integer> e : atividadesRestantes.entrySet()) {
            while (e.getValue() > 0) {
                Atividade atividade = e.getKey().clone();
                
                boolean inseriuAtividade = false;
                while (!inseriuAtividade) {
                    boolean podeInserir = true;

                    Random randomDias = new Random(); 
                    LocalDate dataAleatoria = dataInicioSemana.plusDays(randomDias.nextInt(7));
                    List<Atividade> atividadesDoDia = plano.atividadesDoDia(dataAleatoria);
                    List<Atividade> atividadesDoDiaAnterior = plano.atividadesDoDia(dataAleatoria.minusDays(1));

                    if (atividade instanceof Hard) {
                        if (existeAtividadeHardLista(atividadesDoDia)) podeInserir = false;
                        if (existeAtividadeHardLista(atividadesDoDiaAnterior)) podeInserir = false;
                    }

                    if (atividadesDoDia.size() >= nMaximoAtividadesDia) podeInserir = false;
                    
                    if (podeInserir) {
                        
                        String codigoNovo = atividade.getCodigo();
                        do {
                            Random randomNumCodigo = new Random();
                            codigoNovo += randomNumCodigo.nextInt(10);
                        } while (this.existeAtividadePlanoDeTreino(codUtilizador, codigoNovo) || this.existeAtividade(codUtilizador, codigoNovo));

                        atividade.setBpm(atividade.bpm());
                        atividade.setCalorias(atividade.calorias());
                        atividade.setCodigo(codigoNovo);
                        atividade.setData(dataAleatoria.atTime(6*(atividadesDoDia.size() + 1), 0, 0));
                        plano.addAtividade(atividade);
                        inseriuAtividade = true;
                    }
                } 
                e.setValue(e.getValue() - 1);
            }
        }
    }


    /*
     * /**
     * - nMaximoAtividadesDia*7 >= nTotalAtividadesPossíveis (Map) && nAtivHard <= 3 (controller)
     * 
     * 
     * A LISTA DE ATIVIDADES TEM DE ESTAR ORDENADA POR ATIVIDADES HARD PRIMEIRO
    */
    // FAZER WHILE RANDOM NO DELEGATE PARA
    // OBTER CÓDIGO DA ATIVIDADE, VERIFICANDO
    // QUE O CÓDIGO NÃO EXISTE NEM NO PLANO NEM
    // NAS ATIVIDADE REALIZADAS
    public void planoDeTreinoComObjetivos(String codigoUtilizador, Map<Atividade, Integer> atividadesComRecorrenciaSemanal, int nMaximoAtividadesDia, double caloriasObjetivo) {
        
        LocalDate dataInicio = this.dataAtual.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        this.limparPlanoDeTreino(codigoUtilizador);
        PlanoDeTreino plano = this.utilizadores.get(codigoUtilizador).getPlanoDeTreino();
        
        while (plano.getCaloriasTotais() < caloriasObjetivo) {
            preencheSemana(codigoUtilizador, atividadesComRecorrenciaSemanal, dataInicio, nMaximoAtividadesDia);    
            dataInicio = dataInicio.plusDays(7);
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
