package setTest;

import conjuntos.GestorConjuntos;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Pruebas para la clase GestorConjuntos.
 * 
 * @author Aarón Rojas
 * @version 1.0
 */
public class GestorConjuntosTest {

    private GestorConjuntos<Integer> gestor;

    @Before
    public void setUp() {
        gestor = new GestorConjuntos<>();
    }

    /**
     * Crear conjuntos con nombres válidos e inválidos.
     */
    @Test
    public void crearConjuntoValido() {
        gestor.crearConjunto("A");
        assertTrue(gestor.existeConjunto("A"));
        assertEquals(1, gestor.tamaño());
    }

    @Test(expected = IllegalArgumentException.class)
    public void crearConjuntoNullLanzaError() {
        gestor.crearConjunto(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void crearConjuntoVacioLanzaError() {
        gestor.crearConjunto("   ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void crearConjuntoDuplicadoLanzaError() {
        gestor.crearConjunto("X");
        gestor.crearConjunto("X");
    }

    /**
     * Añadir y eliminar elementos.
     */
    @Test
    public void addYRemoveElemento() {
        gestor.crearConjunto("C1");
        gestor.addElemento("C1", 5);
        gestor.addElemento("C1", 10);
        assertTrue(gestor.union("C1","C1").contains(5));
        assertTrue(gestor.removeElemento("C1", 5));
        assertFalse(gestor.removeElemento("C1", 99));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addElementoConjuntoInexistenteLanzaError() {
        gestor.addElemento("NoExiste", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeElementoConjuntoInexistenteLanzaError() {
        gestor.removeElemento("Nada", 1);
    }

    /**
     * Unión de conjuntos.
     */
    @Test
    public void unionConjuntos() {
        gestor.crearConjunto("A");
        gestor.crearConjunto("B");
        gestor.addElemento("A", 1);
        gestor.addElemento("A", 2);
        gestor.addElemento("B", 2);
        gestor.addElemento("B", 3);
        Set<Integer> u = gestor.union("A", "B");
        assertEquals(3, u.size());
        assertTrue(u.contains(1));
        assertTrue(u.contains(2));
        assertTrue(u.contains(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void unionConjuntoInexistenteLanzaError() {
        gestor.union("X","Y");
    }

    /**
     * Intersección de conjuntos.
     */
    @Test
    public void interseccionConjuntos() {
        gestor.crearConjunto("A");
        gestor.crearConjunto("B");
        gestor.addElemento("A", 1);
        gestor.addElemento("A", 2);
        gestor.addElemento("B", 2);
        gestor.addElemento("B", 3);
        Set<Integer> i = gestor.interseccion("A", "B");
        assertEquals(1, i.size());
        assertTrue(i.contains(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void interseccionConjuntoInexistenteLanzaError() {
        gestor.interseccion("A","Z");
    }

    /**
     * clear elimina todos los conjuntos.
     */
    @Test
    public void clearVacíaGestor() {
        gestor.crearConjunto("A");
        gestor.clear();
        assertEquals(0, gestor.tamaño());
        assertFalse(gestor.existeConjunto("A"));
    }
}