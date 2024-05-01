import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;


import Excessoes.AtividadeExisteException;
import Excessoes.AtividadeNaoExisteException;
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

    public void guardaEstado(String nomeFicheiro) throws FileNotFoundException, IOException {
        this.model.guardaEstado(nomeFicheiro);
    }
    
    public void carregaEstado(String nomeFicheiro) throws IOException, ClassNotFoundException {
        this.model = FitnessModel.carregaEstado(nomeFicheiro);
    }

    // ----------------- Utilizador ---------------- //

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

        this.model.criaUtilizador(codigo, bpmMedio, peso, 0, altura, nome, g, morada, email, password);
    }

    public void loginUtilizador(String codigo, String password) throws UtilizadorNaoExisteException, ParametrosInvalidosException, CredenciaisNaoCoincidem {
        if (codigo.length() == 0 || password.length() == 0) throw new ParametrosInvalidosException();
        if (!this.model.codigoUtilizadorExiste(codigo)) throw new UtilizadorNaoExisteException();
        if (!this.model.credenciaisCoincidem(codigo, password)) throw new CredenciaisNaoCoincidem();
    }




    // ----------------- Atividades ---------------- //

    public void adicionarAtividade(String codigoUtilizador, String codigoAtividade, String descricao, String data, int duracao) throws DateTimeParseException, UtilizadorNaoExisteException, ParametrosInvalidosException, AtividadeExisteException {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //LocalDate data_aux = LocalDate.parse(data, formatter);

        if (codigoUtilizador.length() == 0 || codigoAtividade.length() == 0 || descricao.length() == 0 || (duracao <= 0 || duracao >= 1440)) throw new ParametrosInvalidosException();
        if (!this.model.codigoUtilizadorExiste(codigoUtilizador)) throw new UtilizadorNaoExisteException();
        if (this.model.existeAtividade(codigoUtilizador, codigoAtividade)) throw new AtividadeExisteException();
        //this.model.criaAtividade(codigoUtilizador, codigoAtividade, descricao, data_aux, duracao);
    }

    public void removerAtividade(String codigoUtilizador, String codigoAtividade) throws ParametrosInvalidosException, UtilizadorNaoExisteException, AtividadeNaoExisteException {
        if (codigoUtilizador.length() == 0 || codigoAtividade.length() == 0) throw new ParametrosInvalidosException();
        if (!this.model.codigoUtilizadorExiste(codigoUtilizador)) throw new UtilizadorNaoExisteException();
        if (!this.model.existeAtividade(codigoUtilizador,codigoAtividade)) throw new AtividadeNaoExisteException();
        this.model.removerAtividade(codigoUtilizador,codigoAtividade);
    }

}
