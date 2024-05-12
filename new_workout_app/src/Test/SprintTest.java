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

        Sprint sprint3 = new Sprint("002", "Outra Descrição", data, 30, 3, new Profissional(), 10.0);
        Sprint sprint4 = new Sprint("002", "Outra Descrição", data, 25, 3, new Profissional(), 10.0);

        // --- Verificar igualdade ---
        //parâmetros iguais
        assertTrue(sprint1.equals(sprint2));
        //parâmetros diferentes
        assertFalse(sprint1.equals(sprint3));
        assertFalse(sprint2.equals(sprint4));
        assertFalse(sprint3.equals(sprint4));
    }

    @Test
    public void testToString() {
        Profissional profissional = new Profissional();
        LocalDateTime data = LocalDateTime.now();
        Sprint sprint = new Sprint("001", "Descrição", data, 30, 4, profissional, 10.0);
        String expectedToString = "Sprint - " +
                "código = '001', descrição = 'Descrição', data = '" + data +
                "', duração = '30', bpm médio = '0', séries = '4', calorias = '0.0', "
+                "distância = '10.0', velocidade = '20.0'";
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

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Sprint sprint1 = new Sprint("001", "Descrição", data, 30, 4, profissional1, 10.0);

        double expectedCalorias1 = profissional1.fatorMultiplicativo() * (sprint1.getVelocidade() / 2 + 1) * sprint1.getDuracao() * (sprint1.bpm() / 100 + 1) * sprint1.getSeries();
        assertEquals(expectedCalorias1, sprint1.calorias(), 0.01);

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Sprint sprint2 = new Sprint("001", "Descrição", data, 30, 4, profissional2, 12.0);

        double expectedCalorias2 = profissional2.fatorMultiplicativo() * (sprint2.getVelocidade() / 2 + 1) * sprint2.getDuracao() * (sprint2.bpm() / 100 + 1) * sprint2.getSeries();
        assertEquals(expectedCalorias2, sprint2.calorias(), 0.01);

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Sprint sprint3 = new Sprint("001", "Descrição", data, 30, 4, praticanteOcasional1, 12.0);

        double expectedCalorias3 = praticanteOcasional1.fatorMultiplicativo() * (sprint3.getVelocidade() / 2 + 1) * sprint3.getDuracao() * (sprint3.bpm() / 100 + 1) * sprint3.getSeries();
        assertEquals(expectedCalorias3, sprint3.calorias(), 0.01);

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Sprint sprint4 = new Sprint("001", "Descrição", data, 30, 4, praticanteOcasional2, 12.0);

        double expectedCalorias4 = praticanteOcasional2.fatorMultiplicativo() * (sprint4.getVelocidade() / 2 + 1) * sprint4.getDuracao() * (sprint4.bpm() / 100 + 1) * sprint4.getSeries();
        assertEquals(expectedCalorias4, sprint4.calorias(), 0.01);

        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Sprint sprint5 = new Sprint("001", "Descrição", data, 30, 5, amador1, 12.0);

        double expectedCalorias5 = amador1.fatorMultiplicativo() * (sprint5.getVelocidade() / 2 + 1) * sprint5.getDuracao() * (sprint5.bpm() / 100 + 1) * sprint5.getSeries();;
        assertEquals(expectedCalorias5, sprint5.calorias(), 0.01);

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Sprint sprint6 = new Sprint("001", "Descrição", data, 20, 5, amador2, 12.0);

        double expectedCalorias6 = amador2.fatorMultiplicativo() * (sprint6.getVelocidade() / 2 + 1) * sprint6.getDuracao() * (sprint6.bpm() / 100 + 1) * sprint6.getSeries();
        assertEquals(expectedCalorias6, sprint6.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Sprint sprint1 = new Sprint("001", "Descrição", data, 30, 3, profissional1, 10.0);

        int expectedBpm1 = (int) (profissional1.getBpmMedio() + 30 * profissional1.fatorMultiplicativo());
        assertEquals(expectedBpm1, sprint1.bpm());

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Sprint sprint2 = new Sprint("001", "Descrição", data, 30, 3, profissional2, 10.0);

        int expectedBpm2 = (int) (profissional2.getBpmMedio() + 30 * profissional2.fatorMultiplicativo());
        assertEquals(expectedBpm2, sprint2.bpm());

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Sprint sprint3 = new Sprint("001", "Descrição", data, 30, 3, praticanteOcasional1, 10.0);

        int expectedBpm3 = (int) (praticanteOcasional1.getBpmMedio() + 30 * praticanteOcasional1.fatorMultiplicativo());
        assertEquals(expectedBpm3, sprint3.bpm());

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Sprint sprint4 = new Sprint("001", "Descrição", data, 30, 3, praticanteOcasional2, 10.0);

        int expectedBpm4 = (int) (praticanteOcasional2.getBpmMedio() + 30 * praticanteOcasional2.fatorMultiplicativo());
        assertEquals(expectedBpm4, sprint4.bpm());

        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Sprint sprint5 = new Sprint("001", "Descrição", data, 30, 3, amador1, 10.0);

        int expectedBpm5 = (int) (amador1.getBpmMedio() + 30 * amador1.fatorMultiplicativo());
        assertEquals(expectedBpm5, sprint5.bpm());

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Sprint sprint6 = new Sprint("001", "Descrição", data, 30, 3, amador2, 10.0);

        int expectedBpm6 = (int) (amador2.getBpmMedio() + 30 * amador2.fatorMultiplicativo());
        assertEquals(expectedBpm6, sprint6.bpm());
    }
}
