import java.time.LocalDate;
import java.util.HashMap;

import Atividade.Distancia.Sprint;
import PlanoTreino.PlanoDeTreino;
import Utilizador.TiposUtilizador.Amador;
import Utilizador.TiposUtilizador.PraticanteOcasional;
import Utilizador.Utilizador;
import org.junit.Test;

import Atividade.Distancia.Altimetria.BicicletaMontanha;
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
        // atleta Profissional
        Profissional profissional11 = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        LocalDate data1 = LocalDate.now();
        Sprint sprint1 = new Sprint("001", "Descrição", data1, 30,
                profissional11, 10.0);

        assertEquals("001", sprint1.getCodigo());
        assertEquals("Descrição", sprint1.getDescricao());
        assertEquals(data1, sprint1.getData());
        assertEquals(30, sprint1.getDuracao());
        assertEquals(profissional11, sprint1.getUser());
        assertEquals(10.0, sprint1.getDistancia(), 0.01);

        // atleta Amador
        Amador amador = new Amador("amadorId", 65, 70, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", new HashMap<>(), new PlanoDeTreino());
        LocalDate data2 = LocalDate.now();
        Sprint sprint2 = new Sprint("002", "Descrição", data2, 30,
                amador, 10.0);

        assertEquals("002", sprint2.getCodigo());
        assertEquals("Descrição", sprint2.getDescricao());
        assertEquals(data2, sprint2.getData());
        assertEquals(30, sprint2.getDuracao());
        assertEquals(amador, sprint2.getUser());
        assertEquals(10.0, sprint2.getDistancia(), 0.01);

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional = new PraticanteOcasional("praticanteId", 65, 70, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", new HashMap<>(), new PlanoDeTreino());
        LocalDate data3 = LocalDate.now();
        Sprint sprint3 = new Sprint("003", "Descrição", data3, 30,
                praticanteOcasional, 10.0);

        assertEquals("003", sprint3.getCodigo());
        assertEquals("Descrição", sprint3.getDescricao());
        assertEquals(data3, sprint3.getData());
        assertEquals(30, sprint3.getDuracao());
        assertEquals(praticanteOcasional, sprint3.getUser());
        assertEquals(10.0, sprint3.getDistancia(), 0.01);

        // ------------------- Construtor sem Map e PlanoDeTreino ------------------- //
        // atleta Profissional
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        LocalDate data4 = LocalDate.now();
        Sprint sprint4 = new Sprint("004", "Descrição", data4, 30,
                profissional, 10.0);

        assertEquals("004", sprint4.getCodigo());
        assertEquals("Descrição", sprint4.getDescricao());
        assertEquals(data4, sprint4.getData());
        assertEquals(30, sprint4.getDuracao());
        assertEquals(profissional, sprint4.getUser());
        assertEquals(10.0, sprint4.getDistancia(), 0.01);

        // atleta Amador
        Amador amador2 = new Amador("amadorId", 65, 70, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", new HashMap<>(), new PlanoDeTreino());
        LocalDate data5 = LocalDate.now();
        Sprint sprint5 = new Sprint("005", "Descrição", data5, 30,
                amador2, 10.0);

        assertEquals("005", sprint5.getCodigo());
        assertEquals("Descrição", sprint5.getDescricao());
        assertEquals(data5, sprint5.getData());
        assertEquals(30, sprint5.getDuracao());
        assertEquals(amador2, sprint5.getUser());
        assertEquals(10.0, sprint5.getDistancia(), 0.01);

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("praticanteId", 65, 70, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", new HashMap<>(), new PlanoDeTreino());
        LocalDate data6 = LocalDate.now();
        Sprint sprint6 = new Sprint("006", "Descrição", data6, 30,
                praticanteOcasional2, 10.0);

        assertEquals("006", sprint6.getCodigo());
        assertEquals("Descrição", sprint6.getDescricao());
        assertEquals(data6, sprint6.getData());
        assertEquals(30, sprint6.getDuracao());
        assertEquals(praticanteOcasional2, sprint6.getUser());
        assertEquals(10.0, sprint6.getDistancia(), 0.01);
    }

    @Test
    public void testEquals() {
        // Teste de igualdade entre duas instâncias idênticas
        Sprint sprint1 = new Sprint("001", "Descrição", LocalDate.now(), 30, new Profissional(), 10.0);
        Sprint sprint2 = new Sprint("001", "Descrição", LocalDate.now(), 30, new Profissional(), 10.0);
        assertTrue(sprint1.equals(sprint2));

        // Teste de igualdade entre instâncias diferentes
        Sprint sprint3 = new Sprint("002", "Outra Descrição", LocalDate.now(), 30, new Profissional(), 10.0);
        assertFalse(sprint1.equals(sprint3));
    }

    @Test
    public void testToString() {
        Profissional profissional = new Profissional();
        Sprint sprint = new Sprint("001", "Descrição", LocalDate.now(), 4, profissional, 10.0);
        String expectedToString = "Sprint{Distancia{Atividade{" +
                "código='001', descrição='Descrição', data='" + LocalDate.now() +
                "', duração='4', bpm médio='0', calorias='0.0'}"
+                "distancia='10.0', velocidade='2.5'}}";
        assertEquals(expectedToString, sprint.toString());
    }

    @Test
    public void testClone() {
        Sprint sprint1 = new Sprint("001", "Descrição", LocalDate.now(), 30, new Profissional(), 10.0);
        Sprint sprint2 = sprint1.clone();
        assertEquals(sprint1, sprint2);
    }

    @Test
    public void testCalorias() {
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Sprint sprint = new Sprint("001", "Descrição", LocalDate.now(), 30, profissional, 10.0);

        double expectedCalorias = profissional.fatorMultiplicativo() * (10.0 / 2) * 30 * (profissional.getBpmMedio() / 100);
        assertEquals(expectedCalorias, sprint.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Sprint sprint = new Sprint("001", "Descrição", LocalDate.now(), 30, profissional, 10.0);

        int expectedBpm = (int) (profissional.getBpmMedio() + 30 * profissional.fatorMultiplicativo());
        assertEquals(expectedBpm, sprint.bpm());
    }

}
