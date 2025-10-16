import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;


import com.example.videojuegosjpa.Videojuego;
import com.example.videojuegosjpa.Jugador;
import com.example.videojuegosjpa.Direccion;
import com.example.videojuegosjpa.Logro;


public class DAOS {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    
    private static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    public void agregarVideojuego(Videojuego videojuego) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(videojuego);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al agregar videojuego: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    
    public void editarVideojuego(Videojuego videojuego) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(videojuego);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al editar videojuego: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    
    public void eliminarVideojuego(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Videojuego videojuego = em.find(Videojuego.class, id);
            if (videojuego != null) {
                em.remove(videojuego);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar videojuego: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    
    public Videojuego buscarVideojuegoPorId(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Videojuego.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar videojuego por ID: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
   
    public List<Videojuego> buscarTodosLosVideojuegos() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Videojuego> query = em.createNamedQuery("Videojuego.findAll", Videojuego.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al ejecutar NamedQuery 'Videojuego.findAll': " + e.getMessage());
            return Collections.emptyList(); 
        } finally {
            em.close();
        }
    }
    
  
    public void agregarJugador(Jugador jugador) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(jugador);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al agregar jugador: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    public void editarJugador(Jugador jugador) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(jugador);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al editar jugador: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarJugador(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Jugador jugador = em.find(Jugador.class, id);
            if (jugador != null) {
                em.remove(jugador);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar jugador: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Jugador buscarJugadorPorId(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Jugador.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar jugador por ID: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

   
    public Jugador buscarJugadorPorNickname(String nickname) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Jugador> query = em.createNamedQuery("Jugador.findByNickname", Jugador.class);
            query.setParameter("nickname", nickname);
            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error al ejecutar NamedQuery 'Jugador.findByNickname': " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
  
    public void agregarDireccion(Direccion direccion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(direccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al agregar dirección: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    public void editarDireccion(Direccion direccion) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(direccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al editar dirección: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarDireccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Direccion direccion = em.find(Direccion.class, id);
            if (direccion != null) {
                em.remove(direccion);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar dirección: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    public Direccion buscarDireccionPorId(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Direccion.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar dirección por ID: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
   
    public void agregarLogro(Logro logro) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(logro);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al agregar logro: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    public void editarLogro(Logro logro) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(logro);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al editar logro: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    public void eliminarLogro(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Logro logro = em.find(Logro.class, id);
            if (logro != null) {
                em.remove(logro);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al eliminar logro: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Logro buscarLogroPorId(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Logro.class, id);
        } catch (Exception e) {
            System.err.println("Error al buscar logro por ID: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
    
    public List<Object[]> listarJugadoresConMasVideojuegos() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT j, SIZE(j.videojuegos) AS total FROM Jugador j ORDER BY total DESC";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar jugadores por cantidad de videojuegos: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
    
    
    public List<Object[]> listarVideojuegosConMayorNumeroDeLogros() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT v, SIZE(v.logros) AS total FROM Videojuego v ORDER BY total DESC";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar videojuegos por cantidad de logros: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
    
   
    public List<Jugador> obtenerJugadoresConPuntajeMayorA(int puntajeMinimo) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT j FROM Jugador j JOIN j.videojuegos v JOIN v.logros l GROUP BY j HAVING SUM(l.puntaje) > :puntaje";
            TypedQuery<Jugador> query = em.createQuery(jpql, Jugador.class);
            query.setParameter("puntaje", (long) puntajeMinimo); // El SUM puede ser Long
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al obtener jugadores por puntaje: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
    
    
    public List<Videojuego> listarVideojuegosSinLogros() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT v FROM Videojuego v WHERE v.logros IS EMPTY";
            TypedQuery<Videojuego> query = em.createQuery(jpql, Videojuego.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar videojuegos sin logros: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
    
   
    public List<Jugador> listarJugadoresPorColoniaConMasDeUnVideojuego(String colonia) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT j FROM Jugador j WHERE j.direccion.colonia = :colonia AND SIZE(j.videojuegos) > 1";
            TypedQuery<Jugador> query = em.createQuery(jpql, Jugador.class);
            query.setParameter("colonia", colonia);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar jugadores por colonia y videojuegos: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
    
   
    public List<Logro> listarLogrosConPuntajeMayorAlPromedio() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT l FROM Logro l WHERE l.puntaje > (SELECT AVG(l2.puntaje) FROM Logro l2)";
            TypedQuery<Logro> query = em.createQuery(jpql, Logro.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar logros con puntaje mayor al promedio: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
    
   
    public List<Videojuego> listarVideojuegosConSumaPuntosLogrosMayorA(int puntajeMinimo) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT v FROM Videojuego v JOIN v.logros l GROUP BY v HAVING SUM(l.puntaje) > :puntaje";
            TypedQuery<Videojuego> query = em.createQuery(jpql, Videojuego.class);
            query.setParameter("puntaje", (long) puntajeMinimo);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar videojuegos por suma de puntos de logros: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
    
   
    public List<Jugador> listarJugadoresOrdenadosPorEdadDesc() {
        EntityManager em = getEntityManager();
        try {
            
            String jpql = "SELECT j FROM Jugador j ORDER BY j.fechaNacimiento ASC";
            TypedQuery<Jugador> query = em.createQuery(jpql, Jugador.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar jugadores por edad: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
    
    
    public Videojuego mostrarVideojuegoConLogroMasAlto() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT l.videojuego FROM Logro l ORDER BY l.puntaje DESC";
            TypedQuery<Videojuego> query = em.createQuery(jpql, Videojuego.class);
            query.setMaxResults(1); // Solo nos interesa el primero (el más alto)
            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error al mostrar videojuego con logro más alto: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
    
    
    public List<Object[]> listarJugadoresConDireccionOrdenadosPorColonia() {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT j, j.direccion.calle, j.direccion.colonia FROM Jugador j ORDER BY j.direccion.colonia ASC";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al listar jugadores con dirección: " + e.getMessage());
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
}
