package treeTest;

import arboles.MiArbol;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * Pruebas unitarias para la clase MiArbol.
 * 
 * @author Aarón Rojas
 * @version 1.0
 */
public class MiArbolTest {

	/**
	 * Arbol sobre el que se realizan los test.
	 */
    private MiArbol<String> arbol;

    /**
     * Configurar el arbol antes de los test.
     */
    @Before
    public void setUp() {
        arbol = new MiArbol<>();
    }

    /**
     * Test sobre un arbol vacio recien creado.
     */
    @Test
    public void inicializarUnArbolVacioTest() {
        assertNull("La raíz de un árbol vacío debería ser null", arbol.getRaiz());
        assertEquals("El tamaño de un árbol vacío debería ser 0", 0, arbol.getTamano());
    }

    /**
     * Test sobre la insercción de un nodo raiz en el arbol.
     */
    @Test
    public void agregarRaizTest() {
        arbol.addRaiz("A");
        assertNotNull("Después de addRaiz, la raíz no debería ser null", arbol.getRaiz());
        assertEquals("La raíz debería contener el dato 'A'", "A", arbol.getRaiz().dato);
        assertEquals("El tamaño debería ser 1 después de añadir la raíz", 1, arbol.getTamano());
    }

    /**
     * Test al añadir una raiz a un arbol que ya tiene una.
     */
    @Test(expected = IllegalStateException.class)
    public void agregarRaizDuplicadaTest() {
        arbol.addRaiz("A");
        arbol.addRaiz("B"); // debería arrojar IllegalStateException
    }

    /**
     * Test al añadir un nodo hijo al arbol.
     */
    @Test
    public void agregarHijoTest() {
        arbol.addRaiz("A");
        boolean added = arbol.addHijo("A", "B");
        assertTrue("addHijo debería devolver true si el padre existe", added);
        assertEquals("El tamaño debería ser 2 tras añadir un hijo", 2, arbol.getTamano());
        assertEquals("La profundidad de 'B' debería ser 1", 1, arbol.profundidad("B"));
    }

    /**
     * Test al intentar añadir un hijo a un padre que no existe.
     */
    @Test
    public void agregarHijoANoExistenteTest() {
        boolean added = arbol.addHijo("X", "Y");
        assertFalse("addHijo debería devolver false si el padre no existe", added);
        assertEquals("Tamaño no debería cambiar", 0, arbol.getTamano());
    }

    /**
     * Test de profundidad en el arbol en varios niveles.
     */
    @Test
    public void profundidadTestVariosNiveles() {
        arbol.addRaiz("A");
        arbol.addHijo("A", "B");
        arbol.addHijo("B", "C");
        arbol.addHijo("C", "D");
        assertEquals(0, arbol.profundidad("A"));
        assertEquals(1, arbol.profundidad("B"));
        assertEquals(2, arbol.profundidad("C"));
        assertEquals(3, arbol.profundidad("D"));
        assertEquals(-1, arbol.profundidad("X"));
    }

    /**
     * Test al obtener los nodos hoja del arbol.
     */
    @Test
    public void nodosHojaTest() {
        arbol.addRaiz("A");
        arbol.addHijo("A", "B");
        arbol.addHijo("A", "C");
        arbol.addHijo("B", "D");
        arbol.addHijo("B", "E");
        arbol.addHijo("C", "F");

        List<String> hojas = arbol.nodosHoja();
        // Las hojas esperadas son D, E y F
        assertTrue(hojas.contains("D"));
        assertTrue(hojas.contains("E"));
        assertTrue(hojas.contains("F"));
        assertEquals(3, hojas.size());
    }

}