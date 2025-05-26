package arboles;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la jerarquía de empleados de una organización. Cada nodo es un
 * empleado que puede tener varios subordinados.
 */
public class ArbolEmpleado {

	/**
	 * Nodo interno que representa a un empleado.
	 */
	public class Nodo {
		public String nombre;
		public List<Nodo> subordinados;

		/**
		 * Constructor de nodo.
		 * 
		 * @param nombre Nombre del empleado.
		 */
		public Nodo(String nombre) {
			this.nombre = nombre;
			this.subordinados = new ArrayList<>();
		}
	}

	private Nodo raiz; // El CEO
	private int tamaño; // Número total de empleados

	/**
	 * Crea un árbol vacío.
	 */
	public ArbolEmpleado() {

	}

	/**
	 * Da de alta un empleado bajo un supervisor dado (Padre). Si supervisor es
	 * null, intenta establecer al empleado como CEO (Raiz).
	 * 
	 * @param supervisor Nombre del supervisor.
	 * @param empleado   Nombre del nuevo empleado.
	 * @throws IllegalArgumentException si ya existe CEO(Raiz) o supervisor(Padre)
	 *                                  no encontrado.
	 */
	public void altaEmpleado(String supervisor, String empleado) {

	}

	/**
	 * Obtiene la profundidad (nivel) de un empleado en la jerarquía. CEO está en
	 * nivel 0.
	 * 
	 * @param nombre Nombre del empleado.
	 * @return Nivel o -1 si no existe.
	 */
	public int profundidad(String nombre) {
		return 0;
	}

	/**
	 * Lista los nombres de empleados que no tienen subordinados.
	 * 
	 * @return Lista de hojas.
	 */
	public List<String> empleadosSinSubordinados() {
		return null;
	}

	// Métodos auxiliares para los test (no borrar)

	/**
	 * @return Nodo raíz (CEO).
	 */
	public Nodo getRaiz() {
		return raiz;
	}

	/**
	 * @return Número total de empleados en el árbol.
	 */
	public int getTamaño() {
		return tamaño;
	}
}