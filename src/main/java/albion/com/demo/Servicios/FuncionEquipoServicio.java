package albion.com.demo.Servicios;

import albion.com.demo.Entidades.FuncionEquipo;
import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Repositorios.FuncionEquipoRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public class FuncionEquipoServicio {
    
    @Autowired
    private FuncionEquipoRepositorio funcionEquipoRepositorio;
    
    @Autowired
    private FotoServicio fotoServicio;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void crear(String funcion_es, String funcion_en, String funcion_fr, String funcion_br, MultipartFile foto) throws ErrorServicio {
        FuncionEquipo funcionEquipo = new FuncionEquipo();
        funcionEquipo.setFuncion_es(funcion_es);
        funcionEquipo.setFuncion_en(funcion_en);
        funcionEquipo.setFuncion_fr(funcion_fr);
        funcionEquipo.setFuncion_br(funcion_br);
        funcionEquipo.setFoto(fotoServicio.guardar(foto));
        funcionEquipo.setAlta(true);
        Integer posicion = 1;
        if (funcionEquipoRepositorio.ultimaPosicion() != null) {
            posicion = funcionEquipoRepositorio.ultimaPosicion()+1;
        }
        funcionEquipo.setPosicion(posicion);
        funcionEquipoRepositorio.save(funcionEquipo);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void editar(FuncionEquipo funcionEquipo) throws ErrorServicio {
        Optional<FuncionEquipo> opt = funcionEquipoRepositorio.findById(funcionEquipo.getId());
        if (opt.isPresent()) {
            FuncionEquipo newFuncionEquipo = opt.get();
            newFuncionEquipo.setFuncion_es(funcionEquipo.getFuncion_es());
            newFuncionEquipo.setFuncion_en(funcionEquipo.getFuncion_en());
            newFuncionEquipo.setFuncion_fr(funcionEquipo.getFuncion_fr());
            newFuncionEquipo.setFuncion_br(funcionEquipo.getFuncion_br());
            funcionEquipoRepositorio.save(newFuncionEquipo);
        } else {
            throw new ErrorServicio("No se encontró la Funcion del Equipo.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void alta(String id) throws ErrorServicio {
        Optional<FuncionEquipo> opt = funcionEquipoRepositorio.findById(id);
        if (opt.isPresent()) {
            FuncionEquipo funcionEquipo = opt.get();
            funcionEquipo.setAlta(true);
            funcionEquipoRepositorio.save(funcionEquipo);
        } else {
            throw new ErrorServicio("No se encontró la funcionEquipo.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void baja(String id) throws ErrorServicio {
        Optional<FuncionEquipo> opt = funcionEquipoRepositorio.findById(id);
        if (opt.isPresent()) {
            FuncionEquipo funcionEquipo = opt.get();
            funcionEquipo.setAlta(false);
            funcionEquipoRepositorio.save(funcionEquipo);
        } else {
            throw new ErrorServicio("No se encontró la funcionEquipo.");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void eliminar(String id) throws ErrorServicio {
        Optional<FuncionEquipo> opt = funcionEquipoRepositorio.findById(id);
        if (opt.isPresent()) {
            FuncionEquipo funcionEquipo = opt.get();
            funcionEquipoRepositorio.delete(funcionEquipo);
        } else {
            throw new ErrorServicio("No se encontró la funcionEquipo.");
        }
    }

    @Transactional(readOnly = true)
    public List<FuncionEquipo> todos() throws ErrorServicio {
        List<FuncionEquipo> funcionEquipo = funcionEquipoRepositorio.findAll();
        if (!funcionEquipo.isEmpty()) {
            return funcionEquipo;
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public FuncionEquipo buscar(String id) throws ErrorServicio {
        Optional<FuncionEquipo> funcionEquipo = funcionEquipoRepositorio.findById(id);
        if (funcionEquipo.isPresent()) {
            return funcionEquipo.get();
        } else {
            throw new ErrorServicio("No se encontró la funcionEquipo.");
        }
    }
    
    @Transactional(readOnly = true)
    public FuncionEquipo buscarPorPosicion(int posicion) throws ErrorServicio {
        FuncionEquipo funcionEquipo = funcionEquipoRepositorio.posicion(posicion);
        return funcionEquipo;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void ordenarFuncionEquipo(List<String> ids, List<Integer> posiciones) throws ErrorServicio {
        for (int i = 0; i < ids.size(); i++) {
            Optional<FuncionEquipo> opt = funcionEquipoRepositorio.findById(ids.get(i));
            if (opt.isPresent()) {
                FuncionEquipo funcionEquipo = opt.get();
                funcionEquipo.setPosicion(posiciones.get(i));
                funcionEquipoRepositorio.save(funcionEquipo);
            } else {
                throw new ErrorServicio("No se encontró la funcionEquipo.");
            }
        }
    }
    
}
