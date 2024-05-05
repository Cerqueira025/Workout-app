import java.util.*;

public class Menu {
    
    public interface Handler {
        public void executa();
    }

    public interface PreCondicao {
        public boolean valida();
    }
    
    private static Scanner is = new Scanner(System.in);

    private List<String> opcoes;
    private List<Handler> handlers;
    private List<PreCondicao> condicoes;

    

    public Menu(String[] op) {
        this.opcoes = Arrays.asList(op);
        this.handlers = new ArrayList<>();
        this.condicoes = new ArrayList<>();

        this.opcoes.forEach(s -> {
            this.handlers.add(() -> System.out.println("\n[ERRO] Opção não implementada!"));
            this.condicoes.add(() -> true);
        });
    }

    public void run() {
        int op;
        do {
            show();
            op = readOption();
            if (op>0 && !this.condicoes.get(op-1).valida()) 
                System.out.println("\n[ERRO] Opção indisponível! Tente novamente.");
            else if (op>0) 
                this.handlers.get(op-1).executa();
        } while (op != 0);
    }


    private void show() {
        System.out.println("\n==========Menu==========\n");
        for (int i=0; i<this.opcoes.size(); i++) {
            System.out.print(i+1);
            System.out.print(" - ");
            System.out.println(this.condicoes.get(i).valida() ? this.opcoes.get(i) : "---");
        }
        System.out.println("0 - Sair");
    }

    private int readOption() {
        int op;
        
        System.out.print("\nOpção: ");

        try {
            String line = is.nextLine();
            op = Integer.parseInt(line);
        }
        catch (NumberFormatException e) {
            op = -1;
        }

        if (op < 0 || op > this.opcoes.size()) {
            System.out.println("\n[ERRO] Opção Inválida!\n");
            op = -1;
        }

        return op;
    }

    public void setCondicao(int i, PreCondicao b) {
        this.condicoes.set(i-1, b);
    }

    public void setHandler(int i, Handler h) {
        this.handlers.set(i-1, h);
    }
    
}
