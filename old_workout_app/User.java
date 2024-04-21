import java.io.Serializable;

class User implements Serializable {
  private static final long serialVersionUID = 1L;

  private String username;
  private String password;
  private String name;
  private String morada;
  private String email;
  private Character sexo; //
  private int altura; //
  private int frequencia_cardiaca;
  private int idade;
  private int peso;
  private int nivel; // 3 niveis de atividade 1->praticante ocasional 2-> amador 3-> profissional

  // Construtors
  public User(String username, String password, String name, String morada, String email, Character sexo, int altura,
      int frequencia_cardiaca, int idade, int peso, int nivel) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.morada = morada;
    this.email = email;
    this.sexo = sexo;
    this.altura = altura;
    this.frequencia_cardiaca = frequencia_cardiaca;
    this.idade = idade;
    this.peso = peso;
    this.nivel = nivel;
  }

  public User() {
    this.username = "";
    this.password = "";
    this.name = "";
    this.morada = "";
    this.email = "";
    this.sexo = null;
    this.altura = 160;
    this.frequencia_cardiaca = 70;
    this.idade = 30;
    this.peso = 60;
    this.nivel = 1;
  }

  public User(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    this.name = user.getName();
    this.morada = user.getMorada();
    this.email = user.getEmail();
    this.sexo = user.getSexo();
    this.altura = user.getAltura();
    this.frequencia_cardiaca = user.getFrequenciaCardiaca();
    this.idade = user.getIdade();
    this.peso = user.getPeso();
    this.nivel = user.getNivel();
  }

  // Getters e setters
  public String getUsername() {
    return username;
  }

  public void setUserName(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMorada() {
    return morada;
  }

  public void setMorada(String morada) {
    this.morada = morada;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Character getSexo() {
    return sexo;
  }

  public void setSexo(Character sexo) {
    this.sexo = sexo;
  }

  public int getAltura() {
    return altura;
  }

  public void setAltura(int altura) {
    this.altura = altura;
  }

  public int getFrequenciaCardiaca() {
    return frequencia_cardiaca;
  }

  public void setFrequenciaCardiaca(int frequencia_cardiaca) {
    this.frequencia_cardiaca = frequencia_cardiaca;
  }

  public int getIdade() {
    return idade;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public int getPeso() {
    return peso;
  }

  public void setPeso(int peso) {
    this.peso = peso;
  }

  public int getNivel() {
    return nivel;
  }

  public void setNivel(int nivel) {
    this.nivel = nivel;
  }

  // método para exibir informações do user
  public String toString() {
    StringBuffer sb = new StringBuffer();

    sb.append("Nome de utilizador: " + username);
    // sb.append("Palavra-passe: " + password); // não deve ser apresentada
    sb.append("Nome: " + name);
    sb.append("Morada: " + morada);
    sb.append("Email: " + email);
    sb.append("Sexo: " + sexo);
    sb.append("Altura: " + altura);
    sb.append("Frequência cardíaca: " + frequencia_cardiaca);
    sb.append("Idade: " + idade);
    sb.append("Peso: " + peso);
    sb.append("Nível: " + nivel);

    return sb.toString();
  }

  public User clone() {
    return new User(this);
  }

  /**
   * Consoante o tipo de utilizador e a informação do utilizador em causa deverá
   * existir um método que determine um factor multiplicativo a utilizar no
   * cálculo
   * das calorias
   * 
   * @param user
   * @return
   */
  public double calculo_taxa_metabólica(User user) {
    double tmb = 0;
    if (user.sexo == 'F') {
      tmb = 66 + (13.8 * user.peso) + (5 * user.altura) - (6.8 * user.idade);
    } else {
      tmb = 655 + (9.6 * user.peso) + (1.8 * user.altura) - (4.7 * user.idade);
    }
    return tmb;
  }
}
