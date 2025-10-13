import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    
    @Column(length = 500) 
    private String preferencias;

   
    @OneToMany(
        mappedBy = "cliente",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Pedido> pedidos = new ArrayList<>();

    

    public Cliente() {}

    public Cliente(String nombre, String preferencias) {
        this.nombre = nombre;
        this.preferencias = preferencias;
    }

    public Long getId() {
        return id; 
    }
    public void setId(Long id) {
        this.id = id; 
    }
    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) {
        this.nombre = nombre; 
    }
    public String getPreferencias() { 
        return preferencias;
    }
    public void setPreferencias(String preferencias) { 
        this.preferencias = preferencias; 
    }
    public List<Pedido> getPedidos() {
        return pedidos; 
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos; 
    }

    
   
}