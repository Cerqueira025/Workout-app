import java.time.LocalDate;
import java.util.HashMap;

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
        //Construtor vazio
        BicicletaMontanha bicicleta = new BicicletaMontanha();
        assertNotNull(bicicleta);

        // ------------------- Construtor parametrizado com Map e PlanoDeTreino ------------------- //
        // atleta Profissional
        Profissional profissional11 = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        LocalDate data1 = LocalDate.now();
        BicicletaMontanha bicicleta1 = new BicicletaMontanha("001", "Descrição", data1, 30,
                profissional11, 10.0, 500, 100.0, 12, true);

        assertEquals("001", bicicleta1.getCodigo());
        assertEquals("Descrição", bicicleta1.getDescricao());
        assertEquals(data1, bicicleta1.getData());
        assertEquals(30, bicicleta1.getDuracao());
        assertEquals(profissional11, bicicleta1.getUser());
        assertEquals(10.0, bicicleta1.getDistancia(), 0.01);
        assertEquals(500, bicicleta1.getAltimetria());
        assertEquals(100.0, bicicleta1.getVariacaoSuspensao(), 0.01);
        assertEquals(12, bicicleta1.getNumeroMudancas());
        assertTrue(bicicleta1.hasDiscoTravao());

        // atleta Amador
        Amador amador1 = new Amador("amadorId", 65, 70, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", new HashMap<>(), new PlanoDeTreino());
        LocalDate data2 = LocalDate.now();
        BicicletaMontanha bicicleta2 = new BicicletaMontanha("002", "Descrição", data2, 30,
                amador1, 10.0, 500, 100.0, 12, true);

        assertEquals("002", bicicleta2.getCodigo());
        assertEquals("Descrição", bicicleta2.getDescricao());
        assertEquals(data2, bicicleta2.getData());
        assertEquals(30, bicicleta2.getDuracao());
        assertEquals(amador1, bicicleta2.getUser());
        assertEquals(10.0, bicicleta2.getDistancia(), 0.01);
        assertEquals(500, bicicleta2.getAltimetria());
        assertEquals(100.0, bicicleta2.getVariacaoSuspensao(), 0.01);
        assertEquals(12, bicicleta2.getNumeroMudancas());
        assertTrue(bicicleta2.hasDiscoTravao());

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional = new PraticanteOcasional("praticanteId", 65, 70, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", new HashMap<>(), new PlanoDeTreino());
        LocalDate data3 = LocalDate.now();
        BicicletaMontanha bicicleta3 = new BicicletaMontanha("003", "Descrição", data3, 30,
                praticanteOcasional, 10.0, 500, 100.0, 12, true);

        assertEquals("003", bicicleta3.getCodigo());
        assertEquals("Descrição", bicicleta3.getDescricao());
        assertEquals(data3, bicicleta3.getData());
        assertEquals(30, bicicleta3.getDuracao());
        assertEquals(praticanteOcasional, bicicleta3.getUser());
        assertEquals(10.0, bicicleta3.getDistancia(), 0.01);
        assertEquals(500, bicicleta3.getAltimetria());
        assertEquals(100.0, bicicleta3.getVariacaoSuspensao(), 0.01);
        assertEquals(12, bicicleta3.getNumeroMudancas());
        assertTrue(bicicleta3.hasDiscoTravao());

        // ------------------- Construtor sem Map e PlanoDeTreino ------------------- //
        // atleta Profissional
        Profissional profissional2 = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        LocalDate data = LocalDate.now();
        BicicletaMontanha bicicleta4 = new BicicletaMontanha("001", "Descrição", data, 30,
                profissional2, 10.0, 500, 100.0, 12, true);

        assertEquals("001", bicicleta4.getCodigo());
        assertEquals("Descrição", bicicleta4.getDescricao());
        assertEquals(data, bicicleta4.getData());
        assertEquals(30, bicicleta4.getDuracao());
        assertEquals(profissional2, bicicleta4.getUser());
        assertEquals(10.0, bicicleta4.getDistancia(), 0.01);
        assertEquals(500, bicicleta4.getAltimetria());
        assertEquals(100.0, bicicleta4.getVariacaoSuspensao(), 0.01);
        assertEquals(12, bicicleta4.getNumeroMudancas());
        assertTrue(bicicleta4.hasDiscoTravao());

        // atleta Amador
        Amador amador2 = new Amador("amadorId", 65, 70, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", new HashMap<>(), new PlanoDeTreino());
        LocalDate data4 = LocalDate.now();
        BicicletaMontanha bicicleta5 = new BicicletaMontanha("005", "Descrição", data4, 30,
                amador2, 10.0, 500, 100.0, 12, true);

        assertEquals("005", bicicleta5.getCodigo());
        assertEquals("Descrição", bicicleta5.getDescricao());
        assertEquals(data4, bicicleta5.getData());
        assertEquals(30, bicicleta5.getDuracao());
        assertEquals(amador2, bicicleta5.getUser());
        assertEquals(10.0, bicicleta5.getDistancia(), 0.01);
        assertEquals(500, bicicleta5.getAltimetria());
        assertEquals(100.0, bicicleta5.getVariacaoSuspensao(), 0.01);
        assertEquals(12, bicicleta5.getNumeroMudancas());
        assertTrue(bicicleta5.hasDiscoTravao());

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("praticanteId", 65, 70, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", new HashMap<>(), new PlanoDeTreino());
        LocalDate data5 = LocalDate.now();
        BicicletaMontanha bicicleta6 = new BicicletaMontanha("006", "Descrição", data5, 30,
                praticanteOcasional2, 10.0, 500, 100.0, 12, true);

        assertEquals("006", bicicleta6.getCodigo());
        assertEquals("Descrição", bicicleta6.getDescricao());
        assertEquals(data5, bicicleta6.getData());
        assertEquals(30, bicicleta6.getDuracao());
        assertEquals(praticanteOcasional2, bicicleta6.getUser());
        assertEquals(10.0, bicicleta6.getDistancia(), 0.01);
        assertEquals(500, bicicleta6.getAltimetria());
        assertEquals(100.0, bicicleta6.getVariacaoSuspensao(), 0.01);
        assertEquals(12, bicicleta6.getNumeroMudancas());
        assertTrue(bicicleta6.hasDiscoTravao());
    }

    @Test
    public void testEquals() {
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        LocalDate data = LocalDate.now();
        BicicletaMontanha bicicleta1 = new BicicletaMontanha("001", "Descrição", data, 30,
                profissional, 10.0, 500, 100.0, 12, true);
        BicicletaMontanha bicicleta2 = new BicicletaMontanha("001", "Descrição", data, 30,
                profissional, 10.0, 500, 100.0, 12, true);
        BicicletaMontanha bicicleta3 = new BicicletaMontanha("002", "Descrição", data, 30,
                profissional, 10.0, 500, 100.0, 12, true);

        assertEquals(bicicleta1, bicicleta2);
        assertNotEquals(bicicleta1, bicicleta3);
        assertNotEquals(bicicleta2, bicicleta3);
    }

    /*@Test
    public void testClone() {
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        LocalDate data = LocalDate.now();
        BicicletaMontanha bicicleta1 = new BicicletaMontanha("001", "Descrição", data, 30,
                profissional, 10.0, 500, 100.0, 12, true);

        BicicletaMontanha bicicleta2 = bicicleta1.clone();
        assertEquals(bicicleta1, bicicleta2);
    }*/

    @Test
    public void testCalorias() {
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        LocalDate data = LocalDate.now();
        BicicletaMontanha bicicleta = new BicicletaMontanha("001", "Descrição", data, 30,
                profissional, 10.0, 500, 100.0, 12, true);

        double caloriasEsperadas;
        if (bicicleta.hasDiscoTravao()) {
            caloriasEsperadas = 0.5 * profissional.fatorMultiplicativo() * (100.0 / 2) * 12 * (profissional.getBpmMedio() / 100);
        } else {
            caloriasEsperadas = profissional.fatorMultiplicativo() * (100.0 / 2) * 12 * (profissional.getBpmMedio() / 100);
        }

        assertEquals(caloriasEsperadas, bicicleta.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        // Criar objeto para teste
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        LocalDate data = LocalDate.now();
        BicicletaMontanha bicicleta = new BicicletaMontanha("001", "Descrição", data, 30,
                profissional, 10.0, 500, 100.0, 12, true);

        // Calcular BPM esperado
        int bpmEsperado = (int) (profissional.getBpmMedio() + 20 * profissional.fatorMultiplicativo());

        // Verificar igualdade
        assertEquals(bpmEsperado, bicicleta.bpm());
    }
}
