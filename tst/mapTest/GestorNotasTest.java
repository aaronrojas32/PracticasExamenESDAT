package mapTest;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.UUID;
import java.util.Set;

import org.junit.Test;

import mapas.GestorNotas;

/**
 * Conjunto de pruebas unitarias para la clase GestorNotas.
 * <p>
 * Cada prueba está documentada con Javadoc para describir su propósito,
 * los escenarios evaluados y los resultados esperados.
 */
public class GestorNotasTest {

    /**
     * Verifica que al registrar ocho notas distintas,
     * el tamaño interno del gestor corresponda exactamente a ocho entradas.
     */
    @Test
    public void registrarNotaTest() {
        GestorNotas gestor = new GestorNotas();
        String[] nombres = {"Pedro", "Pablo", "Ana", "Lucia", "Laura", "Sofia", "Fabian", "Dani"};
        String[] asignaturas = {"Algebra", "Matematicas", "Lengua", "Historia", "Fisica", "Quimica", "Filosofia", "Biologia"};
        double[] notas = {2.3, 5.4, 7.2, 9.3, 10.0, 1.5, 3.2, 5.8};

        for (int i = 0; i < nombres.length; i++) {
            gestor.registrarNota(nombres[i], asignaturas[i], notas[i]);
        }

        assertEquals(
            "Se esperaba tamaño 8 tras registrar ocho notas distintas",
            nombres.length,
            gestor.size()
        );
    }

    /**
     * Comprueba que registrar un estudiante nulo produzca IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void registrarEstudianteNuloTest() {
        new GestorNotas().registrarNota(null, "Algebra", 10.0);
    }

    /**
     * Comprueba que registrar una asignatura nula produzca IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void registrarAsignaturaNulaTest() {
        new GestorNotas().registrarNota("Pablo", null, 10.0);
    }

    /**
     * Comprueba que registrar estudiante y asignatura nulos produzca IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void registrarEstudianteYAsignaturaNulaTest() {
        new GestorNotas().registrarNota(null, null, 10.0);
    }

    /**
     * Comprueba que una nota negativa produzca IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void registrarNotaNegativaTest() {
        new GestorNotas().registrarNota("Pablo", "Matematicas", -12.1);
    }

    /**
     * Comprueba que una nota superior a 10 produzca IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void registrarNotaSuperiorADiezTest() {
        new GestorNotas().registrarNota("Fernando", "Fisica", 23.4);
    }

    /**
     * Calcula la media de una asignatura en distintos escenarios:
     * <ul>
     *   <li>Con dos estudiantes en "Matematicas" (8.0 y 6.0) y uno en "Fisica" (9.0).</li>
     *   <li>Comprueba que la media devuelta para "Matematicas" sea 7.0.</li>
     *   <li>Comprueba que la media devuelta para "Fisica" sea 9.0.</li>
     *   <li>Comprueba que la media devuelta para una asignatura sin alumnos ("Quimica") sea 0.0.</li>
     * </ul>
     */
    @Test
    public void mediaAsignaturaTest() {
        GestorNotas gestor = new GestorNotas();
        gestor.registrarNota("Ana", "Matematicas", 8.0);
        gestor.registrarNota("Pedro", "Matematicas", 6.0);
        gestor.registrarNota("Lucia", "Fisica", 9.0);

        assertEquals(7.0, gestor.mediaAsignatura("Matematicas"), 0.001);
        assertEquals(9.0, gestor.mediaAsignatura("Fisica"), 0.001);
        assertEquals(0.0, gestor.mediaAsignatura("Quimica"), 0.001);
    }

    /**
     * Determina el estudiante con la media más alta:
     * Ana (9.0), Pedro (6.0), Lucia (10.0) → debe devolver "Lucia".
     */
    @Test
    public void mejorEstudianteTest() {
        GestorNotas gestor = new GestorNotas();
        gestor.registrarNota("Ana", "Lengua", 9.0);
        gestor.registrarNota("Pedro", "Lengua", 6.0);
        gestor.registrarNota("Lucia", "Lengua", 10.0);

        assertEquals("Lucia", gestor.mejorEstudiante());
    }

    /**
     * Verifica que retorna el conjunto de estudiantes con nota perfecta (10.0):
     * <ul>
     *   <li>Fisica: Ana y Lucia tienen 10.0, Pedro 8.0 → conjunto de 2 elementos.</li>
     *   <li>Quimica: ningún alumno → conjunto vacío.</li>
     * </ul>
     */
    @Test
    public void estudiantesConNotaPerfectaTest() {
        GestorNotas gestor = new GestorNotas();
        gestor.registrarNota("Ana", "Fisica", 10.0);
        gestor.registrarNota("Pedro", "Fisica", 8.0);
        gestor.registrarNota("Lucia", "Fisica", 10.0);
        gestor.registrarNota("Carlos", "Matematicas", 10.0);

        Set<String> fisica = gestor.estudiantesConNotaPerfecta("Fisica");
        assertTrue(fisica.contains("Ana"));
        assertTrue(fisica.contains("Lucia"));
        assertFalse(fisica.contains("Pedro"));
        assertEquals(2, fisica.size());

        Set<String> quimica = gestor.estudiantesConNotaPerfecta("Quimica");
        assertTrue(quimica.isEmpty());
    }

    /**
     * Verifica que registrar dos veces la misma asignatura para un estudiante
     * sobrescribe correctamente la nota previa.
     */
    @Test
    public void sobrescrituraNotaTest() {
        GestorNotas gestor = new GestorNotas();
        gestor.registrarNota("Ana", "Historia", 6.0);
        assertEquals(6.0, gestor.getNota("Ana", "Historia"), 0.001);

        gestor.registrarNota("Ana", "Historia", 9.5);
        assertEquals(9.5, gestor.getNota("Ana", "Historia"), 0.001);
    }

    /**
     * Inserta 100 notas aleatorias siempre válidas (0.0–10.0)
     * y comprueba que la media del estudiante recién agregado coincida con la nota.
     */
    @Test
    public void randomValuesValidosTest() {
        GestorNotas gestor = new GestorNotas();
        Random rand = new Random();
        int inserts = 100;

        for (int i = 0; i < inserts; i++) {
            String est = randomId("Est-");
            String asig = randomId("Asig-");
            double nota = rand.nextDouble() * 10;
            gestor.registrarNota(est, asig, nota);
            assertEquals(nota, gestor.mediaEstudiante(est), 0.001);
        }

        assertEquals(inserts, gestor.size());
    }

    /**
     * Intenta 50 registros con parámetros aleatorios;
     * contabiliza correctamente los exitosos y
     * espera IllegalArgumentException solo cuando alguno sea inválido.
     */
    @Test
    public void randomValuesMixtosTest() {
        GestorNotas gestor = new GestorNotas();
        Random rand = new Random();
        int attempts = 50;
        int registrados = 0;

        for (int i = 0; i < attempts; i++) {
            String est = rand.nextBoolean() ? null : randomId("I-");
            String asig = rand.nextBoolean() ? null : randomId("A-");
            double nota = rand.nextDouble() * 15 - 5;

            boolean invalido = est == null || asig == null || nota < 0 || nota > 10;
            try {
                gestor.registrarNota(est, asig, nota);
                if (invalido) {
                    fail("Se esperaba IllegalArgumentException para datos inválidos");
                }
                registrados++;
                assertEquals(nota, gestor.mediaEstudiante(est), 0.001);
            } catch (IllegalArgumentException e) {
                if (!invalido) {
                    fail("No debería fallar con datos válidos");
                }
            }
        }

        assertEquals(registrados, gestor.size());
    }

    /**
     * Genera un identificador aleatorio de longitud fija con un prefijo.
     *
     * @param prefix texto inicial del identificador
     * @return cadena única compuesta de prefix más 5 caracteres aleatorios
     */
    private String randomId(String prefix) {
        return prefix + UUID.randomUUID().toString().substring(0, 5);
    }
}