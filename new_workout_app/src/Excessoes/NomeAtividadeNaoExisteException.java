package Excessoes;

public class NomeAtividadeNaoExisteException extends Exception {
    
    public NomeAtividadeNaoExisteException() {
        super();
    }
    public NomeAtividadeNaoExisteException(String s) {
        super(s);
    }
}
