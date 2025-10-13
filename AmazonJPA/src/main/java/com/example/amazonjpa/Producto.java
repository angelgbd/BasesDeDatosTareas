import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    private int stock;

    

    public Producto() {}

    public Producto(String nombre, BigDecimal precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
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
    public BigDecimal getPrecio() {
        return precio; 
    }
    public void setPrecio(BigDecimal precio) {
        this.precio = precio; 
    }
    public int getStock() {
        return stock; 
    }
    public void setStock(int stock) { 
        this.stock = stock; 
    }
}
