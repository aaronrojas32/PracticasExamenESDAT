package arboles;

import java.util.ArrayList;
import java.util.List;

public class MiArbol<E> {

    public class Nodo<E> {
        public E dato;
        public List<Nodo<E>> hijos;

        public Nodo(E dato) {
            this.dato = dato;
            this.hijos = new ArrayList<>();
        }
    }

    private Nodo<E> raiz;
    private int tamano;

    public MiArbol() {
        this.raiz = null;
        this.tamano = 0;
    }

    /**
     * Agrega un nodo raíz al árbol. Sólo si está vacío.
     */
    public void addRaiz(E elemento) {
        if (raiz != null) {
            throw new IllegalStateException("El árbol ya tiene raíz");
        }
        raiz = new Nodo<>(elemento);
        tamano = 1;
    }

    /**
     * Agrega un nuevo elemento como hijo del nodo que contiene 'padre'.
     * @param padre dato del nodo padre donde añadir el hijo.
     * @param hijo  dato del nuevo nodo a añadir.
     * @return true si se añadió correctamente, false si padre no existe.
     */
    public boolean addHijo(E padre, E hijo) {
        Nodo<E> nodoPadre = buscarNodo(raiz, padre);
        if (nodoPadre == null) {
            return false;
        }
        Nodo<E> nuevo = new Nodo<>(hijo);
        nodoPadre.hijos.add(nuevo);
        tamano++;
        return true;
    }

    private Nodo<E> buscarNodo(Nodo<E> nodo, E objetivo) {
        if (nodo == null) {
            return null;
        }
        if (nodo.dato.equals(objetivo)) {
            return nodo;
        }
        for (Nodo<E> h : nodo.hijos) {
            Nodo<E> res = buscarNodo(h, objetivo);
            if (res != null) {
                return res;
            }
        }
        return null;
    }

    public int profundidad(E elemento) {
        return profundidadRecursivo(raiz, elemento, 0);
    }

    private int profundidadRecursivo(Nodo<E> nodo, E elemento, int prof) {
        if (nodo == null) {
            return -1;
        }
        if (nodo.dato.equals(elemento)) {
            return prof;
        }
        for (Nodo<E> h : nodo.hijos) {
            int res = profundidadRecursivo(h, elemento, prof + 1);
            if (res != -1) {
                return res;
            }
        }
        return -1;
    }

    public List<E> nodosHoja() {
        List<E> hojas = new ArrayList<>();
        nodosHojaRec(raiz, hojas);
        return hojas;
    }

    private void nodosHojaRec(Nodo<E> nodo, List<E> hojas) {
        if (nodo == null) {
            return;
        }
        if (nodo.hijos.isEmpty()) {
            hojas.add(nodo.dato);
        } else {
            for (Nodo<E> h : nodo.hijos) {
                nodosHojaRec(h, hojas);
            }
        }
    }

    // Métodos extra necesarios para los test
    public int getTamano() {
        return tamano;
    }

    public Nodo<E> getRaiz() {
        return raiz;
    }
}