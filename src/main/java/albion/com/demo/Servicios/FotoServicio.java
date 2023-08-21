package albion.com.demo.Servicios;

import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Repositorios.FotoRepositorio;
import albion.com.demo.Entidades.Foto;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepositorio;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Foto guardar(MultipartFile archivo) throws ErrorServicio {
        if (archivo != null && !archivo.isEmpty()) {
            try {  // seteamos la foto y copiamos, si ocurre error, lo muestra
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
                fotoRepositorio.save(foto);
                return foto;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Foto actualizar(String idFoto, MultipartFile archivo) throws ErrorServicio, IOException {
        Optional<Foto> respuesta = fotoRepositorio.findById(idFoto);
        if (respuesta.isPresent()) {
            Foto foto = respuesta.get();
            if (archivo == null) {
                foto = new Foto();
            } else {
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());
            }
            fotoRepositorio.save(foto);
            return foto;
        } else {
            throw new ErrorServicio("No se encontró la foto");
        }
    }

    @Transactional(readOnly = true)
    public Foto buscarPorId(String id) {
        Optional<Foto> optional = fotoRepositorio.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void eliminar(String id) throws ErrorServicio {
        Optional<Foto> optional = fotoRepositorio.findById(id);
        if (optional.isPresent()) {
            fotoRepositorio.delete(optional.get());
        } else {
            throw new ErrorServicio("No se encontró la foto");
        }
    }

}
