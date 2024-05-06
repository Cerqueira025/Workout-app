import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import Atividade.Atividade;
import Atividade.Distancia.Sprint;
import PlanoTreino.PlanoDeTreino;
import Utilizador.TiposUtilizador.Amador;
import Utilizador.TiposUtilizador.PraticanteOcasional;
import org.junit.Test;

import Utilizador.Genero;
import Utilizador.TiposUtilizador.Profissional;

import static org.junit.Assert.*;
public class SprintTest {
    public SprintTest()
    {
    }

    @Test
    public void testConstructor() {
        // Construtor vazio
        Sprint sprint = new Sprint();
        assertTrue(sprint != null);

        // ------------------- Construtor parametrizado com Map e PlanoDeTreino ------------------- //
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Map<String, Double> recordes = new HashMap<>();
        LocalDateTime data = LocalDateTime.now();

        // atleta Profissional
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Sprint sprint1 = new Sprint("001", "Descrição", data, 30, 4,
                profissional1, 10.0);

        assertEquals("001", sprint1.getCodigo());
        assertEquals("Descrição", sprint1.getDescricao());
        assertEquals(data, sprint1.getData());
        assertEquals(30, sprint1.getDuracao());
        assertEquals(4, sprint1.getSeries());
        assertEquals(profissional1, sprint1.getUtilizador());
        assertEquals(10.0, sprint1.getDistancia(), 0.01);

        // atleta Amador
        Amador amador1 = new Amador("amadorId", 65, 70, 55, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Sprint sprint2 = new Sprint("002", "Descrição", data, 30, 3,
                amador1, 10.0);

        assertEquals("002", sprint2.getCodigo());
        assertEquals("Descrição", sprint2.getDescricao());
        assertEquals(data, sprint2.getData());
        assertEquals(3, sprint2.getSeries());
        assertEquals(30, sprint2.getDuracao());
        assertEquals(amador1, sprint2.getUtilizador());
        assertEquals(10.0, sprint2.getDistancia(), 0.01);

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("praticanteId", 65, 70, 55, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha",  atividades, recordes, plano);
        Sprint sprint3 = new Sprint("003", "Descrição", data, 30, 2,
                praticanteOcasional1, 10.0);

        assertEquals("003", sprint3.getCodigo());
        assertEquals("Descrição", sprint3.getDescricao());
        assertEquals(data, sprint3.getData());
        assertEquals(2, sprint3.getSeries());
        assertEquals(30, sprint3.getDuracao());
        assertEquals(praticanteOcasional1, sprint3.getUtilizador());
        assertEquals(10.0, sprint3.getDistancia(), 0.01);

        // ------------------- Construtor sem Map e PlanoDeTreino ------------------- //
        // atleta Profissional
        Profissional profissional2 = new Profissional("profId", 75, 80, 60, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Sprint sprint4 = new Sprint("004", "Descrição", data, 30, 2,
                profissional2, 10.0);

        assertEquals("004", sprint4.getCodigo());
        assertEquals("Descrição", sprint4.getDescricao());
        assertEquals(data, sprint4.getData());
        assertEquals(30, sprint4.getDuracao());
        assertEquals(2, sprint4.getSeries());
        assertEquals(profissional2, sprint4.getUtilizador());
        assertEquals(10.0, sprint4.getDistancia(), 0.01);

        // atleta Amador
        Amador amador2 = new Amador("amadorId", 65, 70, 20, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Sprint sprint5 = new Sprint("005", "Descrição", data, 30, 3,
                amador2, 10.0);

        assertEquals("005", sprint5.getCodigo());
        assertEquals("Descrição", sprint5.getDescricao());
        assertEquals(data, sprint5.getData());
        assertEquals(30, sprint5.getDuracao());
        assertEquals(3, sprint5.getSeries());
        assertEquals(amador2, sprint5.getUtilizador());
        assertEquals(10.0, sprint5.getDistancia(), 0.01);

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("praticanteId", 65, 70, 75, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Sprint sprint6 = new Sprint("006", "Descrição", data, 30, 2,
                praticanteOcasional2, 10.0);

        assertEquals("006", sprint6.getCodigo());
        assertEquals("Descrição", sprint6.getDescricao());
        assertEquals(data, sprint6.getData());
        assertEquals(30, sprint6.getDuracao());
        assertEquals(2, sprint6.getSeries());
        assertEquals(praticanteOcasional2, sprint6.getUtilizador());
        assertEquals(10.0, sprint6.getDistancia(), 0.01);
    }

    @Test
    public void testEquals() {
        LocalDateTime data = LocalDateTime.now();

        // Teste de igualdade entre duas instâncias idênticas
        Sprint sprint1 = new Sprint("001", "Descrição", data, 30, 2, new Profissional(), 10.0);
        Sprint sprint2 = new Sprint("001", "Descrição", data, 30, 2, new Profissional(), 10.0);
        assertTrue(sprint1.equals(sprint2));

        // Teste de igualdade entre instâncias diferentes
        Sprint sprint3 = new Sprint("002", "Outra Descrição", data, 30, 3, new Profissional(), 10.0);
        assertFalse(sprint1.equals(sprint3));
    }

    @Test
    public void testToString() {
        Profissional profissional = new Profissional();
        LocalDateTime data = LocalDateTime.now();
        Sprint sprint = new Sprint("001", "Descrição", data, 30, 4, profissional, 10.0);
        String expectedToString = "Sprint{Distancia{Atividade{" +
                "código='001', descrição='Descrição', data='" + LocalDate.now() +
                "', duração='4', bpm médio='0', calorias='0.0'}"
+                "distancia='10.0', velocidade='2.5'}}";
        assertEquals(expectedToString, sprint.toString());
    }

    @Test
    public void testClone() {
        LocalDateTime data = LocalDateTime.now();
        Sprint sprint1 = new Sprint("001", "Descrição", data, 30, 2, new Profissional(), 10.0);
        Sprint sprint2 = sprint1.clone();
        assertEquals(sprint1, sprint2);
    }

    @Test
    public void testCalorias() {
        LocalDateTime data = LocalDateTime.now();
        Profissional profissional = new Profissional("profId", 75, 80, 75, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Sprint sprint = new Sprint("001", "Descrição", data, 30, 4, profissional, 10.0);

        double expectedCalorias = profissional.fatorMultiplicativo() * (10.0 / 2) * 30 * (profissional.getBpmMedio() / 100);
        assertEquals(expectedCalorias, sprint.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        LocalDateTime data = LocalDateTime.now();
        Profissional profissional = new Profissional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Sprint sprint = new Sprint("001", "Descrição", data, 30, 3, profissional, 10.0);

        int expectedBpm = (int) (profissional.getBpmMedio() + 30 * profissional.fatorMultiplicativo());
        assertEquals(expectedBpm, sprint.bpm());
    }

}
