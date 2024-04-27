public class Fitness {
    
    public FitnessModel model;
    public FitnessView view;
    public FitnessController controller;


    public static void main(String[] args) {
        new Fitness().run();
    }


    private void run() {
        String[] opcoes = new String[] {"Registar", "Login"};
        String[] atividades = new String[] {"Distância: Sprint", 
                                            "Distância e Altimetria: BicicletaMontanha",
                                            "Repetições: Abdominais",
                                            "Repetições com Pesos: Supino"};

        FitnessModel model = new FitnessModel();
        FitnessController controller = new FitnessController(model);
        FitnessView view = new FitnessView(opcoes, atividades, controller);
        view.run();
    }
}
