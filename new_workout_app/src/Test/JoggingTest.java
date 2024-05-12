import Atividade.Atividade;
import Atividade.Distancia.Jogging;
import PlanoTreino.PlanoDeTreino;
import Utilizador.Genero;
import Utilizador.TiposUtilizador.Amador;
import Utilizador.TiposUtilizador.PraticanteOcasional;
import Utilizador.TiposUtilizador.Profissional;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class JoggingTest {
    public JoggingTest()
    {
    }

    @Test
    public void testConstructor() {
        // Construtor vazio
        Jogging jogging = new Jogging();
        assertTrue(jogging != null);

        // ------------------- Construtor parametrizado com Map e PlanoDeTreino ------------------- //
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Map<String, Double> recordes = new HashMap<>();
        LocalDateTime data = LocalDateTime.now();

        // atleta Profissional
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Jogging jogging1 = new Jogging("001", "Descrição", data, 30, 4,
                profissional1, 10.0);

        assertEquals("001", jogging1.getCodigo());
        assertEquals("Descrição", jogging1.getDescricao());
        assertEquals(data, jogging1.getData());
        assertEquals(30, jogging1.getDuracao());
        assertEquals(4, jogging1.getSeries());
        assertEquals(profissional1, jogging1.getUtilizador());
        assertEquals(10.0, jogging1.getDistancia(), 0.01);

        // atleta Amador
        Amador amador1 = new Amador("amadorId", 65, 70, 55, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Jogging jogging2 = new Jogging("002", "Descrição", data, 30, 3,
                amador1, 10.0);

        assertEquals("002", jogging2.getCodigo());
        assertEquals("Descrição", jogging2.getDescricao());
        assertEquals(data, jogging2.getData());
        assertEquals(3, jogging2.getSeries());
        assertEquals(30, jogging2.getDuracao());
        assertEquals(amador1, jogging2.getUtilizador());
        assertEquals(10.0, jogging2.getDistancia(), 0.01);

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("praticanteId", 65, 70, 55, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha",  atividades, recordes, plano);
        Jogging jogging3 = new Jogging("003", "Descrição", data, 30, 2,
                praticanteOcasional1, 10.0);

        assertEquals("003", jogging3.getCodigo());
        assertEquals("Descrição", jogging3.getDescricao());
        assertEquals(data, jogging3.getData());
        assertEquals(2, jogging3.getSeries());
        assertEquals(30, jogging3.getDuracao());
        assertEquals(praticanteOcasional1, jogging3.getUtilizador());
        assertEquals(10.0, jogging3.getDistancia(), 0.01);

        // ------------------- Construtor sem Map e PlanoDeTreino ------------------- //
        // atleta Profissional
        Profissional profissional2 = new Profissional("profId", 75, 80, 60, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Jogging jogging4 = new Jogging("004", "Descrição", data, 30, 2,
                profissional2, 10.0);

        assertEquals("004", jogging4.getCodigo());
        assertEquals("Descrição", jogging4.getDescricao());
        assertEquals(data, jogging4.getData());
        assertEquals(30, jogging4.getDuracao());
        assertEquals(2, jogging4.getSeries());
        assertEquals(profissional2, jogging4.getUtilizador());
        assertEquals(10.0, jogging4.getDistancia(), 0.01);

        // atleta Amador
        Amador amador2 = new Amador("amadorId", 65, 70, 20, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Jogging jogging5 = new Jogging("005", "Descrição", data, 30, 3,
                amador2, 10.0);

        assertEquals("005", jogging5.getCodigo());
        assertEquals("Descrição", jogging5.getDescricao());
        assertEquals(data, jogging5.getData());
        assertEquals(30, jogging5.getDuracao());
        assertEquals(3, jogging5.getSeries());
        assertEquals(amador2, jogging5.getUtilizador());
        assertEquals(10.0, jogging5.getDistancia(), 0.01);

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("praticanteId", 65, 70, 75, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Jogging jogging6 = new Jogging("006", "Descrição", data, 30, 2,
                praticanteOcasional2, 10.0);

        assertEquals("006", jogging6.getCodigo());
        assertEquals("Descrição", jogging6.getDescricao());
        assertEquals(data, jogging6.getData());
        assertEquals(30, jogging6.getDuracao());
        assertEquals(2, jogging6.getSeries());
        assertEquals(praticanteOcasional2, jogging6.getUtilizador());
        assertEquals(10.0, jogging6.getDistancia(), 0.01);
    }

    @Test
    public void testEquals() {
        LocalDateTime data = LocalDateTime.now();

        // Teste de igualdade entre duas instâncias idênticas
        Jogging jogging1 = new Jogging("001", "Descrição", data, 30, 2, new Profissional(), 10.0);
        Jogging jogging2 = new Jogging("001", "Descrição", data, 30, 2, new Profissional(), 10.0);

        Jogging jogging3 = new Jogging("002", "Outra Descrição", data, 30, 3, new Profissional(), 10.0);
        Jogging jogging4 = new Jogging("002", "Outra Descrição", data, 25, 3, new Profissional(), 10.0);

        // --- Verificar igualdade ---
        //parâmetros iguais
        assertTrue(jogging1.equals(jogging2));
        //parâmetros diferentes
        assertFalse(jogging1.equals(jogging3));
        assertFalse(jogging2.equals(jogging4));
        assertFalse(jogging3.equals(jogging4));
    }

    @Test
    public void testToString() {
        Profissional profissional = new Profissional();
        LocalDateTime data = LocalDateTime.now();
        Jogging sprint = new Jogging("001", "Descrição", data, 30, 4, profissional, 10.0);
        String expectedToString = "Jogging - " +
                "código = '001', descrição = 'Descrição', data = '" + data +
                "', duração = '30', bpm médio = '0', séries = '4', calorias = '0.0', "
                +                "distância = '10.0', velocidade = '20.0'";
        assertEquals(expectedToString, sprint.toString());
    }

    @Test
    public void testClone() {
        LocalDateTime data = LocalDateTime.now();
        Jogging jogging1 = new Jogging("001", "Descrição", data, 30, 2, new Profissional(), 10.0);
        Jogging jogging2 = jogging1.clone();
        assertEquals(jogging1, jogging2);
    }

    @Test
    public void testCalorias() {
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Jogging jogging1 = new Jogging("001", "Descrição", data, 30, 4, profissional1, 10.0);

        double expectedCalorias1 = profissional1.fatorMultiplicativo() * (jogging1.getVelocidade() / 5 + 1) * (jogging1.getDuracao()/2 + 1) * (jogging1.bpm() / 100 + 1) * jogging1.getSeries();
        assertEquals(expectedCalorias1, jogging1.calorias(), 0.01);

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Jogging jogging2 = new Jogging("001", "Descrição", data, 30, 4, profissional2, 12.0);

        double expectedCalorias2 = profissional2.fatorMultiplicativo() * (jogging2.getVelocidade() / 5 + 1) * (jogging2.getDuracao()/2 + 1) * (jogging2.bpm() / 100 + 1) * jogging2.getSeries();
        assertEquals(expectedCalorias2, jogging2.calorias(), 0.01);

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Jogging jogging3 = new Jogging("001", "Descrição", data, 30, 4, praticanteOcasional1, 12.0);

        double expectedCalorias3 = praticanteOcasional1.fatorMultiplicativo() * (jogging3.getVelocidade() / 5 + 1) * (jogging3.getDuracao()/2 + 1) * (jogging3.bpm() / 100 + 1) * jogging3.getSeries();
        assertEquals(expectedCalorias3, jogging3.calorias(), 0.01);

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Jogging jogging4 = new Jogging("001", "Descrição", data, 30, 4, praticanteOcasional2, 12.0);

        double expectedCalorias4 = praticanteOcasional2.fatorMultiplicativo() * (jogging4.getVelocidade() / 5 + 1) * (jogging4.getDuracao()/2 + 1) * (jogging4.bpm() / 100 + 1) * jogging4.getSeries();
        assertEquals(expectedCalorias4, jogging4.calorias(), 0.01);

        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Jogging jogging5 = new Jogging("001", "Descrição", data, 30, 5, amador1, 12.0);

        double expectedCalorias5 = amador1.fatorMultiplicativo() * (jogging5.getVelocidade() / 5 + 1) *( jogging5.getDuracao()/2 + 1) * (jogging5.bpm() / 100 + 1) * jogging5.getSeries();;
        assertEquals(expectedCalorias5, jogging5.calorias(), 0.01);

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Jogging jogging6 = new Jogging("001", "Descrição", data, 20, 5, amador2, 12.0);

        double expectedCalorias6 = amador2.fatorMultiplicativo() * (jogging6.getVelocidade() / 5 + 1) *( jogging6.getDuracao()/2 + 1) * (jogging6.bpm() / 100 + 1) * jogging6.getSeries();
        assertEquals(expectedCalorias6, jogging6.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Jogging jogging1 = new Jogging("001", "Descrição", data, 30, 3, profissional1, 10.0);

        int expectedBpm1 = (int) (profissional1.getBpmMedio() + 25 * profissional1.fatorMultiplicativo());
        assertEquals(expectedBpm1, jogging1.bpm());

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Jogging jogging2 = new Jogging("001", "Descrição", data, 30, 3, profissional2, 10.0);

        int expectedBpm2 = (int) (profissional2.getBpmMedio() + 25 * profissional2.fatorMultiplicativo());
        assertEquals(expectedBpm2, jogging2.bpm());

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Jogging jogging3 = new Jogging("001", "Descrição", data, 30, 3, praticanteOcasional1, 10.0);

        int expectedBpm3 = (int) (praticanteOcasional1.getBpmMedio() + 25 * praticanteOcasional1.fatorMultiplicativo());
        assertEquals(expectedBpm3, jogging3.bpm());

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Jogging jogging4 = new Jogging("001", "Descrição", data, 30, 3, praticanteOcasional2, 10.0);

        int expectedBpm4 = (int) (praticanteOcasional2.getBpmMedio() + 25 * praticanteOcasional2.fatorMultiplicativo());
        assertEquals(expectedBpm4, jogging4.bpm());

        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Jogging jogging5 = new Jogging("001", "Descrição", data, 30, 3, amador1, 10.0);

        int expectedBpm5 = (int) (amador1.getBpmMedio() + 25 * amador1.fatorMultiplicativo());
        assertEquals(expectedBpm5, jogging5.bpm());

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Jogging jogging6 = new Jogging("001", "Descrição", data, 30, 3, amador2, 10.0);

        int expectedBpm6 = (int) (amador2.getBpmMedio() + 25 * amador2.fatorMultiplicativo());
        assertEquals(expectedBpm6, jogging6.bpm());
    }
}
