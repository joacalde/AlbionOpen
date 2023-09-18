package albion.com.demo.Servicios;

import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Repositorios.FotoRepositorio;
import albion.com.demo.Entidades.Foto;
import albion.com.demo.Entidades.Linea;
import albion.com.demo.Repositorios.LineaRepositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private LineaRepositorio lineaRepositorio;

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
    public List<Foto> guardar(List<MultipartFile> archivos) throws ErrorServicio {
        int i = 0;
        List<Foto> fotos = new ArrayList<>(); // 1. Inicialización aquí

        if (archivos != null && !archivos.isEmpty()) {
            for (MultipartFile archivo : archivos) {
                i++;
                try {
                    if (!archivo.isEmpty()) {  // Asegurarse de que el archivo no esté vacío
                        Foto foto = new Foto();
                        foto.setMime(archivo.getContentType());
                        foto.setNombre(archivo.getName());
                        foto.setContenido(archivo.getBytes());
                        foto.setPosicion(i);
                        fotoRepositorio.save(foto);
                        fotos.add(foto);
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        // 2. Verificar si la lista está vacía antes de retornarla
        if (fotos.isEmpty()) {
            return null;
        } else {
            return fotos;
        }
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
            Foto foto = optional.get();

            // Obtener todas las líneas que contienen esta foto
            List<Linea> lineasConFoto = lineaRepositorio.findByFotos_Id(foto.getId());

            // Eliminar la referencia a la foto de cada línea
            for (Linea linea : lineasConFoto) {
                linea.getFotos().remove(foto);
                lineaRepositorio.save(linea);
            }

            // Eliminar la foto del repositorio
            fotoRepositorio.delete(foto);
        } else {
            throw new ErrorServicio("No se encontró la foto");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void eliminarTodas(String lineaId) throws ErrorServicio {
        Optional<Linea> optional = lineaRepositorio.findById(lineaId);
        if (optional.isPresent()) {
            Linea linea = optional.get();
            List<Foto> fotos = linea.getFotos();

            // Desvincula las fotos de la línea primero
            linea.setFotos(null);
            lineaRepositorio.save(linea);

            for (Foto foto : fotos) {
                fotoRepositorio.delete(foto);
            }
        } else {
            throw new ErrorServicio("La línea no existe");
        }

    }

}
