package mapas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Clase para gestionar las notas de estudiantes por asignatura.
 * <p>
 * Permite registrar notas, calcular medias por estudiante o asignatura,
 * determinar el mejor estudiante y listar aquellos con nota perfecta.
 */
public class GestorNotas {

    /**
     * Mapa que asocia cada estudiante con otro mapa de asignatura → nota.
     */
    private Map<String, Map<String, Double>> notas;

    /**
     * Construye un GestorNotas sin registros previos.
     */
    public GestorNotas() {
        notas = new HashMap<>();
    }

    /**
     * Registra o actualiza la nota de un estudiante en una asignatura.
     *
     * @param estudiante  nombre del estudiante (no puede ser null)
     * @param asignatura  nombre de la asignatura (no puede ser null)
     * @param nota        valor de la nota entre 0.0 y 10.0
     * @throws IllegalArgumentException si alguno de los parámetros es inválido
     */
    public void registrarNota(String estudiante, String asignatura, double nota) {
        if (estudiante == null || asignatura == null || nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Alguno de los valores no es valido.");
        }
        if (!notas.containsKey(estudiante)) {
            notas.put(estudiante, new HashMap<>());
        }
        notas.get(estudiante).put(asignatura, nota);
    }

    /**
     * Calcula la media aritmética de las notas de un estudiante.
     *
     * @param estudiante  nombre del estudiante cuyos registros se consultan
     * @return media de sus notas
     * @throws IllegalArgumentException si el estudiante no existe o no tiene notas
     */
    public double mediaEstudiante(String estudiante) {
        if (!notas.containsKey(estudiante) || notas.get(estudiante).isEmpty()) {
            throw new IllegalArgumentException("No existe.");
        }
        Map<String, Double> asignaturas = notas.get(estudiante);
        double suma = 0;
        for (double valor : asignaturas.values()) {
            suma += valor;
        }
        return suma / asignaturas.size();
    }

    /**
     * Calcula la media de una asignatura entre todos los estudiantes que la cursan.
     *
     * @param asignatura  nombre de la asignatura
     * @return media de las notas registradas, o 0.0 si nadie la cursa
     */
    public double mediaAsignatura(String asignatura) {
        double suma = 0;
        int cantidad = 0;
        for (Map<String, Double> mapEst : notas.values()) {
            if (mapEst.containsKey(asignatura)) {
                suma += mapEst.get(asignatura);
                cantidad++;
            }
        }
        return (cantidad == 0) ? 0.0 : suma / cantidad;
    }

    /**
     * Determina el estudiante con la media más alta entre todos los registrados.
     *
     * @return nombre del mejor estudiante, o null si no hay registros
     */
    public String mejorEstudiante() {
        String mejor = null;
        double mejorMedia = -1;
        for (String estudiante : notas.keySet()) {
            double media = mediaEstudiante(estudiante);
            if (media > mejorMedia) {
                mejorMedia = media;
                mejor = estudiante;
            }
        }
        return mejor;
    }

    /**
     * Obtiene el conjunto de estudiantes que han sacado 10.0 en una asignatura.
     *
     * @param asignatura  nombre de la asignatura
     * @return conjunto de estudiantes con nota perfecta (vacío si ninguno)
     */
    public Set<String> estudiantesConNotaPerfecta(String asignatura) {
        Set<String> resultado = new HashSet<>();
        for (Map.Entry<String, Map<String, Double>> entry : notas.entrySet()) {
            String estudiante = entry.getKey();
            Map<String, Double> mapEst = entry.getValue();
            if (mapEst.containsKey(asignatura) && mapEst.get(asignatura) == 10.0) {
                resultado.add(estudiante);
            }
        }
        return resultado;
    }

    /**
     * Número de estudiantes con al menos un registro de nota.
     *
     * @return cantidad de estudiantes registrados
     */
    public int size() {
        return notas.size();
    }

    /**
     * Recupera la nota de un estudiante en una asignatura.
     *
     * @param estudiante  nombre del estudiante
     * @param asignatura  nombre de la asignatura
     * @return nota registrada o null si no existe
     */
    public Double getNota(String estudiante, String asignatura) {
        if (!notas.containsKey(estudiante)) {
            return null;
        }
        return notas.get(estudiante).get(asignatura);
    }
}