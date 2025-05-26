package mapas;

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

	}

	/**
	 * Registra o actualiza la nota de un estudiante en una asignatura.
	 *
	 * @param estudiante nombre del estudiante (no puede ser null)
	 * @param asignatura nombre de la asignatura (no puede ser null)
	 * @param nota       valor de la nota entre 0.0 y 10.0
	 * @throws IllegalArgumentException si alguno de los parámetros es inválido
	 */
	public void registrarNota(String estudiante, String asignatura, double nota) {

	}

	/**
	 * Calcula la media aritmética de las notas de un estudiante.
	 *
	 * @param estudiante nombre del estudiante cuyos registros se consultan
	 * @return media de sus notas
	 * @throws IllegalArgumentException si el estudiante no existe o no tiene notas
	 */
	public double mediaEstudiante(String estudiante) {
		return 0;
	}

	/**
	 * Calcula la media de una asignatura entre todos los estudiantes que la cursan.
	 *
	 * @param asignatura nombre de la asignatura
	 * @return media de las notas registradas, o 0.0 si nadie la cursa
	 */
	public double mediaAsignatura(String asignatura) {
		return 0;
	}

	/**
	 * Determina el estudiante con la media más alta entre todos los registrados.
	 *
	 * @return nombre del mejor estudiante, o null si no hay registros
	 */
	public String mejorEstudiante() {
		return null;
	}

	/**
	 * Obtiene el conjunto de estudiantes que han sacado 10.0 en una asignatura.
	 *
	 * @param asignatura nombre de la asignatura
	 * @return conjunto de estudiantes con nota perfecta (vacío si ninguno)
	 */
	public Set<String> estudiantesConNotaPerfecta(String asignatura) {
		return null;
	}

	// Métodos auxiliares para los test (no borrar)

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
	 * @param estudiante nombre del estudiante
	 * @param asignatura nombre de la asignatura
	 * @return nota registrada o null si no existe
	 */
	public Double getNota(String estudiante, String asignatura) {
		if (!notas.containsKey(estudiante)) {
			return null;
		}
		return notas.get(estudiante).get(asignatura);
	}
}