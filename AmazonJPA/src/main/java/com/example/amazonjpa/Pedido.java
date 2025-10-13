import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    
    @OneToMany(
        mappedBy = "pedido",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<DetallePedido> detalles = new HashSet<>();

   

    public Pedido() {}

    public Long getId() {
        return id; 
    }
    public void setId(Long id) {
        this.id = id; 
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion; 
    }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion; 
    }
    public Cliente getCliente() {
        return cliente; 
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente; 
    }
    public Set<DetallePedido> getDetalles() { 
        return detalles; 
    }
    public void setDetalles(Set<DetallePedido> detalles) {
        this.detalles = detalles; 
    }
}
