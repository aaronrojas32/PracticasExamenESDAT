package mapas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GestorRutas administra tramos de rutas entre paradas.
 * Permite insertar pares origen-destino y consultar paradas intermedias
 * entre un origen y un destino final.
 */
public class GestorRutas {
    
    /**
     * Mapa que almacena para cada parada de origen, su destino inmediato.
     * La clave es el nombre de la parada origen y el valor es la parada destino.
     */
    private Map<String, String> mapaRutas;
    
    /**
     * Constructor: inicializa el mapa de rutas vacío.
     */
    public GestorRutas() {
        this.mapaRutas = new HashMap<>();
    }
    
    /**
     * Inserta en bloque tramos de ruta definidos por pares de paradas.
     * Cada par (paradas[i], paradas[i+1]) se interpreta como un tramo de ruta.
     * Si ya existía una ruta desde el mismo origen, se reconfigura para
     * mantener la conectividad circular intercalando destinos.
     * @param paradas Array con nombres de paradas en secuencia: {origen1, destino1, origen2, destino2, ...}
     * @throws ArrayIndexOutOfBoundsException si el array tiene longitud impar.
     */
    public void inserccionTramos(String[] paradas) {
        // Recorremos en pasos de 2 para leer pares origen-destino
        for (int i = 0; i < paradas.length; i += 2) {
            String origen = paradas[i];
            String destino = paradas[i + 1];
            
            if (mapaRutas.containsKey(origen)) {
                // Si ya existía un destino para este origen,
                // guardamos el destino previo e intercalamos:
                String destinoPrevio = mapaRutas.get(origen);
                // Sobrescribimos origen->destino nuevo
                mapaRutas.put(origen, destino);
                // Enlazamos el nodo destino al destino previo para no romper la cadena
                mapaRutas.put(destino, destinoPrevio);
            }
            // Insertamos el tramo origen->destino (sea nuevo o reconfigurado)
            mapaRutas.put(origen, destino);
        }
    }
    
    /**
     * Calcula la lista de paradas intermedias para ir de un origen a un destino.
     * Recorre los tramos desde el origen siguiendo el mapa hasta alcanzar el destino.
     * @param origen Punto de partida.
     * @param destino Punto de llegada.
     * @return Lista de nombres de paradas intermedias en orden. Vacía si no hay ruta.
     */
    public List<String> paradasIntermedias(String origen, String destino) {
        List<String> resultado = new ArrayList<>();
        String actual = origen;
        
        // Mientras exista un tramo desde la parada actual:
        while (mapaRutas.containsKey(actual)) {
            String siguiente = mapaRutas.get(actual);
            
            // Si el siguiente es el destino, finalizamos y devolvemos lo acumulado
            if (siguiente.equals(destino)) {
                return resultado;
            }
            
            // Añadimos la parada al resultado y continuamos
            resultado.add(siguiente);
            actual = siguiente;
        }
        
        // Si salimos sin encontrar el destino, no hay ruta válida
        return new ArrayList<>();
    }

}