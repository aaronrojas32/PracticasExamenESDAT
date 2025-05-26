package colas;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class GestorProductos<E> {
	
	public class ProductosDuplicados<E>{
		public E producto;
		public int copias;
		
		public ProductosDuplicados(E producto, int copias) {
			this.producto = producto;
			this.copias = copias;
		}
	}
	
	private Queue<ProductosDuplicados<E>> cola;
	
	public GestorProductos() {
		this.cola = new PriorityQueue<>(
		
		new Comparator<ProductosDuplicados<E>>() {
			
			@Override
			public int compare(ProductosDuplicados<E> p1, ProductosDuplicados<E> p2) {
                return Integer.compare(p1.copias, p2.copias);
            }
		});
	}
	
	public void addProductosDuplicado(ProductosDuplicados<E> pd) {
		if(pd != null && pd.copias >0) {
			cola.offer(pd);
		}
	}

	public E siguienteProducto() {
		ProductosDuplicados<E> pd = cola.poll();
		
		if(pd == null) {
			return null;
		}
		
		E resultado = pd.producto;
		pd.copias --;
		
		if(pd.copias >0) {
			cola.offer(pd);
		}
		
		return resultado;
	}
}
