package albion.com.demo.Servicios;

import albion.com.demo.Entidades.Funcion;
import albion.com.demo.Entidades.Producto;
import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Repositorios.FuncionRepositorio;
import albion.com.demo.Repositorios.ProductoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionServicio {

    @Autowired
    private FuncionRepositorio funcionRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void crear(Funcion funcion, String producto_es) throws ErrorServicio {
        funcion.setAlta(true);
        Integer posicion = 1;
        if (funcionRepositorio.ultimaPosicion() != null) {
            posicion = funcionRepositorio.ultimaPosicion()+1;
        }
        funcion.setPosicion(posicion);
        Producto producto = productoRepositorio.producto_es(producto_es);
        List<Funcion> funciones = producto.getFunciones();
        funciones.add(funcion);
        producto.setFunciones(funciones);
        productoRepositorio.save(producto);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void editar(Funcion funcion) throws ErrorServicio {
        Optional<Funcion> opt = funcionRepositorio.findById(funcion.getId());
        if (opt.isPresent()) {
            Funcion newfuncion = opt.get();
            newfuncion.setFuncion_es(funcion.getFuncion_es());
            newfuncion.setFuncion_en(funcion.getFuncion_en());
            newfuncion.setFuncion_fr(funcion.getFuncion_fr());
            newfuncion.setFuncion_br(funcion.getFuncion_br());
        } else {
            throw new ErrorServicio("No se encontró la función");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void alta(String id) throws ErrorServicio {
        Optional<Funcion> opt = funcionRepositorio.findById(id);
        if (opt.isPresent()) {
            Funcion funcion = opt.get();
            funcion.setAlta(true);
            funcionRepositorio.save(funcion);
        } else {
            throw new ErrorServicio("No se encontró la función");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void baja(String id) throws ErrorServicio {
        Optional<Funcion> opt = funcionRepositorio.findById(id);
        if (opt.isPresent()) {
            Funcion funcion = opt.get();
            funcion.setAlta(false);
            funcionRepositorio.save(funcion);
        } else {
            throw new ErrorServicio("No se encontró la función");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void eliminar(String id) throws ErrorServicio {
        Optional<Funcion> opt = funcionRepositorio.findById(id);
        if (opt.isPresent()) {
            Funcion funcion = opt.get();
            funcionRepositorio.delete(funcion);
        } else {
            throw new ErrorServicio("No se encontró la función");
        }
    }

    @Transactional(readOnly = true)
    public List<Funcion> todos() throws ErrorServicio {
        List<Funcion> funciones = funcionRepositorio.findAll();
        if (!funciones.isEmpty()) {
            return funciones;
        } else {
            Funcion funcion = new Funcion("No hay funciones, Debe crear una primero");
            funciones.add(funcion);
            return funciones;
        }
    }
    
    @Transactional(readOnly = true)
    public Funcion buscar(String id) throws ErrorServicio {
        Optional<Funcion> funcion = funcionRepositorio.findById(id);
        if (funcion.isPresent()) {
            return funcion.get();
        } else {
            throw new ErrorServicio("No se encontró la función.");
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void ordenarFunciones(List<String> ids, List<Integer> posiciones) throws ErrorServicio {
        for (int i = 0; i < ids.size(); i++) {
            Optional<Funcion> opt = funcionRepositorio.findById(ids.get(i));
            if (opt.isPresent()) {
                Funcion funcion = opt.get();
                funcion.setPosicion(posiciones.get(i));
                funcionRepositorio.save(funcion);
            } else {
                throw new ErrorServicio("No se encontró la función.");
            }
        }
    }

}
