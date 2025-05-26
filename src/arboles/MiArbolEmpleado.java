package arboles;

import java.util.ArrayList;
import java.util.List;

public class MiArbolEmpleado {
	
    protected class Nodo {
        protected String nombre;
        protected List<Nodo> subordinados;
        public Nodo(String nombre) {
            this.nombre      = nombre;
            this.subordinados = new ArrayList<>();
        }
    }
    
    private Nodo raiz;   // el CEO
    private int tamaño;  // número de empleados en el árbol
    
    public MiArbolEmpleado() {
        this.raiz   = null;
        this.tamaño = 0;
    }
    
    public void altaEmpleado(String supervisor, String empleado) {
    	if(supervisor == null) {
    		if(raiz == null) {
    			raiz = new Nodo(empleado);
    			tamaño++;
    		}
    		else {
    			throw new IllegalArgumentException("Ya hay CEO");
    		}
    	}
    	
    	Nodo buscado = buscarNodo(raiz, supervisor);
    	
    	if(buscado == null) {
    		throw new IllegalArgumentException("No exsite el supervisor");
    	}
    	
    	buscado.subordinados.add(new Nodo(empleado));
    	tamaño++;
    }
    
    private Nodo buscarNodo(Nodo actual, String buscado) {
    	if (actual == null) {
    		return null;
    	}
    	
    	if(actual.nombre.equals(buscado)) {
    		return actual;
    	}
    	
    	for(Nodo h : actual.subordinados) {
    		Nodo res = buscarNodo(h,buscado);
    		
    		if(res != null) {
    			return res;
    		}
    	}
    	
    	return null;
    }
    
    
}
