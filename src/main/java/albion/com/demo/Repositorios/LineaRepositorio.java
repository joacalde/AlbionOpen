package albion.com.demo.Repositorios;

import albion.com.demo.Entidades.Linea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaRepositorio extends JpaRepository<Linea, String> {

    @Query("SELECT a FROM Linea a WHERE a.modelo = :modelo")
    public Linea modelo(@Param("modelo") String modelo);

    @Query("SELECT max(a.posicion) FROM Linea a")
    public Integer ultimaPosicion();

}
