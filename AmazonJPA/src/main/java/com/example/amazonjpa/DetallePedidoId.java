import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetallePedidoId implements Serializable {

    private Long pedidoId;
    private Long productoId;

    // --- Constructores, Getters, Setters, equals y hashCode ---

    public DetallePedidoId() {}

    public DetallePedidoId(Long pedidoId, Long productoId) {
        this.pedidoId = pedidoId;
        this.productoId = productoId;
    }

    public Long getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }
    public Long getProductoId() {
        return productoId; 
    }
    public void setProductoId(Long productoId) { 
        this.productoId = productoId; 
    }

    
}
