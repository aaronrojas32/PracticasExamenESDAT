package arboles;

import java.util.ArrayList;
import java.util.List;

public class MiArbol<E> {

	public class Nodo<E> {
		public E dato;
		public List<Nodo<E>> hijos;

		public Nodo(E dato) {
			this.dato = dato;
			this.hijos = new ArrayList<>();
		}
	}

	private Nodo<E> raiz;
	private int tamano;

	/**
	 * Constructor que inizializa un arbol vacío.
	 */
	public MiArbol() {
	}

	/**
	 * Agrega un nodo raíz al árbol. Sólo si está vacío.
	 */
	public void addRaiz(E elemento) {

	}

	/**
	 * Agrega un nuevo elemento como hijo del nodo que contiene 'padre'.
	 * 
	 * @param padre dato del nodo padre donde añadir el hijo.
	 * @param hijo  dato del nuevo nodo a añadir.
	 * @return true si se añadió correctamente, false si padre no existe.
	 */
	public boolean addHijo(E padre, E hijo) {
		return false;
	}

	public int profundidad(E elemento) {
		return 0;
	}

	public List<E> nodosHoja() {
		return null;
	}

	// Métodos auxiliares para los test (no borrar)
	public int getTamano() {
		return tamano;
	}

	public Nodo<E> getRaiz() {
		return raiz;
	}
}