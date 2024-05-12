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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;


import Atividade.Atividade;
import Atividade.Hard;
import Excecoes.AtividadeExisteException;
import Excecoes.AtividadeNaoExisteException;
import Excecoes.EmailExisteException;
import Excecoes.UtilizadorExisteException;
import Excecoes.UtilizadorNaoExisteException;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Genero;
import Utilizador.Utilizador;

public class FitnessModel implements Serializable {

    private static Map<String, Predicate<Atividade>> predicatesAtividade = new HashMap<>();
    private static Map<String, ToDoubleFunction<Atividade>> funcoesAtividade = new HashMap<>();
    
    // ----------------- Métodos para Predicates e ToDoubleFunctions ---------------- //

    public static void addToDoubleFunction(String nomeFuncao, ToDoubleFunction<Atividade> tdf) {
        FitnessModel.funcoesAtividade.put(nomeFuncao, tdf);
    } 
    
    public static ToDoubleFunction<Atividade> getToDoubleFunction(String nomeFuncao) {
        return FitnessModel.funcoesAtividade.get(nomeFuncao);
    } 

    public static void addPredicate(String nomePredicate, Predicate<Atividade> p) {
        FitnessModel.predicatesAtividade.put(nomePredicate, p);
    } 
    
    public static Predicate<Atividade> getPredicate(String nomePredicate) {
        return FitnessModel.predicatesAtividade.get(nomePredicate);
    } 


    private LocalDate dataAtual;
    private Map<String, Utilizador> utilizadores;
    private Map<String, Utilizador> recordesGerais;
    
    // ----------------- Construtores ---------------- //

    public FitnessModel() {
        this.dataAtual = LocalDate.now();
        this.utilizadores = new HashMap<>();
        this.recordesGerais = new HashMap<>();
    }

    public FitnessModel(LocalDate data, Map<String, Utilizador> utilizadores, Map<String, Utilizador> recordesGerais) {
        this.dataAtual = data;
        this.utilizadores = utilizadores.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
        this.recordesGerais = recordesGerais.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
    }

    public FitnessModel(FitnessModel outro) {
        this.dataAtual = outro.getData();
        this.utilizadores = outro.getUtilizadores();
        this.recordesGerais = outro.getRecordesGerais();
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

    // ----------------- Utilizador ---------------- //

    public String getCodigoUtilizador(Utilizador utilizador) {
        return utilizador.getCodigo();
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
        this.utilizadores.put(utilizador.getCodigo(), utilizador);
    }

    public void removeUtilizador(String codigo) throws UtilizadorNaoExisteException {
        if (!this.codigoUtilizadorExiste(codigo)) throw new UtilizadorNaoExisteException();
        this.utilizadores.remove(codigo);
    }

    public Utilizador getUtilizador(String codigo) {
        return this.utilizadores.get(codigo);
    }

    public Map<String,Double> recordesUtilizador(String codigoUtilizador) {
        return this.utilizadores.get(codigoUtilizador).getRecordesAtividades();
    }

    public String getNomeUtilizador(String codigoUtilizador) {
		return this.utilizadores.get(codigoUtilizador).getNome();
	}
    
    public Genero getGeneroUtilizador(String codigoUtilizador) {
        return this.utilizadores.get(codigoUtilizador).getGenero();
    }
 
	public String getMoradaUtilizador(String codigoUtilizador) {
		return this.utilizadores.get(codigoUtilizador).getMorada();
	}

    public String getEmailUtilizador(String codigoUtilizador) {
		return this.utilizadores.get(codigoUtilizador).getEmail();
	}

	public int getBpmUtilizador(String codigoUtilizador) {
		return this.utilizadores.get(codigoUtilizador).getBpmMedio();
	}

    public int getAlturaUtilizador(String codigoUtilizador) {
        return this.utilizadores.get(codigoUtilizador).getAltura();
    }

	public double getPesoUtilizador(String codigoUtilizador) {
		return this.utilizadores.get(codigoUtilizador).getPeso();
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

    /** 
     * quando se adiciona uma atividade realizada, o que realmente acontece é:
     * - define-se na atividade o valor das calorias e bpm médio esperado
     * - adiciona-se a atividade no map de atividades realizadas do utilizador
     * - atualizam-se os valores de calorias totais gastas, peso atual e recordes
     * do utilizador
     * - atualizam-se os recordes gerais
    */
    public void addAtividadeRealizada(String codigoUtilizador, Atividade atividade) throws UtilizadorNaoExisteException, AtividadeExisteException {
        double caloriasAtividade = atividade.calorias();
        Utilizador utilizador = this.utilizadores.get(codigoUtilizador);

        atividade.setBpm(atividade.bpm());
        atividade.setCalorias(caloriasAtividade);
        
        this.addAtividade(codigoUtilizador, atividade);
        
        utilizador.atualizaCaloriasGastas(caloriasAtividade);
        utilizador.atualizaPeso(caloriasAtividade);
        utilizador.atualizaRecordesCalorias(atividade);
        this.atualizaRecordesGerais(utilizador);
    }

    public void removerAtividade(String codigoUtilizador, String codigoAtividade) throws UtilizadorNaoExisteException, AtividadeNaoExisteException {
        if (!this.codigoUtilizadorExiste(codigoUtilizador)) throw new UtilizadorNaoExisteException();
        if (!this.existeAtividade(codigoUtilizador, codigoAtividade)) throw new AtividadeNaoExisteException();
        this.utilizadores.get(codigoUtilizador).removeAtividade(codigoAtividade);
    }

    public List<Atividade> getAtividadesRealizadas(String codigoUtilizador) {
        return this.utilizadores.get(codigoUtilizador).getAtividadesList();
    }

    public List<Atividade> getAtividadesRealizadas(Utilizador utilizador) {
        return utilizador.getAtividadesList();
    }

    public boolean existemAtividadesRealizadas() {
        boolean existe = false;
        Iterator<Utilizador> utilizadores = this.utilizadores.values().iterator();

        while (utilizadores.hasNext() && !existe) {
            Utilizador u = utilizadores.next();
            if (u.getAtividades().size() > 0) 
                existe = true;
        }        
        return existe;
     }
     
     // ----------------- Plano de treino ---------------- //
     
     public boolean existeAtividadePlanoDeTreino(String codigoUtilizador, String codAtividade) {
         return this.utilizadores.get(codigoUtilizador).existeAtividadePlanoDeTreino(codAtividade);
        }
        
        public void setPlanoDeTreino(String codigoUtilizador, PlanoDeTreino planoDeTreino) throws UtilizadorNaoExisteException {
            if (!this.codigoUtilizadorExiste(codigoUtilizador)) throw new UtilizadorNaoExisteException();
            this.utilizadores.get(codigoUtilizador).setPlanoDeTreino(planoDeTreino);
        }
        
        public void limparPlanoDeTreino(String codigoUtilizador) {
            this.utilizadores.get(codigoUtilizador).limparPlanoDeTreino();
        }
        
        /**
         * é importante notar que, quando se adiciona uma atividade no plano
         * de treino, é necessário definir o bpm médio e as calorias da atividade
         * antes de inseri-la
         */
        public void addAtividadePlanoDeTreino(String codigoUtilizador, Atividade atividade) throws UtilizadorNaoExisteException, AtividadeExisteException {
            if (!this.codigoUtilizadorExiste(codigoUtilizador)) throw new UtilizadorNaoExisteException();
            if (this.existeAtividade(codigoUtilizador, atividade.getCodigo()) || this.existeAtividadePlanoDeTreino(codigoUtilizador, atividade.getCodigo())) throw new AtividadeExisteException();
            atividade.setBpm(atividade.bpm());
            atividade.setCalorias(atividade.calorias());
            this.utilizadores.get(codigoUtilizador).addAtividadePlanoDeTreino(atividade);
        }
        
        public void removeAtividadePlanoDeTreino(String codigoUtilizador, String codAtividade) throws UtilizadorNaoExisteException, AtividadeNaoExisteException {
            if (!this.codigoUtilizadorExiste(codigoUtilizador)) throw new UtilizadorNaoExisteException();
            if (!this.existeAtividadePlanoDeTreino(codigoUtilizador, codAtividade)) throw new AtividadeNaoExisteException();
            this.utilizadores.get(codigoUtilizador).apagaAtividadePlanoDeTreino(codAtividade);
        }
        
        public PlanoDeTreino getPlanoDeTreino(String codigoUtilizador) {
            return this.utilizadores.get(codigoUtilizador).getPlanoDeTreino();
        }
        
        public int getDuracaoPlanoDeTreino(String codigoUtilizador) {
            return this.utilizadores.get(codigoUtilizador).getPlanoDeTreino().getDuracao();
        }
        
        public Map<String, Atividade> getAtividadesPlanoDeTreino(String codigoUtilizador) {
            return this.utilizadores.get(codigoUtilizador).getAtividadesPlanoDeTreino();
        }
        
        public List<Atividade> getAtividadesPlanoDeTreinoCrescente(String codigoUtilizador) {
            Comparator<Atividade> comparator = (a1,a2) -> a1.getData().compareTo(a2.getData());
            
            return this.getAtividadesPlanoDeTreino(codigoUtilizador).values().stream().sorted(comparator).collect(Collectors.toList());
        }
        
        public LocalDate getDataPlanoDeTreino(String codigoUtilizador) {
            return this.utilizadores.get(codigoUtilizador).getPlanoDeTreino().getDataRealizacao();
        }

        public boolean existePlanoDeTreino() {
            boolean existe = false;
            Iterator<Utilizador> utilizadores = this.utilizadores.values().iterator();
    
            while (utilizadores.hasNext() && !existe) {
                Utilizador u = utilizadores.next();
                if (u.getAtividadesPlanoDeTreino().size() > 0) 
                    existe = true;
            }        
            return existe;
         }

    // ----------------- Outras queries e auxiliares ---------------- //

    /**
     * método geral responsável por filtrar as atividades de um utilizador
     * consoante um predicate e por aplicar uma função toDouble, devolvendo
     * o valor acumulado
     */
    public double estatisticaAcumulacaoPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim, Predicate<Atividade> insOf, ToDoubleFunction<Atividade> fun) {
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

    
    private int numeroAtividadesPeriodo(String codigoUtilizador, LocalDate inicio, LocalDate fim) {
        Predicate<Atividade> predicate = a -> (a.getData().toLocalDate().compareTo(inicio) >= 0 && a.getData().toLocalDate().compareTo(fim) <= 0);
        return (int) this.utilizadores.get(codigoUtilizador).getAtividades().values().stream().filter(predicate).count();
    }

    public boolean nomeAtividadeRecordeExiste(String nomeAtividade) {
        return this.recordesGerais.containsKey(nomeAtividade);
    }

    public double caloriasRecordeAtividade(String nomeAtividade) {
        return this.recordesGerais.get(nomeAtividade).caloriasRecordeAtividade(nomeAtividade);
    }

    private void atualizaRecordesGerais(Utilizador utilizador) {
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


    /**
     * responde à query 1 "qual é o utilizador que mais calorias dispendeu
     * num período ou desde sempre". faz uso do método geral de iteração
     * mencionado em cima para comparar as calorias dos utilizadores 
     * acumuladas num período, de forma a perceber qual o que gastou
     * mais calorias
     */
    public Utilizador utilizadorMaisCalorias(LocalDate inicio, LocalDate fim) { // referência no relatório
        Comparator<Utilizador> comparator = (u1, u2) -> {
                                                            double dif = this.estatisticaAcumulacaoPeriodo(u2.getCodigo(), inicio, fim, a -> a instanceof Atividade, a -> a.calorias())
                                                                       - this.estatisticaAcumulacaoPeriodo(u1.getCodigo(), inicio, fim, a -> a instanceof Atividade, a -> a.calorias());
                                                            return Double.compare(dif,0);
                                                        };
        
        return this.utilizadores.values().stream().sorted(comparator).findFirst().orElse(null);
    }
    
    /**
     * responde à query 2 "qual o utilizador que mais actividades realizou
     * num período ou desde sempre".
     */
    public Utilizador utilizadorComMaisAtividades(LocalDate inicio, LocalDate fim) {
        Comparator<Utilizador> comparator = (u1, u2) -> this.numeroAtividadesPeriodo(u2.getCodigo(), inicio, fim) - this.numeroAtividadesPeriodo(u1.getCodigo(), inicio, fim);
        return this.utilizadores.values().stream().sorted(comparator).findFirst().orElse(null);
    }

    /**
     * responde à query 3 "qual o tipo de actividade mais realizada". este
     * método cria um Map<String, Integer> com o objetivo de mapear a string
     * correspondete ao nome simples da classe da atividade ao número de vezes
     * que a atividade é feita. deste modo, devolve-se a string que corresponde
     * ao nome da atividade que mais vezes apareceu.
     */
    public String atividadeMaisRealizada() {
        Map<String,Integer> nomeAtividades = new HashMap<>();
        for (Utilizador u : this.utilizadores.values()) {
            for (Atividade a : u.getAtividades().values()) {
                String nome = a.getClass().getSimpleName();
                if (nomeAtividades.containsKey(nome)) nomeAtividades.replace(nome,nomeAtividades.get(nome) + 1);
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
    
    /**
     * responde à query 6 "qual o plano de treino mais exigente em função 
     * do dispêndio de calorias proposto". faz uso do método geral de iteração
     * mencionado em cima para comparar a média das calorias semanais dos planos
     * de treino dos utilizadores, num período, de forma a perceber qual o mais
     * exigente.
     */
    public PlanoDeTreino planoDeTreinoMaisExigente() {
        Comparator<Utilizador> comparator = (u1, u2) -> {
                                                            double dif = u2.getPlanoDeTreino().mediaCaloriasSemanal() - u1.getPlanoDeTreino().mediaCaloriasSemanal();
                                                            return Double.compare(dif, 0);
                                                        };
        return this.utilizadores.values().stream().sorted(comparator).findFirst().orElse(null).getPlanoDeTreino();
    }        

    // ----------------- Salto no tempo ---------------- //

    /**
     * este método tem em atenção o número de dias proposto a saltar
     * e, para cada atividade de cada utilizador, verifica se a mesma
     * encontra-se antes da nova data proposta. caso tal se verifique,
     * significa que a atividade tem de ser "realizada", o que se resume
     * aos seguintes passos: 
     * - registar as calorias da atividade na atividade
     * - remover a atividade do plano de treino e adicioná-la ao map
     * de atividades realizadas
     * - atualizar os valores de calorias totais gastas, peso atual e recordes
     * do utilizador
     * - atualizar os recordes gerais
     */
    public void saltoNoTempo(int dias) {
        LocalDate proximaData = this.dataAtual.plusDays(dias);

        for(Utilizador utilizador : this.utilizadores.values()) {
            for(Atividade atividade : utilizador.getAtividadesPlanoDeTreino().values()) {
                if(atividade.getData().toLocalDate().isBefore(proximaData)) {
                    double caloriasAtividade = atividade.calorias();
                    
                    // --------- Recalcular as calorias devido à atualização do peso ---------- //
                    atividade.setCalorias(caloriasAtividade);

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

    /**
     * este método tem como vista simplificar o método de criação de
     * um plano de treino com objetivos. o método itera pela lista de
     * atividades a realizar e preenche a semana as vezes que foram 
     * determinadas. para fazer o que foi dito anteriormente, cria uma
     * data aleatória dentro da semana em questão, e verifica se
     * é possível inserir a atividade nesse dia, segundo as seguintes
     * restrições:
     * - uma atividade hard não pode ser realizada em dias consecutivos
     * - não pode haver mais do que uma ativdade hard no mesmo dia
     * - o número de atividades nesse dia não pode exceder o máximo
     * imposto pelo utilizador
     * 
     * caso a atividade possa ser inserida, é lhe então atribuída a data
     * aleatória, cria-se e define-se um código novo válido e dá-se
     * uma hora de realização. por último, a atividade é inserida no plano
     * de treino, e decrementa-se o número de vezes que se pretende realizar
     * a atividade em questão (cria-se um Map auxiliar para não alterar os valores
     * estritamente definidos)
     */
    private void preencheSemana(String codigoUtilizador, PlanoDeTreino plano, Map<Atividade, Integer> atividadesComRecorrenciaSemanal, LocalDate dataInicioSemana, int nMaximoAtividadesDia) {
        Map<Atividade, Integer> atividadesRestantes = atividadesComRecorrenciaSemanal.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v->v.getValue()));
        
        for (Map.Entry<Atividade, Integer> e : atividadesRestantes.entrySet()) {
            while (e.getValue() > 0) {
                Atividade atividade = e.getKey().clone();
                
                boolean inseriuAtividade = false;
                int thresholdIteracoes = 100;
                while (!inseriuAtividade && thresholdIteracoes > 0) {
                    boolean podeInserir = true;

                    Random randomDias = new Random(); 
                    LocalDate dataAleatoria = dataInicioSemana.plusDays(randomDias.nextInt(7));
                    List<Atividade> atividadesDoDia = plano.atividadesDoDia(dataAleatoria);
                    List<Atividade> atividadesDoDiaAnterior = plano.atividadesDoDia(dataAleatoria.minusDays(1));
                    List<Atividade> atividadesDoDiaPosterior = plano.atividadesDoDia(dataAleatoria.plusDays(1));

                    if (atividade instanceof Hard) {
                        if (existeAtividadeHardLista(atividadesDoDia)) podeInserir = false;
                        if (existeAtividadeHardLista(atividadesDoDiaAnterior)) podeInserir = false;
                        if (existeAtividadeHardLista(atividadesDoDiaPosterior)) podeInserir = false;
                    }

                    if (atividadesDoDia.size() >= nMaximoAtividadesDia) podeInserir = false;
                    
                    if (podeInserir) {
                        String codigoNovo = atividade.getCodigo();
                        do {
                            Random randomNumCodigo = new Random();
                            codigoNovo += randomNumCodigo.nextInt(10);
                        } while (this.existeAtividadePlanoDeTreino(codigoUtilizador, codigoNovo) || this.existeAtividade(codigoUtilizador, codigoNovo));

                        atividade.setBpm(atividade.bpm());
                        atividade.setCalorias(atividade.calorias());
                        atividade.setCodigo(codigoNovo);
                        atividade.setData(dataAleatoria.atTime(6*(atividadesDoDia.size() + 1), 0, 0));
                        plano.addAtividade(atividade);
                        inseriuAtividade = true;
                    }
                    thresholdIteracoes--;
                } 
                e.setValue(e.getValue() - 1);
            }
        }
    }

    /** 
     * este método recebe um Map<Atividade, Integer>, que mapea
     * uma atividade ao número de vezes a realizar por semana (as 
     * restrições dos valores já são previamente verificadas), o número
     * máximo de atividades a realizar por dia e as calorias objetivo.
     * com isto, o método define a data inicial do plano de treino como
     * sendo a próxima segunda feira em relação à data atual, limpa o
     * plano de treino antigo e, até atingir as calorias objetivo,
     * preenche todos os dias de uma semana com as atividades pedidas.
     * no final define a duração do plano, assim como repõe o plano 
     * de treino no utilizador
     */ 
    public void planoDeTreinoComObjetivos(String codigoUtilizador, Map<Atividade, Integer> atividadesComRecorrenciaSemanal, int nMaximoAtividadesDia, double caloriasObjetivo) {
        LocalDate dataInicio = this.dataAtual.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        this.limparPlanoDeTreino(codigoUtilizador);

        this.utilizadores.get(codigoUtilizador).setDataPlanoDeTreino(dataInicio);
        PlanoDeTreino plano = this.utilizadores.get(codigoUtilizador).getPlanoDeTreino();
        
        int numeroSemanas = 0;
        while (plano.getCaloriasTotais() < caloriasObjetivo) {
            preencheSemana(codigoUtilizador, plano, atividadesComRecorrenciaSemanal, dataInicio, nMaximoAtividadesDia);    
            dataInicio = dataInicio.plusDays(7);
            numeroSemanas++;
        }
        this.utilizadores.get(codigoUtilizador).setDuracaoPlanoDeTreino(numeroSemanas);
        this.utilizadores.get(codigoUtilizador).setPlanoDeTreino(plano);
    }

    // ----------------- Gerais ---------------- //
    
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        FitnessModel fm = (FitnessModel) o;
        return this.dataAtual.equals(fm.getData())
            && this.utilizadores.equals(fm.getUtilizadores())
            && this.recordesGerais.equals(fm.getRecordesGerais());
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

        object_output.writeObject(FitnessModel.predicatesAtividade);
        object_output.writeObject(FitnessModel.funcoesAtividade);
        object_output.writeObject(this);

        object_output.flush();
        object_output.close();
    }

    public static FitnessModel carregaEstado(String nomeFicheiro) throws IOException, ClassNotFoundException {
        FileInputStream file_input = new FileInputStream(nomeFicheiro);
        ObjectInputStream object_input = new ObjectInputStream(file_input);

        FitnessModel.predicatesAtividade = (Map<String, Predicate<Atividade>>) object_input.readObject();
        FitnessModel.funcoesAtividade = (Map<String, ToDoubleFunction<Atividade>>) object_input.readObject();
        FitnessModel novoModel = (FitnessModel) object_input.readObject();

        object_input.close();

        return novoModel;
    }
}
