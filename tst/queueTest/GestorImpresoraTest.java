package queueTest;

import colas.GestorImpresora;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

/**
 * Casos de prueba para GestorImpresora.
 * 
 * @author Aarón Rojas
 * @version 1.0
 */
public class GestorImpresoraTest {

    private GestorImpresora gestor;

    /**
     * Inicializa una nueva instancia antes de cada test.
     */
    @Before
    public void setUp() {
        gestor = new GestorImpresora();
    }

    /**
     * Al iniciar, la cola debe estar vacía.
     */
    @Test
    public void inicialmenteVacio() {
        assertTrue("Debe estar vacío al iniciar", gestor.isEmpty());
        assertEquals("Size debe ser 0", 0, gestor.size());
        assertNull("siguientePagina debe devolver null si está vacío", gestor.siguientePagina());
    }

    /**
     * agregarTrabajo con id null o páginas <= 0 lanza excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void agregarTrabajoIdNullLanzaError() {
        gestor.agregarTrabajo(null, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void agregarTrabajoPaginasCeroLanzaError() {
        gestor.agregarTrabajo("T1", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void agregarTrabajoPaginasNegativasLanzaError() {
        gestor.agregarTrabajo("T1", -2);
    }

    /**
     * agregarTrabajo válido incrementa size y cambia isEmpty.
     */
    @Test
    public void agregarTrabajoValido() {
        gestor.agregarTrabajo("T1", 3);
        assertFalse(gestor.isEmpty());
        assertEquals(1, gestor.size());
        List<String> pendientes = gestor.trabajosPendientes();
        assertEquals(1, pendientes.size());
        assertEquals("T1", pendientes.get(0));
    }

    /**
     * siguientePagina imprime páginas siguiendo el orden FIFO y reingresa trabajos incompletos.
     */
    @Test
    public void siguientePaginaCicloYEliminacion() {
        // T1 con 2 páginas, T2 con 1 página
        gestor.agregarTrabajo("T1", 2);
        gestor.agregarTrabajo("T2", 1);
        // Imprime T1 page1
        assertEquals("T1", gestor.siguientePagina());
        // Cola: T2, T1(1)
        List<String> lista1 = gestor.trabajosPendientes();
        assertArrayEquals(new String[]{"T2", "T1"}, lista1.toArray());
        // Imprime T2 page1 (T2 completa)
        assertEquals("T2", gestor.siguientePagina());
        // Cola: T1(1)
        List<String> lista2 = gestor.trabajosPendientes();
        assertArrayEquals(new String[]{"T1"}, lista2.toArray());
        // Imprime T1 page2
        assertEquals("T1", gestor.siguientePagina());
        // Cola vacía
        assertTrue(gestor.isEmpty());
        assertNull(gestor.siguientePagina());
    }

    /**
     * clear elimina todos los trabajos.
     */
    @Test
    public void clearVacíaCola() {
        gestor.agregarTrabajo("T1", 1);
        gestor.agregarTrabajo("T2", 1);
        gestor.clear();
        assertTrue(gestor.isEmpty());
        assertEquals(0, gestor.size());
    }
}