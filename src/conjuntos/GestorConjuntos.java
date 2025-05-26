package conjuntos;

import java.util.Map;
import java.util.Set;

/**
 * Administra múltiples conjuntos identificados por nombre.
 * 
 * @param <E> Tipo de los elementos de los conjuntos.
 */
public class GestorConjuntos<E> {

	private Map<String, Set<E>> conjuntos;

	/**
	 * Inicializa el gestor sin conjuntos.
	 */
	public GestorConjuntos() {

	}

	/**
	 * Crea un conjunto vacío con el nombre dado.
	 * 
	 * @param nombre Nombre del conjunto; no null ni vacío.
	 * @throws IllegalArgumentException si nombre inválido o ya existe.
	 */
	public void crearConjunto(String nombre) {

	}

	/**
	 * Comprueba si existe un conjunto con el nombre dado.
	 * 
	 * @param nombre Nombre del conjunto.
	 * @return true si existe.
	 */
	public boolean existeConjunto(String nombre) {
		return false;
	}

	/**
	 * Añade un elemento a un conjunto existente.
	 * 
	 * @param nombre   Nombre del conjunto.
	 * @param elemento Elemento a añadir (puede ser null si E lo permite).
	 * @throws IllegalArgumentException si el conjunto no existe.
	 */
	public void addElemento(String nombre, E elemento) {

	}

	/**
	 * Elimina un elemento de un conjunto.
	 * 
	 * @param nombre   Nombre del conjunto.
	 * @param elemento Elemento a eliminar.
	 * @return true si estaba presente y se eliminó.
	 * @throws IllegalArgumentException si el conjunto no existe.
	 */
	public boolean removeElemento(String nombre, E elemento) {
		return false;
	}

	/**
	 * Realiza la unión de dos conjuntos existentes.
	 * 
	 * @param c1 Nombre del primer conjunto.
	 * @param c2 Nombre del segundo conjunto.
	 * @return Nuevo conjunto como unión.
	 * @throws IllegalArgumentException si algún conjunto no existe.
	 */
	public Set<E> union(String c1, String c2) {
		return null;
	}

	/**
	 * Realiza la intersección de dos conjuntos existentes.
	 * 
	 * @param c1 Nombre del primer conjunto.
	 * @param c2 Nombre del segundo conjunto.
	 * @return Nuevo conjunto como intersección.
	 * @throws IllegalArgumentException si algún conjunto no existe.
	 */
	public Set<E> interseccion(String c1, String c2) {
		return null;
	}

	// Métodos auxiliares para los test (no borrar)

	/**
	 * @return Número de conjuntos gestionados.
	 */
	public int tamaño() {
		return conjuntos.size();
	}

	/**
	 * Borra todos los conjuntos.
	 */
	public void clear() {
		conjuntos.clear();
	}

	private void validarNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("Nombre de conjunto inválido");
		}
	}
}