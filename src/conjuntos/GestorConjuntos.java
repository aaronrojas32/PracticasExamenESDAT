package conjuntos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GestorConjuntos<E>{
	
	private final Map<String, Set<E>> conjuntos;
	
	public GestorConjuntos() {
        this.conjuntos = new HashMap<>();
    }
	
	public void crearConjunto(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre de conjunto inválido");
        }
        if (conjuntos.containsKey(nombre)) {
            throw new IllegalArgumentException("El conjunto '" + nombre + "' ya existe");
        }
        conjuntos.put(nombre, new HashSet<>());
    }
	
	public void addElemento(String nombre, E elemento) {
		if(!conjuntos.containsKey(nombre)){
			throw new IllegalArgumentException("No existe el conjunto");
		}
		
		conjuntos.get(nombre).add(elemento);
	}
	
	public Set<E> union(String c1, String c2){
		Set<E> resultado = new HashSet<>();
		resultado.addAll(conjuntos.get(c1));
		resultado.addAll(conjuntos.get(c2));
		
		if (c1 == null || c2 == null) {
            throw new IllegalArgumentException("Conjunto no encontrado");
        }
		
		return resultado;
	}
	
	public Set<E> interseccion(String c1, String c2) {
        Set<E> s1 = conjuntos.get(c1);
        Set<E> s2 = conjuntos.get(c2);
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Conjunto no encontrado");
        }
        Set<E> resultado = new HashSet<>(s1);
        resultado.retainAll(s2);
        return resultado;
    }
}
