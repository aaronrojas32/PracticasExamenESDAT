package conjuntos;

import java.util.Date;

public class CompraProducto implements Comparable<CompraProducto> {
    public enum Seccion { Casa, Deportes, Moda, Motor, Salud, Telefonía };

    private String idCliente;
    private Date fechaYHora;
    private Seccion seccion;
    private double importe;

    public CompraProducto(String _idCliente,
                          Seccion _seccion,
                          Date _fechaYHora,
                          double _importe) {
        this.idCliente   = _idCliente;
        this.seccion     = _seccion;
        this.fechaYHora  = _fechaYHora;
        this.importe     = _importe;
    }

    // getter necesario para otras operaciones:
    public Date getFechaYHora() {
        return fechaYHora;
    }
    public String getIdCliente() {
        return idCliente;
    }

    @Override
    public int compareTo(CompraProducto o) {
        // orden natural por fechaYHora ascendente
        return this.fechaYHora.compareTo(o.fechaYHora);
    }
    
    
}

