package mapas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorRutas {
	
	private Map<String, String> mapaRutas;
	
	public GestorRutas() {
		this.mapaRutas = new HashMap<>();
	}
	
	public void inserccionTramos(String[] paradas) {
		for(int i=0; i<paradas.length; i+=2) {
			String origen = paradas[i];
			String destino = paradas[i+1];
			
			if(mapaRutas.containsKey(origen)) {
				String destinoPrevio = mapaRutas.get(origen);
				
				mapaRutas.put(origen, destino);
				
				mapaRutas.put(destino, destinoPrevio);
			}
			
			else {}
			mapaRutas.put(origen, destino);
		}
	}
	
	public List<String> paradasIntermedias(String origen, String destino){
		List <String> resultado = new ArrayList<>();
		String actual = origen;
		
		while(mapaRutas.containsKey(actual)) {
			String siguiente = mapaRutas.get(actual);
			
			if(siguiente.equals(destino)) {
				return resultado;
			}
			
			resultado.add(siguiente);
			actual = siguiente;
		}
		
		return new ArrayList<>();
	}

}
