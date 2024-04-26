import Utilizador.Genero;

public class FitnessController {

    private FitnessModel model;

    public FitnessController(FitnessModel model) {
        this.model = model;
    }

    public void registarUtilizador(String codigo, int bpmMedio, double peso, int altura,
            String nome, String genero, String morada, String email, String password) {
        
        Genero g;
        switch (genero) {
            case "Masculino":
                g = Genero.Masculino;
                break;
            case "Feminino":  
                g = Genero.Feminino;
                break;
            default:
                g = Genero.Outro;
                break;
        }

        this.model.criaUtilizador(codigo, bpmMedio, peso, altura, nome, g, morada, email, password);
        System.out.println(this.model.toString());
    }

}