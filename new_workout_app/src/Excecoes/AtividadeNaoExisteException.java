package Excecoes;

public class AtividadeNaoExisteException extends Exception {
    public AtividadeNaoExisteException() {
        super();
    }
    public AtividadeNaoExisteException(String s) {
        super(s);
    }
}
