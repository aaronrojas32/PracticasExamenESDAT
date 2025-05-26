package mapas;

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
    	
    }
    
    /**
     * Calcula la lista de paradas intermedias para ir de un origen a un destino.
     * Recorre los tramos desde el origen siguiendo el mapa hasta alcanzar el destino.
     * @param origen Punto de partida.
     * @param destino Punto de llegada.
     * @return Lista de nombres de paradas intermedias en orden. Vacía si no hay ruta.
     */
    public List<String> paradasIntermedias(String origen, String destino) {
    	return null;
    }

}