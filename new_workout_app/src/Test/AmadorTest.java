import Utilizador.TiposUtilizador.Amador;
import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Genero;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class AmadorTest {
    public AmadorTest()
    {
    }

    @Test
    public void testConstructor(){
        //Construtor vazio
        Amador amador1 = new Amador();
        assertTrue(amador1!=null);

        // ------------------- Construtor parametrizado com Maps e PlanoDeTreino -------------------//
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Map<String, Double> recordes = new HashMap<>();

        Amador amador2 = new Amador("001", 70, 75.5, 100, 175, "João", Genero.Masculino, "Rua X", "joao@example.com", "senha",atividades, recordes, plano);
        assertTrue(amador2 != null);
        assertEquals("001", amador2.getCodigo());
        assertEquals(70, amador2.getBpmMedio());
        assertEquals(75.5, amador2.getPeso(), 0.001);
        assertEquals(100, amador2.getCaloriasGastas(), 0.001);
        assertEquals(175, amador2.getAltura());
        assertEquals("João", amador2.getNome());
        assertEquals(Genero.Masculino, amador2.getGenero());
        assertEquals("Rua X", amador2.getMorada());
        assertEquals("joao@example.com", amador2.getEmail());
        assertEquals("senha", amador2.getPassword());
        assertEquals(atividades, amador2.getAtividades());
        assertEquals(recordes, amador2.getRecordesAtividades());
        assertEquals(plano, amador2.getPlanoDeTreino());

        // ------------------- Construtor parametrizado sem Maps e PlanoDeTreino -------------------//
        Amador amador3 = new Amador("001", 70, 75.5, 44, 175, "João", Genero.Masculino, "Rua X", "joao@example.com", "senha");
        assertTrue(amador3 != null);
        assertEquals("001", amador3.getCodigo());
        assertEquals(70, amador3.getBpmMedio());
        assertEquals(75.5, amador3.getPeso(), 0.001);
        assertEquals(44, amador3.getCaloriasGastas(), 0.001);
        assertEquals(175, amador3.getAltura());
        assertEquals("João", amador3.getNome());
        assertEquals(Genero.Masculino, amador3.getGenero());
        assertEquals("Rua X", amador3.getMorada());
        assertEquals("joao@example.com", amador3.getEmail());
        assertEquals("senha", amador3.getPassword());

    }

    @Test
    public void fatorMultiplicativoTest(){
        Amador amador = new Amador("001", 70, 75.5, 35, 175, "João", Genero.Masculino, "Rua X", "joao@example.com", "senha");
        double fator = amador.fatorMultiplicativo();
        // Verificação se o fator está dentro do intervalo esperado
        assertTrue(fator > 0.0 && fator < 3.0);
    }
}
