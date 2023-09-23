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
    
}
