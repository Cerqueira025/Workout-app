import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;

import Atividade.Atividade;
import Atividade.Distancia.Distancia;
import Atividade.Distancia.Sprint;
import Atividade.Distancia.Altimetria.Altimetria;
import Atividade.Distancia.Altimetria.BicicletaMontanha;
import Atividade.Repeticoes.Repeticoes;
import Atividade.Repeticoes.Abdominais;
import Atividade.Repeticoes.Pesos.Pesos;
import Atividade.Repeticoes.Pesos.Supino;
import Excessoes.EmailExisteException;
import Excessoes.ParametrosInvalidosException;
import Excessoes.UtilizadorExisteException;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Genero;
import Utilizador.Utilizador;
import Utilizador.TiposUtilizador.*;

public class TextUI {
    private FitnessModel model;
    private Scanner sc;

    public TextUI() {
        this.model = new FitnessModel(); // definir a data posteriormente
        
        Predicate<Atividade> ativ = a -> a instanceof Atividade;
        this.model.addPredicate("éAtividade", ativ);
        Predicate<Atividade> dist = a -> a instanceof Distancia;
        this.model.addPredicate("éAtividadeDistância", dist);
        Predicate<Atividade> alti = a -> a instanceof Altimetria;
        this.model.addPredicate("éAtividadeDistânciaAltimetria", alti);
        Predicate<Atividade> repe = a -> a instanceof Repeticoes;
        this.model.addPredicate("éAtividadeRepetições", repe);
        Predicate<Atividade> peso = a -> a instanceof Pesos;
        this.model.addPredicate("éAtividadeRepetiçõesPesos", peso);
        
        ToDoubleFunction<Atividade> calculoCalorias = a -> a.calorias();
        this.model.addToDoubleFunction("obtémCalorias", calculoCalorias);
        ToDoubleFunction<Atividade> calculoRepeticoes = a -> ((Repeticoes) a).getRepeticoes();
        this.model.addToDoubleFunction("obtémRepetições", calculoRepeticoes);
        ToDoubleFunction<Atividade> calculoPeso = a -> ((Pesos) a).getPeso();
        this.model.addToDoubleFunction("obtémPeso", calculoPeso);
        ToDoubleFunction<Atividade> calculoDistancia = a -> ((Distancia) a).getDistancia();
        this.model.addToDoubleFunction("obtémDistancia", calculoDistancia);
        ToDoubleFunction<Atividade> calculoAltimetria = a -> ((Altimetria) a).getAltimetria();
        this.model.addToDoubleFunction("obtémAltimetria", calculoAltimetria);
        
        sc = new Scanner(System.in);

    }

    public void run() {
        Menu menu = new Menu(new String[] {
                        "Carregar Estado",
                        "Nova Simulação"
                        });

        menu.setHandler(1, () -> carregaEstado());
        menu.setHandler(2, () -> paginaInicial());

        menu.run();
    }

    public void carregaEstado() {
        System.out.print("\nFicheiro a carregar: ");
        String ficheiroCarregado = sc.nextLine();

        try {
            this.model.carregaEstado(ficheiroCarregado);
            System.out.println("\n[SUCESSO] Ficheiro carregado\n");
            paginaInicial();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\n[ERRO] Falha ao ler dados\n" + e);
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





    public Utilizador criarUtilizador(int tipoUtilizador, String codigo, int bpmMedio, double peso, double caloriasGastas,
                               int altura, String nome, Genero genero, String morada, String email, String password) throws ParametrosInvalidosException,
                               UtilizadorExisteException, EmailExisteException {
        Utilizador u;
        switch (tipoUtilizador) {
            case 1:
                u = new PraticanteOcasional(codigo, bpmMedio, peso, caloriasGastas, altura, nome, genero, morada, email, password);
                break;
            case 2:
                u = new Amador(codigo, bpmMedio, peso, caloriasGastas, altura, nome, genero, morada, email, password);
                break;
            case 3:
                u = new Profissional(codigo, bpmMedio, peso, caloriasGastas, altura, nome, genero, morada, email, password);
                break;
            default:
                throw new ParametrosInvalidosException();
        }
        if (codigo.length() == 0 || (bpmMedio <= 0 || bpmMedio >= 200)|| (Double.compare(peso,0) <= 0
                                 || Double.compare(peso,500) >= 0) || (altura <= 0 || altura >= 300)
                                 || nome.length() == 0 || morada.length() == 0 || email.length() == 0
                                 || password.length() == 0) 
            throw new ParametrosInvalidosException();
         
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
                            "Remover um plano de treino", 
                            "Adicionar uma atividade ao plano de treino", 
                            "Remover uma atividade do plano de treino", 
                            "Mostrar o plano de treino"
                        });
            
            menu.setHandler(1, () -> addAtividadeRealizada(codigoUtilizador));
            
            menu.setCondicao(2, () -> this.model.getAtividadesRealizadas(codigoUtilizador).size() > 0);
            menu.setHandler(2, () -> removerAtividadeRealizada(codigoUtilizador));
            
            menu.setCondicao(3, () -> this.model.getAtividadesRealizadas(codigoUtilizador).size() > 0);
            menu.setHandler(3, () -> mostrarAtividadesRealizadas(codigoUtilizador));
            
            menu.setHandler(4, () -> criarPlanoDeTreino(codigoUtilizador));
            
            menu.setCondicao(5, () -> this.model.getAtividadesPlanoDeTreino(codigoUtilizador).size() > 0);
            menu.setHandler(5, () -> limparPlanoDeTreino(codigoUtilizador));
            
            menu.setCondicao(6, () -> this.model.getPlanoDeTreino(codigoUtilizador).getDuracao() > 0);
            menu.setHandler(6, () -> addAtividadePlanoDeTreino(codigoUtilizador));
            
            menu.setCondicao(7, () -> this.model.getAtividadesPlanoDeTreino(codigoUtilizador).size() > 0);
            menu.setHandler(7, () -> removerAtividadePlanoDeTreino(codigoUtilizador));
            
            menu.setCondicao(8, () -> this.model.getAtividadesPlanoDeTreino(codigoUtilizador).size() > 0);
            menu.setHandler(8, () -> mostrarPlanoDeTreino(codigoUtilizador));

            menu.run();
        }
    }

    public void addAtividadeRealizada(String codigoUtilizador) {
        Atividade a = criarAtividade(codigoUtilizador);
        this.model.addAtividadeRealizada(codigoUtilizador, a);
    }

    public void removerAtividadeRealizada(String codigoUtilizador) {
        System.out.print("\nInsira o código da atividade que pretende remover: ");
        String codigoAtividade = sc.nextLine();
        
        this.model.removerAtividade(codigoUtilizador, codigoAtividade);
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

        this.model.setPlanoDeTreino(codigoUtilizador, new PlanoDeTreino(data, duracao));

        System.out.print("Quantas atividades pretende inserir: ");
        int nAtividades = sc.nextInt();
        sc.nextLine(); // limpar o buffer

        for(int i=0; i<nAtividades; i++) {
            this.model.addAtividadePlanoDeTreino(codigoUtilizador, criarAtividade(codigoUtilizador));
        }


    }

    public void limparPlanoDeTreino(String codigoUtilizador) {
        this.model.limparPlanoDeTreino(codigoUtilizador);
    }

    public void addAtividadePlanoDeTreino(String codigoUtilizador) {
        Atividade a = criarAtividade(codigoUtilizador);
        this.model.addAtividadePlanoDeTreino(codigoUtilizador, a);
    }

    public void removerAtividadePlanoDeTreino(String codigoUtilizador) {
        System.out.print("\nInsira o código da atividade que pretende remover: ");
        String codigoAtividade = sc.nextLine();
        
        this.model.removeAtividadePlanoDeTreino(codigoUtilizador, codigoAtividade);
    }

    public void mostrarPlanoDeTreino(String codigoUtilizador) {
        System.out.println("\nPlano de Treino: ");
        System.out.println(this.model.getPlanoDeTreino(codigoUtilizador));
    }


    public Atividade criarAtividade(String codigoUtilizador) {
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

        Atividade a = new Sprint(); // REVER
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
                            // todo
                                break;
                        }
                        break;
                    default:
                    // todo
                        break;
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
                                // todo
                                break;
                        }
                        break;
                    default:
                        // todo
                        break;
                }
                break;
            default:
                // todo
                break;
        }

        return a;
    }

}