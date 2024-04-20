import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Historico {
  private String user;
  private List<Treino> treinos;
  private String fileName = "treinos.csv";

  // Construtor
  public Historico(){
    this.user = "";
    this.treinos = new ArrayList<>();
  }

  //Getters e setters
  public String getUser() {
    return user;
  }
  public void setUser(String user) {
    this.user = user;
  }
  
  public List<Treino> getTreinos() {
    return treinos;
  }
  public void setTreinos(List<Treino> treinos) {
    this.treinos = treinos;
  }

  public String getFileName() {
    return fileName;
  }
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  /**
   * Método responsável por descarregar o histórico do ficheiro de dados
   */
  private void loadHistoricFromFile() {
  }

  /**
   * Método que guarda o registo de um novo utilizador no ficheiro
   */
  private void saveHistoricToFile(User user, Treino treino) {

  }
  
}
