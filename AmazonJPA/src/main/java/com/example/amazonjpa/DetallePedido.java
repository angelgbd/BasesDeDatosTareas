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
    @MapsId("productoId") 
    private Producto producto;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario; 

 

    public DetallePedido() {}

    public DetallePedido(Pedido pedido, Producto producto, int cantidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio(); // Captura el precio actual
        this.id = new DetallePedidoId(pedido.getId(), producto.getId());
    }

    public DetallePedidoId getId() { 
        return id; 
    }
    public void setId(DetallePedidoId id) { 
        this.id = id; 
    }
    public Pedido getPedido() { 
        return pedido; 
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido; 
    }
    public Producto getProducto() {
        return producto; 
    }
    public void setProducto(Producto producto) {
        this.producto = producto; 
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario; 
    }
}
