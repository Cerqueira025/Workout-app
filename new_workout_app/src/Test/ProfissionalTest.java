import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Genero;
import Utilizador.TiposUtilizador.Profissional;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfissionalTest {
    public ProfissionalTest()
    {
    }

    @Test
    public void testConstructor(){
        //Construtor vazio
        Profissional profissional1 = new Profissional();
        assertTrue(profissional1!=null);

        // ------------------- Construtor parametrizado com Maps e PlanoDeTreino -------------------//
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Map<String, Double> recordes = new HashMap<>();
        Profissional profissional2 = new Profissional("001", 70, 75.5, 45, 175, "João", Genero.Masculino, "Rua X", "joao@example.com", "senha", atividades, recordes, plano);

        assertTrue(profissional2!=null);
        assertEquals("001", profissional2.getCodigo());
        assertEquals(70, profissional2.getBpmMedio());
        assertEquals(75.5, profissional2.getPeso(), 0.001);
        assertEquals(45, profissional2.getCaloriasGastas(), 0.001);
        assertEquals(175, profissional2.getAltura());
        assertEquals("João", profissional2.getNome());
        assertEquals(Genero.Masculino, profissional2.getGenero());
        assertEquals("Rua X", profissional2.getMorada());
        assertEquals("joao@example.com", profissional2.getEmail());
        assertEquals("senha", profissional2.getPassword());
        assertEquals(atividades, profissional2.getAtividades());
        assertEquals(recordes, profissional2.getRecordesAtividades());
        assertEquals(plano, profissional2.getPlanoDeTreino());

        //Construtor sem Map e PlanoDeTreino
        Profissional profissional3 = new Profissional("001", 70, 75.5, 60, 175, "João", Genero.Masculino, "Rua X", "joao@example.com", "senha");

        assertTrue(profissional3!=null);
        assertEquals("001", profissional3.getCodigo());
        assertEquals(70, profissional3.getBpmMedio());
        assertEquals(75.5, profissional3.getPeso(), 0.001);
        assertEquals(60, profissional3.getCaloriasGastas(), 0.001);
        assertEquals(175, profissional3.getAltura());
        assertEquals("João", profissional3.getNome());
        assertEquals(Genero.Masculino, profissional3.getGenero());
        assertEquals("Rua X", profissional3.getMorada());
        assertEquals("joao@example.com", profissional3.getEmail());
        assertEquals("senha", profissional3.getPassword());
      }

    @Test
    public void fatorMultiplicativoTest(){
        Profissional profissional = new Profissional("001", 70, 75.5, 65, 175, "João", Genero.Masculino, "Rua X", "joao@example.com", "senha");
        double fator = profissional.fatorMultiplicativo();
        assertTrue(fator > 0.0 && fator < 3.0);
    }

}
