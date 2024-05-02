import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
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
        Map<String, Atividade> atividades11 = new HashMap<>();
        PlanoDeTreino plano11 = new PlanoDeTreino();
        Profissional profissional1 = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades11, plano11);
        LocalDate data1 = LocalDate.now();
        Supino supino1 = new Supino("001", "Descrição", data1, 30, profissional1, 10, 50, 45.0);

        assertEquals("001", supino1.getCodigo());
        assertEquals("Descrição", supino1.getDescricao());
        assertEquals(data1, supino1.getData());
        assertEquals(30, supino1.getDuracao());
        assertEquals(profissional1, supino1.getUser());
        assertEquals(10, supino1.getRepeticoes());
        assertEquals(50, supino1.getPeso(), 0.01);
        assertEquals(45.0, supino1.getInclinacao(), 0.01);

        //atleta Amador
        Map<String, Atividade> atividades12 = new HashMap<>();
        PlanoDeTreino plano12 = new PlanoDeTreino();
        Amador amador1 = new Amador("amadorId", 70, 75, 170,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades12, plano12);
        LocalDate data = LocalDate.now();
        Supino supino12 = new Supino("001", "Descrição", data, 30, amador1, 10, 50, 45.0);

        assertEquals("001", supino12.getCodigo());
        assertEquals("Descrição", supino12.getDescricao());
        assertEquals(data, supino12.getData());
        assertEquals(30, supino12.getDuracao());
        assertEquals(amador1, supino12.getUser());
        assertEquals(10, supino12.getRepeticoes());
        assertEquals(50, supino12.getPeso(), 0.01);
        assertEquals(45.0, supino12.getInclinacao(), 0.01);

        // Praticante Ocasional
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano13 = new PlanoDeTreino();
        PraticanteOcasional praticanteOcasional1 = new PraticanteOcasional("praticanteId", 65, 70, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, plano13);
        LocalDate data13 = LocalDate.now();
        Supino supino13 = new Supino("001", "Descrição", data13, 30, praticanteOcasional1, 10, 50, 45.0);

        assertEquals("001", supino13.getCodigo());
        assertEquals("Descrição", supino13.getDescricao());
        assertEquals(data13, supino13.getData());
        assertEquals(30, supino13.getDuracao());
        assertEquals(praticanteOcasional1, supino13.getUser());
        assertEquals(10, supino13.getRepeticoes());
        assertEquals(50, supino13.getPeso(), 0.01);
        assertEquals(45.0, supino13.getInclinacao(), 0.01);

        // ------------------- Construtor sem Map e PlanoDeTreino ------------------- //
        // atleta Profissional
        Profissional profissional2 = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha");
        LocalDate data21 = LocalDate.now();
        Supino supino21 = new Supino("001", "Descrição", data21, 30, profissional2, 10, 50, 45.0);

        assertEquals("001", supino21.getCodigo());
        assertEquals("Descrição", supino21.getDescricao());
        assertEquals(data21, supino21.getData());
        assertEquals(30, supino21.getDuracao());
        assertEquals(profissional2, supino21.getUser());
        assertEquals(10, supino21.getRepeticoes());
        assertEquals(50, supino21.getPeso(), 0.01);
        assertEquals(45.0, supino21.getInclinacao(), 0.01);

        //atleta Amador
        Amador amador2 = new Amador("amadorId", 70, 75, 170,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades12, plano12);
        LocalDate data22 = LocalDate.now();
        Supino supino22 = new Supino("001", "Descrição", data22, 30, amador2, 10, 50, 45.0);

        assertEquals("001", supino22.getCodigo());
        assertEquals("Descrição", supino22.getDescricao());
        assertEquals(data22, supino22.getData());
        assertEquals(30, supino22.getDuracao());
        assertEquals(amador2, supino22.getUser());
        assertEquals(10, supino22.getRepeticoes());
        assertEquals(50, supino22.getPeso(), 0.01);
        assertEquals(45.0, supino22.getInclinacao(), 0.01);

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional2 = new PraticanteOcasional("praticanteId", 65, 70, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, plano13);
        LocalDate data23 = LocalDate.now();
        Supino supino23 = new Supino("001", "Descrição", data13, 30, praticanteOcasional2, 10, 50, 45.0);

        assertEquals("001", supino23.getCodigo());
        assertEquals("Descrição", supino23.getDescricao());
        assertEquals(data23, supino23.getData());
        assertEquals(30, supino23.getDuracao());
        assertEquals(praticanteOcasional2, supino23.getUser());
        assertEquals(10, supino23.getRepeticoes());
        assertEquals(50, supino23.getPeso(), 0.01);
        assertEquals(45.0, supino23.getInclinacao(), 0.01);
    }

    @Test
    public void testEquals() {
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, plano);
        LocalDate data = LocalDate.now();
        Supino supino1 = new Supino("001", "Descrição", data, 30, profissional, 10, 50, 45.0);
        Supino supino2 = new Supino("001", "Descrição", data, 30, profissional, 10, 50, 45.0);
        Supino supino3 = new Supino("002", "Descrição", data, 30, profissional, 10, 50, 45.0);

        assertTrue(supino1.equals(supino2));
        assertFalse(supino1.equals(supino3));
        assertFalse(supino2.equals(supino3));
    }

    @Test
    public void testClone() {
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, plano);
        LocalDate data = LocalDate.now();
        Supino supino1 = new Supino("001", "Descrição", data, 30, profissional, 10, 50, 45.0);
        Supino supino2 = supino1.clone();

        assertEquals(supino1, supino2);
    }

    @Test
    public void testCalorias() {
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, plano);
        LocalDate data = LocalDate.now();
        Supino supino = new Supino("001", "Descrição", data, 30, profissional, 10, 50, 45.0);

        double caloriasEsperadas = profissional.fatorMultiplicativo() * (45.0/3) * 30 * (profissional.getBpmMedio()/100);

        assertEquals(caloriasEsperadas, supino.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, plano);
        LocalDate data = LocalDate.now();
        Supino supino = new Supino("001", "Descrição", data, 30, profissional, 10, 50, 45.0);

        int bpmEsperado = (int) (profissional.getBpmMedio() + 10 * profissional.fatorMultiplicativo());

        assertEquals(bpmEsperado, supino.bpm());
    }

}
