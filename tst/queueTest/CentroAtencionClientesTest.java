package queueTest;

import colas.CentroAtencionClientes;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Casos de prueba para CentroAtencionClientes.
 * 
 * @author Aarón Rojas
 * @version 1.0
 */
public class CentroAtencionClientesTest {

    private CentroAtencionClientes centro;

    @Before
    public void setUp() {
        centro = new CentroAtencionClientes();
    }

    /**
     * Inicialmente no debe haber clientes.
     */
    @Test
    public void inicialmenteSinClientes() {
        assertFalse("No debe haber clientes esperando", centro.hayClientesEsperando());
        assertEquals("Size debe ser 0", 0, centro.size());
        assertNull("atenderSiguiente debe devolver null si no hay clientes", centro.atenderSiguiente());
        assertTrue("clientesEnEspera debe devolver lista vacía", centro.clientesEnEspera().isEmpty());
    }

    /**
     * nuevoCliente con nombre null lanza excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void nuevoClienteNullLanzaError() {
        centro.nuevoCliente(null);
    }

    /**
     * nuevoCliente con nombre vacío o espacios lanza excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void nuevoClienteVacioLanzaError() {
        centro.nuevoCliente("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nuevoClienteSoloEspaciosLanzaError() {
        centro.nuevoCliente("   ");
    }

    /**
     * nuevoCliente válido incrementa la cola.
     */
    @Test
    public void agregarClienteValido() {
        centro.nuevoCliente("Alice");
        assertTrue(centro.hayClientesEsperando());
        assertEquals(1, centro.size());
        List<String> espera = centro.clientesEnEspera();
        assertEquals(1, espera.size());
        assertEquals("Alice", espera.get(0));
    }

    /**
     * atenderSiguiente sigue orden FIFO.
     */
    @Test
    public void atenderSiguienteOrdenFIFO() {
        centro.nuevoCliente("Alice");
        centro.nuevoCliente("Bob");
        centro.nuevoCliente("Charlie");

        assertEquals("Alice", centro.atenderSiguiente());
        assertEquals(2, centro.size());
        assertEquals("Bob", centro.atenderSiguiente());
        assertEquals("Charlie", centro.atenderSiguiente());
        assertNull(centro.atenderSiguiente());
        assertFalse(centro.hayClientesEsperando());
    }

    /**
     * clientesEnEspera refleja el estado actual de la cola.
     */
    @Test
    public void listaClientesEnEspera() {
        centro.nuevoCliente("Alice");
        centro.nuevoCliente("Bob");
        List<String> espera1 = centro.clientesEnEspera();
        assertArrayEquals(new String[]{"Alice","Bob"}, espera1.toArray());
        centro.atenderSiguiente(); // atiende Alice
        List<String> espera2 = centro.clientesEnEspera();
        assertArrayEquals(new String[]{"Bob"}, espera2.toArray());
    }

    /**
     * clear vacía todos los clientes.
     */
    @Test
    public void clearVacíaCola() {
        centro.nuevoCliente("Alice");
        centro.nuevoCliente("Bob");
        centro.clear();
        assertEquals(0, centro.size());
        assertFalse(centro.hayClientesEsperando());
        assertTrue(centro.clientesEnEspera().isEmpty());
    }
}