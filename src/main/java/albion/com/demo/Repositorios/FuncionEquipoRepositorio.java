package albion.com.demo.Repositorios;

import albion.com.demo.Entidades.FuncionEquipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FuncionEquipoRepositorio extends JpaRepository<FuncionEquipo, String>{
    
    @Query("SELECT a FROM FuncionEquipo a WHERE a.posicion = :posicion")
    public FuncionEquipo posicion(@Param("posicion") int posicion);
    
    @Query("SELECT max(a.posicion) FROM FuncionEquipo a")
    public Integer ultimaPosicion();
    
}
