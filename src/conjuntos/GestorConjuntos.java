package conjuntos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Administra múltiples conjuntos identificados por nombre.
 * @param <E> Tipo de los elementos de los conjuntos.
 */
public class GestorConjuntos<E> {

    private final Map<String, Set<E>> conjuntos;

    /**
     * Inicializa el gestor sin conjuntos.
     */
    public GestorConjuntos() {
        this.conjuntos = new HashMap<>();
    }

    /**
     * Crea un conjunto vacío con el nombre dado.
     * @param nombre Nombre del conjunto; no null ni vacío.
     * @throws IllegalArgumentException si nombre inválido o ya existe.
     */
    public void crearConjunto(String nombre) {
        validarNombre(nombre);
        if (conjuntos.containsKey(nombre)) {
            throw new IllegalArgumentException("El conjunto '" + nombre + "' ya existe");
        }
        conjuntos.put(nombre, new HashSet<>());
    }

    /**
     * Comprueba si existe un conjunto con el nombre dado.
     * @param nombre Nombre del conjunto.
     * @return true si existe.
     */
    public boolean existeConjunto(String nombre) {
        return conjuntos.containsKey(nombre);
    }

    /**
     * Añade un elemento a un conjunto existente.
     * @param nombre Nombre del conjunto.
     * @param elemento Elemento a añadir (puede ser null si E lo permite).
     * @throws IllegalArgumentException si el conjunto no existe.
     */
    public void addElemento(String nombre, E elemento) {
        Set<E> s = conjuntos.get(nombre);
        if (s == null) {
            throw new IllegalArgumentException("No existe el conjunto '" + nombre + "'");
        }
        s.add(elemento);
    }

    /**
     * Elimina un elemento de un conjunto.
     * @param nombre Nombre del conjunto.
     * @param elemento Elemento a eliminar.
     * @return true si estaba presente y se eliminó.
     * @throws IllegalArgumentException si el conjunto no existe.
     */
    public boolean removeElemento(String nombre, E elemento) {
        Set<E> s = conjuntos.get(nombre);
        if (s == null) {
            throw new IllegalArgumentException("No existe el conjunto '" + nombre + "'");
        }
        return s.remove(elemento);
    }

    /**
     * @return Número de conjuntos gestionados.
     */
    public int tamaño() {
        return conjuntos.size();
    }

    /**
     * Realiza la unión de dos conjuntos existentes.
     * @param c1 Nombre del primer conjunto.
     * @param c2 Nombre del segundo conjunto.
     * @return Nuevo conjunto como unión.
     * @throws IllegalArgumentException si algún conjunto no existe.
     */
    public Set<E> union(String c1, String c2) {
        Set<E> s1 = conjuntos.get(c1);
        Set<E> s2 = conjuntos.get(c2);
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("Conjunto no encontrado");
        }
        Set<E> resultado = new HashSet<>(s1);
        resultado.addAll(s2);
        return resultado;
    }

    /**
     * Realiza la intersección de dos conjuntos existentes.
     * @param c1 Nombre del primer conjunto.
     * @param c2 Nombre del segundo conjunto.
     * @return Nuevo conjunto como intersección.
     * @throws IllegalArgumentException si algún conjunto no existe.
     */
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

    /**
     * Borra todos los conjuntos.
     */
    public void clear() {
        conjuntos.clear();
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre de conjunto inválido");
        }
    }
}