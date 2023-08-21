package albion.com.demo.Repositorios;

import albion.com.demo.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, String> {

    @Query("SELECT a FROM Producto a WHERE a.producto_es = :producto_es")
    public Producto producto_es(@Param("producto_es") String producto_es);

    @Query("SELECT a FROM Producto a WHERE a.producto_en = :producto_en")
    public Producto producto_en(@Param("producto_en") String producto_en);

    @Query("SELECT a FROM Producto a WHERE a.producto_fr = :producto_fr")
    public Producto producto_fr(@Param("producto_fr") String producto_fr);

    @Query("SELECT a FROM Producto a WHERE a.producto_br = :producto_br")
    public Producto producto_br(@Param("producto_br") String producto_br);

    @Query("SELECT a FROM Producto a WHERE a.posicion = :posicion")
    public Producto posicion(@Param("posicion") int posicion);
    
    @Query("SELECT max(a.posicion) FROM Producto a")
    public Integer ultimaPosicion();

}
