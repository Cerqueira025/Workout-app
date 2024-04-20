import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.OptionalInt;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Treino {
  private String nome;
  private List<Atividade> treino;
  private LocalDateTime data;

  // Construtor
  public Treino(String nome, LocalDateTime data) {
    this.nome = nome;
    this.treino = new ArrayList<>();
    this.data = data;
  }

  public Treino(){
    this.nome = "";
    this.treino = new ArrayList<>();
    this.data = LocalDateTime.now();
  }

  //Getters e setters
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<Atividade> getTreino() {
    return treino;
  }
  public void setTreino(List<Atividade> treino) {
    this.treino = treino;
  }

  public LocalDateTime getData() {
    return data;
  }
  public void setData(LocalDateTime data) {
    this.data = data;
  }

  public String toString(){
    StringBuffer sb = new StringBuffer();

    sb.append("Nome: " + nome);
    sb.append("Data: " + data);
    //sb.append("Calorias: " + );
    for(Atividade atividade : treino){
      atividade.toString();
      /*if(atividade.getTipo() < 5){
        Repeticoes rep = new Repeticoes();
      }*/
    }

    return sb.toString();
  }



  public void adicionaAtividade(Atividade atividade) {
    treino.add(atividade);
  }

  /**
   * Método que possibilita o registo dos treinos
   */
  public void regista_treino() {

    Treino novo_treino = new Treino();

    //define o nome do treino
    String nome = JOptionPane.showInputDialog(null, "Nome para o treino:\n");
    novo_treino.setNome(nome);

    //define o data e hora da realização
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    String input = JOptionPane.showInputDialog(null, "Data e hora (no formato 'yyyy-MM-dd HH:mm'):\n");
    LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
    novo_treino.setData(dateTime);


    int tipo_treino;
    do{
      tipo_treino = Integer.parseInt(JOptionPane.showInputDialog(
        "Que tipo de treino é que pretendes registar?\n" +  
        "Escolha uma opção:\n"+
        "1. Alongamentos\n"+
        "2. Força\n"+
        "3. Cardio\n"+
        "4. Misto (registe atividades de diferentes naturezas)\n"+
        "5. Outros (Corrida, Natação, Caminhada, etc.)\n"+
        "6. Sair\n"));
        switch (tipo_treino) {
          case 1:
            System.out.println("Alongamentos");
            GestorAtividades alongamentos = new GestorAtividades();
            List<Atividade> alongamentosList = alongamentos.getAlongamentos();
            Object[] alongamentosArray = alongamentosList.toArray();
            Object selectedValue = JOptionPane.showInputDialog(null, "Selecione o alongamento", "Alongamentos",JOptionPane.INFORMATION_MESSAGE, null, alongamentosArray, alongamentosArray[0]);
            addExercicioRepeticoes(alongamentosList, novo_treino);
            treino.toString();
            break;
          case 2:
            System.out.println("Força");
            break;
          case 3:
            System.out.println("Cardio");
            break;
          case 4:
            System.out.println("Misto");
            //pode registar qualquer tipo de atividade como corrida em conjunto com agachamento
            break;
          case 5:
            System.out.println("Outros\n");
            System.out.println("Alguma destas opções?");
            System.out.println("1. Corrida");
            System.out.println("2. Caminhada");
            System.out.println("3. Ciclismo");
            System.out.println("4. Natação");
            System.out.println("5. Nenhum dos anteriores");
            break;
          case 6:
            System.out.println("Saindo do programa...");
            break;
          default:
            System.out.println("Opção inválida. Por favor, escolha novamente.");
            break;
        }
    }
    while (tipo_treino != 6);
}

  /**
   * Método que adiciona os vários exercícios de repetição a um treino
   * 
   * erro: dentro do while ele entra no if mas não executa o código dentro dele
   * @param listaAtividades
   * @param treino
   */
  private void addExercicioRepeticoes(List<Atividade> listaAtividades, Treino treino){
    Scanner scanner_exercicio = new Scanner(System.in);
    List<Atividade> exercicios_treino = new ArrayList<>();
    int ex;

    do{
      int reps = Integer.parseInt(JOptionPane.showInputDialog("Quantas repetições?\n"));
      //OptionalInt peso = 
    }
    while(ex!=0);

    while(ex != 0){
      ex = scanner_exercicio.nextInt();
      
      if(ex > 0 && ex <= listaAtividades.size()){ //se o nº introduzido corresponde a um indice existente

        System.out.println("Quanto peso?");
        int pesoValue = scanner_exercicio.nextInt();
        OptionalInt peso = OptionalInt.of(pesoValue);
        
        Atividade atividade = listaAtividades.get(ex-1);
        Repeticoes rep = new Repeticoes(atividade.getNome(), atividade.getTipo(), atividade.getCalorias(), atividade.getDificuldade(), reps, peso, atividade.getDuracao());
        exercicios_treino.add(rep);
      }
    }

    treino.setTreino(exercicios_treino);
    treino.toString();
    scanner_exercicio.close();
  }


  private void addExercicioDistancia(List<Atividade> listaAtividades, Treino treino){
    Scanner scanner_exercicio = new Scanner(System.in);
    List<Atividade> exercicios_treino = new ArrayList<>();
    System.out.println("Selecione '0' para sair");
    int ex = 1;

    while(ex != 0){
      ex = scanner_exercicio.nextInt();
      
      if(ex > 0 && ex <= listaAtividades.size()){ //se o nº introduzido corresponde a um indice existente
        exercicios_treino.add(listaAtividades.get(ex-1));
      }
      else{
        System.err.println("A atividade que selecionaste não está disponível. Por favor tenta outra.");
      }

    }

    treino.setTreino(exercicios_treino);
    scanner_exercicio.close();
  }


  /**
   * Método responsável pela criação de um treino que se adequa ao usuário, ao 
   * tipo de treino que este pretende, objetivo de gasto calórico, carga semanal
   * e nº máximo de atividades por dia e atividades distintas.
   */
  public void cria_treino() {
    Scanner tipo_treino = new Scanner(System.in);
    System.out.println("Que tipo de treino é que pretendes criar?");

    tipo_treino.close();

  }
}