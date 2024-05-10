package Excecoes;

public class NomeAtividadeNaoExisteException extends Exception {
    
    public NomeAtividadeNaoExisteException() {
        super();
    }
    public NomeAtividadeNaoExisteException(String s) {
        super(s);
    }
}
