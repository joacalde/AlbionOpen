package albion.com.demo.Repositorios;

import albion.com.demo.Entidades.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionRepositorio  extends JpaRepository<Funcion, String>{
    
    @Query("SELECT a FROM Funcion a WHERE a.funcion_es = :funcion_es")
    public Funcion funcion_es (@Param("funcion_es") String funcion_es);
    
    @Query("SELECT a FROM Funcion a WHERE a.funcion_en = :funcion_en")
    public Funcion funcion_en (@Param("funcion_en") String funcion_en);
    
    @Query("SELECT a FROM Funcion a WHERE a.funcion_fr = :funcion_fr")
    public Funcion funcion_fr (@Param("funcion_fr") String funcion_fr);
    
    @Query("SELECT a FROM Funcion a WHERE a.funcion_br = :funcion_br")
    public Funcion funcion_br (@Param("funcion_br") String funcion_br);
    
    @Query("SELECT a FROM Funcion a WHERE a.posicion = :posicion")
    public Funcion posicion (@Param("posicion") String posicion);
    
    @Query("SELECT max(a.posicion) FROM Funcion a")
    public Integer ultimaPosicion ();
    
}
