import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Menu {
  public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
    User user = new User();
    UserManager userManager = new UserManager();
    Scanner scanner_menu = new Scanner(System.in);
    utilizador(user, userManager);
    // User user = new User("Lara02","bacalhau","Lara","Rua do
    // continente","a02@uminho.pt",70,19,55,2);
    menu_principal(user, userManager);
    scanner_menu.close();
  }

  /**
   * Método responsável pelo Sing in ou registo dos users
   * 
   * @param user
   * @throws IOException
   * @throws ClassNotFoundException
   * @throws FileNotFoundException
   */
  private static void utilizador(User user, UserManager userManager)
      throws FileNotFoundException, ClassNotFoundException, IOException {

    int login_option = Integer.parseInt(JOptionPane.showInputDialog("Sign in(1) or Register(2)"));

    // realizar o login ou registo
    if (login_option == 1) {
      user = userManager.signin();
    } else if (login_option == 2) {
      user = userManager.register();
    } else {
      JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente");
      return;
    }
  }

  private static void menu_principal(User user, UserManager userManager) {
    int menu_option;

    do {
      menu_option = Integer.parseInt(JOptionPane.showInputDialog(
          "Escolha uma opção:\n" +
              "1. Treino\n" +
              "2. Histórico\n" +
              "3. Estatísticas\n" +
              "4. Global\n" +
              "5. Configurações\n" +
              "6. Sair\n"));
      switch (menu_option) {
        case 1:
          System.out.println("Treino");
          op_treino();
          break;
        case 2:
          System.out.println("Histórico");
          break;
        case 3:
          System.out.println("Estatísticas.");
          System.out.println(user.toString());
          break;
        case 4:
          System.out.println("Global");
          List<User> users = userManager.getList();
          userManager.listOfUsers(users);
          break;
        case 5:
          System.out.println("Configurações");
          break;
        case 6:
          System.out.println("Saindo do programa...");
          System.exit(0);
          break;
        default:
          System.out.println("Opção inválida. Por favor, escolha novamente.");
          break;
      }
    } while (menu_option != 6);
  }

  /**
   * Este método é chamado quando a opção 'Treino' é selecionada no menu
   */
  private static void op_treino() {
    int opcao;
    do{
      opcao = Integer.parseInt(JOptionPane.showInputDialog(
        "O que pretende fazer?\n"+
        "1. Registar treino\n"+
        "2. Criar plano\n" +
        "3. Sair\n"));
        Treino treino = new Treino();
        switch (opcao) {
          case 1:
            treino.regista_treino();
            break;
          case 2:
            treino.cria_treino();
            break;
          case 3:
            System.out.println("Saindo do programa...");
            break;
          default:
            System.out.println("Opção inválida. Por favor, escolha novamente.");
            break;
        }
    }
    while (opcao != 3);
  }

  public static void clearConsole() {
    /*
     * try{
     * final String os = System.getProperty("os.name");
     * 
     * if (os.contains("Windows")){
     * Runtime.getRuntime().exec("cls");
     * }
     * else{
     * Runtime.getRuntime().exec("clear");
     * }
     * }
     * catch (final Exception e){
     * System.out.println("Erro ao limpar o terminal: " + e.getMessage());
     * }
     */
    for (int i = 0; i < 100; i++) {
      System.out.println();
    }
    // System.out.print("\033\143");
  }
}

/**
 * A resolver:
 * - erro após a definição do user
 * - serialization (loadFromFile) (maybe done)
 * - erro ao selecionar os exercícios para um treino (Treino ->
 * 'addExercicioRepeticoes')
 * - aprimorar os menus
 * 
 * A desenvolver:
 * - calculo da taxa metabólica
 * - criação de rankings e records
 * - criação de um calendário
 */