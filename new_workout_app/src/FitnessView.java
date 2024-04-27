import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

import Excessoes.CredenciaisNaoCoincidem;
import Excessoes.EmailExisteException;
import Excessoes.ParametrosInvalidosException;
import Excessoes.UtilizadorExisteException;
import Excessoes.UtilizadorNaoExisteException;

import java.util.InputMismatchException;

public class FitnessView {

    private List<String> opcoes;
    private TipoMenu menu;
    private int op;
    private FitnessController controller;
   
   

    public FitnessView(String[] opcoes, FitnessController controller) {
        this.opcoes = Arrays.asList(opcoes);
        this.menu = TipoMenu.Principal;
        this.controller = controller;
        this.op = 0;
    }


    public void run() {
        String[] opcoes;
        do {
            switch (this.menu) {
                case Principal:
                    opcoes = new String[] {"Registar", "Login"};
                    this.opcoes = Arrays.asList(opcoes);
                    runPrincipal();
                    break;
                case Utilizador:
                    opcoes = new String[] {"M1", "M2"};
                    this.opcoes = Arrays.asList(opcoes);
                    runUtilizador();
                    break;
                default:
                    break;
            }
        } while (this.menu != TipoMenu.Sair);
    }


    public void runPrincipal() {
        do {
            executa();
            switch (this.op) {
                case 1:
                    registarUtilizador();
                    break;
                case 2:
                    loginUtilizador();
                    break;
                default:
                    break;
            }
        } while (this.op != 0 && this.op != -1);
        if (this.op == 0) this.menu = TipoMenu.Sair;
    }


    public void runUtilizador() {
        do {
            executa();
            switch (this.op) {
                case 1:
                    // Adicionar uma atividade solta (realizada);
                    break;
                case 2:
                    // Remover uma atividade realizada;
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


    public void executa() {
        do {
            showMenu();
            this.op = lerOpcao();
        } while (this.op == -1);
    }
    


    private void showMenu() {
        System.out.println("\n *** MENU *** ");
        for (int i = 0; i < this.opcoes.size(); i++) {
            System.out.println(i+1 + " - " + this.opcoes.get(i));
        }
        System.out.println("0 - Sair");
    }



    public int lerOpcao() {
        int op;
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Opção: ");
        try {
            op = scanner.nextInt();
        } catch (InputMismatchException e) {
            op = -1;
        }
        if (op < 0 || op > this.opcoes.size()) {
            System.out.println("[ERRO] Opção Inválida!");
            op = -1;
        }
        return op;
    }


    public void registarUtilizador() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome de utlizador: ");
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
            System.out.println("[SUCESSO] Utilizador criado!");
        }
        catch (ParametrosInvalidosException e) {
            System.out.println("[ERRO] Parâmetros inválidos");
        }
        catch (UtilizadorExisteException e) {
            System.out.println("[ERRO] Nome de utilizador já existe");
        }
        catch (EmailExisteException e) {
            System.out.println("[ERRO] Email já existe");
        }
    }



    public void loginUtilizador() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome de utlizador: ");
        String codigo = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            this.controller.loginUtilizador(codigo, password);
            System.out.println("[SUCESSO] Login efetuado!");
            this.menu = TipoMenu.Utilizador;
            this.op = -1;
        }
        catch (ParametrosInvalidosException e) {
            System.out.println("[ERRO] Parâmetros inválidos");
        }
        catch (UtilizadorNaoExisteException e) {
            System.out.println("[ERRO] Utilizador não existe");
        }
        catch (CredenciaisNaoCoincidem e) {
            System.out.println("[ERRO] Password incorreta");
        }


        
    }


}