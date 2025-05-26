package colas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GestorImpresora {

	public class TrabajoImpresion{
		public String id;
		public int paginas;
		
		public TrabajoImpresion(String id, int paginas) {
			this.id = id;
			this.paginas = paginas;
		}
	}
	
	/**
	 * Cola que almacena los trabajos de impresion.
	 */
	private Queue<TrabajoImpresion> cola;
	
	/**
	 * Constructor de la clase.
	 */
	public GestorImpresora() {
		this.cola = new LinkedList<>();
	}
	
	/**
	 * Añade un nuevo trabajo al final de la cola de impresion.
	 * @param id Id del trabajo
	 * @param paginas Numero de paginas del trabajo
	 */
	public void agregarTrabajo(String id, int paginas) {
		if(id == null || paginas <=0) {
			throw new IllegalArgumentException("Valores no validos.");
		}
		
		cola.add(new TrabajoImpresion(id, paginas));
	}
	
	/**
	 * Obtiene la siguiente pagina de la cola de impresion.
	 * 
	 * @return id de la siguiente pagina o null si la cola esta vacia.
	 */
	public String siguientePagina() {
		TrabajoImpresion actual = cola.poll();
		
		actual.paginas--;
		
		if(actual.paginas>0) {
			cola.add(actual);
		}
		
		if(cola.isEmpty()) {
			return null;
		}
		
		return actual.id;
	}
	
	/**
	 * Devuelve todos los trabajos de la cola en orden.
	 * 
	 * @return ids de los trabajos en cola.
	 */
	public List<String> trabajosPendientes(){
		List<String> pendientes = new ArrayList<>();
		
		for(TrabajoImpresion t : cola) {
			pendientes.add(t.id);
		}
		
		return pendientes;
	}
}
