import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager implements Serializable {
  private static final long serialVersionUID = 1L;

  private List<User> users;
  private String fileName = "users.ser";

  // Construtor
  public UserManager() throws FileNotFoundException, ClassNotFoundException, IOException {
    this.users = new ArrayList<>();
    loadUsersFromFile();
  }

  // getters
  public List<User> getList() {
    return users;
  }

  /**
   * Método responsável por descarregar os utilizadores do ficheiro de dados
   */
  @SuppressWarnings("unchecked")
  private void loadUsersFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
    FileInputStream fileIn = new FileInputStream(fileName);
    ObjectInputStream in = new ObjectInputStream(fileIn);
    users = (ArrayList<User>) in.readObject();
    in.close();
    fileIn.close();
  }

  /**
   * Método que guarda o registo de um novo utilizador no ficheiro
   * 
   * @throws IOException
   */
  private void saveUserToFile(User user) {
    /*
     * FileOutputStream fileOut = new FileOutputStream(fileName);
     * ObjectOutputStream out = new ObjectOutputStream(fileOut);
     * out.writeObject(user);
     * out.close();
     * fileOut.close();
     */
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
      users.add(user);
      oos.writeObject(users);
    } catch (IOException e) {
      System.err.println("Ocorreu um erro ao salvar o usuário: " + e.getMessage());
    }
  }

  /**
   * Método que permite a sincronização de uma conta.
   * 
   * São pedidos os dados de acesso a uma conta, no caso, o nome de utilizador
   * e a senha. Posteriormente vai verificar se o utilizador existe nos dados
   * da aplicação.
   */
  public User signin() {
    User actualUser = new User();
    Scanner sigin_scanner = new Scanner(System.in);
    System.out.println("User name:");
    String username = sigin_scanner.nextLine();
    System.out.println("Password:");
    String password = sigin_scanner.nextLine();

    boolean found = false;
    for (User user : users) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
        // se houver um registo com igual nome de utilizador e senha
        System.out.println("Login bem-sucedido!");
        // define os parametros do user
        actualUser = user.clone();
        /*
         * Qual o mais correto?
         * actualUser = User(user.getUsername(),user.getPassword(),user.getName(),
         * user.getMorada(), user.getEmail(), user.getFrequenciaCardiaca(),
         * user.getIdade(),user.getPeso(),user.getNivel());
         * actualUser.setUserName(user.getUsername());
         * actualUser.setPassword(user.getPassword());
         * actualUser.setName(user.getName());
         * actualUser.setMorada(user.getMorada());
         */

        found = true;
        break;
      }
    }
    if (!found) {
      System.err.println("Usuário ou senha incorretos.");
      System.exit(1);
    }
    sigin_scanner.close();
    return actualUser;
  }

  /**
   * Método que permite o registo de um novo utilizador.
   * 
   * Questiona por dados como:
   * -nome de utilizador
   * -senha
   * -nome
   * -idade
   * -peso
   * -nível de atividade
   * 
   * O nível de atividade física permite conhecer a rotina do utilizador
   * de modo a adequar as cargas de treino.
   * 
   * Após recolher os dados, guarda-os no ficheiro para que o registo do
   * novo utilizador permaneça para utilizações futuras.
   * 
   */
  public User register() {
    Scanner register_scanner = new Scanner(System.in);
    String username = "";

    int exist = 1;
    while (exist != 0 && !users.isEmpty()) {
      System.out.println("Digite seu nome de usuário:");
      username = register_scanner.nextLine();
      for (User user : users) {
        if (user.getUsername().equals(username)) {
          System.out.println("Nome de utilizador já existe");
        } else {
          exist = 0;
          break;
        }
      }
    }
    System.out.println("Defina a sua senha:");
    String password = register_scanner.nextLine();

    System.out.println("Qual é o seu nome:");
    String name = register_scanner.nextLine();

    System.out.println("Indique a sua morada:");
    String morada = register_scanner.nextLine();

    System.out.println("Indique o seu email:");
    String email = register_scanner.nextLine();

    String sexoInput = register_scanner.nextLine();
    char sexoChar = sexoInput.charAt(0); // Obter o primeiro caractere da entrada
    Character sexo = sexoChar;

    System.out.println("Indique a sua altura:");
    int altura = register_scanner.nextInt();

    System.out.println("Qual é a sua frequência cardíaca média:");
    int frequencia_cardiaca = register_scanner.nextInt();

    System.out.println("Qual a sua idade:");
    int idade = register_scanner.nextInt();

    System.out.println("Indique o seu peso");
    int peso = register_scanner.nextInt();

    System.out.println("Qual o nivel de treino que se adequa a si?");
    System.out.println("Nível 1: o seu dia inclui pouco desporto e muito tempo sentado");
    System.out.println("Nível 2: passa maior parte do dia em pé devido ao trabalho ou tarefas diárias");
    System.out.println("Nível 3: o seu trabalho é fisicamente exigente, se pratica desporto ou se costuma estar ativo");

    int nivel = register_scanner.nextInt();

    User new_user = new User(username, password, name, morada, email, sexo, altura, frequencia_cardiaca, idade, peso,
        nivel);
    users.add(new_user); // Adiciona o user à lista
    saveUserToFile(new_user); // Guarda o novo user no arquivo
    System.out.println("Registro realizado com sucesso!");

    register_scanner.close();

    return new_user;
  }

  public void addUsers() {
    User new_user = new User("Lara02", "bacalhau", "Lara", "Rua do continente", "a02@uminho.pt", 'F', 165, 70, 19, 55,
        2);
    users.add(new_user); // Adiciona o user à lista
    saveUserToFile(new_user); // Guarda o novo user no arquivo
  }

  public void listOfUsers(List<User> users) {
    for (User user : users) {
      System.out.println(user.toString());
    }
  }

}
