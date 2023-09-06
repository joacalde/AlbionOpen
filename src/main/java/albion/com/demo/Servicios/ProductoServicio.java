package albion.com.demo.Servicios;

import albion.com.demo.Entidades.Producto;
import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Repositorios.ProductoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;
    
    @Autowired
    private FotoServicio fotoServicio;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void crear(String producto_es, String producto_en, String producto_fr, String producto_br, MultipartFile foto) throws ErrorServicio {
        Producto producto = new Producto();
        producto.setProducto_es(producto_es);
        producto.setProducto_en(producto_en);
        producto.setProducto_fr(producto_fr);
        producto.setProducto_br(producto_br);
        producto.setFoto(fotoServicio.guardar(foto));
        producto.setAlta(true);
        Integer posicion = 1;
        if (productoRepositorio.ultimaPosicion() != null) {
            posicion = productoRepositorio.ultimaPosicion()+1;
        }
        producto.setPosicion(posicion);
        productoRepositorio.save(producto);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void editar(Producto producto) throws ErrorServicio {
        Optional<Producto> opt = productoRepositorio.findById(producto.getId());
        if (opt.isPresent()) {
            Producto newproducto = opt.get();
            newproducto.setProducto_es(producto.getProducto_es());
            newproducto.setProducto_en(producto.getProducto_en());
            newproducto.setProducto_fr(producto.getProducto_fr());
            newproducto.setProducto_br(producto.getProducto_br());
            productoRepositorio.save(newproducto);
        } else {
            throw new ErrorServicio("No se encontró el producto.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void alta(String id) throws ErrorServicio {
        Optional<Producto> opt = productoRepositorio.findById(id);
        if (opt.isPresent()) {
            Producto producto = opt.get();
            producto.setAlta(true);
            productoRepositorio.save(producto);
        } else {
            throw new ErrorServicio("No se encontró el producto.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void baja(String id) throws ErrorServicio {
        Optional<Producto> opt = productoRepositorio.findById(id);
        if (opt.isPresent()) {
            Producto producto = opt.get();
            producto.setAlta(false);
            productoRepositorio.save(producto);
        } else {
            throw new ErrorServicio("No se encontró el producto.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void eliminar(String id) throws ErrorServicio {
        Optional<Producto> opt = productoRepositorio.findById(id);
        if (opt.isPresent()) {
            Producto producto = opt.get();
            productoRepositorio.delete(producto);
        } else {
            throw new ErrorServicio("No se encontró el producto.");
        }
    }

    @Transactional(readOnly = true)
    public List<Producto> todos() throws ErrorServicio {
        List<Producto> producto = productoRepositorio.findAll();
        if (!producto.isEmpty()) {
            return producto;
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Producto buscar(String id) throws ErrorServicio {
        Optional<Producto> producto = productoRepositorio.findById(id);
        if (producto.isPresent()) {
            return producto.get();
        } else {
            throw new ErrorServicio("No se encontró el producto.");
        }
    }
    
    @Transactional(readOnly = true)
    public Producto buscarPorPosicion(int posicion) throws ErrorServicio {
        Producto producto = productoRepositorio.posicion(posicion);
        return producto;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void ordenarProductos(List<String> ids, List<Integer> posiciones) throws ErrorServicio {
        for (int i = 0; i < ids.size(); i++) {
            Optional<Producto> opt = productoRepositorio.findById(ids.get(i));
            if (opt.isPresent()) {
                Producto producto = opt.get();
                producto.setPosicion(posiciones.get(i));
                productoRepositorio.save(producto);
            } else {
                throw new ErrorServicio("No se encontró el producto.");
            }
        }
    }

}
