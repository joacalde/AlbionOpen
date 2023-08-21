package albion.com.demo.Repositorios;

import albion.com.demo.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
    @Query("SELECT a FROM Usuario a WHERE a.usuario = :usuario")
    public Usuario buscarUsuario(@Param("usuario") String usuario);
}
