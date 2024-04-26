import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Genero;

import java.util.InputMismatchException;

public class FitnessView {

    private List<String> opcoes;
    private int op;
    private FitnessController controller;
   
   

    public FitnessView(String[] opcoes, FitnessController controller) {
        this.opcoes = Arrays.asList(opcoes);
        this.controller = controller;
        this.op = 0;
    }



    public void run() {
        do {
            executa();
            switch (this.op) {
                case 1:
                    registarUtilizador();
                    break;
                case 2:
                    //removerUtilizador();
                    break;
                case 3:
                    //listarUtilizadores();
                    break;
                default:
                    break;
            }
        } while (this.op != 0);
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
            System.out.println("Opção Inválida!");
            op = -1;
        }
        return op;
    }


    public void registarUtilizador() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
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
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Bpm médio: ");
        int bpmMedio = scanner.nextInt();
        scanner.nextLine(); // limpar o buffer
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            this.controller.registarUtilizador(codigo, bpmMedio, peso, altura,
            nome, genero, morada, email, password);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void removerUtilizador() {

    }

    public void listarUtilizador() {

    }

}