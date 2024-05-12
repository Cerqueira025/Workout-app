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

import Atividade.Repeticoes.Pesos.Biceps;

public class BicepsTest {
    public BicepsTest()
    {
    }

    @Test
    public void testConstructor() {
        //Construtor vazio
        Biceps biceps = new Biceps();
        assertTrue(biceps!=null);

        // ------------------- Construtor parametrizado com Map e PlanoDeTreino ------------------- //
        // atleta Profissional
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        Profissional profissional1 = new Profissional("profId", 75, 80, 75, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Biceps biceps1 = new Biceps("001", "Descrição", data, 30, 4, profissional1, 10, 50, true);

        assertEquals("001", biceps1.getCodigo());
        assertEquals("Descrição", biceps1.getDescricao());
        assertEquals(data, biceps1.getData());
        assertEquals(30, biceps1.getDuracao());
        assertEquals(4, biceps1.getSeries());
        assertEquals(profissional1, biceps1.getUtilizador());
        assertEquals(10, biceps1.getRepeticoes());
        assertEquals(50, biceps1.getPeso(), 0.01);
        assertTrue(biceps1.getBilateral());

        //atleta Amador
        Amador amador1 = new Amador("amadorId", 70, 75, 60, 170,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Biceps biceps2 = new Biceps("001", "Descrição", data, 30, 2, amador1, 10, 50, true);

        assertEquals("001", biceps2.getCodigo());
        assertEquals("Descrição", biceps2.getDescricao());
        assertEquals(data, biceps2.getData());
        assertEquals(30, biceps2.getDuracao());
        assertEquals(2, biceps2.getSeries());
        assertEquals(amador1, biceps2.getUtilizador());
        assertEquals(10, biceps2.getRepeticoes());
        assertEquals(50, biceps2.getPeso(), 0.01);
        assertTrue(biceps1.getBilateral());


        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("praticanteId", 65, 70, 44, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Biceps biceps3 = new Biceps("001", "Descrição", data, 30, 4, praticanteOcasional1, 10, 50, true);

        assertEquals("001", biceps3.getCodigo());
        assertEquals("Descrição", biceps3.getDescricao());
        assertEquals(data, biceps3.getData());
        assertEquals(30, biceps3.getDuracao());
        assertEquals(4, biceps3.getSeries());
        assertEquals(praticanteOcasional1, biceps3.getUtilizador());
        assertEquals(10, biceps3.getRepeticoes());
        assertEquals(50, biceps3.getPeso(), 0.01);
        assertTrue(biceps1.getBilateral());

        // ------------------- Construtor sem Map e PlanoDeTreino ------------------- //
        // atleta Profissional
        Profissional profissional2 = new Profissional("profId", 75, 80, 65, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Biceps biceps4 = new Biceps("001", "Descrição", data, 30, 4, profissional2, 10, 50, true);

        assertEquals("001", biceps4.getCodigo());
        assertEquals("Descrição", biceps4.getDescricao());
        assertEquals(data, biceps4.getData());
        assertEquals(30, biceps4.getDuracao());
        assertEquals(4, biceps1.getSeries());
        assertEquals(profissional2, biceps4.getUtilizador());
        assertEquals(10, biceps4.getRepeticoes());
        assertEquals(50, biceps4.getPeso(), 0.01);
        assertTrue(biceps1.getBilateral());

        //atleta Amador
        Amador amador2 = new Amador("amadorId", 70, 75, 44, 170,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Biceps biceps5 = new Biceps("001", "Descrição", data, 30, 2, amador2, 10, 50, true);

        assertEquals("001", biceps5.getCodigo());
        assertEquals("Descrição", biceps5.getDescricao());
        assertEquals(data, biceps5.getData());
        assertEquals(30, biceps5.getDuracao());
        assertEquals(2, biceps5.getSeries());
        assertEquals(amador2, biceps5.getUtilizador());
        assertEquals(10, biceps5.getRepeticoes());
        assertEquals(50, biceps5.getPeso(), 0.01);
        assertTrue(biceps1.getBilateral());

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("praticanteId", 65, 70, 30, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Biceps biceps6 = new Biceps("001", "Descrição", data, 30, 2, praticanteOcasional2, 10, 50, true);

        assertEquals("001", biceps6.getCodigo());
        assertEquals("Descrição", biceps6.getDescricao());
        assertEquals(data, biceps6.getData());
        assertEquals(30, biceps6.getDuracao());
        assertEquals(2, biceps6.getSeries());
        assertEquals(praticanteOcasional2, biceps6.getUtilizador());
        assertEquals(10, biceps6.getRepeticoes());
        assertEquals(50, biceps6.getPeso(), 0.01);
        assertTrue(biceps1.getBilateral());
    }

    @Test
    public void testEquals() {
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        Profissional profissional = new Profissional("profId", 75, 80, 75, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);

        Biceps biceps1 = new Biceps("001", "Descrição", data, 30, 4, profissional, 10, 50, true);
        Biceps biceps2 = new Biceps("001", "Descrição", data, 30, 4, profissional, 10, 50, true);
        Biceps biceps3 = new Biceps("002", "Descrição", data, 30, 2, profissional, 10, 50, true);
        Biceps biceps4 = new Biceps("002", "Descrição", data, 30, 5, profissional, 10, 50, false);

        // --- Verificar igualdade ---
        //parâmetros iguais
        assertTrue(biceps1.equals(biceps2));
        //parâmetros diferentes
        assertFalse(biceps1.equals(biceps3));
        assertFalse(biceps2.equals(biceps4));
        assertFalse(biceps3.equals(biceps4));
    }

    @Test
    public void testToString() {
        Profissional profissional = new Profissional();
        LocalDateTime data = LocalDateTime.now();
        Biceps biceps1 = new Biceps("001", "Descrição", data, 30, 4, profissional, 10, 50, true);
        String expectedToString = "Bíceps - " +
                "código = '001', descrição = 'Descrição', data = '"+
                data + "', duração = '30', bpm médio = '0', séries = '4', calorias = '0.0', "+
                "repetições = '10', peso = '50.0', "+
                "bilateral = '" + biceps1.getBilateral() + "'";
        assertEquals(expectedToString, biceps1.toString());
    }
    @Test
    public void testClone() {
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        Profissional profissional = new Profissional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);

        Biceps biceps1 = new Biceps("001", "Descrição", data, 30, 2, profissional, 10, 50, true);
        Biceps biceps2 = biceps1.clone();

        assertEquals(biceps1, biceps2);
    }

    @Test
    public void testCalorias() {
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Biceps biceps1 = new Biceps("001", "Descrição", data, 30, 2, profissional1, 10, 50, true);

        double caloriasEsperadas1 = profissional1.fatorMultiplicativo() * (biceps1.getPeso()/20 + 1) * biceps1.getDuracao() * ((biceps1.bpm()/100) + 1) * biceps1.getSeries() + biceps1.getRepeticoes();
        assertEquals(caloriasEsperadas1, biceps1.calorias(), 0.01);

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Biceps biceps2 = new Biceps("001", "Descrição", data, 30, 2, profissional2, 10, 50, false);

        double caloriasEsperadas2 = 1.2 * profissional2.fatorMultiplicativo() * (biceps2.getPeso()/20 + 1) * biceps2.getDuracao() * ((biceps2.bpm()/100) + 1) * biceps2.getSeries() + biceps2.getRepeticoes();
        assertEquals(caloriasEsperadas2, biceps2.calorias(), 0.01);

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Biceps biceps3 = new Biceps("001", "Descrição", data, 30, 2, praticanteOcasional1, 10, 50, true);

        double caloriasEsperadas3 = praticanteOcasional1.fatorMultiplicativo() * (biceps3.getPeso()/20 + 1) * biceps3.getDuracao() * ((biceps3.bpm()/100) + 1) * biceps3.getSeries() + biceps3.getRepeticoes();
        assertEquals(caloriasEsperadas3, biceps3.calorias(), 0.01);

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Biceps biceps4 = new Biceps("001", "Descrição", data, 30, 2, praticanteOcasional2, 10, 50, false);

        double caloriasEsperadas4 = 1.2 * praticanteOcasional2.fatorMultiplicativo() * (biceps4.getPeso()/20 + 1) * biceps4.getDuracao() * ((biceps4.bpm()/100) + 1) * biceps4.getSeries() + biceps4.getRepeticoes();
        assertEquals(caloriasEsperadas4, biceps4.calorias(), 0.01);

        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Biceps biceps5 = new Biceps("001", "Descrição", data, 30, 2, amador1, 10, 50, true);

        double caloriasEsperadas5 = amador1.fatorMultiplicativo() * (biceps5.getPeso()/20 + 1) * biceps5.getDuracao() * ((biceps5.bpm()/100) + 1) * biceps5.getSeries() + biceps5.getRepeticoes();
        assertEquals(caloriasEsperadas5, biceps5.calorias(), 0.01);

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Biceps biceps6 = new Biceps("001", "Descrição", data, 30, 2, amador2, 10, 50, false);

        double caloriasEsperadas6 = 1.2 * amador2.fatorMultiplicativo() * (biceps6.getPeso()/20 + 1) * biceps6.getDuracao() * ((biceps6.bpm()/100) + 1) * biceps6.getSeries() + biceps6.getRepeticoes();
        assertEquals(caloriasEsperadas6, biceps6.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Biceps biceps1 = new Biceps("001", "Descrição", data, 30, 2, profissional1, 10, 50, true);

        int bpmEsperado1 = (int) (profissional1.getBpmMedio() + 9 * profissional1.fatorMultiplicativo());
        assertEquals(bpmEsperado1, biceps1.bpm());

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Biceps biceps2 = new Biceps("001", "Descrição", data, 30, 2, profissional2, 10, 50, false);

        int bpmEsperado2 = (int) (profissional2.getBpmMedio() + 9 * profissional2.fatorMultiplicativo());
        assertEquals(bpmEsperado2, biceps2.bpm());

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Biceps biceps3 = new Biceps("001", "Descrição", data, 30, 2, praticanteOcasional1, 10, 50, true);

        int bpmEsperado3 = (int) (praticanteOcasional1.getBpmMedio() + 9 * praticanteOcasional1.fatorMultiplicativo());
        assertEquals(bpmEsperado3, biceps3.bpm());

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Biceps biceps4 = new Biceps("001", "Descrição", data, 30, 2, praticanteOcasional2, 10, 50, false);

        int bpmEsperado4 = (int) (praticanteOcasional2.getBpmMedio() + 9 * praticanteOcasional2.fatorMultiplicativo());
        assertEquals(bpmEsperado4, biceps4.bpm());

        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        Biceps biceps5 = new Biceps("001", "Descrição", data, 30, 2, amador1, 10, 50, true);

        int bpmEsperado5 = (int) (amador1.getBpmMedio() + 9 * amador1.fatorMultiplicativo());
        assertEquals(bpmEsperado5, biceps5.bpm());

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        Biceps biceps6 = new Biceps("001", "Descrição", data, 30, 2, amador2, 10, 50, false);

        int bpmEsperado6 = (int) (amador2.getBpmMedio() + 9 * amador2.fatorMultiplicativo());
        assertEquals(bpmEsperado6, biceps6.bpm());
    }

}
