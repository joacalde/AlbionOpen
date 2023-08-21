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

    @Query("SELECT l FROM Linea l LEFT JOIN l.foto1 f WHERE f.id = :fotoId")
    public Linea lineaPorFoto1Id(@Param("fotoId") String fotoId);

    @Query("SELECT l FROM Linea l LEFT JOIN l.foto2 f WHERE f.id = :fotoId")
    public Linea lineaPorFoto2Id(@Param("fotoId") String fotoId);

    @Query("SELECT l FROM Linea l LEFT JOIN l.foto3 f WHERE f.id = :fotoId")
    public Linea lineaPorFoto3Id(@Param("fotoId") String fotoId);

    @Query("SELECT l FROM Linea l LEFT JOIN l.foto4 f WHERE f.id = :fotoId")
    public Linea lineaPorFoto4Id(@Param("fotoId") String fotoId);

    @Query("SELECT l FROM Linea l LEFT JOIN l.foto5 f WHERE f.id = :fotoId")
    public Linea lineaPorFoto5Id(@Param("fotoId") String fotoId);

    @Query("SELECT l FROM Linea l LEFT JOIN l.foto6 f WHERE f.id = :fotoId")
    public Linea lineaPorFoto6Id(@Param("fotoId") String fotoId);

    @Query("SELECT l FROM Linea l LEFT JOIN l.foto7 f WHERE f.id = :fotoId")
    public Linea lineaPorFoto7Id(@Param("fotoId") String fotoId);

    @Query("SELECT l FROM Linea l LEFT JOIN l.foto8 f WHERE f.id = :fotoId")
    public Linea lineaPorFoto8Id(@Param("fotoId") String fotoId);

}
