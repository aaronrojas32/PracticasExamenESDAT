package arboles;

import java.util.ArrayList;
import java.util.List;

public class MiArbol<E> {

	protected class Nodo<E> {
		protected E dato = null;
		protected List<Nodo<E>> hijos = null;

		public Nodo(E dato) {
			this.dato = dato;
		}
	}

	private Nodo<E> raiz;
	private int tamano;

	public MiArbol() {
		this.raiz = null;
		this.tamano = 0;
	}

	public int depth (E elemento) {
		return profundidadRecursivo(raiz, elemento,0);
	}

	private int profundidadRecursivo(Nodo<E> nodo, E elemento, int prof) {
		if(nodo == null) {
			return -1;
		}
		
		if(nodo.dato.equals(elemento)) {
			return prof;
		}
		
		for(Nodo<E> h: nodo.hijos) {
			int res = profundidadRecursivo(h,elemento,prof+1);
			
			if(res != -1) {
				return res;
			}
		}
		
		return -1;
	}
	
	public List<E> nodosHoja(){
		List<E> hojas = new ArrayList<>();
		nodosHojaRec(raiz,hojas);
		return hojas;
	}
	
	private void nodosHojaRec(Nodo<E> nodo, List<E> hojas) {
		if(nodo == null) {
			return;
		}
		
		if(nodo.hijos.isEmpty()) {
			hojas.add(nodo.dato);
		}
		else {
			for(Nodo<E> h: nodo.hijos) {
				nodosHojaRec(h,hojas);
			}
		}
	}

}
