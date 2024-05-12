import Atividade.Atividade;
import PlanoTreino.PlanoDeTreino;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PlanoDeTreinoTest {
    public PlanoDeTreinoTest()
    {
    }

    @Test
    public void testConstructor(){
        //Construtor vazio
        PlanoDeTreino plano1 = new PlanoDeTreino();
        assertTrue(plano1 != null);

        //
        // Construtor parametrizado com mapa de atividades
        Map<String, Atividade> atividades = new HashMap<>();
        PlanoDeTreino plano2 = new PlanoDeTreino(LocalDate.now(), 12, atividades);
        assertTrue(plano2 != null);
        assertEquals(LocalDate.now(), plano2.getDataRealizacao());
        assertEquals(12, plano2.getDuracao());
        assertEquals(atividades, plano2.getAtividades());

        // Construtor parametrizado sem mapa de atividades
        PlanoDeTreino plano3 = new PlanoDeTreino(LocalDate.now(), 8);
        assertTrue(plano3 != null);
        assertEquals(LocalDate.now(), plano3.getDataRealizacao());
        assertEquals(8, plano3.getDuracao());
        assertTrue(plano3.getAtividades().isEmpty());

        // Construtor de cópia
        PlanoDeTreino plano4 = new PlanoDeTreino(plano2);
        assertTrue(plano4 != null);
        assertEquals(plano2.getDataRealizacao(), plano4.getDataRealizacao());
        assertEquals(plano2.getDuracao(), plano4.getDuracao());
        assertEquals(plano2.getAtividades(), plano4.getAtividades());
    }

    @Test
    public void testSettersAndGetters() {
        PlanoDeTreino plano = new PlanoDeTreino();

        // Teste setters
        LocalDate dataRealizacao = LocalDate.of(2022, 4, 30);
        plano.setDataRealizacao(dataRealizacao);
        assertEquals(dataRealizacao, plano.getDataRealizacao());

        plano.setDuracao(10);
        assertEquals(10, plano.getDuracao());

        // Teste getAtividade (ESTE TESTE JÁ NÃO SERÁ NECESSÁRIO)
        assertNull(plano.getAtividade("001")); // Verifica se a atividade foi removida corretamente
    }

    @Test
    public void testClone() {
        PlanoDeTreino plano1 = new PlanoDeTreino(LocalDate.now(), 12);
        PlanoDeTreino clone = plano1.clone();
        assertEquals(plano1.getDataRealizacao(), clone.getDataRealizacao());
        assertEquals(plano1.getDuracao(), clone.getDuracao());
        assertTrue(plano1.getAtividades().isEmpty());
        assertTrue(clone.getAtividades().isEmpty());
    }

    @Test
    public void testEquals() {
        PlanoDeTreino plano1 = new PlanoDeTreino(LocalDate.now(), 12);
        PlanoDeTreino plano2 = new PlanoDeTreino(LocalDate.now(), 12);
        PlanoDeTreino plano3 = new PlanoDeTreino(LocalDate.of(2023, 5, 1), 12);
        PlanoDeTreino plano4 = new PlanoDeTreino(LocalDate.now(), 10);

        assertTrue(plano1.equals(plano2));
        assertTrue(plano2.equals(plano1));

        assertTrue(plano1.equals(plano1));
        assertTrue(plano2.equals(plano2));

        assertFalse(plano2.equals(plano3));
        assertFalse(plano1.equals(plano3));
        assertFalse(plano1.equals(plano4));

        // Verifica se o plano não é igual a null
        assertFalse(plano1.equals(null));
    }
}
