package albion.com.demo.Servicios;

import albion.com.demo.Entidades.Equipo;
import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Repositorios.EquipoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EquipoServicio {

    @Autowired
    EquipoRepositorio equipoRepositorio;

    @Autowired
    private FotoServicio fotoServicio;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void crear(
            String modelo, String denominacion, String produccion,
            String titulo_es, String titulo_en, String titulo_fr, String titulo_br,
            String descripcion_es, String descripcion_en, String descripcion_fr, String descripcion_br
            ) throws ErrorServicio {
        Integer posicion = 1;
        if (equipoRepositorio.ultimaPosicion() != null) {
            posicion = equipoRepositorio.ultimaPosicion() + 1;
        }
        Equipo equipo = new Equipo();
        equipo.setModelo(modelo);
        equipo.setDenominacion(denominacion);
        equipo.setProduccion(produccion);
        equipo.setTitulo_es(titulo_es);
        equipo.setTitulo_en(titulo_en);
        equipo.setTitulo_fr(titulo_fr);
        equipo.setTitulo_br(titulo_br);
        equipo.setDescripcion_es(descripcion_es);
        equipo.setDescripcion_en(descripcion_en);
        equipo.setDescripcion_fr(descripcion_fr);
        equipo.setDescripcion_br(descripcion_br);
        equipo.setPosicion(posicion);
        equipoRepositorio.save(equipo);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void editar(Equipo equipo) throws ErrorServicio {
        Optional<Equipo> opt = equipoRepositorio.findById(equipo.getId());
        if (opt.isPresent()) {
            Equipo newequipo = opt.get();
            newequipo.setModelo(equipo.getModelo());
            newequipo.setDenominacion(equipo.getDenominacion());
            newequipo.setProduccion(equipo.getProduccion());
            newequipo.setTitulo_es(equipo.getTitulo_es());
            newequipo.setTitulo_en(equipo.getTitulo_en());
            newequipo.setTitulo_fr(equipo.getTitulo_fr());
            newequipo.setTitulo_br(equipo.getTitulo_br());
            newequipo.setDescripcion_es(equipo.getDescripcion_es());
            newequipo.setDescripcion_en(equipo.getDescripcion_en());
            newequipo.setDescripcion_fr(equipo.getDescripcion_fr());
            newequipo.setDescripcion_br(equipo.getDescripcion_br());
            equipoRepositorio.save(equipo);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void alta(String id) throws ErrorServicio {
        Optional<Equipo> opt = equipoRepositorio.findById(id);
        if (opt.isPresent()) {
            Equipo equipo = opt.get();
            equipo.setAlta(true);
            equipoRepositorio.save(equipo);
        } else {
            throw new ErrorServicio("No se encontró el equipo.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void baja(String id) throws ErrorServicio {
        Optional<Equipo> opt = equipoRepositorio.findById(id);
        if (opt.isPresent()) {
            Equipo equipo = opt.get();
            equipo.setAlta(false);
            equipoRepositorio.save(equipo);
        } else {
            throw new ErrorServicio("No se encontró el equipo.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void eliminar(String id) throws ErrorServicio {
        Optional<Equipo> optional = equipoRepositorio.findById(id);
        if (optional.isPresent()) {
            equipoRepositorio.delete(optional.get());
        } else {
            throw new ErrorServicio("No se encontró el equipo.");
        }
    }

    @Transactional(readOnly = true)
    public List<Equipo> todos() throws ErrorServicio {
        List<Equipo> equipo = equipoRepositorio.findAll();
        if (!equipo.isEmpty()) {
            return equipo;
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Equipo buscar(String id) throws ErrorServicio {
        Optional<Equipo> equipo = equipoRepositorio.findById(id);
        if (equipo.isPresent()) {
            return equipo.get();
        } else {
            throw new ErrorServicio("No se encontró el equipo.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void ordenarLineas(List<String> ids, List<Integer> posiciones) throws ErrorServicio {
        for (int i = 0; i < ids.size(); i++) {
            Optional<Equipo> opt = equipoRepositorio.findById(ids.get(i));
            if (opt.isPresent()) {
                Equipo equipo = opt.get();
                equipo.setPosicion(posiciones.get(i));
                equipoRepositorio.save(equipo);
            } else {
                throw new ErrorServicio("No se encontró el equipo.");
            }
        }
    }
    
    
    
}
