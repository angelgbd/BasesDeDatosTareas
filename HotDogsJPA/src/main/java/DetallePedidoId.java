import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetallePedidoId implements Serializable {

    private Long pedidoId;
    private Long hotdogId;

    // --- Constructores, Getters, Setters, equals y hashCode ---

    public DetallePedidoId() {}

    public DetallePedidoId(Long pedidoId, Long hotdogId) {
        this.pedidoId = pedidoId;
        this.hotdogId = hotdogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedidoId that = (DetallePedidoId) o;
        return Objects.equals(pedidoId, that.pedidoId) &&
               Objects.equals(hotdogId, that.hotdogId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, hotdogId);
    }
}