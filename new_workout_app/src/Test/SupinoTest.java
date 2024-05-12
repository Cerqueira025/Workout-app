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
        assertEquals(4, supino1.getSeries());
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
        assertEquals(2, supino2.getSeries());
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
        assertEquals(4, supino3.getSeries());
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
        assertEquals(4, supino1.getSeries());
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
        assertEquals(2, supino5.getSeries());
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
        assertEquals(2, supino6.getSeries());
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
        Supino supino4 = new Supino("002", "Descrição", data, 30, 5, profissional, 10, 50, 45.0);

        // --- Verificar igualdade ---
        //parâmetros iguais
        assertTrue(supino1.equals(supino2));
        //parâmetros diferentes
        assertFalse(supino1.equals(supino3));
        assertFalse(supino2.equals(supino4));
        assertFalse(supino3.equals(supino4));
    }

    @Test
    public void testToString() {
        Profissional profissional = new Profissional();
        LocalDateTime data = LocalDateTime.now();
        Supino supino1 = new Supino("001", "Descrição", data, 30, 4, profissional, 10, 50, 45.0);
        String expectedToString = "Supino - " +
                "código = '001', descrição = 'Descrição', data = '"+
                data + "', duração = '30', bpm médio = '0', séries = '4', calorias = '0.0', "+
                "repetições = '10', peso = '50.0', "+
                "inclinação = '" + supino1.getInclinacao() + "'";
        assertEquals(expectedToString, supino1.toString());
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
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Supino supino1 = new Supino("001", "Descrição", data, 30, 2, profissional1, 10, 50, 45.0);

        double caloriasEsperadas1 = profissional1.fatorMultiplicativo() * (supino1.getInclinacao()/30+1)
                * (supino1.getPeso()/20 + 1) * supino1.getDuracao() * ((supino1.bpm()/100) + 1) * supino1.getSeries() + supino1.getRepeticoes();
        assertEquals(caloriasEsperadas1, supino1.calorias(), 0.01);

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Supino supino2 = new Supino("001", "Descrição", data, 30, 2, profissional2, 10, 50, 45.0);

        double caloriasEsperadas2 = profissional2.fatorMultiplicativo() * (supino2.getInclinacao()/30+1)
                * (supino2.getPeso()/20 + 1) * supino2.getDuracao() * ((supino2.bpm()/100) + 1) * supino2.getSeries() + supino2.getRepeticoes();
        assertEquals(caloriasEsperadas2, supino2.calorias(), 0.01);

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Supino supino3 = new Supino("001", "Descrição", data, 30, 2, praticanteOcasional1, 10, 50, 45.0);

        double caloriasEsperadas3 = praticanteOcasional1.fatorMultiplicativo() * (supino3.getInclinacao()/30+1)
                * (supino3.getPeso()/20 + 1) * supino3.getDuracao() * ((supino3.bpm()/100) + 1) * supino3.getSeries() + supino3.getRepeticoes();
        assertEquals(caloriasEsperadas3, supino3.calorias(), 0.01);

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Supino supino4 = new Supino("001", "Descrição", data, 30, 2, praticanteOcasional2, 10, 50, 45.0);

        double caloriasEsperadas4 = praticanteOcasional2.fatorMultiplicativo() * (supino4.getInclinacao()/30+1)
                * (supino4.getPeso()/20 + 1) * supino4.getDuracao() * ((supino4.bpm()/100) + 1) * supino4.getSeries() + supino4.getRepeticoes();
        assertEquals(caloriasEsperadas4, supino4.calorias(), 0.01);

        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Supino supino5 = new Supino("001", "Descrição", data, 30, 2, amador1, 10, 50, 45.0);

        double caloriasEsperadas5 = amador1.fatorMultiplicativo() * (supino5.getInclinacao()/30+1)
                * (supino5.getPeso()/20 + 1) * supino5.getDuracao() * ((supino5.bpm()/100) + 1) * supino5.getSeries() + supino5.getRepeticoes();
        assertEquals(caloriasEsperadas5, supino5.calorias(), 0.01);

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Supino supino6 = new Supino("001", "Descrição", data, 30, 2, amador2, 10, 50, 45.0);

        double caloriasEsperadas6 = amador2.fatorMultiplicativo() * (supino6.getInclinacao()/30+1)
                * (supino6.getPeso()/20 + 1) * supino6.getDuracao() * ((supino6.bpm()/100) + 1) * supino6.getSeries() + supino6.getRepeticoes();
        assertEquals(caloriasEsperadas6, supino6.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Supino supino1 = new Supino("001", "Descrição", data, 30, 2, profissional1, 10, 50, 45.0);

        int bpmEsperado1 = (int) (profissional1.getBpmMedio() + 10 * profissional1.fatorMultiplicativo());
        assertEquals(bpmEsperado1, supino1.bpm());

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Supino supino2 = new Supino("001", "Descrição", data, 30, 2, profissional2, 10, 50, 45.0);

        int bpmEsperado2 = (int) (profissional2.getBpmMedio() + 10 * profissional2.fatorMultiplicativo());
        assertEquals(bpmEsperado2, supino2.bpm());

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Supino supino3 = new Supino("001", "Descrição", data, 30, 2, praticanteOcasional1, 10, 50, 45.0);

        int bpmEsperado3 = (int) (praticanteOcasional1.getBpmMedio() + 10 * praticanteOcasional1.fatorMultiplicativo());
        assertEquals(bpmEsperado3, supino3.bpm());

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Supino supino4 = new Supino("001", "Descrição", data, 30, 2, praticanteOcasional2, 10, 50, 45.0);

        int bpmEsperado4 = (int) (praticanteOcasional2.getBpmMedio() + 10 * praticanteOcasional2.fatorMultiplicativo());
        assertEquals(bpmEsperado4, supino4.bpm());

        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Supino supino5 = new Supino("001", "Descrição", data, 30, 2, amador1, 10, 50, 45.0);

        int bpmEsperado5 = (int) (amador1.getBpmMedio() + 10 * amador1.fatorMultiplicativo());
        assertEquals(bpmEsperado5, supino5.bpm());

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Supino supino6 = new Supino("001", "Descrição", data, 30, 2, amador2, 10, 50, 45.0);

        int bpmEsperado6 = (int) (amador2.getBpmMedio() + 10 * amador2.fatorMultiplicativo());
        assertEquals(bpmEsperado6, supino6.bpm());
    }

}
