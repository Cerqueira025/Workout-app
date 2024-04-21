import java.util.OptionalInt;

public class Repeticoes extends Atividade {

  private int repeticoes;
  private OptionalInt peso;

  //Construtor
  public Repeticoes(int repeticoes, OptionalInt peso){
    this.repeticoes = repeticoes;
    this.peso = peso;
  }

  // Construtor para atividades de repetições
  public Repeticoes(String nome, int tipo, int calorias, int dificuldade, int repeticoes, OptionalInt peso, OptionalInt duracao) {
    super(nome, tipo, calorias, dificuldade, duracao);
    this.repeticoes = repeticoes;
    this.peso = peso;
  }

  // Getters e setters
  public int getRepeticoes() {
    return repeticoes;
  }

  public void setRepeticoes(int repeticoes) {
    this.repeticoes = repeticoes;
  }

  public OptionalInt getPeso() {
    return peso;
  }

  public void setPeso(OptionalInt peso) {
    this.peso = peso;
  }

  
}
