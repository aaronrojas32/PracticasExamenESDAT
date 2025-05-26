package mapTest;

import mapas.GestorPrerequisitos;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Pruebas unitarias para la clase de gestorPrerequisitos.
 * <p>
 * Se valida la inserción y actualización de relaciones prerequisito,
 * así como la obtención de la cadena de cursos necesarios para cursar uno dado.
 */
public class GestorPrerequisitosTest {

    private GestorPrerequisitos gestor;

    /**
     * Se inicializa un nuevo gestor antes de cada prueba.
     */
    @Before
    public void setUp() {
        gestor = new GestorPrerequisitos();
    }

    /**
     * Sin prerequisitos registrados, la consulta de cualquier asignatura devuelve lista vacía.
     */
    @Test
    public void prereqIntermediosSinInsercionesTest() {
        List<String> prereqs = gestor.prereqIntermedios("Programación I");
        assertNotNull("La lista no debe ser null", prereqs);
        assertTrue("Sin prerequisitos registrados, la lista debe estar vacía", prereqs.isEmpty());
    }

    /**
     * Inserción lineal de prerequisitos:
     * "Introducción a la Programación" → "Estructuras de Datos" → "Algoritmos" → "Diseño de Software".
     * La consulta de prereqIntermedios("Introducción a la Programación") debe devolver en orden:
     * ["Estructuras de Datos", "Algoritmos", "Diseño de Software"].
     */
    @Test
    public void insercionLinealYConsultaTest() {
        String[] pares = {
            "Introducción a la Programación", "Estructuras de Datos",
            "Estructuras de Datos", "Algoritmos",
            "Algoritmos", "Diseño de Software"
        };
        gestor.insercionPrerequisitos(pares);

        List<String> ruta = gestor.prereqIntermedios("Introducción a la Programación");
        assertEquals("Debe haber 3 prerequisitos intermedios", 3, ruta.size());
        assertEquals("El primer prerequisito debe ser Estructuras de Datos", 
                     "Estructuras de Datos", ruta.get(0));
        assertEquals("El segundo prerequisito debe ser Algoritmos", 
                     "Algoritmos", ruta.get(1));
        assertEquals("El tercer prerequisito debe ser Diseño de Software", 
                     "Diseño de Software", ruta.get(2));
    }

    /**
     * Actualización de prerequisitos:
     * Se registra inicialmente "Matemáticas I" → "Matemáticas II".
     * Luego se actualiza "Matemáticas I" → "Cálculo I", intercambiando
     * la relación para que "Cálculo I" tenga como prerequisito "Matemáticas II".
     */
    @Test
    public void insercionActualizacionTest() {
        gestor.insercionPrerequisitos(new String[]{"Matemáticas I", "Matemáticas II"});
        List<String> rutaInicial = gestor.prereqIntermedios("Matemáticas I");
        assertEquals(1, rutaInicial.size());
        assertEquals("Matemáticas II", rutaInicial.get(0));

        gestor.insercionPrerequisitos(new String[]{"Matemáticas I", "Cálculo I"});
        List<String> rutaActualizada = gestor.prereqIntermedios("Matemáticas I");
        assertEquals(2, rutaActualizada.size());
        assertEquals("Cálculo I", rutaActualizada.get(0));
        assertEquals("Matemáticas II", rutaActualizada.get(1));
    }

    /**
     * Consulta de un nodo intermedio en la cadena:
     * Para la cadena "Física I" → "Física II" → "Óptica" → "Electrodinámica",
     * al consultar prereqIntermedios("Física II") debe devolver ["Óptica", "Electrodinámica"].
     */
    @Test
    public void consultaIntermediaTest() {
        String[] pares = {
            "Física I", "Física II",
            "Física II", "Óptica",
            "Óptica", "Electrodinámica"
        };
        gestor.insercionPrerequisitos(pares);

        List<String> ruta = gestor.prereqIntermedios("Física II");
        assertEquals(2, ruta.size());
        assertEquals("Óptica", ruta.get(0));
        assertEquals("Electrodinámica", ruta.get(1));
    }

    /**
     * Inserciones independientes en áreas distintas:
     * Cadena de Informática: "Redes" → "Seguridad Informática";
     * Cadena de Química: "Química General" → "Química Orgánica" → "Bioquímica".
     * Cada consulta debe devolver solo su propio recorrido.
     */
    @Test
    public void insercionesIndependientesTest() {
        gestor.insercionPrerequisitos(new String[]{
            "Redes", "Seguridad Informática"
        });
        gestor.insercionPrerequisitos(new String[]{
            "Química General", "Química Orgánica",
            "Química Orgánica", "Bioquímica"
        });

        List<String> rutaInfo = gestor.prereqIntermedios("Redes");
        assertArrayEquals(new String[]{"Seguridad Informática"}, rutaInfo.toArray());

        List<String> rutaQuim = gestor.prereqIntermedios("Química General");
        assertArrayEquals(new String[]{"Química Orgánica", "Bioquímica"}, rutaQuim.toArray());

        List<String> rutaSin = gestor.prereqIntermedios("Historia Antigua");
        assertTrue("Asignatura sin prerequisitos debe devolver lista vacía", rutaSin.isEmpty());
    }
}