package Excecoes;

public class EmailExisteException extends Exception {

    public EmailExisteException() {
        super();
    }
    public EmailExisteException(String s) {
        super(s);
    }
}
