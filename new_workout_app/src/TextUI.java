import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import Atividade.Atividade;
import Atividade.Hard;
import Atividade.Distancia.Distancia;
import Atividade.Distancia.Sprint;
import Atividade.Distancia.Altimetria.Altimetria;
import Atividade.Distancia.Altimetria.BicicletaMontanha;
import Atividade.Repeticoes.Repeticoes;
import Atividade.Repeticoes.Abdominais;
import Atividade.Repeticoes.Pesos.Pesos;
import Atividade.Repeticoes.Pesos.Supino;
import Excessoes.AtividadeExisteException;
import Excessoes.AtividadeNaoExisteException;
import Excessoes.EmailExisteException;
import Excessoes.NomeAtividadeNaoExisteException;
import Excessoes.ParametrosInvalidosException;
import Excessoes.UtilizadorExisteException;
import Excessoes.UtilizadorNaoExisteException;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Genero;
import Utilizador.Utilizador;
import Utilizador.TiposUtilizador.*;

public class TextUI {
    private FitnessModel model;
    private Scanner sc;

    public TextUI() {
        this.model = new FitnessModel(); // definir a data posteriormente
        
        Predicate<Atividade> ativ = (Predicate<Atividade> & Serializable) a -> a instanceof Atividade;
        FitnessModel.addPredicate("éAtividade", ativ);
        Predicate<Atividade> dist = (Predicate<Atividade> & Serializable) a -> a instanceof Distancia;
        FitnessModel.addPredicate("éAtividadeDistância", dist);
        Predicate<Atividade> alti = (Predicate<Atividade> & Serializable) a -> a instanceof Altimetria;
        FitnessModel.addPredicate("éAtividadeDistânciaAltimetria", alti);
        Predicate<Atividade> repe = (Predicate<Atividade> & Serializable) a -> a instanceof Repeticoes;
        FitnessModel.addPredicate("éAtividadeRepetições", repe);
        Predicate<Atividade> peso = (Predicate<Atividade> & Serializable) a -> a instanceof Pesos;
        FitnessModel.addPredicate("éAtividadeRepetiçõesPesos", peso);
        
        ToDoubleFunction<Atividade> calculoCalorias = (ToDoubleFunction<Atividade> & Serializable) a -> a.calorias();
        FitnessModel.addToDoubleFunction("obtémCalorias", calculoCalorias);
        ToDoubleFunction<Atividade> calculoRepeticoes = (ToDoubleFunction<Atividade> & Serializable) a -> ((Repeticoes) a).getRepeticoes();
        FitnessModel.addToDoubleFunction("obtémRepetições", calculoRepeticoes);
        ToDoubleFunction<Atividade> calculoPeso = (ToDoubleFunction<Atividade> & Serializable) a -> ((Pesos) a).getPeso();
        FitnessModel.addToDoubleFunction("obtémPeso", calculoPeso);
        ToDoubleFunction<Atividade> calculoDistancia = (ToDoubleFunction<Atividade> & Serializable) a -> ((Distancia) a).getDistancia();
        FitnessModel.addToDoubleFunction("obtémDistancia", calculoDistancia);
        ToDoubleFunction<Atividade> calculoAltimetria = (ToDoubleFunction<Atividade> & Serializable) a -> ((Altimetria) a).getAltimetria();
        FitnessModel.addToDoubleFunction("obtémAltimetria", calculoAltimetria);
        
        sc = new Scanner(System.in);

    }

    public void run() {
        Menu menu = new Menu(new String[] {
                        "Carregar Estado",
                        "Nova Simulação",
                        "Guardar Estado"
                        });

        menu.setHandler(1, () -> carregaEstado());
        menu.setHandler(2, () -> paginaInicial());
        menu.setHandler(3, () -> guardaEstado());

        menu.run();
    }

    public void carregaEstado() {
        System.out.print("\nFicheiro a carregar: ");
        String ficheiroACarregar = sc.nextLine();

        try {
            this.model.carregaEstado(ficheiroACarregar);
            System.out.println("\n[SUCESSO] Ficheiro carregado\n");
            paginaInicial();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\n[ERRO] Falha ao carregar dados\n");
        } 
    }

    public void guardaEstado() {
        System.out.print("\nNome do ficheiro a guardar: ");
        String ficheiroAGuardar = sc.nextLine();

        try {
            this.model.guardaEstado(ficheiroAGuardar);
            System.out.println("\n[SUCESSO] Ficheiro guardado\n");
        } catch (IOException e) {
            System.out.println("\n[ERRO] Falha ao guardar dados\n");
        } 
    }

    public void paginaInicial() {
        Menu menu = new Menu(new String[] {
                        "Registar",
                        "Login"
                        });

        menu.setHandler(1, () -> registarUtilizador());
        menu.setCondicao(2, () -> this.model.getUtilizadores().size() > 0);
        menu.setHandler(2, () -> loginUtilizador());

        menu.run();
    }

    public void registarUtilizador() {
        System.out.print("\nNome de utlizador: ");
        String codigo = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Género (1 - Masculino, 2 - Feminino, 3 - Outro): ");
        int codGenero = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        Genero genero;
        switch (codGenero) {
            case 1:
                genero = Genero.Masculino;
                break;
            case 2:
                genero = Genero.Feminino;
                break;
            default:
                genero = Genero.Outro;
                break;
        }

        System.out.print("Altura: ");
        int altura = sc.nextInt();
        sc.nextLine(); // limpar o buffer

        System.out.print("Peso: ");
        double peso = sc.nextDouble();
        sc.nextLine(); // limpar o buffer

        System.out.print("Morada: ");
        String morada = sc.nextLine();

        System.out.print("Bpm médio: ");
        int bpmMedio = sc.nextInt();
        sc.nextLine(); // limpar o buffer

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Tipo de utilizador (1 - Praticante Ocasional, 2 - Amador, 3 - Profissional): ");
        int tipoUtilizador = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        
        try {
            Utilizador u = criarUtilizador(tipoUtilizador, codigo, bpmMedio, peso, 0, altura, nome, genero, 
            morada, email, password);
            this.model.addUtilizador(u);
            System.out.println("\n[SUCESSO] Utilizador criado!\n");
        }
        catch (ParametrosInvalidosException e) {
            System.out.println("\n[ERRO] Parâmetros inválidos\n");
        }
        catch (UtilizadorExisteException e) {
            System.out.println("\n[ERRO] Nome de utilizador já existe\n");
        }
        catch (EmailExisteException e) {
            System.out.println("\n[ERRO] Email já existe\n");
        }
    }




    public Utilizador criarUtilizador(int tipoUtilizador, String codigoUtilizador, int bpmMedio, double peso, double caloriasGastas,
                               int altura, String nome, Genero genero, String morada, String email, String password) throws ParametrosInvalidosException,
                               UtilizadorExisteException, EmailExisteException {
        if (codigoUtilizador.length() == 0 || (bpmMedio <= 35 || bpmMedio >= 200)|| (Double.compare(peso,30) <= 0
                                 || Double.compare(peso,500) >= 0) || (altura <= 100 || altura >= 300)
                                 || nome.length() == 0 || morada.length() == 0 || email.length() == 0
                                 || password.length() == 0) 
            throw new ParametrosInvalidosException();
        if (this.model.codigoUtilizadorExiste(codigoUtilizador)) throw new UtilizadorExisteException();
        if (this.model.emailUtilizadorExiste(email)) throw new EmailExisteException();

        Utilizador u;
        switch (tipoUtilizador) {
            case 1:
                u = new PraticanteOcasional(codigoUtilizador, bpmMedio, peso, caloriasGastas, altura, nome, genero, morada, email, password);
                break;
            case 2:
                u = new Amador(codigoUtilizador, bpmMedio, peso, caloriasGastas, altura, nome, genero, morada, email, password);
                break;
            case 3:
                u = new Profissional(codigoUtilizador, bpmMedio, peso, caloriasGastas, altura, nome, genero, morada, email, password);
                break;
            default:
                throw new ParametrosInvalidosException();
        }
         
        return u;
    }



    

    public void loginUtilizador() {
        System.out.print("\nNome de utlizador: ");
        String codigoUtilizador = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        if (codigoUtilizador.length() == 0 || password.length() == 0)
            System.out.println("\n[ERRO] Parâmetros inválidos\n");
        else if (!this.model.codigoUtilizadorExiste(codigoUtilizador)) 
            System.out.println("\n[ERRO] Utilizador não existe\n");
        else if (!this.model.credenciaisCoincidem(codigoUtilizador, password))
            System.out.println("\n[ERRO] Password incorreta\n");
        else {
            System.out.println("\n[SUCESSO] Login efetuado!\n");
            
            Menu menu = new Menu(new String[] { "Adicionar uma atividade realizada", 
                            "Remover uma atividade realizada", 
                            "Mostrar todas as atividades realizadas", 
                            "Adicionar um plano de treino",
                            "Criar plano de treino Automático",
                            "Remover um plano de treino", 
                            "Adicionar uma atividade ao plano de treino", 
                            "Remover uma atividade do plano de treino", 
                            "Mostrar o plano de treino",
                            "Ver estatísticas gerais",
                            "Saltar no tempo"
                        });
            
            menu.setHandler(1, () -> addAtividadeRealizada(codigoUtilizador));
            
            menu.setCondicao(2, () -> this.model.getAtividadesRealizadas(codigoUtilizador).size() > 0);
            menu.setHandler(2, () -> this.removerAtividadeRealizada(codigoUtilizador));
            
            menu.setCondicao(3, () -> this.model.getAtividadesRealizadas(codigoUtilizador).size() > 0);
            menu.setHandler(3, () -> this.mostrarAtividadesRealizadas(codigoUtilizador));
            
            menu.setHandler(4, () -> this.criarPlanoDeTreino(codigoUtilizador));
            
            menu.setHandler(5, () -> this.criarPlanoDeTreinoComObjetivos(codigoUtilizador));

            menu.setCondicao(6, () -> this.model.getAtividadesPlanoDeTreino(codigoUtilizador).size() > 0);
            menu.setHandler(6, () -> this.model.limparPlanoDeTreino(codigoUtilizador));
            
            menu.setCondicao(7, () -> this.model.getDuracaoPlanoDeTreino(codigoUtilizador) > 0);
            menu.setHandler(7, () -> this.addAtividadePlanoDeTreino(codigoUtilizador));
            
            menu.setCondicao(8, () -> this.model.getAtividadesPlanoDeTreino(codigoUtilizador).size() > 0);
            menu.setHandler(8, () -> this.removerAtividadePlanoDeTreino(codigoUtilizador));
            
            menu.setCondicao(9, () -> this.model.getAtividadesPlanoDeTreino(codigoUtilizador).size() > 0);
            menu.setHandler(9, () -> this.mostrarPlanoDeTreino(codigoUtilizador));

            menu.setCondicao(10, () -> this.model.existemAtividadesRealizadas());
            menu.setHandler(10, () -> this.estatisticasGerias(codigoUtilizador));

            menu.setCondicao(11, () -> this.model.getAtividadesPlanoDeTreino(codigoUtilizador).size() > 0);
            menu.setHandler(11, () -> this.saltoNoTempo());

            menu.run();
        }
    }

    public void addAtividadeRealizada(String codigoUtilizador) {
        try {
            Atividade a = criarAtividade(codigoUtilizador);
            this.model.addAtividadeRealizada(codigoUtilizador, a);
        } catch (ParametrosInvalidosException e) {
            System.out.println("[ERRO] Parâmetros inválidos\n");
        } catch (UtilizadorNaoExisteException e) {
            System.out.println("[ERRO] Utilizador não existe\n");
        } catch (AtividadeExisteException e) {
            System.out.println("[ERRO] Atividade já existe\n");
        } catch (NomeAtividadeNaoExisteException e) {
            System.out.println("[ERRO] Atividade não tem recordes\n");
        }
   
    }

    public void removerAtividadeRealizada(String codigoUtilizador) {
        System.out.print("\nInsira o código da atividade que pretende remover: ");
        String codigoAtividade = sc.nextLine();
        
        try {
            this.model.removerAtividade(codigoUtilizador, codigoAtividade);
            System.out.println("[SUCESSO] Atividade removida\n");
        } catch (UtilizadorNaoExisteException e) {
            System.out.println("[ERRO] Utilizador não existe\n");
        } catch (AtividadeNaoExisteException e) {
            System.out.println("[ERRO] Atividade não existe\n");
        } 
    }

    public void mostrarAtividadesRealizadas(String codigoUtilizador) {
        System.out.println("\nAtividades realizadas: ");
        System.out.println(this.model.getAtividadesRealizadas(codigoUtilizador));

        //List<Atividade> atividades = this.model.getAtividadesRealizadas(codigoUtilizador);
        //for (Atividade a : atividades) 
        //    System.out.println(a);
    }

    public void criarPlanoDeTreino(String codigoUtilizador) {
        System.out.print("Data (ano): ");
        int ano = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        System.out.print("Data (mês): ");
        int mes = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        System.out.print("Data (dia): ");
        int dia = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        LocalDate data = LocalDate.of(ano, mes, dia);

        System.out.print("Duração (semanas): ");
        int duracao = sc.nextInt();
        sc.nextLine(); // limpar o buffer

        try {
            this.model.setPlanoDeTreino(codigoUtilizador, new PlanoDeTreino(data, duracao));
    
            System.out.print("Quantas atividades pretende inserir: ");
            int nAtividades = sc.nextInt();
            sc.nextLine(); // limpar o buffer
    
            for(int i=0; i<nAtividades; i++) {
                try {
                    this.model.addAtividadePlanoDeTreino(codigoUtilizador, criarAtividade(codigoUtilizador));
                } catch (ParametrosInvalidosException e) {
                    System.out.println("\n[ERRO] Parâmetros inválidos\n");
                }
            }
        } catch (UtilizadorNaoExisteException e) {
            System.out.println("[ERRO] Utilizador não existe\n");
        } catch (AtividadeExisteException e) {
            System.out.println("[ERRO] Atividade já existe\n");
        }
    }


    public void criarPlanoDeTreinoComObjetivos(String codigoUtilizador) {
        Utilizador utilizador = this.model.getUtilizador(codigoUtilizador);
        // Map<Atividade, Integer> atividadesComRecorrenciaSemanal

        System.out.print("\nCalorias objetivo: ");
        double caloriasObjetivo = sc.nextDouble();
        sc.nextLine(); // limpar o buffer
        System.out.print("Número máximo de atividades por dia: ");
        int numMaxAtividadesPorDia = sc.nextInt();
        sc.nextLine(); // limpar o buffer
                
        Menu menu = new Menu(new String[] { "Plano de treino de distância",
                            "Plano de treino de distância e altimetria",
                            "Plano de treino de repetições",
                            "Plano de treino de repetições com pesos",
                            "Plano de treino de distância com atividades hard",
                            "Plano de treino de distância e altimetria com atividades hard",
                            "Plano de treino de repetições com atividades hard",
                            "Plano de treino de repetições com pesos com atividades hard"
                        });


        Atividade sprint = new Sprint("sprint", "", LocalDateTime.now(), 15, 1, utilizador, 0.2);
        Atividade bicicletaMontanha = new BicicletaMontanha("BicicletaMontanha", "", LocalDateTime.now(), 120, 1, utilizador, 15, 20, 20, 8, true);
        Atividade abdominais = new Abdominais("Abdominais", "", LocalDateTime.now(), 3, 3, utilizador, 40, 60);
        Atividade supino = new Supino("Supino", "", LocalDateTime.now(), 1, 3, utilizador, 20, 40, 25);

        System.out.println(sprint.calorias());
        System.out.println(bicicletaMontanha.calorias());
        System.out.println(abdominais.calorias());
        System.out.println(supino.calorias());

        List<Atividade> atividadesDistancia = new ArrayList<>();
        atividadesDistancia.add(sprint);

        List<Atividade> atividadesDistanciaComHard = new ArrayList<>();
        atividadesDistanciaComHard.add(bicicletaMontanha);
        atividadesDistanciaComHard.add(sprint);


        List<Atividade> atividadesDistanciaEAltimetria = new ArrayList<>();
        // falta uma atividade não hard

        List<Atividade> atividadesDistanciaEAltimetriaComHard = new ArrayList<>();
        atividadesDistanciaEAltimetriaComHard.add(bicicletaMontanha);

        
        List<Atividade> atividadesRepeticoes = new ArrayList<>();
        atividadesRepeticoes.add(abdominais);

        List<Atividade> atividadesRepeticoesComHard = new ArrayList<>();
        atividadesRepeticoesComHard.add(supino);
        atividadesRepeticoesComHard.add(abdominais);


        List<Atividade> atividadesRepeticoesComPesos = new ArrayList<>();
        // falta uma atividade não hard

        List<Atividade> atividadesRepeticoesComPesosComHard = new ArrayList<>();
        atividadesRepeticoesComPesosComHard.add(supino);


        menu.setCondicao(1, () -> atividadesDistancia.size() > 0);
        menu.setHandler(1, () -> this.preencherPlanoDeTreinoComObjetivos(codigoUtilizador, atividadesDistancia, numMaxAtividadesPorDia, caloriasObjetivo));
            
        menu.setCondicao(2, () -> atividadesDistanciaEAltimetria.size() > 0);
        menu.setHandler(2, () -> this.preencherPlanoDeTreinoComObjetivos(codigoUtilizador, atividadesDistanciaEAltimetria, numMaxAtividadesPorDia, caloriasObjetivo));
                        
        menu.setCondicao(3, () -> atividadesRepeticoes.size() > 0);                
        menu.setHandler(3, () -> this.preencherPlanoDeTreinoComObjetivos(codigoUtilizador, atividadesRepeticoes, numMaxAtividadesPorDia, caloriasObjetivo));
        
        menu.setCondicao(4, () -> atividadesRepeticoesComPesos.size() > 0);                
        menu.setHandler(4, () -> this.preencherPlanoDeTreinoComObjetivos(codigoUtilizador, atividadesRepeticoesComPesos, numMaxAtividadesPorDia, caloriasObjetivo));
        
        menu.setCondicao(5, () -> atividadesDistanciaComHard.size() > 0);
        menu.setHandler(5, () -> this.preencherPlanoDeTreinoComObjetivos(codigoUtilizador, atividadesDistanciaComHard, numMaxAtividadesPorDia, caloriasObjetivo));
        
        menu.setCondicao(6, () -> atividadesDistanciaEAltimetriaComHard.size() > 0);    
        menu.setHandler(6, () -> this.preencherPlanoDeTreinoComObjetivos(codigoUtilizador, atividadesDistanciaEAltimetriaComHard, numMaxAtividadesPorDia, caloriasObjetivo));
        
        menu.setCondicao(7, () -> atividadesRepeticoesComHard.size() > 0);                
        menu.setHandler(7, () -> this.preencherPlanoDeTreinoComObjetivos(codigoUtilizador, atividadesRepeticoesComHard, numMaxAtividadesPorDia, caloriasObjetivo));
        
        menu.setCondicao(8, () -> atividadesRepeticoesComPesosComHard.size() > 0);                
        menu.setHandler(8, () -> this.preencherPlanoDeTreinoComObjetivos(codigoUtilizador, atividadesRepeticoesComPesosComHard, numMaxAtividadesPorDia, caloriasObjetivo));
            
        menu.run();

    }


    public void preencherPlanoDeTreinoComObjetivos(String codigoUtilizador, List<Atividade> atividades, int numMaxAtividadesPorDia, double caloriasObjetivo) {
        if (numMaxAtividadesPorDia > 3) numMaxAtividadesPorDia = 3; // máximo de 3 atividades diárias
        int acumuladorAtividades = 999; // atribuir um valor suficientemente grande para entrar no ciclo while
        int acumuladorAtividadesHard = 999;
        int numMaxAtividadesPorSemana = 7*numMaxAtividadesPorDia;
        while (numMaxAtividadesPorSemana < acumuladorAtividades || acumuladorAtividadesHard > 3) {
            acumuladorAtividades = 0; // reset
            acumuladorAtividadesHard = 0;

            Map<Atividade, Integer> atividadesComRecorrenciaSemanal = new HashMap<>();
            
            for (Atividade atividade : atividades) {
                System.out.print("Quantas vezes deseja repetir a atividade " + atividade.getClass().getSimpleName() + " por semana? ");
                int vezesPorSemana = sc.nextInt();
                sc.nextLine(); // limpar buffer

                atividadesComRecorrenciaSemanal.put(atividade, vezesPorSemana);
                acumuladorAtividades += vezesPorSemana;
                if (atividade instanceof Hard) acumuladorAtividadesHard += vezesPorSemana;
            }

            if (numMaxAtividadesPorSemana < acumuladorAtividades) System.out.println("[ERRO] Ultrapassou o limite máximo de atividades numa semana (" + numMaxAtividadesPorSemana + " atividades) ");
            else if (acumuladorAtividadesHard > 3) System.out.println("[ERRO] Ultrapassou o limite máximo de atividades hard numa semana (3 atividades hard) ");
            else this.model.planoDeTreinoComObjetivos(codigoUtilizador, atividadesComRecorrenciaSemanal, numMaxAtividadesPorDia, caloriasObjetivo);
        }
    }



    public void addAtividadePlanoDeTreino(String codigoUtilizador) {
        try {
            Atividade a = criarAtividade(codigoUtilizador);
            this.model.addAtividadePlanoDeTreino(codigoUtilizador, a); 
        } catch (ParametrosInvalidosException e) {
            System.out.println("\n[ERRO] Parâmetros inválidos\n");
        } catch (UtilizadorNaoExisteException e) {
            System.out.println("[ERRO] Utilizador não existe\n");
        } catch (AtividadeExisteException e) {
            System.out.println("[ERRO] Atividade já existe\n");
        }
    }

    public Atividade criarAtividade(String codigoUtilizador) throws ParametrosInvalidosException, UtilizadorNaoExisteException {
        Utilizador utilizador = this.model.getUtilizador(codigoUtilizador);

        System.out.print("\nCodigo da atividade: ");
        String codigo = sc.nextLine();

        System.out.print("Descrição: ");
        String descricao = sc.nextLine();

        System.out.print("Data (ano): ");
        int ano = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        System.out.print("Data (mês): ");
        int mes = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        System.out.print("Data (dia): ");
        int dia = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        System.out.print("Data (hora): ");
        int hora = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        System.out.print("Data (minuto): ");
        int minuto = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        System.out.print("Data (segundo): ");
        int segundo = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        LocalDateTime data = LocalDateTime.of(ano, mes, dia, hora, minuto, segundo);

        System.out.print("Duração (minutos): ");
        int duracao = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        

        System.out.print("Número de séries: ");
        int series = sc.nextInt();
        sc.nextLine(); // limpar o buffer

        System.out.print("Tipo de atividade (1 - Atividades de Distância, 2 - Atividades de Repetições): ");
        int tipoAtividade = sc.nextInt();
        sc.nextLine(); // limpar o buffer

        Atividade a; // tratamento com throw
        switch (tipoAtividade) {
            
            /*-------ATIVIDADES DE DISTÂNCIA-------*/
            case 1:
                System.out.print("Distância percorrida (kms): ");
                double distancia = sc.nextDouble();
                sc.nextLine(); // limpar o buffer

                System.out.print("Atividades de Distância (1 - Sprint, 2 - Atividades de Distância e Altimetria): ");
                int tipoAtividadeDistancia = sc.nextInt();
                sc.nextLine(); // limpar o buffer
                switch (tipoAtividadeDistancia) {

                    /*-------SPRINT-------*/
                    case 1:
                        a = new Sprint(codigo, descricao, data, duracao, series, utilizador, distancia);
                        break;

                    /*-------ATIVIDADES DE DISTÂNCIA E ALTIMETRIA-------*/
                    case 2:
                        System.out.print("Altimetria (m): ");
                        int altimetria = sc.nextInt();
                        sc.nextLine(); // limpar o buffer
                        
                        System.out.print("Atividades de Distância e Altimetria (1 - BicicletaMontanha): ");
                        int tipoAtividadeDistanciaAltimetria = sc.nextInt();
                        sc.nextLine(); // limpar o buffer
                        switch (tipoAtividadeDistanciaAltimetria) {

                            /*-------BICICLETA MONTANHA-------*/
                            case 1:
                                System.out.print("Variação Suspensão (mm): ");
                                double variacaoSuspencao = sc.nextDouble();
                                sc.nextLine(); // limpar o buffer
        
                                System.out.print("Número de mudanças: ");
                                int numeroMudancas = sc.nextInt();
                                sc.nextLine(); // limpar o buffer
        
                                System.out.print("Disco de travão (1 - Sim, 2 - Não): ");
                                boolean discoTravao = sc.nextInt() == 1 ? true : false;
                                sc.nextLine(); // limpar o buffer
                                                                                                                                
                                a = new BicicletaMontanha(codigo, descricao, data, duracao, series, utilizador, distancia, altimetria, variacaoSuspencao, numeroMudancas, discoTravao);
                                break;
                            default:
                                throw new ParametrosInvalidosException();

                        }
                        break;
                    default:
                        throw new ParametrosInvalidosException();

                }
                break;

            /*-------ATIVIDADES DE REPETIÇÕES-------*/
            case 2:
                System.out.print("Número de repetições: ");
                int repeticoes = sc.nextInt();
                sc.nextLine(); // limpar o buffer

                System.out.print("Atividades de Repetições (1 - Abdominais, 2 - Atividades de Repetições com Pesos): ");
                int tipoAtividadeRepeticoes = sc.nextInt();
                sc.nextLine(); // limpar o buffer
                switch (tipoAtividadeRepeticoes) {
                    /*-------ABDOMINAIS-------*/
                    case 1:
                        System.out.print("Amplitude (graus): ");
                        double amplitude = sc.nextDouble();
                        sc.nextLine(); // limpar o buffer

                        a = new Abdominais(codigo, descricao, data, duracao, series, utilizador, repeticoes, amplitude);
                        break;
                    /*-------ATIVIDADES DE REPETIÇÕES COM PESOS-------*/
                    case 2:
                        System.out.print("Peso (kg): ");
                        double peso = sc.nextDouble();
                        sc.nextLine(); // limpar o buffer

                        System.out.print("Atividades de Repetições com Peso (1 - Supino): ");
                        int tipoAtividadeRepeticoesPeso = sc.nextInt();
                        sc.nextLine(); // limpar o buffer

                        switch (tipoAtividadeRepeticoesPeso) {
                            /*-------SUPINO-------*/
                            case 1:
                                System.out.print("Inclinacao (graus): ");
                                double inclinacao = sc.nextDouble();
                                sc.nextLine(); // limpar o buffer
    
                                a = new Supino(codigo, descricao, data, duracao, series, utilizador, repeticoes, peso, inclinacao);
                                break;
                            default:
                                throw new ParametrosInvalidosException();

                        }
                        break;
                    default:
                        throw new ParametrosInvalidosException();

                }
                break;
            default:
                throw new ParametrosInvalidosException();

        }
        return a;
    }



    public void removerAtividadePlanoDeTreino(String codigoUtilizador) {
        System.out.print("\nInsira o código da atividade que pretende remover: ");
        String codigoAtividade = sc.nextLine();
        
        try {
            this.model.removeAtividadePlanoDeTreino(codigoUtilizador, codigoAtividade);      
        } catch (UtilizadorNaoExisteException e) {
            System.out.println("[ERRO] Utilizador não existe\n");
        } catch (AtividadeNaoExisteException e) {
            System.out.println("[ERRO] Atividade não existe\n");
        }
    }

    public void mostrarPlanoDeTreino(String codigoUtilizador) {
        System.out.println("\nPlano de Treino: ");
        System.out.println(this.model.getAtividadesPlanoDeTreinoCrescente(codigoUtilizador));
    }

    public void saltoNoTempo() {
        System.out.print("\nNúmero de dias a avançar: ");
        int dias = sc.nextInt();
        sc.nextLine();

        try {
            this.model.saltoNoTempo(dias);
            System.out.println("\n[SUCESSO] Avançou " + dias + " dias: ");
        } catch (NomeAtividadeNaoExisteException e) {
            System.out.println("[ERRO] Atividade não tem recordes\n");
        }
    }

    public void estatisticasGerias(String codigoUtilizador) {
        System.out.print("Pretende ver num dado período ou desde sempre? (1 - período, 2 - sempre): ");
        int op = sc.nextInt();
        sc.nextLine(); // limpar o buffer
        
        LocalDate dataInicio = LocalDate.EPOCH;
        LocalDate dataFim = this.model.getData();

        if (op == 1) {
            do {
                System.out.print("Insira o ano da data inicial: ");
                int dataInicioAno = sc.nextInt();
                sc.nextLine(); // limpar o buffer

                System.out.print("Insira o mês da data inicial: ");
                int dataInicioMes = sc.nextInt();
                sc.nextLine(); // limpar o buffer

                System.out.print("Insira o dia da data inicial: ");
                int dataInicioDia = sc.nextInt();
                sc.nextLine(); // limpar o buffer

                dataInicio = LocalDate.of(dataInicioAno, dataInicioMes, dataInicioDia);

                System.out.print("Insira o ano da data final: ");
                int dataFimAno = sc.nextInt();
                sc.nextLine(); // limpar o buffer

                System.out.print("Insira o mês da data final: ");
                int dataFimMes = sc.nextInt();
                sc.nextLine(); // limpar o buffer

                System.out.print("Insira o dia da data final: ");
                int dataFimDia = sc.nextInt();
                sc.nextLine(); // limpar o buffer

                dataFim = LocalDate.of(dataFimAno, dataFimMes, dataFimDia);

                if (dataFim.isBefore(dataInicio)) {
                    System.out.println("[ERRO] Datas inválidas\n");
                }
            } while (dataFim.isBefore(dataInicio));
        }
        
        final LocalDate constDataInicio = dataInicio; 
        final LocalDate constDataFim = dataFim;

        Menu menu = new Menu(new String[] {"Qual é o utilizador que mais calorias dispendeu num período ou desde sempre",
                            "Quantas calorias é que dispendeu num período ou desde sempre",
                            "Qual é o utilizador que mais actividades realizou num período ou desde sempre",
                            "Quantos kms é que realizou num período ou desde sempre",
                            "Quantos metros de altimetria é que totalizou num período ou desde sempre",
                            "Quantos kg de peso é que levantou num período ou desde sempre",
                            "Quantas repetições é que totalizou num período ou desde sempre",
                            "Qual é o tipo de actividade mais realizada",
                            "Qual é o plano de treino mais exigente em função do dispêndio de calorias proposto (Média semanal)"
                            //"Recordes individuais",
                            //"Recordes da aplicação"
                        });

        menu.setHandler(1, () -> System.out.println(this.model.utilizadorMaisCalorias(constDataInicio, constDataFim)));
            
        menu.setCondicao(2, () -> this.model.getAtividadesRealizadas(codigoUtilizador).size() > 0);
        menu.setHandler(2, () -> System.out.println(this.model.estatisticaAcumulacaoPeriodo(codigoUtilizador, constDataInicio, constDataFim, FitnessModel.getPredicate("éAtividade"), FitnessModel.getToDoubleFunction("obtémCalorias")) + " calorias"));

        menu.setHandler(3, () -> System.out.println(this.model.utilizadorComMaisAtividades(constDataInicio, constDataFim)));
                        
        menu.setCondicao(4, () -> this.model.getAtividadesRealizadas(codigoUtilizador).size() > 0);
        menu.setHandler(4, () -> System.out.println(this.model.estatisticaAcumulacaoPeriodo(codigoUtilizador, constDataInicio, constDataFim, FitnessModel.getPredicate("éAtividadeDistância"), FitnessModel.getToDoubleFunction("obtémDistancia")) + " kms"));
        
        menu.setCondicao(5, () -> this.model.getAtividadesRealizadas(codigoUtilizador).size() > 0);
        menu.setHandler(5, () -> System.out.println(this.model.estatisticaAcumulacaoPeriodo(codigoUtilizador, constDataInicio, constDataFim, FitnessModel.getPredicate("éAtividadeDistânciaAltimetria"), FitnessModel.getToDoubleFunction("obtémAltimetria")) + " m"));

        menu.setCondicao(6, () -> this.model.getAtividadesRealizadas(codigoUtilizador).size() > 0);
        menu.setHandler(6, () -> System.out.println(this.model.estatisticaAcumulacaoPeriodo(codigoUtilizador, constDataInicio, constDataFim, FitnessModel.getPredicate("éAtividadeRepetiçõesPesos"), FitnessModel.getToDoubleFunction("obtémPeso")) + " kg"));

        menu.setCondicao(7, () -> this.model.getAtividadesRealizadas(codigoUtilizador).size() > 0);
        menu.setHandler(7, () -> System.out.println(this.model.estatisticaAcumulacaoPeriodo(codigoUtilizador, constDataInicio, constDataFim, FitnessModel.getPredicate("éAtividadeRepetições"), FitnessModel.getToDoubleFunction("obtémRepetições")) + " repetições"));

        menu.setHandler(8, () -> System.out.println(this.model.atividadeMaisRealizada()));
            
        menu.setHandler(9, () -> System.out.println(this.model.planoDeTreinoMaisExigente()));                        

        //menu.setHandler(10, () -> );

        //menu.setHandler(11, () -> );

        menu.run();
    }


    public void utilizadorMaisCalorias() {
    }

}