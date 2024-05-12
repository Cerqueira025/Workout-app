import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import Utilizador.TiposUtilizador.Amador;
import Utilizador.TiposUtilizador.PraticanteOcasional;
import org.junit.Test;

import Atividade.Distancia.Altimetria.TrailMontanha;
import Utilizador.Genero;
import Utilizador.TiposUtilizador.Profissional;

import static org.junit.Assert.*;

public class TrailMontanhaTest {
    public  TrailMontanhaTest()
    {
    }

    @Test
    public void testConstructor() {
        // ------------------- Construtor vazio ------------------- //
        TrailMontanha trail = new TrailMontanha();
        assertNotNull(trail);

        // ------------------- Construtor parametrizado (tipos de utilizador)  ------------------- //
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        // Atleta Profissional
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        TrailMontanha trail1 = new TrailMontanha("001", "Descrição", data, 30, 4,
                profissional1, 10.0, 500, true);

        assertEquals("001", trail1.getCodigo());
        assertEquals("Descrição", trail1.getDescricao());
        assertEquals(data, trail1.getData());
        assertEquals(30, trail1.getDuracao());
        assertEquals(4, trail1.getSeries());
        assertEquals(profissional1, trail1.getUtilizador());
        assertEquals(10.0, trail1.getDistancia(), 0.01);
        assertEquals(500, trail1.getAltimetria());
        assertTrue(trail1.getBastoes());

        // Atleta Amador
        Amador amador1 = new Amador("amadorId", 65, 70, 55, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        TrailMontanha trail2 = new TrailMontanha("002", "Descrição", data, 30, 3,
                amador1, 10.0, 500, true);

        assertEquals("002", trail2.getCodigo());
        assertEquals("Descrição", trail2.getDescricao());
        assertEquals(data, trail2.getData());
        assertEquals(30, trail2.getDuracao());
        assertEquals(3, trail2.getSeries());
        assertEquals(amador1, trail2.getUtilizador());
        assertEquals(10.0, trail2.getDistancia(), 0.01);
        assertEquals(500, trail2.getAltimetria());
        assertTrue(trail2.getBastoes());

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional = new PraticanteOcasional("praticanteId", 65, 70, 15, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        TrailMontanha trail3 = new TrailMontanha("003", "Descrição", data, 30, 2,
                praticanteOcasional, 10.0, 500, true);

        assertEquals("003", trail3.getCodigo());
        assertEquals("Descrição", trail3.getDescricao());
        assertEquals(data, trail3.getData());
        assertEquals(30, trail3.getDuracao());
        assertEquals(2, trail3.getSeries());
        assertEquals(praticanteOcasional, trail3.getUtilizador());
        assertEquals(10.0, trail3.getDistancia(), 0.01);
        assertEquals(500, trail3.getAltimetria());
        assertTrue(trail3.getBastoes());

        // ------------------- Construtor ------------------- //
        // Atleta Profissional
        Profissional profissional2 = new Profissional("profId", 75, 80, 22, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        TrailMontanha trail4 = new TrailMontanha("001", "Descrição", data, 30, 3,
                profissional2, 10.0, 500, true);

        assertEquals("001", trail4.getCodigo());
        assertEquals("Descrição", trail4.getDescricao());
        assertEquals(data, trail4.getData());
        assertEquals(30, trail4.getDuracao());
        assertEquals(3, trail4.getSeries());
        assertEquals(profissional2, trail4.getUtilizador());
        assertEquals(10.0, trail4.getDistancia(), 0.01);
        assertEquals(500, trail4.getAltimetria());;
        assertTrue(trail4.getBastoes());

        // Atleta Amador
        Amador amador2 = new Amador("amadorId", 65, 70, 35, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        TrailMontanha trail5 = new TrailMontanha("005", "Descrição", data, 30, 3,
                amador2, 10.0, 500, true);

        assertEquals("005", trail5.getCodigo());
        assertEquals("Descrição", trail5.getDescricao());
        assertEquals(data, trail5.getData());
        assertEquals(30, trail5.getDuracao());
        assertEquals(3, trail5.getSeries());
        assertEquals(amador2, trail5.getUtilizador());
        assertEquals(10.0, trail5.getDistancia(), 0.01);
        assertEquals(500, trail5.getAltimetria());
        assertTrue(trail5.getBastoes());

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("praticanteId", 65, 70, 45, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        TrailMontanha trail6 = new TrailMontanha("006", "Descrição", data, 30, 2,
                praticanteOcasional2, 10.0, 500, true);

        assertEquals("006", trail6.getCodigo());
        assertEquals("Descrição", trail6.getDescricao());
        assertEquals(data, trail6.getData());
        assertEquals(30, trail6.getDuracao());
        assertEquals(2, trail6.getSeries());
        assertEquals(praticanteOcasional2, trail6.getUtilizador());
        assertEquals(10.0, trail6.getDistancia(), 0.01);
        assertEquals(500, trail6.getAltimetria());
        assertTrue(trail6.getBastoes());
    }

    @Test
    public void testEquals() {
        Profissional profissional = new Profissional("profId", 75, 80, 15, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        LocalDateTime data = LocalDateTime.now();
        TrailMontanha trail1 = new TrailMontanha("001", "Descrição", data, 30, 2,
                profissional, 10.0, 500, true);
        TrailMontanha trail2 = new TrailMontanha("001", "Descrição", data, 30, 2,
                profissional, 10.0, 500, true);
        TrailMontanha trail3 = new TrailMontanha("002", "Descrição", data, 30, 2,
                profissional, 10.0, 500, true);
        TrailMontanha trail4 = new TrailMontanha("002", "Descrição", data, 30, 2,
                profissional, 10.0, 500, false);


        // --- Verificar igualdade ---
        //parâmetros iguais
        assertTrue(trail1.equals(trail2));
        //parâmetros diferentes
        assertFalse(trail1.equals(trail3)); // descricao diferente
        assertFalse(trail2.equals(trail4)); // descricao e numeroMudancas diferente
        assertFalse(trail3.equals(trail4)); // numeroMudancas diferente
    }

    @Test
    public void testToString() {
        Profissional profissional = new Profissional();
        LocalDateTime data = LocalDateTime.now();
        TrailMontanha trail1 = new TrailMontanha("001", "Descrição", data, 30, 2,
                profissional, 10.0, 500, true);
        String expectedToString = "TrailMontanha - " +
                "código = '001', descrição = 'Descrição', data = '"+
                data + "', duração = '30', bpm médio = '0', séries = '2', calorias = '0.0', "+
                "distância = '10.0', velocidade = '20.0', altimetria = '500'"+
                ", bastões = '" + trail1.getBastoes() + '\'';
        assertEquals(expectedToString, trail1.toString());
    }

    /*@Test
    public void testClone() {
        // Criar objeto para teste
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        Profissional profissional = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);

        TrailMontanha trail1 = new TrailMontanha("001", "Descrição", data, 30, 4,
                profissional, 10.0, 500, 100.0, 12, true);
        TrailMontanha trail2 = trail1.clone();

        assertEquals(trail1, trail2);
    }*/

    @Test
    public void testCalorias() {
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        TrailMontanha trail1 = new TrailMontanha("001", "Descrição", data, 30, 3,
                profissional1, 10.0, 500, true);

        double caloriasEsperadas1 = 0.8 * profissional1.fatorMultiplicativo() * ((trail1.getVelocidade()/2 + 1)) * ((trail1.getDuracao()/60 + 1))
                * ((trail1.getAltimetria()/10 + 1)) * ((trail1.bpm()/100 + 1)) * trail1.getSeries() * 0.5;
        assertEquals(caloriasEsperadas1, trail1.calorias(), 0.01);

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        TrailMontanha trail2 = new TrailMontanha("001", "Descrição", data, 30, 3,
                profissional2, 10.0, 500, false);

        double caloriasEsperadas2 = profissional2.fatorMultiplicativo() * ((trail2.getVelocidade()/2 + 1)) * ((trail2.getDuracao()/60 + 1))
                * ((trail2.getAltimetria()/10 + 1)) * ((trail2.bpm()/100 + 1)) * trail2.getSeries() * 0.5;
        assertEquals(caloriasEsperadas2, trail2.calorias(), 0.01);

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        TrailMontanha trail3 = new TrailMontanha("001", "Descrição", data, 30, 3,
                praticanteOcasional1, 10.0, 500, true);

        double caloriasEsperadas3 = 0.8 * praticanteOcasional1.fatorMultiplicativo() * ((trail3.getVelocidade()/2 + 1)) * ((trail3.getDuracao()/60 + 1))
                * ((trail3.getAltimetria()/10 + 1)) * ((trail3.bpm()/100 + 1)) * trail3.getSeries() * 0.5;
        assertEquals(caloriasEsperadas3, trail3.calorias(), 0.01);

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        TrailMontanha trail4 = new TrailMontanha("001", "Descrição", data, 30, 3,
                praticanteOcasional2, 10.0, 500, false);

        double caloriasEsperadas4 = praticanteOcasional2.fatorMultiplicativo() * ((trail4.getVelocidade()/2 + 1)) * ((trail4.getDuracao()/60 + 1))
                * ((trail4.getAltimetria()/10 + 1)) * ((trail4.bpm()/100 + 1)) * trail4.getSeries() * 0.5;
        assertEquals(caloriasEsperadas4, trail4.calorias(), 0.01);


        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        TrailMontanha trail5 = new TrailMontanha("001", "Descrição", data, 30, 3,
                amador1, 10.0, 500, true);

        double caloriasEsperadas5 = 0.8 * amador1.fatorMultiplicativo() * ((trail5.getVelocidade()/2 + 1)) * ((trail5.getDuracao()/60 + 1))
                * ((trail5.getAltimetria()/10 + 1)) * ((trail5.bpm()/100 + 1)) * trail5.getSeries() * 0.5;
        assertEquals(caloriasEsperadas5, trail5.calorias(), 0.01);

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        TrailMontanha trail6 = new TrailMontanha("001", "Descrição", data, 30, 3,
                amador2, 10.0, 500, false);

        double caloriasEsperadas6 = amador2.fatorMultiplicativo() * ((trail6.getVelocidade()/2 + 1)) * ((trail6.getDuracao()/60 + 1))
                * ((trail6.getAltimetria()/10 + 1))  * ((trail6.bpm()/100 + 1)) * trail6.getSeries() * 0.5;
        assertEquals(caloriasEsperadas6, trail6.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        TrailMontanha trail1 = new TrailMontanha("001", "Descrição", data, 30, 3,
                profissional1, 10.0, 500, true);

        int bpmEsperado1 = (int) (profissional1.getBpmMedio() + 15 * profissional1.fatorMultiplicativo());
        assertEquals(bpmEsperado1, trail1.bpm());

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        TrailMontanha trail2 = new TrailMontanha("001", "Descrição", data, 30, 3,
                profissional2, 10.0, 500, false);

        int bpmEsperado2 = (int) (profissional2.getBpmMedio() + 15 * profissional2.fatorMultiplicativo());
        assertEquals(bpmEsperado2, trail2.bpm());

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        TrailMontanha trail3 = new TrailMontanha("001", "Descrição", data, 30, 3,
                praticanteOcasional1, 10.0, 500, true);

        int bpmEsperado3 = (int) (praticanteOcasional1.getBpmMedio() + 15 * praticanteOcasional1.fatorMultiplicativo());
        assertEquals(bpmEsperado3, trail3.bpm());

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        TrailMontanha trail4 = new TrailMontanha("001", "Descrição", data, 30, 3,
                praticanteOcasional2, 10.0, 500, false);

        int bpmEsperado4 = (int) (praticanteOcasional2.getBpmMedio() + 15 * praticanteOcasional2.fatorMultiplicativo());
        assertEquals(bpmEsperado4, trail4.bpm());


        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        TrailMontanha trail5 = new TrailMontanha("001", "Descrição", data, 30, 3,
                amador1, 10.0, 500, true);

        int bpmEsperado5 = (int) (amador1.getBpmMedio() + 15 * amador1.fatorMultiplicativo());
        assertEquals(bpmEsperado5, trail5.bpm());

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        TrailMontanha trail6 = new TrailMontanha("001", "Descrição", data, 30, 3,
                amador2, 10.0, 500, false);

        int bpmEsperado6 = (int) (amador2.getBpmMedio() + 15 * amador2.fatorMultiplicativo());
        assertEquals(bpmEsperado6, trail6.bpm());
    }
}
