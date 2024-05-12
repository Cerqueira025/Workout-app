import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import Utilizador.TiposUtilizador.Amador;
import Utilizador.TiposUtilizador.PraticanteOcasional;
import org.junit.Test;

import Atividade.Distancia.Altimetria.BicicletaMontanha;
import Utilizador.Genero;
import Utilizador.TiposUtilizador.Profissional;

import static org.junit.Assert.*;

public class BicicletaMontanhaTest {
    public  BicicletaMontanhaTest()
    {
    }

    @Test
    public void testConstructor() {
        // ------------------- Construtor vazio ------------------- //
        BicicletaMontanha bicicleta = new BicicletaMontanha();
        assertNotNull(bicicleta);

        // ------------------- Construtor parametrizado (tipos de utilizador)  ------------------- //
        Map<String, Atividade> atividades = new HashMap<>();
        Map<String, Double> recordes = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        LocalDateTime data = LocalDateTime.now();

        // Atleta Profissional
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        BicicletaMontanha bicicleta1 = new BicicletaMontanha("001", "Descrição", data, 30, 4,
                profissional1, 10.0, 500, 100.0, 12, true);

        assertEquals("001", bicicleta1.getCodigo());
        assertEquals("Descrição", bicicleta1.getDescricao());
        assertEquals(data, bicicleta1.getData());
        assertEquals(30, bicicleta1.getDuracao());
        assertEquals(4, bicicleta1.getSeries());
        assertEquals(profissional1, bicicleta1.getUtilizador());
        assertEquals(10.0, bicicleta1.getDistancia(), 0.01);
        assertEquals(500, bicicleta1.getAltimetria());
        assertEquals(100.0, bicicleta1.getVariacaoSuspensao(), 0.01);
        assertEquals(12, bicicleta1.getNumeroMudancas());
        assertTrue(bicicleta1.getDiscoTravao());

        // Atleta Amador
        Amador amador1 = new Amador("amadorId", 65, 70, 55, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        BicicletaMontanha bicicleta2 = new BicicletaMontanha("002", "Descrição", data, 30, 3,
                amador1, 10.0, 500, 100.0, 12, true);

        assertEquals("002", bicicleta2.getCodigo());
        assertEquals("Descrição", bicicleta2.getDescricao());
        assertEquals(data, bicicleta2.getData());
        assertEquals(30, bicicleta2.getDuracao());
        assertEquals(3, bicicleta2.getSeries());
        assertEquals(amador1, bicicleta2.getUtilizador());
        assertEquals(10.0, bicicleta2.getDistancia(), 0.01);
        assertEquals(500, bicicleta2.getAltimetria());
        assertEquals(100.0, bicicleta2.getVariacaoSuspensao(), 0.01);
        assertEquals(12, bicicleta2.getNumeroMudancas());
        assertTrue(bicicleta2.getDiscoTravao());

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional = new PraticanteOcasional("praticanteId", 65, 70, 15, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, recordes, plano);
        BicicletaMontanha bicicleta3 = new BicicletaMontanha("003", "Descrição", data, 30, 2,
                praticanteOcasional, 10.0, 500, 100.0, 12, true);

        assertEquals("003", bicicleta3.getCodigo());
        assertEquals("Descrição", bicicleta3.getDescricao());
        assertEquals(data, bicicleta3.getData());
        assertEquals(30, bicicleta3.getDuracao());
        assertEquals(2, bicicleta3.getSeries());
        assertEquals(praticanteOcasional, bicicleta3.getUtilizador());
        assertEquals(10.0, bicicleta3.getDistancia(), 0.01);
        assertEquals(500, bicicleta3.getAltimetria());
        assertEquals(100.0, bicicleta3.getVariacaoSuspensao(), 0.01);
        assertEquals(12, bicicleta3.getNumeroMudancas());
        assertTrue(bicicleta3.getDiscoTravao());

        // ------------------- Construtor ------------------- //
        // Atleta Profissional
        Profissional profissional2 = new Profissional("profId", 75, 80, 22, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta4 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                profissional2, 10.0, 500, 100.0, 12, true);

        assertEquals("001", bicicleta4.getCodigo());
        assertEquals("Descrição", bicicleta4.getDescricao());
        assertEquals(data, bicicleta4.getData());
        assertEquals(30, bicicleta4.getDuracao());
        assertEquals(3, bicicleta4.getSeries());
        assertEquals(profissional2, bicicleta4.getUtilizador());
        assertEquals(10.0, bicicleta4.getDistancia(), 0.01);
        assertEquals(500, bicicleta4.getAltimetria());
        assertEquals(100.0, bicicleta4.getVariacaoSuspensao(), 0.01);
        assertEquals(12, bicicleta4.getNumeroMudancas());
        assertTrue(bicicleta4.getDiscoTravao());

        // Atleta Amador
        Amador amador2 = new Amador("amadorId", 65, 70, 35, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta5 = new BicicletaMontanha("005", "Descrição", data, 30, 3,
                amador2, 10.0, 500, 100.0, 12, true);

        assertEquals("005", bicicleta5.getCodigo());
        assertEquals("Descrição", bicicleta5.getDescricao());
        assertEquals(data, bicicleta5.getData());
        assertEquals(30, bicicleta5.getDuracao());
        assertEquals(3, bicicleta5.getSeries());
        assertEquals(amador2, bicicleta5.getUtilizador());
        assertEquals(10.0, bicicleta5.getDistancia(), 0.01);
        assertEquals(500, bicicleta5.getAltimetria());
        assertEquals(100.0, bicicleta5.getVariacaoSuspensao(), 0.01);
        assertEquals(12, bicicleta5.getNumeroMudancas());
        assertTrue(bicicleta5.getDiscoTravao());

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("praticanteId", 65, 70, 45, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta6 = new BicicletaMontanha("006", "Descrição", data, 30, 2,
                praticanteOcasional2, 10.0, 500, 100.0, 12, true);

        assertEquals("006", bicicleta6.getCodigo());
        assertEquals("Descrição", bicicleta6.getDescricao());
        assertEquals(data, bicicleta6.getData());
        assertEquals(30, bicicleta6.getDuracao());
        assertEquals(2, bicicleta6.getSeries());
        assertEquals(praticanteOcasional2, bicicleta6.getUtilizador());
        assertEquals(10.0, bicicleta6.getDistancia(), 0.01);
        assertEquals(500, bicicleta6.getAltimetria());
        assertEquals(100.0, bicicleta6.getVariacaoSuspensao(), 0.01);
        assertEquals(12, bicicleta6.getNumeroMudancas());
        assertTrue(bicicleta6.getDiscoTravao());
    }

    @Test
    public void testEquals() {
        Profissional profissional = new Profissional("profId", 75, 80, 15, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        LocalDateTime data = LocalDateTime.now();
        BicicletaMontanha bicicleta1 = new BicicletaMontanha("001", "Descrição", data, 30, 2,
                profissional, 10.0, 500, 100.0, 12, true);
        BicicletaMontanha bicicleta2 = new BicicletaMontanha("001", "Descrição", data, 30, 2,
                profissional, 10.0, 500, 100.0, 12, true);
        BicicletaMontanha bicicleta3 = new BicicletaMontanha("002", "Descrição", data, 30, 2,
                profissional, 10.0, 500, 100.0, 12, true);
        BicicletaMontanha bicicleta4 = new BicicletaMontanha("002", "Descrição", data, 30, 2,
                profissional, 10.0, 500, 100.0, 10, true);

        
        // --- Verificar igualdade ---
        //parâmetros iguais
        assertTrue(bicicleta1.equals(bicicleta2));
        //parâmetros diferentes
        assertFalse(bicicleta1.equals(bicicleta3)); // descricao diferente
        assertFalse(bicicleta2.equals(bicicleta4)); // descricao e numeroMudancas diferente
        assertFalse(bicicleta3.equals(bicicleta4)); // numeroMudancas diferente
    }

    @Test
    public void testToString() {
        Profissional profissional = new Profissional();
        LocalDateTime data = LocalDateTime.now();
        BicicletaMontanha bicicleta1 = new BicicletaMontanha("001", "Descrição", data, 30, 2,
                profissional, 10.0, 500, 100.0, 12, true);
        String expectedToString = "BicicletaMontanha - " +
                "código = '001', descrição = 'Descrição', data = '"+
                data + "', duração = '30', bpm médio = '0', séries = '2', calorias = '0.0', "+
                "distância = '10.0', velocidade = '20.0', altimetria = '500', "+
                "variação da suspenção = '" + bicicleta1.getVariacaoSuspensao() + '\'' +
                ", número de mudanças = '" + bicicleta1.getNumeroMudancas() + '\'' +
                ", disco travão = '" + bicicleta1.getDiscoTravao() + '\'';
        assertEquals(expectedToString, bicicleta1.toString());
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

        BicicletaMontanha bicicleta1 = new BicicletaMontanha("001", "Descrição", data, 30, 4,
                profissional, 10.0, 500, 100.0, 12, true);
        BicicletaMontanha bicicleta2 = bicicleta1.clone();

        assertEquals(bicicleta1, bicicleta2);
    }*/

    @Test
    public void testCalorias() {
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta1 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                profissional1, 10.0, 500, 100.0, 12, true);

        double caloriasEsperadas1 = 0.8 * profissional1.fatorMultiplicativo() * ((bicicleta1.getVelocidade()/2 + 1)) * ((bicicleta1.getDuracao()/100 + 1))
                * ((bicicleta1.getAltimetria()/10 + 1)) * ((bicicleta1.getVariacaoSuspensao()/20 + 1)) * ((bicicleta1.getNumeroMudancas()/4 + 1))
                * ((bicicleta1.bpm()/100 + 1)) * bicicleta1.getSeries() * 0.5;
        assertEquals(caloriasEsperadas1, bicicleta1.calorias(), 0.01);

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta2 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                profissional2, 10.0, 500, 100.0, 12, false);

        double caloriasEsperadas2 = profissional2.fatorMultiplicativo() * ((bicicleta2.getVelocidade()/2 + 1)) * ((bicicleta2.getDuracao()/100 + 1))
                * ((bicicleta2.getAltimetria()/10 + 1)) * ((bicicleta2.getVariacaoSuspensao()/20 + 1)) * ((bicicleta2.getNumeroMudancas()/4 + 1))
                * ((bicicleta2.bpm()/100 + 1)) * bicicleta2.getSeries() * 0.5;
        assertEquals(caloriasEsperadas2, bicicleta2.calorias(), 0.01);

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta3 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                praticanteOcasional1, 10.0, 500, 100.0, 12, true);

        double caloriasEsperadas3 = 0.8 * praticanteOcasional1.fatorMultiplicativo() * ((bicicleta3.getVelocidade()/2 + 1)) * ((bicicleta3.getDuracao()/100 + 1))
                * ((bicicleta3.getAltimetria()/10 + 1)) * ((bicicleta3.getVariacaoSuspensao()/20 + 1)) * ((bicicleta3.getNumeroMudancas()/4 + 1))
                * ((bicicleta3.bpm()/100 + 1)) * bicicleta3.getSeries() * 0.5;
        assertEquals(caloriasEsperadas3, bicicleta3.calorias(), 0.01);

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta4 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                praticanteOcasional2, 10.0, 500, 100.0, 12, false);

        double caloriasEsperadas4 = praticanteOcasional2.fatorMultiplicativo() * ((bicicleta4.getVelocidade()/2 + 1)) * ((bicicleta4.getDuracao()/100 + 1))
                * ((bicicleta4.getAltimetria()/10 + 1)) * ((bicicleta4.getVariacaoSuspensao()/20 + 1)) * ((bicicleta4.getNumeroMudancas()/4 + 1))
                * ((bicicleta4.bpm()/100 + 1)) * bicicleta4.getSeries() * 0.5;
        assertEquals(caloriasEsperadas4, bicicleta4.calorias(), 0.01);


        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta5 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                amador1, 10.0, 500, 100.0, 12, true);

        double caloriasEsperadas5 = 0.8 * amador1.fatorMultiplicativo() * ((bicicleta5.getVelocidade()/2 + 1)) * ((bicicleta5.getDuracao()/100 + 1))
                * ((bicicleta5.getAltimetria()/10 + 1)) * ((bicicleta5.getVariacaoSuspensao()/20 + 1)) * ((bicicleta5.getNumeroMudancas()/4 + 1))
                * ((bicicleta5.bpm()/100 + 1)) * bicicleta5.getSeries() * 0.5;
        assertEquals(caloriasEsperadas5, bicicleta5.calorias(), 0.01);

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta6 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                amador2, 10.0, 500, 100.0, 12, false);

        double caloriasEsperadas6 = amador2.fatorMultiplicativo() * ((bicicleta6.getVelocidade()/2 + 1)) * ((bicicleta6.getDuracao()/100 + 1))
                * ((bicicleta6.getAltimetria()/10 + 1)) * ((bicicleta6.getVariacaoSuspensao()/20 + 1)) * ((bicicleta6.getNumeroMudancas()/4 + 1))
                * ((bicicleta6.bpm()/100 + 1)) * bicicleta6.getSeries() * 0.5;
        assertEquals(caloriasEsperadas6, bicicleta6.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        LocalDateTime data = LocalDateTime.now();

        // Profissional
        //teste 1
        Profissional profissional1 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta1 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                profissional1, 10.0, 500, 100.0, 12, true);

        int bpmEsperado1 = (int) (profissional1.getBpmMedio() + 20 * profissional1.fatorMultiplicativo());
        assertEquals(bpmEsperado1, bicicleta1.bpm());

        //teste 2
        Profissional profissional2 = new Profissional("profId", 75, 80, 45, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta2 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                profissional2, 10.0, 500, 100.0, 12, false);

        int bpmEsperado2 = (int) (profissional2.getBpmMedio() + 20 * profissional2.fatorMultiplicativo());
        assertEquals(bpmEsperado2, bicicleta2.bpm());

        //Praticante Ocasional
        //teste 1
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta3 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                praticanteOcasional1, 10.0, 500, 100.0, 12, true);

        int bpmEsperado3 = (int) (praticanteOcasional1.getBpmMedio() + 20 * praticanteOcasional1.fatorMultiplicativo());
        assertEquals(bpmEsperado3, bicicleta3.bpm());

        //teste 2
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta4 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                praticanteOcasional2, 10.0, 500, 100.0, 12, false);

        int bpmEsperado4 = (int) (praticanteOcasional2.getBpmMedio() + 20 * praticanteOcasional2.fatorMultiplicativo());
        assertEquals(bpmEsperado4, bicicleta4.bpm());


        //Amador
        //teste 1
        Amador amador1 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta5 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                amador1, 10.0, 500, 100.0, 12, true);

        int bpmEsperado5 = (int) (amador1.getBpmMedio() + 20 * amador1.fatorMultiplicativo());
        assertEquals(bpmEsperado5, bicicleta5.bpm());

        //teste 2
        Amador amador2 = new Amador("profId", 75, 80, 44, 180,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha");
        BicicletaMontanha bicicleta6 = new BicicletaMontanha("001", "Descrição", data, 30, 3,
                amador2, 10.0, 500, 100.0, 12, false);

        int bpmEsperado6 = (int) (amador2.getBpmMedio() + 20 * amador2.fatorMultiplicativo());
        assertEquals(bpmEsperado6, bicicleta6.bpm());
    }
}
