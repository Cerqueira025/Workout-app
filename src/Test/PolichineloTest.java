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

import Atividade.Repeticoes.Polichinelo;
public class PolichineloTest {
    public PolichineloTest()
    {
    }

    @Test
    public void testConstructor() {
        // ------------------- Construtor vazio ------------------- //
        Polichinelo polichinelo = new Polichinelo();
        assertTrue(polichinelo != null);

        // ------------------- Construtor parametrizado (tipos de utilizador) -------------------//
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Map<String, Double> recordes = new HashMap<>();
        LocalDateTime data = LocalDateTime.now();

        // Atleta Profissional
        Profissional profissional = new Profissional("profId", 75, 80.0, 55, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo1 = new Polichinelo("001", "Descrição", data, 30, 3, profissional, 10);

        assertEquals("001", polichinelo1.getCodigo());
        assertEquals("Descrição", polichinelo1.getDescricao());
        assertEquals(data, polichinelo1.getData());
        assertEquals(30, polichinelo1.getDuracao());
        assertEquals(3, polichinelo1.getSeries());
        assertEquals(profissional, polichinelo1.getUtilizador());
        assertEquals(10, polichinelo1.getRepeticoes());

        // Atleta Amador
        Amador amador = new Amador("amadorId", 70, 75, 100, 170,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo2 = new Polichinelo("002", "Descrição", data, 30, 3, amador, 10);

        assertEquals("002", polichinelo2.getCodigo());
        assertEquals("Descrição", polichinelo2.getDescricao());
        assertEquals(data, polichinelo2.getData());
        assertEquals(30, polichinelo2.getDuracao());
        assertEquals(3, polichinelo2.getSeries());
        assertEquals(amador, polichinelo2.getUtilizador());
        assertEquals(10, polichinelo2.getRepeticoes());

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional = new PraticanteOcasional("praticanteId", 65, 70, 90, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo3 = new Polichinelo("003", "Descrição", data, 30, 2, praticanteOcasional, 10);

        assertEquals("003", polichinelo3.getCodigo());
        assertEquals("Descrição", polichinelo3.getDescricao());
        assertEquals(data, polichinelo3.getData());
        assertEquals(30, polichinelo3.getDuracao());
        assertEquals(2, polichinelo3.getSeries());
        assertEquals(praticanteOcasional, polichinelo3.getUtilizador());
        assertEquals(10, polichinelo3.getRepeticoes());
    }

    @Test
    public void TestToString() {
        Profissional profissional = new Profissional();
        LocalDateTime data = LocalDateTime.now();
        Polichinelo polichinelo1 = new Polichinelo("001", "Descrição", data, 30, 2, profissional, 10);
        String expectedToString = "Polichinelo - " +
                "código = '001', descrição = 'Descrição', data = '"+ data +
                "', duração = '30', bpm médio = '0', séries = '2', calorias = '0.0', repetições = '10'";
        assertEquals(expectedToString, polichinelo1.toString());
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
        Polichinelo polichinelo1 = new Polichinelo("001", "Descrição", data, 30, 2, profissional, 10);
        Polichinelo polichinelo2 = new Polichinelo("001", "Descrição", data, 30, 2, profissional, 10);

        Polichinelo polichinelo3 = new Polichinelo("002", "Descrição", data, 30, 2, profissional, 10);
        Polichinelo polichinelo4 = new Polichinelo("002", "Descrição", data, 30, 2, profissional, 9);


        // --- Verificar igualdade ---
        //parâmetros iguais
        assertTrue(polichinelo1.equals(polichinelo2));
        //parâmetros diferentes
        assertFalse(polichinelo1.equals(polichinelo3));
        assertFalse(polichinelo2.equals(polichinelo4));
        assertFalse(polichinelo3.equals(polichinelo4));
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
        Polichinelo polichinelo1 = new Polichinelo("001", "Descrição", data, 30, 2, profissional, 10);

        // Clonar e verificar igualdade
        Polichinelo polichinelo2 = polichinelo1.clone();
        assertEquals(polichinelo1, polichinelo2);
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
        Polichinelo polichinelo1 = new Polichinelo("001", "Descrição", data, 30, 2, profissional1, 10);

        double caloriasEsperadas1 = profissional1.fatorMultiplicativo() * (polichinelo1.getDuracao()/2 + 1) *
        (polichinelo1.bpm()/ 100 + 1) * polichinelo1.getSeries() + polichinelo1.getRepeticoes()*2;
        assertEquals(caloriasEsperadas1, polichinelo1.calorias(), 0.01);

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 180, 170,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo2 = new Polichinelo("002", "Descrição", data, 20, 2, profissional2, 10);

        double caloriasEsperadas2 = profissional2.fatorMultiplicativo() * (polichinelo2.getDuracao()/2 + 1) *
        (polichinelo2.bpm()/ 100 + 1) * polichinelo2.getSeries() + polichinelo2.getRepeticoes()*2;
        assertEquals(caloriasEsperadas2, polichinelo2.calorias(), 0.01);

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo3 = new Polichinelo("001", "Descrição", data, 30, 2, praticanteOcasional1, 10);

        double caloriasEsperadas3 = praticanteOcasional1.fatorMultiplicativo() * (polichinelo3.getDuracao()/2 + 1) *
        (polichinelo3.bpm()/ 100 + 1) * polichinelo3.getSeries() + polichinelo3.getRepeticoes()*2;
        assertEquals(caloriasEsperadas3, polichinelo3.calorias(), 0.01);

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo4 = new Polichinelo("002", "Descrição", data, 15, 2, praticanteOcasional2, 5);

        double caloriasEsperadas4 = praticanteOcasional2.fatorMultiplicativo() * (polichinelo4.getDuracao()/2 + 1) *
        (polichinelo4.bpm()/ 100 + 1) * polichinelo4.getSeries() + polichinelo4.getRepeticoes()*2;
        assertEquals(caloriasEsperadas4, polichinelo4.calorias(), 0.01);


        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo5 = new Polichinelo("001", "Descrição", data, 30, 2, amador1, 10);

        double caloriasEsperadas5 = amador1.fatorMultiplicativo() * (polichinelo5.getDuracao()/2 + 1) *
        (polichinelo5.bpm()/ 100 + 1) * polichinelo5.getSeries() + polichinelo5.getRepeticoes()*2;
        assertEquals(caloriasEsperadas5, polichinelo5.calorias(), 0.01);


        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo6 = new Polichinelo("002", "Descrição", data, 15, 2, amador2, 5);

        double caloriasEsperadas6 = amador2.fatorMultiplicativo() * (polichinelo6.getDuracao()/2 + 1) *
        (polichinelo6.bpm()/ 100 + 1) * polichinelo6.getSeries() + polichinelo6.getRepeticoes()*2;
        assertEquals(caloriasEsperadas6, polichinelo6.calorias(), 0.01);
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
        Polichinelo polichinelo1 = new Polichinelo("001", "Descrição", data, 30, 2, profissional1, 10);

        int bpmEsperado1 = (int) (profissional1.getBpmMedio() + 20 * profissional1.fatorMultiplicativo());
        assertEquals(bpmEsperado1, polichinelo1.bpm());

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo2 = new Polichinelo("002", "Descrição", data, 15, 2, profissional2, 5);

        int bpmEsperado2 = (int) (profissional2.getBpmMedio() + 20 * profissional2.fatorMultiplicativo());
        assertEquals(bpmEsperado2, polichinelo2.bpm());


        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo3 = new Polichinelo("001", "Descrição", data, 30, 2, praticanteOcasional1, 10);

        int bpmEsperado3 = (int) (praticanteOcasional1.getBpmMedio() + 20 * praticanteOcasional1.fatorMultiplicativo());
        assertEquals(bpmEsperado3, polichinelo3.bpm());

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo4 = new Polichinelo("002", "Descrição", data, 15, 2, praticanteOcasional2, 5);

        int bpmEsperado4 = (int) (praticanteOcasional2.getBpmMedio() + 20 * praticanteOcasional2.fatorMultiplicativo());
        assertEquals(bpmEsperado4, polichinelo4.bpm());


        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo5 = new Polichinelo("001", "Descrição", data, 30, 2, amador1, 10);

        int bpmEsperado5 = (int) (amador1.getBpmMedio() + 20 * amador1.fatorMultiplicativo());
        assertEquals(bpmEsperado5, polichinelo5.bpm());

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        Polichinelo polichinelo6 = new Polichinelo("002", "Descrição", data, 15, 2, amador2, 5);

        int bpmEsperado6 = (int) (amador2.getBpmMedio() + 20 * amador2.fatorMultiplicativo());
        assertEquals(bpmEsperado6, polichinelo6.bpm());
    }
}
