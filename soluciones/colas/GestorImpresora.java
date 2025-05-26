package colas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Gestiona una cola de trabajos de impresión.
 * Cada trabajo tiene un identificador y un número de páginas.
 */
public class GestorImpresora {

    /**
     * Representa un trabajo de impresión en la cola.
     */
    public static class TrabajoImpresion {
        public final String id;
        public int paginas;

        /**
         * Constructor de un trabajo de impresión.
         * @param id Identificador único del trabajo.
         * @param paginas Número de páginas a imprimir (>=1).
         * @throws IllegalArgumentException si id es null o paginas <= 0.
         */
        public TrabajoImpresion(String id, int paginas) {
            if (id == null) {
                throw new IllegalArgumentException("ID no puede ser null");
            }
            if (paginas <= 0) {
                throw new IllegalArgumentException("Páginas debe ser mayor que 0");
            }
            this.id = id;
            this.paginas = paginas;
        }

        /**
         * @return Identificador del trabajo.
         */
        public String getId() {
            return id;
        }

        /**
         * @return Número de páginas restantes.
         */
        public int getPaginas() {
            return paginas;
        }

        /**
         * Decrementa el contador de páginas en una unidad.
         */
        public void imprimirPagina() {
            if (paginas > 0) {
                paginas--;
            }
        }
    }

    private final Queue<TrabajoImpresion> cola;

    /**
     * Crea un gestor de impresora con cola vacía.
     */
    public GestorImpresora() {
        this.cola = new LinkedList<>();
    }

    /**
     * Añade un nuevo trabajo al final de la cola de impresión.
     * @param id Identificador del trabajo (no null).
     * @param paginas Número de páginas (>0).
     * @throws IllegalArgumentException si id es null o paginas <= 0.
     */
    public void agregarTrabajo(String id, int paginas) {
        TrabajoImpresion t = new TrabajoImpresion(id, paginas);
        cola.add(t);
    }

    /**
     * Imprime la siguiente página del siguiente trabajo en cola.
     * Si tras imprimir quedan páginas, el trabajo se reingresa al final.
     * @return Identificador del trabajo del que se imprimió una página, o null si no hay trabajos.
     */
    public String siguientePagina() {
        TrabajoImpresion actual = cola.poll();
        if (actual == null) {
            return null;
        }
        actual.imprimirPagina();
        String id = actual.getId();
        if (actual.getPaginas() > 0) {
            cola.add(actual);
        }
        return id;
    }

    /**
     * @return Lista con los identificadores de trabajos pendientes en orden de cola.
     */
    public List<String> trabajosPendientes() {
        List<String> pendientes = new ArrayList<>();
        for (TrabajoImpresion t : cola) {
            pendientes.add(t.getId());
        }
        return pendientes;
    }

    /**
     * @return número de trabajos distintos en cola.
     */
    public int size() {
        return cola.size();
    }

    /**
     * Vacia la cola de trabajos.
     */
    public void clear() {
        cola.clear();
    }

    /**
     * @return true si no hay trabajos en cola.
     */
    public boolean isEmpty() {
        return cola.isEmpty();
    }
}