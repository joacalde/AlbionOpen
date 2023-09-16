package albion.com.demo.Servicios;

import albion.com.demo.Entidades.Foto;
import albion.com.demo.Entidades.Funcion;
import albion.com.demo.Entidades.Linea;
import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Repositorios.FuncionRepositorio;
import albion.com.demo.Repositorios.LineaRepositorio;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LineaServicio {

    @Autowired
    private FotoServicio fotoServicio;

    @Autowired
    private FuncionRepositorio funcionRepositorio;

    @Autowired
    private LineaRepositorio lineaRepositorio;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void crear(
            String modelo, String configuracion, String produccion,
            String titulo_es, String titulo_en,
            String descripcion_es, String descripcion_en,
            String funcionId) throws ErrorServicio {
        Integer posicion = 1;
        if (lineaRepositorio.ultimaPosicion() != null) {
            posicion = lineaRepositorio.ultimaPosicion() + 1;
        }
        Linea linea = new Linea();
        linea.setModelo(modelo);
        linea.setConfiguracion(configuracion);
        linea.setProduccion(produccion);
        linea.setTitulo_es(titulo_es);
        linea.setTitulo_en(titulo_en);
        linea.setDescripcion_es(descripcion_es);
        linea.setDescripcion_en(descripcion_en);
        linea.setPosicion(posicion);
        Optional<Funcion> opt = funcionRepositorio.findById(funcionId);
        if (opt.isPresent()) {
            Funcion funcion = opt.get();
            List<Linea> lineas = funcion.getLineas();
            lineas.add(linea);
            funcionRepositorio.save(funcion);
        } else {
            throw new ErrorServicio("No se encontro la función.");
        }
    }

    @Transactional(readOnly = true)
    public void validarModelo(String modelo) throws ErrorServicio {
        if (modelo == null || modelo.isEmpty()) {
            throw new ErrorServicio("La nueva linea debe tener modelo.");
        }
        if (lineaRepositorio.modelo(modelo) != null) {
            throw new ErrorServicio("Ya existe ese modelo.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void editar(Linea linea) throws ErrorServicio {
        Optional<Linea> opt = lineaRepositorio.findById(linea.getId());
        if (opt.isPresent()) {
            Linea newlinea = opt.get();
            newlinea.setModelo(linea.getModelo());
            newlinea.setConfiguracion(linea.getConfiguracion());
            newlinea.setProduccion(linea.getProduccion());
            newlinea.setTitulo_es(linea.getTitulo_es());
            newlinea.setTitulo_en(linea.getTitulo_en());
            newlinea.setTitulo_fr(linea.getTitulo_fr());
            newlinea.setTitulo_br(linea.getTitulo_br());
            newlinea.setDescripcion_es(linea.getDescripcion_es());
            newlinea.setDescripcion_en(linea.getDescripcion_en());
            newlinea.setDescripcion_fr(linea.getDescripcion_fr());
            newlinea.setDescripcion_br(linea.getDescripcion_br());
        } else {
            throw new ErrorServicio("La nueva linea debe tener modelo.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void alta(String id) throws ErrorServicio {
        Optional<Linea> opt = lineaRepositorio.findById(id);
        if (opt.isPresent()) {
            Linea linea = opt.get();
            linea.setAlta(true);
            lineaRepositorio.save(linea);
        } else {
            throw new ErrorServicio("No se encontró la linea");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void baja(String id) throws ErrorServicio {
        Optional<Linea> opt = lineaRepositorio.findById(id);
        if (opt.isPresent()) {
            Linea linea = opt.get();
            linea.setAlta(false);
            lineaRepositorio.save(linea);
        } else {
            throw new ErrorServicio("No se encontró la linea");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void eliminar(String id) throws ErrorServicio {
        Optional<Linea> optional = lineaRepositorio.findById(id);
        if (optional.isPresent()) {
            lineaRepositorio.delete(optional.get());
        } else {
            throw new ErrorServicio("No se encontró la Linea.");
        }
    }

    @Transactional(readOnly = true)
    public List<Linea> todas() throws ErrorServicio {
        List<Linea> linea = lineaRepositorio.findAll();
        if (!linea.isEmpty()) {
            return linea;
        } else {
            throw new ErrorServicio("No se encontró la linea");
        }
    }

    @Transactional(readOnly = true)
    public Linea buscar(String id) throws ErrorServicio {
        Optional<Linea> linea = lineaRepositorio.findById(id);
        if (linea.isPresent()) {
            return linea.get();
        } else {
            throw new ErrorServicio("No se encontró la linea");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void ordenarLineas(List<String> ids, List<Integer> posiciones) throws ErrorServicio {
        for (int i = 0; i < ids.size(); i++) {
            Optional<Linea> opt = lineaRepositorio.findById(ids.get(i));
            if (opt.isPresent()) {
                Linea linea = opt.get();
                linea.setPosicion(posiciones.get(i));
                lineaRepositorio.save(linea);
            } else {
                throw new ErrorServicio("No se encontró la función.");
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void agregarFotoLineas(List<String> ids, Map<String, List<MultipartFile>> fotosPorLinea) throws ErrorServicio {
        for (String id : ids) {
            Optional<Linea> opt = lineaRepositorio.findById(id);
            if (opt.isPresent()) {
                Linea linea = opt.get();
                List<Foto> fotosDeLinea = linea.getFotos();
                List<MultipartFile> fotos = fotosPorLinea.get(id);

                // Solo procesar las fotos si la lista no está vacía
                if (fotos != null && !fotos.isEmpty()) {
                    List<Foto> fotosAgregar = fotoServicio.guardar(fotos);

                    // Verificar que fotosAgregar no sea null o esté vacío antes de intentar agregarlo
                    if (fotosAgregar != null && !fotosAgregar.isEmpty()) {
                        for (Foto foto : fotosAgregar) {
                            fotosDeLinea.add(foto);
                        }
                        linea.setFotos(fotosDeLinea);
                        lineaRepositorio.save(linea);
                    }
                }
            } else {
                throw new ErrorServicio("Hubo un error, Inténtelo nuevamente.");
            }
        }
    }

}
