import java.util.List;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

import Excessoes.AtividadeExisteException;
import Excessoes.AtividadeNaoExisteException;
import Excessoes.CredenciaisNaoCoincidem;
import Excessoes.EmailExisteException;
import Excessoes.ParametrosInvalidosException;
import Excessoes.UtilizadorExisteException;
import Excessoes.UtilizadorNaoExisteException;

import java.util.InputMismatchException;

public class FitnessView {

    private int op;
    private String codUtilizador;
    private String ficheiroCarregado;
    private TipoMenu menu;
    private List<String> opcoes;
    private List<String> atividades;
    private FitnessController controller;
   
   

    public FitnessView(String[] opcoes, String[] atividades, FitnessController controller) {
        this.op = 0;
        this.codUtilizador = "";
        this.ficheiroCarregado = "";
        this.menu = TipoMenu.Principal;
        this.opcoes = Arrays.asList(opcoes);
        this.atividades = Arrays.asList(atividades);
        this.controller = controller;
    }


    public void run() {
        do {
            switch (this.menu) {
                case Principal:
                    runPrincipal();
                    break;
                case Utilizador:
                    runUtilizador();
                    break;
                default:
                    break;
            }
        } while (this.menu != TipoMenu.Sair);
    }


    public void runPrincipal() {
        do {
            String[] opcoes = new String[] {"Registar", "Login", "Carregar estado", "Guardar e sair"};
            this.opcoes = Arrays.asList(opcoes);
            executa(() -> showMenu(""), true);
            switch (this.op) {
                case 1:
                    registarUtilizador();
                    break;
                case 2:
                    loginUtilizador();
                    break;
                case 3:
                    carregarEstado();
                    break;
                case 4:
                    guardaEstado();
                    break;
                default:
                    break;
            }
        } while (this.op != 0 && this.op != -1);
        if (this.op == 0) this.menu = TipoMenu.Sair;
    }


    public void runUtilizador() {
        do {
            String [] opcoes = new String[] {"Adicionar uma atividade solta (realizada)", "Remover uma atividade realizada", "Mostrar todas as atividades realizadas", "Adicionar um plano de treino", "Remover um plano de treino", "Adicionar uma atividade no plano de treino", "Remover uma atividade do plano de treino", "Mostrar o plano de treino detalhado", "Mostrar o plano de treino geral"};
            this.opcoes = Arrays.asList(opcoes);
            executa(() -> showMenu(" UTILIZADOR"), true);
            switch (this.op) {
                case 1:
                    adicionarAtividade();
                    break;
                case 2:
                    removerAtividade();
                    break;
                case 3:
                    // Mostrar todas as atividades realizadas;
                    break;
                case 4:
                    // Adicionar um plano de treino;
                    break;
                case 5:
                    // Remover um plano de treino;
                    break;
                case 6:
                    // Adicionar uma atividade no plano de treino;
                    break;
                case 7:
                    // Remover uma atividade do plano de treino;
                    break;
                case 8:
                    // Mostrar o plano de treino detalhado;
                    break;
                case 9:
                    // Mostrar o plano de treino geral;
                    break;
                default:
                    break;
            }
        } while (this.op != 0);
        this.menu = TipoMenu.Principal;
    }


    public void executa(Runnable runnable, boolean ler0) {
        do {
            runnable.run();
            this.op = lerOpcao(ler0);
        } while (this.op == -1);
    }
    
    private void showMenu(String titulo) {
        System.out.println("\n=======MENU" + titulo + "=======\n");
        showOpcoes();
        System.out.println("0 - Sair");
    }

    private void showOpcoes() {
        for (int i = 0; i < this.opcoes.size(); i++) {
            System.out.println(i+1 + " - " + this.opcoes.get(i));
        }
    }

    public int lerOpcao(boolean ler0) {
        int op;
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("\nOpção: ");
        try {
            op = scanner.nextInt();
        } catch (InputMismatchException e) {
            op = -1;
        }
        if (op < (ler0 ? 0 : 1) || op > this.opcoes.size()) {
            System.out.println("\n[ERRO] Opção Inválida!\n");
            op = -1;
        }
        return op;
    }

    public void guardaEstado() {
        Scanner scanner = new Scanner(System.in);
        
        String nomeFicheiroAGuardar = "";

        if (this.ficheiroCarregado.length() > 0) {
            System.out.print("\nCarregou este estado a partir de um ficheiro. Pretende guardar no mesmo ficheiro?\n");
            String [] opcoes = new String[] {"Sim", "Não"};
            this.opcoes = Arrays.asList(opcoes);
            executa(() -> showOpcoes(), false);
            if (this.op == 1)
                nomeFicheiroAGuardar = this.ficheiroCarregado;
        }
        if (this.ficheiroCarregado.length() <= 0 || this.op == 2) {     
            System.out.print("\nIndique o nome do novo ficheiro a guardar: ");
            nomeFicheiroAGuardar = scanner.nextLine();
        }
        try {
            this.controller.guardaEstado(nomeFicheiroAGuardar);
            System.out.println("\n[SUCESSO] Ficheiro guardado\n");
        } catch (IOException e) {
            System.out.println("\n[ERRO] Falha ao guardar dados\n" + e);
        }
        this.op = 0;
    }


    public void registarUtilizador() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nNome de utlizador: ");
        String codigo = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("Altura: ");
        int altura = scanner.nextInt();
        scanner.nextLine(); // limpar o buffer
        System.out.print("Peso: ");
        double peso = scanner.nextDouble();
        scanner.nextLine(); // limpar o buffer
        System.out.print("Morada: ");
        String morada = scanner.nextLine();
        System.out.print("Bpm médio: ");
        int bpmMedio = scanner.nextInt();
        scanner.nextLine(); // limpar o buffer
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            this.controller.registarUtilizador(codigo, bpmMedio, peso, altura,
            nome, genero, morada, email, password);
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



    public void loginUtilizador() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nNome de utlizador: ");
        String codigo = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            this.controller.loginUtilizador(codigo, password);
            System.out.println("\n[SUCESSO] Login efetuado!\n");
            this.menu = TipoMenu.Utilizador;
            this.op = -1;
            this.codUtilizador = codigo;
        }
        catch (ParametrosInvalidosException e) {
            System.out.println("\n[ERRO] Parâmetros inválidos\n");
        }
        catch (UtilizadorNaoExisteException e) {
            System.out.println("\n[ERRO] Utilizador não existe\n");
        }
        catch (CredenciaisNaoCoincidem e) {
            System.out.println("\n[ERRO] Password incorreta\n");
        }
    }

    public void carregarEstado() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nFicheiro a carregar: ");
        this.ficheiroCarregado = scanner.nextLine();

        try {
            this.controller.carregaEstado(this.ficheiroCarregado);
            System.out.println("\n[SUCESSO] Ficheiro carregado\n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("\n[ERRO] Falha ao ler dados\n" + e);
        } 
    }


    public void adicionarAtividade() {
        this.opcoes = this.atividades;
        executa(() -> showOpcoes(), false);

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nCodigo da atividade: ");
        String codigo = scanner.nextLine();
        System.out.print("Descrição da atividade: ");
        String descricao = scanner.nextLine();
        System.out.print("Data (dd/mm/aaaa): ");
        String data = scanner.nextLine();
        System.out.print("Duração (minutos): ");
        int duracao = scanner.nextInt();


        try {
            this.controller.adicionarAtividade(this.codUtilizador, codigo, descricao, data, duracao);
            System.out.println("\n[SUCESSO] Atividade adicionada\n");
        }
        catch (DateTimeParseException e) {
            System.out.println("\n[ERRO] Formato de data inválido\n");
        }
        catch (ParametrosInvalidosException e) {
            System.out.println("\n[ERRO] Parâmetros inválidos\n");
        }
        catch (UtilizadorNaoExisteException e) {
            System.out.println("\n[ERRO] Utilizador não existe\n");
        }
        catch (AtividadeExisteException e) {
            System.out.println("\n[ERRO] Atividade já existe\n");
        }
    }



    public void removerAtividade() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nCódigo da atividade: ");
        String codigoAtividade = scanner.nextLine();

        try {
            this.controller.removerAtividade(this.codUtilizador, codigoAtividade);
            System.out.println("\n[SUCESSO] Atividade eliminada!\n");
        }
        catch (ParametrosInvalidosException e) {
            System.out.println("\n[ERRO] Parâmetros inválidos\n");
        }
        catch (UtilizadorNaoExisteException e) {
            System.out.println("\n[ERRO] Utilizador não existe\n");
        }
        catch (AtividadeNaoExisteException e) {
            System.out.println("\n[ERRO] Atividade não existe\n");
        }
    }


}
