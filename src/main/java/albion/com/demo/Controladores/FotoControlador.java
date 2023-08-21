package albion.com.demo.Controladores;

import albion.com.demo.Entidades.Foto;
import albion.com.demo.Errores.ErrorServicio;
import albion.com.demo.Servicios.FotoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foto")
public class FotoControlador {

    @Autowired
    private FotoServicio fotoServicio;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> fotoUsuario(@PathVariable String id) throws ErrorServicio {
        Foto foto = fotoServicio.buscarPorId(id);
        if (foto == null) {
            return null;
        } else {
            byte[] foto2 = foto.getContenido();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto2, headers, HttpStatus.OK);
        }
    }
}
