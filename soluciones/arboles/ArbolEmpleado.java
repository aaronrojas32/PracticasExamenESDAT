package arboles;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la jerarquía de empleados de una organización.
 * Cada nodo es un empleado que puede tener varios subordinados.
 */
public class ArbolEmpleado {
    
    /**
     * Nodo interno que representa a un empleado.
     */
    public class Nodo {
        public String nombre;
        public List<Nodo> subordinados;

        /**
         * Constructor de nodo.
         * @param nombre Nombre del empleado.
         */
        public Nodo(String nombre) {
            this.nombre = nombre;
            this.subordinados = new ArrayList<>();
        }
    }
    
    private Nodo raiz;   // El CEO
    private int tamaño;  // Número total de empleados
    
    /**
     * Crea un árbol vacío.
     */
    public ArbolEmpleado() {
        this.raiz = null;
        this.tamaño = 0;
    }
    
    /**
     * Da de alta un empleado bajo un supervisor dado (Padre).
     * Si supervisor es null, intenta establecer al empleado como CEO (Raiz).
     * @param supervisor Nombre del supervisor.
     * @param empleado Nombre del nuevo empleado.
     * @throws IllegalArgumentException si ya existe CEO o supervisor no encontrado.
     */
    public void altaEmpleado(String supervisor, String empleado) {
        if (supervisor == null) {
            if (raiz == null) {
                raiz = new Nodo(empleado);
                tamaño++;
                return;
            } else {
                throw new IllegalArgumentException("Ya hay CEO");
            }
        }
        Nodo nodoSupervisor = buscarNodo(raiz, supervisor);
        if (nodoSupervisor == null) {
            throw new IllegalArgumentException("No existe el supervisor: " + supervisor);
        }
        nodoSupervisor.subordinados.add(new Nodo(empleado));
        tamaño++;
    }
    
    /**
     * Obtiene la profundidad (nivel) de un empleado en la jerarquía.
     * CEO está en nivel 0.
     * @param nombre Nombre del empleado.
     * @return Nivel o -1 si no existe.
     */
    public int profundidad(String nombre) {
        return profundidadRec(raiz, nombre, 0);
    }
    
    private int profundidadRec(Nodo nodo, String buscado, int nivel) {
        if (nodo == null) {
            return -1;
        }
        if (nodo.nombre.equals(buscado)) {
            return nivel;
        }
        for (Nodo sub : nodo.subordinados) {
            int res = profundidadRec(sub, buscado, nivel + 1);
            if (res != -1) {
                return res;
            }
        }
        return -1;
    }
    
    /**
     * Lista los nombres de empleados que no tienen subordinados.
     * @return Lista de hojas.
     */
    public List<String> empleadosSinSubordinados() {
        List<String> hojas = new ArrayList<>();
        recolectarHojas(raiz, hojas);
        return hojas;
    }
    
    private void recolectarHojas(Nodo nodo, List<String> hojas) {
        if (nodo == null) {
            return;
        }
        if (nodo.subordinados.isEmpty()) {
            hojas.add(nodo.nombre);
        } else {
            for (Nodo sub : nodo.subordinados) {
                recolectarHojas(sub, hojas);
            }
        }
    }
    
    /**
     * Busca un nodo por nombre en el árbol.
     * @param actual Nodo donde iniciar la búsqueda.
     * @param buscado Nombre a buscar.
     * @return Nodo encontrado o null.
     */
    private Nodo buscarNodo(Nodo actual, String buscado) {
        if (actual == null) {
            return null;
        }
        if (actual.nombre.equals(buscado)) {
            return actual;
        }
        for (Nodo sub : actual.subordinados) {
            Nodo res = buscarNodo(sub, buscado);
            if (res != null) {
                return res;
            }
        }
        return null;
    }
    
    /**
     * @return Nodo raíz (CEO).
     */
    public Nodo getRaiz() {
        return raiz;
    }
    
    /**
     * @return Número total de empleados en el árbol.
     */
    public int getTamaño() {
        return tamaño;
    }
}