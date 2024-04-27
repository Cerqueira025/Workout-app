import Excessoes.CredenciaisNaoCoincidem;
import Excessoes.EmailExisteException;
import Excessoes.ParametrosInvalidosException;
import Excessoes.UtilizadorExisteException;
import Excessoes.UtilizadorNaoExisteException;
import Utilizador.Genero;

public class FitnessController {

    private FitnessModel model;

    public FitnessController(FitnessModel model) {
        this.model = model;
    }



    public void registarUtilizador(String codigo, int bpmMedio, double peso, int altura,
            String nome, String genero, String morada, String email, String password)
            throws ParametrosInvalidosException, UtilizadorExisteException, EmailExisteException {
        
        Genero g;
        switch (genero) {
            case "M":
                g = Genero.Masculino;
                break;
            case "F":  
                g = Genero.Feminino;
                break;
            default:
                g = Genero.Outro;
                break;
        }
        
        if (codigo.length() == 0 || (bpmMedio <= 0 || bpmMedio >= 200)|| (Double.compare(peso,0) <= 0
                                 || Double.compare(peso,500) >= 0) || (altura <= 0 || altura >= 300)
                                 || nome.length() == 0 || morada.length() == 0 || email.length() == 0
                                 || password.length() == 0) {
            throw new ParametrosInvalidosException();
        }

        this.model.criaUtilizador(codigo, bpmMedio, peso, altura, nome, g, morada, email, password);
    }



    public void loginUtilizador(String codigo, String password) throws UtilizadorNaoExisteException, ParametrosInvalidosException, CredenciaisNaoCoincidem {
        if (codigo.length() == 0 || password.length() == 0) throw new ParametrosInvalidosException();
        if (!this.model.codigoUtilizadorExiste(codigo)) throw new UtilizadorNaoExisteException();
        if (!this.model.credenciaisCoincidem(codigo, password)) throw new CredenciaisNaoCoincidem();
    }

}
