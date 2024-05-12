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

import Atividade.Repeticoes.Abdominais;
public class AbdominaisTest {
    public AbdominaisTest()
    {
    }

    @Test
    public void testConstructor() {
        // ------------------- Construtor vazio ------------------- //
        Abdominais abdominais = new Abdominais();
        assertTrue(abdominais != null);

        // ------------------- Construtor parametrizado (tipos de utilizador) -------------------//
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Map<String, Double> recordes = new HashMap<>();
        LocalDateTime data = LocalDateTime.now();

        // Atleta Profissional
        Profissional profissional = new Profissional("profId", 75, 80.0, 55, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais1 = new Abdominais("001", "Descrição", data, 30, 3, profissional, 10, 45.0);

        assertEquals("001", abdominais1.getCodigo());
        assertEquals("Descrição", abdominais1.getDescricao());
        assertEquals(data, abdominais1.getData());
        assertEquals(30, abdominais1.getDuracao());
        assertEquals(3, abdominais1.getSeries());
        assertEquals(profissional, abdominais1.getUtilizador());
        assertEquals(10, abdominais1.getRepeticoes());
        assertEquals(45.0, abdominais1.getAmplitude(), 0.01);

        // Atleta Amador
        Amador amador = new Amador("amadorId", 70, 75, 100, 170,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais2 = new Abdominais("002", "Descrição", data, 30, 3, amador, 10, 45.0);

        assertEquals("002", abdominais2.getCodigo());
        assertEquals("Descrição", abdominais2.getDescricao());
        assertEquals(data, abdominais2.getData());
        assertEquals(30, abdominais2.getDuracao());
        assertEquals(3, abdominais2.getSeries());
        assertEquals(amador, abdominais2.getUtilizador());
        assertEquals(10, abdominais2.getRepeticoes());
        assertEquals(45.0, abdominais2.getAmplitude(), 0.01);

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional = new PraticanteOcasional("praticanteId", 65, 70, 90, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais3 = new Abdominais("003", "Descrição", data, 30, 2, praticanteOcasional, 10, 45.0);

        assertEquals("003", abdominais3.getCodigo());
        assertEquals("Descrição", abdominais3.getDescricao());
        assertEquals(data, abdominais3.getData());
        assertEquals(30, abdominais3.getDuracao());
        assertEquals(2, abdominais3.getSeries());
        assertEquals(praticanteOcasional, abdominais3.getUtilizador());
        assertEquals(10, abdominais3.getRepeticoes());
        assertEquals(45.0, abdominais3.getAmplitude(), 0.01);
    }

    @Test
    public void TestToString() {
        Profissional profissional = new Profissional();
        LocalDateTime data = LocalDateTime.now();
        Abdominais abdominais1 = new Abdominais("001", "Descrição", data, 30, 2, profissional, 10, 45.0);
        String expectedToString = "Abdominais - " +
                "código = '001', descrição = 'Descrição', data = '"+ data +
                "', duração = '30', bpm médio = '0', séries = '2', calorias = '0.0', repetições = '10', "+
                "amplitude = '" + abdominais1.getAmplitude() +
                "'";
        assertEquals(expectedToString, abdominais1.toString());
    }

    @Test
    public void testEquals() {
        // Criar objetos para teste
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        Profissional profissional = new Profissional("profId", 70, 75, 170, 175,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais1 = new Abdominais("001", "Descrição", data, 30, 2, profissional, 10, 45.0);
        Abdominais abdominais2 = new Abdominais("001", "Descrição", data, 30, 2, profissional, 10, 45.0);

        Abdominais abdominais3 = new Abdominais("002", "Descrição", data, 30, 2, profissional, 10, 45.0);
        Abdominais abdominais4 = new Abdominais("002", "Descrição", data, 30, 2, profissional, 9, 45.0);


        // --- Verificar igualdade ---
        //parâmetros iguais
        assertTrue(abdominais1.equals(abdominais2));
        //parâmetros diferentes
        assertFalse(abdominais1.equals(abdominais3));
        assertFalse(abdominais2.equals(abdominais4));
        assertFalse(abdominais3.equals(abdominais4));
    }

    @Test
    public void testClone() {
        // Criar objeto para teste
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        Profissional profissional = new Profissional("profId", 70, 75, 170, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais1 = new Abdominais("001", "Descrição", data, 30, 2, profissional, 10, 45.0);

        // Clonar e verificar igualdade
        Abdominais abdominais2 = abdominais1.clone();
        assertEquals(abdominais1, abdominais2);
    }

    @Test
    public void testCalorias() {
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 180, 170,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais1 = new Abdominais("001", "Descrição", data, 30, 2, profissional1, 10, 45.0);

        double caloriasEsperadas1 = profissional1.fatorMultiplicativo() * (abdominais1.getAmplitude() / 40 + 1)
                * (abdominais1.getDuracao()/2 + 1) * (abdominais1.bpm()/ 100 + 1) * abdominais1.getSeries() + abdominais1.getRepeticoes();
        assertEquals(caloriasEsperadas1, abdominais1.calorias(), 0.01);

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 180, 170,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais2 = new Abdominais("002", "Descrição", data, 20, 2, profissional2, 10, 30.0);

        double caloriasEsperadas2 = profissional2.fatorMultiplicativo() * (abdominais2.getAmplitude() / 40 + 1)
                * (abdominais2.getDuracao()/2 + 1) * (abdominais2.bpm()/ 100 + 1) * abdominais2.getSeries() + abdominais2.getRepeticoes();
        assertEquals(caloriasEsperadas2, abdominais2.calorias(), 0.01);

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais3 = new Abdominais("001", "Descrição", data, 30, 2, praticanteOcasional1, 10, 45.0);

        double caloriasEsperadas3 = praticanteOcasional1.fatorMultiplicativo() * (abdominais3.getAmplitude() / 40 + 1)
                * (abdominais3.getDuracao()/2 + 1) * (abdominais3.bpm()/ 100 + 1) * abdominais3.getSeries() + abdominais3.getRepeticoes();
        assertEquals(caloriasEsperadas3, abdominais3.calorias(), 0.01);

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais4 = new Abdominais("002", "Descrição", data, 15, 2, praticanteOcasional2, 5, 25.0);

        double caloriasEsperadas4 = praticanteOcasional2.fatorMultiplicativo() * (abdominais4.getAmplitude() / 40 + 1)
                * (abdominais4.getDuracao()/2 + 1) * (abdominais4.bpm()/ 100 + 1) * abdominais4.getSeries() + abdominais4.getRepeticoes();
        assertEquals(caloriasEsperadas4, abdominais4.calorias(), 0.01);


        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais5 = new Abdominais("001", "Descrição", data, 30, 2, amador1, 10, 45.0);

        double caloriasEsperadas5 = amador1.fatorMultiplicativo() * (abdominais5.getAmplitude() / 40 + 1)
                * (abdominais5.getDuracao()/2 + 1) * (abdominais5.bpm()/ 100 + 1) * abdominais5.getSeries() + abdominais5.getRepeticoes();
        assertEquals(caloriasEsperadas5, abdominais5.calorias(), 0.01);


        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais6 = new Abdominais("002", "Descrição", data, 15, 2, amador2, 5, 25.0);

        double caloriasEsperadas6 = amador2.fatorMultiplicativo() * (abdominais6.getAmplitude() / 40 + 1)
                * (abdominais6.getDuracao()/2 + 1) * (abdominais6.bpm()/ 100 + 1) * abdominais6.getSeries() + abdominais6.getRepeticoes();
        assertEquals(caloriasEsperadas6, abdominais6.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais1 = new Abdominais("001", "Descrição", data, 30, 2, profissional1, 10, 45.0);

        int bpmEsperado1 = (int) (profissional1.getBpmMedio() + 10 * profissional1.fatorMultiplicativo());
        assertEquals(bpmEsperado1, abdominais1.bpm());

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais2 = new Abdominais("002", "Descrição", data, 15, 2, profissional2, 5, 25.0);

        int bpmEsperado2 = (int) (profissional2.getBpmMedio() + 10 * profissional2.fatorMultiplicativo());
        assertEquals(bpmEsperado2, abdominais2.bpm());


        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais3 = new Abdominais("001", "Descrição", data, 30, 2, praticanteOcasional1, 10, 45.0);

        int bpmEsperado3 = (int) (praticanteOcasional1.getBpmMedio() + 10 * praticanteOcasional1.fatorMultiplicativo());
        assertEquals(bpmEsperado3, abdominais3.bpm());

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais4 = new Abdominais("002", "Descrição", data, 15, 2, praticanteOcasional2, 5, 25.0);

        int bpmEsperado4 = (int) (praticanteOcasional2.getBpmMedio() + 10 * praticanteOcasional2.fatorMultiplicativo());
        assertEquals(bpmEsperado4, abdominais4.bpm());


        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais5 = new Abdominais("001", "Descrição", data, 30, 2, amador1, 10, 45.0);

        int bpmEsperado5 = (int) (amador1.getBpmMedio() + 10 * amador1.fatorMultiplicativo());
        assertEquals(bpmEsperado5, abdominais5.bpm());

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Abdominais abdominais6 = new Abdominais("002", "Descrição", data, 15, 2, amador2, 5, 25.0);

        int bpmEsperado6 = (int) (amador2.getBpmMedio() + 10 * amador2.fatorMultiplicativo());
        assertEquals(bpmEsperado6, abdominais6.bpm());
    }
}
