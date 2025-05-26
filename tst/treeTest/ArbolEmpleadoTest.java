package treeTest;

import arboles.ArbolEmpleado;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * Casos de prueba para la clase ArbolEmpleado.
 * 
 * @author Aarón Rojas
 * @version 1.0
 */
public class ArbolEmpleadoTest {
    
    private ArbolEmpleado arbol;
    
    /**
     * Inicializa un árbol antes de cada test.
     */
    @Before
    public void setUp() {
        arbol = new ArbolEmpleado();
    }
    
    /**
     * Un árbol recién creado debe estar vacío.
     */
    @Test
    public void inicializarArbolVacio() {
        assertNull("Raíz debería ser null en árbol vacío", arbol.getRaiz());
        assertEquals("Tamaño debería ser 0 en árbol vacío", 0, arbol.getTamaño());
    }
    
    /**
     * Dar de alta al CEO (sin supervisor).
     */
    @Test
    public void altaCEOExitoso() {
        arbol.altaEmpleado(null, "CEO");
        assertNotNull("Raíz no debería ser null tras alta de CEO", arbol.getRaiz());
        assertEquals("Nombre de la raíz debe ser 'CEO'", "CEO", arbol.getRaiz().nombre);
        assertEquals("Tamaño debería ser 1 tras alta de CEO", 1, arbol.getTamaño());
    }
    
    /**
     * Intentar dar de alta un segundo CEO lanza excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void altaCEODuplicadoLanzaError() {
        arbol.altaEmpleado(null, "CEO");
        arbol.altaEmpleado(null, "OtroCEO");
    }
    
    /**
     * Dar de alta subordinado bajo supervisor válido.
     */
    @Test
    public void altaSubordinadoExitoso() {
        arbol.altaEmpleado(null, "CEO");
        arbol.altaEmpleado("CEO", "Jefe1");
        assertEquals("Tamaño debe ser 2 tras dar de alta subordinado", 2, arbol.getTamaño());
        assertEquals("Profundidad de 'Jefe1' debe ser 1", 1, arbol.profundidad("Jefe1"));
    }
    
    /**
     * Dar de alta subordinado bajo supervisor inexistente lanza excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void altaSubordinadoSinSupervisorLanzaError() {
        arbol.altaEmpleado(null, "CEO");
        arbol.altaEmpleado("Desconocido", "Empleado");
    }
    
    /**
     * Verifica niveles de varios empleados.
     */
    @Test
    public void profundidadVariosNiveles() {
        arbol.altaEmpleado(null, "CEO");
        arbol.altaEmpleado("CEO", "A");
        arbol.altaEmpleado("A", "B");
        assertEquals(0, arbol.profundidad("CEO"));
        assertEquals(1, arbol.profundidad("A"));
        assertEquals(2, arbol.profundidad("B"));
        assertEquals(-1, arbol.profundidad("X"));
    }
    
    /**
     * Comprueba lista de empleados sin subordinados (hojas).
     */
    @Test
    public void empleadosSinSubordinadosTest() {
        arbol.altaEmpleado(null, "CEO");
        arbol.altaEmpleado("CEO", "A");
        arbol.altaEmpleado("CEO", "B");
        arbol.altaEmpleado("A", "C");
        // Hojas esperadas: B y C
        List<String> hojas = arbol.empleadosSinSubordinados();
        assertTrue(hojas.contains("B"));
        assertTrue(hojas.contains("C"));
        assertEquals(2, hojas.size());
    }
}