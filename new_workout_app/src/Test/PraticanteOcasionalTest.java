import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Genero;
import Utilizador.TiposUtilizador.PraticanteOcasional;
import Utilizador.TiposUtilizador.Profissional;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


public class PraticanteOcasionalTest {
    public PraticanteOcasionalTest()
    {
    }

    @Test
    public void testConstructor() {
        //Construtor vazio
        PraticanteOcasional praticante1 = new PraticanteOcasional();
        assertTrue(praticante1!=null);

        //Construtor parametrizado com Map e PlanoDeTreino
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        PraticanteOcasional praticante2 = new PraticanteOcasional("001", 70, 75.5, 175, "João", Genero.Masculino, "Rua X", "joao@example.com", "senha", atividades, plano);
        assertTrue(praticante2 != null);
        assertEquals("001", praticante2.getCodigo());
        assertEquals(70, praticante2.getBpmMedio());
        assertEquals(75.5, praticante2.getPeso(), 0.001);
        assertEquals(175, praticante2.getAltura());
        assertEquals("João", praticante2.getNome());
        assertEquals(Genero.Masculino, praticante2.getGenero());
        assertEquals("Rua X", praticante2.getMorada());
        assertEquals("joao@example.com", praticante2.getEmail());
        assertEquals("senha", praticante2.getPassword());

        //Construtor parametrizado sem Map e PlanoDeTreino
        PraticanteOcasional praticante3 = new PraticanteOcasional("001", 70, 75.5, 175, "João", Genero.Masculino, "Rua X", "joao@example.com", "senha");
        assertTrue(praticante3 != null);
        assertEquals("001", praticante3.getCodigo());
        assertEquals(70, praticante3.getBpmMedio());
        assertEquals(75.5, praticante3.getPeso(), 0.001);
        assertEquals(175, praticante3.getAltura());
        assertEquals("João", praticante3.getNome());
        assertEquals(Genero.Masculino, praticante3.getGenero());
        assertEquals("Rua X", praticante3.getMorada());
        assertEquals("joao@example.com", praticante3.getEmail());
        assertEquals("senha", praticante3.getPassword());

        //Construtor de cópia
    }
    @Test
    public void fatorMultiplicativoTest(){
        PraticanteOcasional praticante = new PraticanteOcasional("001", 70, 75.5, 175, "João", Genero.Masculino, "Rua X", "joao@example.com", "senha");
        double fator = praticante.fatorMultiplicativo();
        assertTrue(fator > 0.0 && fator < 2.0);
    }

}
