package colas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Gestiona la atención de clientes en una cola FIFO.
 */
public class CentroAtencionClientes {

    private final Queue<String> colaClientes;

    /**
     * Constructor que inicializa la cola de clientes.
     */
    public CentroAtencionClientes() {
        this.colaClientes = new LinkedList<>();
    }

    /**
     * Añade un nuevo cliente al final de la cola.
     * @param nombre Nombre del cliente; no puede ser null ni vacío.
     * @throws IllegalArgumentException si nombre es null o cadena vacía.
     */
    public void nuevoCliente(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no es válido.");
        }
        colaClientes.add(nombre);
    }

    /**
     * Atiende al siguiente cliente en la cola.
     * @return Nombre del cliente atendido, o null si no hay ninguno.
     */
    public String atenderSiguiente() {
        return colaClientes.poll();
    }

    /**
     * Lista los clientes que están en espera, en orden de llegada.
     * @return Lista de nombres de clientes en cola.
     */
    public List<String> clientesEnEspera() {
        return new ArrayList<>(colaClientes);
    }

    /**
     * Comprueba si hay clientes esperando.
     * @return true si hay al menos un cliente en cola.
     */
    public boolean hayClientesEsperando() {
        return !colaClientes.isEmpty();
    }

    /**
     * @return Número de clientes en espera.
     */
    public int size() {
        return colaClientes.size();
    }

    /**
     * Vacía la cola de clientes.
     */
    public void clear() {
        colaClientes.clear();
    }
}