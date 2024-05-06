import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Genero;
import Utilizador.TiposUtilizador.Amador;
import Utilizador.TiposUtilizador.PraticanteOcasional;
import Utilizador.TiposUtilizador.Profissional;
import org.junit.Test;

import Atividade.Repeticoes.Pesos.Supino;

public class SupinoTest {
    public SupinoTest()
    {
    }

    @Test
    public void testConstructor() {
        //Construtor vazio
        Supino supino = new Supino();
        assertTrue(supino!=null);

        // ------------------- Construtor parametrizado com Map e PlanoDeTreino ------------------- //
        // atleta Profissional
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        Profissional profissional1 = new Profissional("profId", 75, 80, 75, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Supino supino1 = new Supino("001", "Descrição", data, 30, 4, profissional1, 10, 50, 45.0);

        assertEquals("001", supino1.getCodigo());
        assertEquals("Descrição", supino1.getDescricao());
        assertEquals(data, supino1.getData());
        assertEquals(30, supino1.getDuracao());
        assertEquals(4, supino1.getUtilizador());
        assertEquals(profissional1, supino1.getUtilizador());
        assertEquals(10, supino1.getRepeticoes());
        assertEquals(50, supino1.getPeso(), 0.01);
        assertEquals(45.0, supino1.getInclinacao(), 0.01);

        //atleta Amador
        Amador amador1 = new Amador("amadorId", 70, 75, 60, 170,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Supino supino2 = new Supino("001", "Descrição", data, 30, 2, amador1, 10, 50, 45.0);

        assertEquals("001", supino2.getCodigo());
        assertEquals("Descrição", supino2.getDescricao());
        assertEquals(data, supino2.getData());
        assertEquals(30, supino2.getDuracao());
        assertEquals(2, supino1.getUtilizador());
        assertEquals(amador1, supino2.getUtilizador());
        assertEquals(10, supino2.getRepeticoes());
        assertEquals(50, supino2.getPeso(), 0.01);
        assertEquals(45.0, supino2.getInclinacao(), 0.01);

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("praticanteId", 65, 70, 44, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Supino supino3 = new Supino("001", "Descrição", data, 30, 4, praticanteOcasional1, 10, 50, 45.0);

        assertEquals("001", supino3.getCodigo());
        assertEquals("Descrição", supino3.getDescricao());
        assertEquals(data, supino3.getData());
        assertEquals(30, supino3.getDuracao());
        assertEquals(4, supino1.getUtilizador());
        assertEquals(praticanteOcasional1, supino3.getUtilizador());
        assertEquals(10, supino3.getRepeticoes());
        assertEquals(50, supino3.getPeso(), 0.01);
        assertEquals(45.0, supino3.getInclinacao(), 0.01);

        // ------------------- Construtor sem Map e PlanoDeTreino ------------------- //
        // atleta Profissional
        Profissional profissional2 = new Profissional("profId", 75, 80, 65, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Supino supino4 = new Supino("001", "Descrição", data, 30, 4, profissional2, 10, 50, 45.0);

        assertEquals("001", supino4.getCodigo());
        assertEquals("Descrição", supino4.getDescricao());
        assertEquals(data, supino4.getData());
        assertEquals(30, supino4.getDuracao());
        assertEquals(4, supino1.getUtilizador());
        assertEquals(profissional2, supino4.getUtilizador());
        assertEquals(10, supino4.getRepeticoes());
        assertEquals(50, supino4.getPeso(), 0.01);
        assertEquals(45.0, supino4.getInclinacao(), 0.01);

        //atleta Amador
        Amador amador2 = new Amador("amadorId", 70, 75, 44, 170,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Supino supino5 = new Supino("001", "Descrição", data, 30, 2, amador2, 10, 50, 45.0);

        assertEquals("001", supino5.getCodigo());
        assertEquals("Descrição", supino5.getDescricao());
        assertEquals(data, supino5.getData());
        assertEquals(30, supino5.getDuracao());
        assertEquals(2, supino1.getUtilizador());
        assertEquals(amador2, supino5.getUtilizador());
        assertEquals(10, supino5.getRepeticoes());
        assertEquals(50, supino5.getPeso(), 0.01);
        assertEquals(45.0, supino5.getInclinacao(), 0.01);

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("praticanteId", 65, 70, 30, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Supino supino6 = new Supino("001", "Descrição", data, 30, 2, praticanteOcasional2, 10, 50, 45.0);

        assertEquals("001", supino6.getCodigo());
        assertEquals("Descrição", supino6.getDescricao());
        assertEquals(data, supino6.getData());
        assertEquals(30, supino6.getDuracao());
        assertEquals(2, supino1.getUtilizador());
        assertEquals(praticanteOcasional2, supino6.getUtilizador());
        assertEquals(10, supino6.getRepeticoes());
        assertEquals(50, supino6.getPeso(), 0.01);
        assertEquals(45.0, supino6.getInclinacao(), 0.01);
    }

    @Test
    public void testEquals() {
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        Profissional profissional = new Profissional("profId", 75, 80, 75, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);

        Supino supino1 = new Supino("001", "Descrição", data, 30, 4, profissional, 10, 50, 45.0);
        Supino supino2 = new Supino("001", "Descrição", data, 30, 4, profissional, 10, 50, 45.0);
        Supino supino3 = new Supino("002", "Descrição", data, 30, 2, profissional, 10, 50, 45.0);

        assertTrue(supino1.equals(supino2));
        assertFalse(supino1.equals(supino3));
        assertFalse(supino2.equals(supino3));
    }

    @Test
    public void testClone() {
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        Profissional profissional = new Profissional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);

        Supino supino1 = new Supino("001", "Descrição", data, 30, 2, profissional, 10, 50, 45.0);
        Supino supino2 = supino1.clone();

        assertEquals(supino1, supino2);
    }

    @Test
    public void testCalorias() {
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        Profissional profissional = new Profissional("profId", 75, 80, 60, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);

        Supino supino = new Supino("001", "Descrição", data, 30, 2, profissional, 10, 50, 45.0);

        double caloriasEsperadas = profissional.fatorMultiplicativo() * (45.0/3) * 30 * (profissional.getBpmMedio()/100);

        assertEquals(caloriasEsperadas, supino.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        Profissional profissional = new Profissional("profId", 75, 80, 60, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);

        Supino supino = new Supino("001", "Descrição", data, 30, 2, profissional, 10, 50, 45.0);

        int bpmEsperado = (int) (profissional.getBpmMedio() + 10 * profissional.fatorMultiplicativo());

        assertEquals(bpmEsperado, supino.bpm());
    }

}
