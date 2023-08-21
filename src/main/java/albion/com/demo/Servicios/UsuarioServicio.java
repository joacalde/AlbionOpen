package albion.com.demo.Servicios;

import albion.com.demo.Entidades.Usuario;
import albion.com.demo.Repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void registrar(String nombre, String contrasena){
        Usuario usuario = new Usuario();
        usuario.setUsuario(nombre);
        String encriptada = new BCryptPasswordEncoder().encode(contrasena);
        usuario.setContrasena(encriptada);
        usuarioRepositorio.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario us = usuarioRepositorio.buscarUsuario(usuario);
        if (usuario != null) {
            
            List<GrantedAuthority> permisos = new ArrayList<>();
            
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_ADMIN");
            permisos.add(p1);
            
            User user = new User(us.getUsuario(),us.getContrasena(),permisos);
            return user;
        }else{
            return null;
        }
    }
}
