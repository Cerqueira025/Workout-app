import java.util.OptionalInt;

public class Atividade  {
  private String nome;
  private int tipo; // 1-alongamento 2-força parte superior 3-força parte inferior 4-cardio 5-outros
  private int calorias; // nº de calorias que gastará (nº que deve ser multiplicado de acordo com o
                        // utilizador)
  private int dificuldade; //1-easy 2-medium 3-hard
  private OptionalInt duracao; // duração em segundos que deve ser definida de acordo com o plano
  
  // Construtor parametrizado
  public Atividade(String nome, int tipo, int calorias, int dificuldade, OptionalInt duracao) {
    this.nome = nome;
    this.tipo = tipo;
    this.calorias = calorias;
    this.dificuldade = dificuldade;
    this.duracao = duracao;
  }

  public Atividade(){
    this.nome = "";
    this.tipo = 0;
    this.calorias = 0;
    this.dificuldade = 0;
  }

  public Atividade(Atividade atividade){
    this.nome = atividade.getNome();
    this.tipo = atividade.getTipo();
    this.calorias = atividade.getCalorias();
    this.dificuldade = atividade.getDificuldade();
  }

  // Getters e setters
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getTipo() {
    return tipo;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  public int getCalorias() {
    return calorias;
  }

  public void setCalorias(int calorias) {
    this.calorias = calorias;
  }

  public int getDificuldade(){
    return dificuldade;
  }
  public void setDificuldade(int dificuldade){
    this.dificuldade = dificuldade;
  }

  public OptionalInt getDuracao() {
    return duracao;
  }

  public void setDuracao(OptionalInt duracao) {
    this.duracao = duracao;
  }

  public String tipoToString(int tipo){
    String tipoStr = new String();
    switch(tipo){
      case 1:
        tipoStr = "Alongamento";
        break;
      case 2:
        tipoStr = "Força parte superior";
        break;
      case 3:
        tipoStr = "Força parte inferior";
        break;
      case 4:
        tipoStr = "Cardio";
        break;
      case 5:
        tipoStr = "Outros";
        break;
      default:
        System.out.println("Tipo inválido");
        break;
    }
    return tipoStr;
  }

  //Adicionar espaço entre nome e "Tipo" ???
  public String toString(){
    StringBuffer sb = new StringBuffer();

    sb.append("Nome: " + nome);
    sb.append("Tipo: "+ tipoToString(tipo));
    //sb.append("Calorias: " + calorias);

    return sb.toString();
  }



  public int calculoCalorias(int taxa_metabolica, int peso, int tempo) {
    return taxa_metabolica * peso * tempo;
  }

  /**
   * taxa_metabolica
   * 1-alongamentos: 2 a 3
   * 2-força parte superior: 3 a 6
   * 3-força parte inferior: 3 a 6
   * 4-cardio: 6 a 12
   * 5-outros(corrida, caminhada, ciclismo, natação, etc.)
   */

}