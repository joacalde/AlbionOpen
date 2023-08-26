/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package albion.com.demo.Repositorios;

import albion.com.demo.Entidades.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepositorio  extends JpaRepository<Equipo, String> {
    
    @Query("SELECT e FROM Equipo e WHERE e.modelo = :modelo")
    public Equipo modelo(@Param("modelo") String modelo);
    
    @Query("SELECT max(a.posicion) FROM Linea a")
    public Integer ultimaPosicion();
    
    @Query("SELECT e FROM Equipo e LEFT JOIN e.foto1 f WHERE f.id = :fotoId")
    public Equipo equipoPorFoto1Id(@Param("fotoId") String fotoId);

    @Query("SELECT e FROM Equipo e LEFT JOIN e.foto2 f WHERE f.id = :fotoId")
    public Equipo equipoPorFoto2Id(@Param("fotoId") String fotoId);

    @Query("SELECT e FROM Equipo e LEFT JOIN e.foto3 f WHERE f.id = :fotoId")
    public Equipo equipoPorFoto3Id(@Param("fotoId") String fotoId);

    @Query("SELECT e FROM Equipo e LEFT JOIN e.foto4 f WHERE f.id = :fotoId")
    public Equipo equipoPorFoto4Id(@Param("fotoId") String fotoId);

    @Query("SELECT e FROM Equipo e LEFT JOIN e.foto5 f WHERE f.id = :fotoId")
    public Equipo equipoPorFoto5Id(@Param("fotoId") String fotoId);

    @Query("SELECT e FROM Equipo e LEFT JOIN e.foto6 f WHERE f.id = :fotoId")
    public Equipo equipoPorFoto6Id(@Param("fotoId") String fotoId);

    @Query("SELECT e FROM Equipo e LEFT JOIN e.foto7 f WHERE f.id = :fotoId")
    public Equipo equipoPorFoto7Id(@Param("fotoId") String fotoId);

    @Query("SELECT e FROM Equipo e LEFT JOIN e.foto8 f WHERE f.id = :fotoId")
    public Equipo equipoPorFoto8Id(@Param("fotoId") String fotoId);
    
}
