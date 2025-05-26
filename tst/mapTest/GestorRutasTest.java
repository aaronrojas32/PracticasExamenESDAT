package mapTest;

import mapas.GestorRutas;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Pruebas unitarias para la gestión de rutas de autobús entre ciudades.
 * <p>
 * 
 * @author Aaron Rojas
 * @version 1.0
 */
public class GestorRutasTest {

    private GestorRutas gestor;

    /**
     * Inicializa un nuevo GestorRutas antes de cada prueba.
     */
    @Before
    public void setUp() {
        gestor = new GestorRutas();
    }

    /**
     * Sin tramos registrados, no hay ruta posible entre dos ciudades distintas.
     */
    @Test
    public void paradasIntermediasSinTramosTest() {
        List<String> ruta = gestor.paradasIntermedias("Madrid", "Barcelona");
        assertNotNull("La lista no debe ser null", ruta);
        assertTrue("Sin tramos, la lista debe estar vacía", ruta.isEmpty());
    }

    /**
     * Inserción lineal de tramos: Madrid → Barcelona → Valencia → Sevilla.
     * Al consultar desde Madrid hasta Sevilla, las paradas intermedias deben ser ["Barcelona","Valencia"].
     */
    @Test
    public void insercionLinealYConsultaTest() {
        String[] tramos = {
            "Madrid", "Barcelona",
            "Barcelona", "Valencia",
            "Valencia", "Sevilla"
        };
        gestor.inserccionTramos(tramos);

        List<String> intermedias = gestor.paradasIntermedias("Madrid", "Sevilla");
        assertEquals("Debe haber dos paradas intermedias", 2, intermedias.size());
        assertEquals("Primera parada intermedia debe ser Barcelona", "Barcelona", intermedias.get(0));
        assertEquals("Segunda parada intermedia debe ser Valencia", "Valencia", intermedias.get(1));
    }

    /**
     * Inserción con actualización de tramo: si se re-inserta un tramo con mismo origen,
     * el destino anterior debe quedar como tramo saliente del nuevo destino.
     * Ej: Madrid→Barcelona luego Madrid→Bilbao genera Madrid→Bilbao y Bilbao→Barcelona.
     */
    @Test
    public void insercionActualizacionTest() {
        gestor.inserccionTramos(new String[]{"Madrid", "Barcelona"});
        List<String> directas = gestor.paradasIntermedias("Madrid", "Barcelona");
        assertTrue("Ruta directa no tiene intermedios", directas.isEmpty());

        gestor.inserccionTramos(new String[]{"Madrid", "Bilbao"});
        // Ahora Madrid→Bilbao→Barcelona, intermedias de Madrid a Barcelona debe ser ["Bilbao"]
        List<String> ruta = gestor.paradasIntermedias("Madrid", "Barcelona");
        assertEquals(1, ruta.size());
        assertEquals("Bilbao debe ser parada intermedia", "Bilbao", ruta.get(0));
    }

    /**
     * Consulta intermedia parte de la ruta:
     * Con cadena Valencia→Alicante→Murcia→Granada,
     * al solicitar paradasIntermedias("Alicante","Granada") debe devolver ["Murcia"].
     */
    @Test
    public void consultaIntermediaTest() {
        String[] tramos = {
            "Valencia", "Alicante",
            "Alicante", "Murcia",
            "Murcia", "Granada"
        };
        gestor.inserccionTramos(tramos);

        List<String> inter = gestor.paradasIntermedias("Alicante", "Granada");
        assertEquals("Debe haber una parada intermedia", 1, inter.size());
        assertEquals("La parada intermedia debe ser Murcia", "Murcia", inter.get(0));
    }

    /**
     * Rutas independientes en distintas regiones:
     * Línea Norte: León→Oviedo→Gijón;
     * Línea Sur: Córdoba→Málaga.
     * Cada consulta debe devolver solo su recorrido intermedio,
     * y ruta entre ciudades distintas de líneas debe ser vacía.
     */
    @Test
    public void rutasIndependientesTest() {
        gestor.inserccionTramos(new String[]{
            "León", "Oviedo",
            "Oviedo", "Gijón"
        });
        gestor.inserccionTramos(new String[]{
            "Córdoba", "Málaga"
        });

        List<String> rutaNorte = gestor.paradasIntermedias("León", "Gijón");
        assertArrayEquals(new String[]{"Oviedo"}, rutaNorte.toArray());

        List<String> rutaSur = gestor.paradasIntermedias("Córdoba", "Málaga");
        assertTrue("Ruta directa Córdoba→Málaga no tiene intermedios", rutaSur.isEmpty());

        List<String> sinConexion = gestor.paradasIntermedias("León", "Málaga");
        assertTrue("Sin conexión entre León y Málaga, lista vacía", sinConexion.isEmpty());
    }
}
