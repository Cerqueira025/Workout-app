import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;


public class GestorAtividades {
  
  private List<Atividade> alongamentos;
  private List<Atividade> forca_superior;
  private List<Atividade> forca_inferior;
  private List<Atividade> cardio;
  private List<Atividade> outros;

  // Contrutor
  public GestorAtividades() {
    this.alongamentos = new ArrayList<>();
    this.forca_superior = new ArrayList<>();
    this.forca_inferior = new ArrayList<>();
    this.cardio = new ArrayList<>();
    this.outros = new ArrayList<>();
    atividades();
  }

  //Getters e setters
  public List<Atividade> getAlongamentos() {
    return alongamentos;
  }
  public void setAlongamentos(List<Atividade> alongamentos) {
    this.alongamentos = alongamentos;
  }

  public List<Atividade> getForcaSuperior() {
    return forca_superior;
  }
  public void setForcaSuperior(List<Atividade> forca_superior) {
    this.forca_superior = forca_superior;
  }

  public List<Atividade> getForcaInferior() {
    return forca_inferior;
  }
  public void setForcaInferior(List<Atividade> forca_inferior) {
    this.forca_inferior = forca_inferior;
  }

  public List<Atividade> getCardio() {
    return cardio;
  }
  public void setCardio(List<Atividade> cardio) {
    this.cardio = cardio;
  }

  public List<Atividade> getOutros() {
    return outros;
  }
  public void setOutros(List<Atividade> outros) {
    this.outros = outros;
  }

  private void atividades(){
    // 1-alongamentos
    Atividade pescoco = new Atividade("pescoço", 1, 1, 1, OptionalInt.empty());
    alongamentos.add(pescoco);
    Atividade ombros = new Atividade("ombros", 1, 1, 1, OptionalInt.empty());
    alongamentos.add(ombros);
    Atividade gemeos = new Atividade("gêmeos", 1, 1, 1, OptionalInt.empty());
    alongamentos.add(gemeos);

    // 2-força parte superior
    Atividade flexao = new Atividade("flexões", 2, 1, 1, OptionalInt.empty());
    forca_superior.add(flexao);
    Atividade abdominais = new Atividade("abdominais", 2, 1, 1, OptionalInt.empty());
    forca_superior.add(abdominais);
    
    // 3-força parte inferior
    Atividade agachamento = new Atividade("agachamento", 3, 1, 1, OptionalInt.empty());
    forca_inferior.add(agachamento);
    Atividade lunge = new Atividade("lunge", 3, 1, 1, OptionalInt.empty());
    forca_inferior.add(lunge);

    // 4-cardio
    Atividade burpees = new Atividade("burpees", 4, 1, 1, OptionalInt.empty());
    cardio.add(burpees);
    Atividade salto_corda = new Atividade("saltar à corda", 4, 1, 1, OptionalInt.empty());
    cardio.add(salto_corda);
    Atividade polichinelos = new Atividade("polichinelos", 4, 1, 1, OptionalInt.empty());
    cardio.add(polichinelos);

    // 5-outras
    Atividade corrida = new Atividade("corrida", 5, 1, 1, OptionalInt.empty());
    outros.add(corrida);
    Atividade ciclismo = new Atividade("ciclismo", 5, 1, 1, OptionalInt.empty());
    outros.add(ciclismo);
  }


  public void printListaAtividade(List<Atividade> lista) {
    for (int i = 0; i < lista.size(); i++) {
      Atividade atividade = lista.get(i);
      System.out.println((i + 1) + ". " + atividade.toString());
    }
}

  
}
