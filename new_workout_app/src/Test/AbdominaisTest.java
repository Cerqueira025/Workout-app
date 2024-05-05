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
import Utilizador.Utilizador;
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

        // ------------------- Construtor parametrizado com Map e PlanoDeTreino -------------------//
        // Atleta Profissional
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, plano);
        LocalDate data = LocalDate.now();
        Abdominais abdominais1 = new Abdominais("001", "Descrição", data, 30, profissional, 10, 45.0);

        assertEquals("001", abdominais1.getCodigo());
        assertEquals("Descrição", abdominais1.getDescricao());
        assertEquals(data, abdominais1.getData());
        assertEquals(30, abdominais1.getDuracao());
        assertEquals(profissional, abdominais1.getUser());
        assertEquals(10, abdominais1.getRepeticoes());
        assertEquals(45.0, abdominais1.getAmplitude(), 0.01);

        // Atleta Amador
        Amador amador = new Amador("amadorId", 70, 75, 170,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, plano);
        Abdominais abdominais2 = new Abdominais("002", "Descrição", data, 30, amador, 10, 45.0);

        assertEquals("002", abdominais2.getCodigo());
        assertEquals("Descrição", abdominais2.getDescricao());
        assertEquals(data, abdominais2.getData());
        assertEquals(30, abdominais2.getDuracao());
        assertEquals(amador, abdominais2.getUser());
        assertEquals(10, abdominais2.getRepeticoes());
        assertEquals(45.0, abdominais2.getAmplitude(), 0.01);

        // Praticante Ocasional
        PraticanteOcasional praticanteOcasional = new PraticanteOcasional("praticanteId", 65, 70, 160,
                "Nome", Genero.Feminino, "Morada", "email@example.com", "senha", atividades, plano);
        Abdominais abdominais3 = new Abdominais("003", "Descrição", data, 30, praticanteOcasional, 10, 45.0);

        assertEquals("003", abdominais3.getCodigo());
        assertEquals("Descrição", abdominais3.getDescricao());
        assertEquals(data, abdominais3.getData());
        assertEquals(30, abdominais3.getDuracao());
        assertEquals(praticanteOcasional, abdominais3.getUser());
        assertEquals(10, abdominais3.getRepeticoes());
        assertEquals(45.0, abdominais3.getAmplitude(), 0.01);
    }

    @Test
    public void testEquals() {
        // Criar objetos para teste
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Profissional profissional = new Profissional("profId", 70, 75, 170,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, plano);
        LocalDate data = LocalDate.now();
        Abdominais abdominais1 = new Abdominais("001", "Descrição", data, 30, profissional, 10, 45.0);
        Abdominais abdominais2 = new Abdominais("001", "Descrição", data, 30, profissional, 10, 45.0);
        Abdominais abdominais3 = new Abdominais("002", "Descrição", data, 30, profissional, 10, 45.0);

        // Verificar igualdade
        assertTrue(abdominais1.equals(abdominais2));
        assertFalse(abdominais1.equals(abdominais3));
        assertFalse(abdominais2.equals(abdominais3));
    }

    @Test
    public void testClone() {
        // Criar objeto para teste
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Profissional profissional = new Profissional("profId", 70, 75, 170,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, plano);
        LocalDate data = LocalDate.now();
        Abdominais abdominais1 = new Abdominais("001", "Descrição", data, 30, profissional, 10, 45.0);

        // Clonar e verificar igualdade
        Abdominais abdominais2 = abdominais1.clone();
        assertEquals(abdominais1, abdominais2);
    }

    @Test
    public void testCalorias() {
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, plano);
        LocalDate data = LocalDate.now();
        Abdominais abdominais = new Abdominais("001", "Descrição", data, 30, profissional, 10, 45.0);

        double caloriasEsperadas = profissional.fatorMultiplicativo() * (45.0 / 4) * 30 * (profissional.getBpmMedio() / 100);

        assertEquals(caloriasEsperadas, abdominais.calorias(), 0.01);
    }

    @Test
    public void testBpm() {
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano = new PlanoDeTreino();
        Profissional profissional = new Profissional("profId", 75, 80, 180,
                "Nome", Genero.Masculino, "Morada", "email@example.com", "senha", atividades, plano);
        LocalDate data = LocalDate.now();
        Abdominais abdominais = new Abdominais("001", "Descrição", data, 30, profissional, 10, 45.0);

        int bpmEsperado = (int) (profissional.getBpmMedio() + 10 * profissional.fatorMultiplicativo());

        assertEquals(bpmEsperado, abdominais.bpm());
    }
}
