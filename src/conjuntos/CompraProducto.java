package conjuntos;

import java.util.Date;
import java.util.Objects;

/**
 * Representa la compra de un producto por un cliente en una sección determinada
 * con una fecha/hora e importe.
 */
public class CompraProducto implements Comparable<CompraProducto> {
    /** Sección de compra. */
    public enum Seccion { Casa, Deportes, Moda, Motor, Salud, Telefonía }

    private final String idCliente;
    private final Date fechaYHora;
    private final Seccion seccion;
    private final double importe;

    /**
     * Constructor.
     * @param idCliente Identificador del cliente (no null ni vacío).
     * @param seccion   Sección donde se realiza la compra (no null).
     * @param fechaYHora Fecha y hora de la compra (no null).
     * @param importe   Importe de la compra (>= 0).
     * @throws IllegalArgumentException si algún parámetro es inválido.
     */
    public CompraProducto(String idCliente,
                          Seccion seccion,
                          Date fechaYHora,
                          double importe) {
        if (idCliente == null || idCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("idCliente no puede ser null o vacío");
        }
        if (seccion == null) {
            throw new IllegalArgumentException("seccion no puede ser null");
        }
        if (fechaYHora == null) {
            throw new IllegalArgumentException("fechaYHora no puede ser null");
        }
        if (importe < 0) {
            throw new IllegalArgumentException("importe no puede ser negativo");
        }
        this.idCliente = idCliente;
        this.seccion = seccion;
        this.fechaYHora = new Date(fechaYHora.getTime()); // defensiva
        this.importe = importe;
    }

    /** @return identificador del cliente. */
    public String getIdCliente() {
        return idCliente;
    }

    /** @return fecha y hora de la compra. */
    public Date getFechaYHora() {
        return new Date(fechaYHora.getTime());
    }

    /** @return sección de la compra. */
    public Seccion getSeccion() {
        return seccion;
    }

    /** @return importe de la compra. */
    public double getImporte() {
        return importe;
    }

    /**
     * Orden natural por fechaYHora ascendente.
     */
    @Override
    public int compareTo(CompraProducto o) {
        return this.fechaYHora.compareTo(o.fechaYHora);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CompraProducto)) return false;
        CompraProducto other = (CompraProducto) obj;
        return importe == other.importe
            && idCliente.equals(other.idCliente)
            && seccion == other.seccion
            && fechaYHora.equals(other.fechaYHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, fechaYHora, seccion, importe);
    }

    @Override
    public String toString() {
        return String.format("Compra[idCliente=%s, seccion=%s, fecha=%s, importe=%.2f]",
                idCliente, seccion, fechaYHora, importe);
    }
}