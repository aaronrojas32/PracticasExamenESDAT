package mapas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorPrerequisitos {
	/**
	 * Mapa con los prerequistos de las asignaturas.
	 */
	private Map<String, String> requisitos;
	
	/**
	 * Constructor de la clase que crea el nuevo mapa para almacenar las asignaturas.
	 */
	public GestorPrerequisitos() {
		this.requisitos = new HashMap<>();
	}
	
	/**
	 * Inserta un nuevo par en el mapa, si la asignatura ya existe se actualiza con los prerequisitos.
	 * 
	 * @param pares Cadena de pares de asignaturas.
	 */
	public void insercionPrerequisitos(String[] pares) {
		for(int i=0;i<pares.length;i+=2) {
			if(!requisitos.containsKey(pares[i])) {
				requisitos.put(pares[i], pares[i+1]);
			}
			
			else {
				String actual = requisitos.get(pares[i]);
				requisitos.put(pares[i], pares[i+1]);
				requisitos.put(pares[i+1], actual);
			}
		}
	}
	
	/**
	 * Obtiene la lista de preqrequisitos de una asingatura.
	 * 
	 * @param asignatura Asignatura de la que se quieren obtener los prerequistos
	 * @return pre Lista con todos los prerequsitos
	 */
	public List<String> prereqIntermedios(String asignatura){
		List<String> pre = new ArrayList<>();
		String actual = asignatura;
		
		while(requisitos.containsKey(actual)) {
			pre.add(requisitos.get(actual));
			actual = requisitos.get(actual);
		}
		
		return pre;
	}
}
