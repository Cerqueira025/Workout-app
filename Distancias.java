import java.util.OptionalInt;

public class Distancias extends Atividade {

  private int distancia;
  private OptionalInt altimetria;
  
  // Construtor parametrizado
  public Distancias(int distancia, OptionalInt altimetria){
    this.distancia = distancia;
    this.altimetria = altimetria;
  }

  // Construtor para atividades de distâncias
  public Distancias(String nome, int tipo, int calorias, int dificuldade, int distancia, OptionalInt altimetria, OptionalInt duracao) {
    super(nome, tipo, calorias, dificuldade, duracao);
    this.distancia = distancia;
    this.altimetria = altimetria;
  }

  // Getters e setters
  public int getDistancia() {
    return distancia;
  }
  public void setDistancia(int distancia) {
    this.distancia = distancia;
  }

  public OptionalInt getAltimetria() {
    return altimetria;
  }

  public void setPeso(OptionalInt altimetria) {
    this.altimetria = altimetria;
  }

}
