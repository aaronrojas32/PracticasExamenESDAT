package colas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CentroAtencionClientes {

	private Queue<String> colaClientes;
	
	public CentroAtencionClientes() {
		this.colaClientes = new LinkedList<>();
	}
	
	public void nuevoCliente(String nombre) {
		if(nombre == null || nombre == "") {
			throw new IllegalArgumentException("El nombre no es valido.");
		}
		
		colaClientes.add(nombre);
	}
	
	public String atenderSiguiente() {
		
		if(colaClientes.size() == 0) return null;
		
		return colaClientes.poll();
	}
	
	public List<String> clientesEnEspera(){
		List<String> clientes = new ArrayList<>();
		
		for(String cliente : colaClientes) {
			clientes.add(cliente);
		}
		
		return clientes;
	}
	
	public boolean hayClientesEsperando() {
		return !colaClientes.isEmpty();
	}
}