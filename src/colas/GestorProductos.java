package colas;

import java.util.Queue;

/**
 * Gestiona una cola de productos con múltiples copias, devolviendo siempre el
 * producto con menos copias restantes.
 * 
 * @param <E> Tipo de los productos.
 */
public class GestorProductos<E> {

	/**
	 * Representa un producto y el número de copias disponibles.
	 * 
	 * @param <E> Tipo del producto.
	 */
	public static class ProductoDuplicado<E> {
		private final E producto;
		private int copias;

		/**
		 * Constructor.
		 * 
		 * @param producto Instancia del producto.
		 * @param copias   Número inicial de copias; debe ser positivo.
		 */
		public ProductoDuplicado(E producto, int copias) {
			if (producto == null) {
				throw new IllegalArgumentException("Producto no puede ser null");
			}
			if (copias <= 0) {
				throw new IllegalArgumentException("Copias debe ser mayor que 0");
			}
			this.producto = producto;
			this.copias = copias;
		}

		/**
		 * @return El producto.
		 */
		public E getProducto() {
			return producto;
		}

		/**
		 * @return Número de copias restantes.
		 */
		public int getCopias() {
			return copias;
		}

		/**
		 * Disminuye en una unidad las copias.
		 */
		private void descontarCopia() {
			if (copias > 0) {
				copias--;
			}
		}
	}

	private Queue<ProductoDuplicado<E>> cola;

	/**
	 * Crea un gestor vacío.
	 */
	public GestorProductos() {

	}

	/**
	 * Añade un producto con sus copias al gestor.
	 * 
	 * @param producto Instancia del producto.
	 * @param copias   Número de copias; debe ser mayor que cero.
	 * @throws IllegalArgumentException si producto es null o copias &lt;= 0.
	 */
	public void addProducto(E producto, int copias) {

	}

	/**
	 * Obtiene el siguiente producto a procesar, decrementando sus copias. Si tras
	 * la extracción quedan copias, se reintroduce en la cola.
	 * 
	 * @return El producto, o null si no hay más.
	 */
	public E siguienteProducto() {
		return null;
	}

	// Métodos auxiliares para los test (no borrar)
	/**
	 * @return número de entradas distintas en la cola.
	 */
	public int size() {
		return cola.size();
	}

	/**
	 * Vacia la cola.
	 */
	public void clear() {
		cola.clear();
	}

	/**
	 * @return true si el gestor está vacío.
	 */
	public boolean isEmpty() {
		return cola.isEmpty();
	}
}