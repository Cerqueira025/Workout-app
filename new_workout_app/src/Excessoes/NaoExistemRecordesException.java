package Excessoes;

public class NaoExistemRecordesException extends Exception {
    
    public NaoExistemRecordesException() {
        super();
    }
    public NaoExistemRecordesException(String s) {
        super(s);
    }
}
