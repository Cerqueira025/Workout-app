import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Genero;
import Utilizador.TiposUtilizador.PraticanteOcasional;
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
        // ------------------- Construtor vazio ------------------- //
        PraticanteOcasional praticante1 = new PraticanteOcasional();
        assertTrue(praticante1!=null);

        // ------------------- Construtor parametrizado com Maps e PlanoDeTreino -------------------//
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Map<String, Double> recordes = new HashMap<>();
        PraticanteOcasional praticante2 = new PraticanteOcasional("001", 70, 75.5, 70, 175,
                "João", Genero.Masculino, "Rua X", "joao@example.com", "senha", atividades, recordes, plano);
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
        assertEquals(atividades, praticante2.getAtividades());
        assertEquals(recordes, praticante2.getRecordesAtividades());
        assertEquals(plano, praticante2.getPlanoDeTreino());

        // ------------------- Construtor parametrizado sem Maps e PlanoDeTreino -------------------//
        PraticanteOcasional praticante3 = new PraticanteOcasional("001", 70, 75.5, 44, 160,
                "Ana", Genero.Feminino, "Rua X", "ana@example.com", "senha");
        assertTrue(praticante3 != null);
        assertEquals("001", praticante3.getCodigo());
        assertEquals(70, praticante3.getBpmMedio());
        assertEquals(75.5, praticante3.getPeso(), 0.001);
        assertEquals(160, praticante3.getAltura());
        assertEquals("Ana", praticante3.getNome());
        assertEquals(Genero.Feminino, praticante3.getGenero());
        assertEquals("Rua X", praticante3.getMorada());
        assertEquals("ana@example.com", praticante3.getEmail());
        assertEquals("senha", praticante3.getPassword());

        //Construtor de cópia
    }
    @Test
    public void fatorMultiplicativoTest(){
        PraticanteOcasional praticante = new PraticanteOcasional("001", 70, 75.5, 70, 175,
                "João", Genero.Masculino, "Rua X", "joao@example.com", "senha");
        double fator = praticante.fatorMultiplicativo();
        assertTrue(fator > 0.0 && fator < 2.0);
    }

}
