package albion.com.demo.Repositorios;

import albion.com.demo.Entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto, String> {
    
    @Query("SELECT f FROM Foto f WHERE f.id = :id")
    public Foto buscarFotoPorId (@Param("id") String id);
}
