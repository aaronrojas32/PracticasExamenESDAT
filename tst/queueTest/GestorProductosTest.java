package queueTest;

import colas.GestorProductos;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Casos de prueba para GestorProductos.
 * 
 * @author Aarón Rojas
 * @version 1.0
 */
public class GestorProductosTest {

    private GestorProductos<String> gestor;

    @Before
    public void setUp() {
        gestor = new GestorProductos<>();
    }

    /**
     * Al iniciar, debe estar vacío.
     */
    @Test
    public void gestorInicialmenteVacio() {
        assertTrue("Debe estar vacío al iniciar", gestor.isEmpty());
        assertEquals("Size debe ser 0", 0, gestor.size());
        assertNull("siguienteProducto() debe devolver null", gestor.siguienteProducto());
    }

    /**
     * No se permiten addProducto con parámetros inválidos.
     */
    @Test(expected = IllegalArgumentException.class)
    public void addProductoNullDebeFallar() {
        gestor.addProducto(null, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addProductoConCopiasCeroDebeFallar() {
        gestor.addProducto("A", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addProductoConCopiasNegativasDebeFallar() {
        gestor.addProducto("A", -3);
    }

    /**
     * Añadir productos y verificar size.
     */
    @Test
    public void addProductoValido() {
        gestor.addProducto("A", 2);
        gestor.addProducto("B", 1);
        assertFalse(gestor.isEmpty());
        assertEquals(2, gestor.size());
    }

    /**
     * siguienteProducto debe procesar en orden de menor copias.
     */
    @Test
    public void ordenPorCopiasYReinsercion() {
        gestor.addProducto("A", 2); // A:2
        gestor.addProducto("B", 1); // B:1
        // B tiene menos copias, va primero
        assertEquals("B", gestor.siguienteProducto()); // B->0, se elimina
        assertEquals(1, gestor.size()); // queda A
        // Ahora solo A con 2 copias
        assertEquals("A", gestor.siguienteProducto()); // A->1, se reinserta
        assertEquals(1, gestor.size());
        // Sigue A porque es único
        assertEquals("A", gestor.siguienteProducto()); // A->0, se elimina
        assertTrue(gestor.isEmpty());
        assertNull(gestor.siguienteProducto());
    }

    /**
     * clear vacía completamente.
     */
    @Test
    public void clearVacíaGestor() {
        gestor.addProducto("X", 3);
        gestor.addProducto("Y", 4);
        gestor.clear();
        assertTrue(gestor.isEmpty());
        assertEquals(0, gestor.size());
        assertNull(gestor.siguienteProducto());
    }
}