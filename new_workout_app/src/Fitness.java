public class Fitness {
    
    public FitnessModel model;
    public FitnessView view;
    public FitnessController controller;


    public static void main(String[] args) {
        new Fitness().run();
    }



    private void run() {
        String[] opcoes = new String[] {"Registar", "Login"};

        FitnessModel model = new FitnessModel();
        FitnessController controller = new FitnessController(model);
        FitnessView view = new FitnessView(opcoes, controller);
        view.run();
    }



}
