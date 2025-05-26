package setTest;

import conjuntos.CompraProducto;
import conjuntos.CompraProducto.Seccion;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Pruebas unitarias para CompraProducto.
 * 
 * @author Aarón Rojas
 * @version 1.0
 */
public class CompraProductoTest {

    private Date fecha1, fecha2;
    private CompraProducto c1, c2, c3;

    @Before
    public void setUp() {
        fecha1 = new Date(1620000000000L); // 03-May-2021 01:20:00 GMT
        fecha2 = new Date(1620003600000L); // 03-May-2021 02:20:00 GMT
        c1 = new CompraProducto("clienteA", Seccion.Moda, fecha1, 100.0);
        c2 = new CompraProducto("clienteA", Seccion.Moda, fecha1, 100.0);
        c3 = new CompraProducto("clienteB", Seccion.Deportes, fecha2, 50.0);
    }

    /**
     * Constructor con parámetros válidos.
     */
    @Test
    public void constructorValido() {
        assertEquals("El idCliente debería ser 'clienteA'", "clienteA", c1.getIdCliente());
        assertEquals("La sección debería ser Seccion.Moda", Seccion.Moda, c1.getSeccion());
        assertEquals("La fecha devuelta no coincide con la esperada", fecha1, c1.getFechaYHora());
        assertEquals("El importe debería ser 100.0", 100.0, c1.getImporte(), 0.0);
    }

    /**
     * Constructor con idCliente nulo o vacío lanza excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorIdNullLanzaError() {
        new CompraProducto(null, Seccion.Casa, fecha1, 10.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorIdVacioLanzaError() {
        new CompraProducto("   ", Seccion.Casa, fecha1, 10.0);
    }

    /**
     * Constructor con sección o fecha null lanza excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorSeccionNullLanzaError() {
        new CompraProducto("c", null, fecha1, 10.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorFechaNullLanzaError() {
        new CompraProducto("c", Seccion.Salud, null, 10.0);
    }

    /**
     * Constructor con importe negativo lanza excepción.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorImporteNegativoLanzaError() {
        new CompraProducto("c", Seccion.Motor, fecha1, -5.0);
    }

    /**
     * equals y hashCode deben ser consistentes y distinguir objetos distintos.
     */
    @Test
    public void equalsYHashCodeConsistentes() {
        assertTrue("c1 y c2 deberían ser iguales", c1.equals(c2));
        assertEquals("c1 y c2 deberían tener mismo hashCode", c1.hashCode(), c2.hashCode());
        assertFalse("c1 y c3 no deberían ser iguales", c1.equals(c3));
    }

    /**
     * compareTo ordena por fecha ascendente.
     */
    @Test
    public void compareToOrdenacion() {
        List<CompraProducto> lista = Arrays.asList(c3, c1);
        Collections.sort(lista);
        assertEquals("Después de ordenar, el primer elemento debería ser c1", c1, lista.get(0));
        assertEquals("Después de ordenar, el segundo elemento debería ser c3", c3, lista.get(1));
    }

    /**
     * toString incluye todos los campos en formato legible.
     */
    @Test
    public void toStringFormato() {
        String s = c1.toString();
        assertTrue("toString debería contener el idCliente", s.contains("clienteA"));
        assertTrue("toString debería contener la sección 'Moda'", s.contains("Moda"));
        assertTrue("toString debería formatear el importe con dos decimales", s.contains("importe=100,00"));
    }
}