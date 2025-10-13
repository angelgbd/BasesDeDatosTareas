import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @EmbeddedId
    private DetallePedidoId id = new DetallePedidoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pedidoId") 
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("hotdogId") 
    @JoinColumn(name = "hotdog_id")
    private Hotdog hotdog;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario; // Guarda el precio al momento de la venta

    

    public DetallePedido() {}

    public DetallePedido(Pedido pedido, Hotdog hotdog, int cantidad) {
        this.pedido = pedido;
        this.hotdog = hotdog;
        this.cantidad = cantidad;
        this.precioUnitario = hotdog.getPrecio(); // Captura el precio del menú
        this.id = new DetallePedidoId(pedido.getId(), hotdog.getId());
    }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    public Hotdog getHotdog() { return hotdog; }
    public void setHotdog(Hotdog hotdog) { this.hotdog = hotdog; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
}